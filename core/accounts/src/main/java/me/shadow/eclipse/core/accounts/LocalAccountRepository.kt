package me.shadow.eclipse.core.accounts

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.accountDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "eclipse_accounts",
)

class LocalAccountRepository internal constructor(
    private val dataStore: DataStore<Preferences>,
) : AccountRepository {
    override val accounts: Flow<List<Account>> = dataStore.data.map { preferences ->
        preferences[ACCOUNT_IDS]
            .orEmpty()
            .mapNotNull { id -> preferences.toAccount(id) }
            .sortedBy { it.displayName.lowercase() }
    }

    override val selectedAccountId: Flow<String?> = dataStore.data.map { preferences ->
        preferences[SELECTED_ACCOUNT_ID]
    }

    override suspend fun addLocalAccount(displayName: String): AccountMutationResult {
        val normalizedName = displayName.trim()
        val validationError = when {
            normalizedName.isEmpty() -> AccountNameError.BLANK
            normalizedName.length > MAX_DISPLAY_NAME_LENGTH -> AccountNameError.TOO_LONG
            else -> null
        }
        if (validationError != null) {
            return AccountMutationResult.InvalidName(validationError)
        }

        return try {
            var result: AccountMutationResult = AccountMutationResult.Added
            dataStore.edit { preferences ->
                val accountIds = preferences[ACCOUNT_IDS].orEmpty()
                val duplicate = accountIds.any { id ->
                    preferences[accountNameKey(id)].equals(normalizedName, ignoreCase = true)
                }
                if (duplicate) {
                    result = AccountMutationResult.DuplicateName
                } else {
                    val accountId = UUID.randomUUID().toString()
                    preferences[ACCOUNT_IDS] = accountIds + accountId
                    preferences[accountNameKey(accountId)] = normalizedName
                    preferences[accountTypeKey(accountId)] = AccountType.LOCAL.name
                    if (preferences[SELECTED_ACCOUNT_ID] == null) {
                        preferences[SELECTED_ACCOUNT_ID] = accountId
                    }
                }
            }
            result
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            AccountMutationResult.Failure(exception)
        }
    }

    override suspend fun selectAccount(accountId: String): AccountMutationResult = try {
        var result: AccountMutationResult = AccountMutationResult.NotFound
        dataStore.edit { preferences ->
            if (accountId in preferences[ACCOUNT_IDS].orEmpty()) {
                preferences[SELECTED_ACCOUNT_ID] = accountId
                result = AccountMutationResult.Selected
            }
        }
        result
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        AccountMutationResult.Failure(exception)
    }

    override suspend fun removeAccount(accountId: String): AccountMutationResult = try {
        var result: AccountMutationResult = AccountMutationResult.NotFound
        dataStore.edit { preferences ->
            val accountIds = preferences[ACCOUNT_IDS].orEmpty()
            if (accountId in accountIds) {
                val remainingIds = accountIds - accountId
                preferences[ACCOUNT_IDS] = remainingIds
                preferences.remove(accountNameKey(accountId))
                preferences.remove(accountTypeKey(accountId))
                if (preferences[SELECTED_ACCOUNT_ID] == accountId) {
                    if (remainingIds.isEmpty()) {
                        preferences.remove(SELECTED_ACCOUNT_ID)
                    } else {
                        preferences[SELECTED_ACCOUNT_ID] = remainingIds.first()
                    }
                }
                result = AccountMutationResult.Removed
            }
        }
        result
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        AccountMutationResult.Failure(exception)
    }

    private fun Preferences.toAccount(id: String): Account? {
        val displayName = this[accountNameKey(id)] ?: return null
        val type = this[accountTypeKey(id)]
            ?.let { storedType -> runCatching { AccountType.valueOf(storedType) }.getOrNull() }
            ?: AccountType.LOCAL
        return Account(
            id = id,
            displayName = displayName,
            type = type,
        )
    }

    private fun accountNameKey(accountId: String) = stringPreferencesKey("account_name_$accountId")

    private fun accountTypeKey(accountId: String) = stringPreferencesKey("account_type_$accountId")

    companion object {
        fun create(context: Context): AccountRepository = LocalAccountRepository(
            dataStore = context.applicationContext.accountDataStore,
        )

        private const val MAX_DISPLAY_NAME_LENGTH = 64
        private val ACCOUNT_IDS = stringSetPreferencesKey("account_ids")
        private val SELECTED_ACCOUNT_ID = stringPreferencesKey("selected_account_id")
    }
}
