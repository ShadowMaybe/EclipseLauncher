package me.shadow.eclipse.core.accounts

import kotlinx.coroutines.flow.Flow

enum class AccountType {
    MICROSOFT,
    LOCAL,
}

data class Account(
    val id: String,
    val displayName: String,
    val type: AccountType,
)

enum class AccountNameError {
    BLANK,
    TOO_LONG,
}

sealed interface AccountMutationResult {
    data object Added : AccountMutationResult
    data object Selected : AccountMutationResult
    data object Removed : AccountMutationResult
    data object DuplicateName : AccountMutationResult
    data object NotFound : AccountMutationResult
    data class InvalidName(val error: AccountNameError) : AccountMutationResult
    data class Failure(val cause: Throwable) : AccountMutationResult
}

interface AccountRepository {
    val accounts: Flow<List<Account>>
    val selectedAccountId: Flow<String?>

    suspend fun addLocalAccount(displayName: String): AccountMutationResult
    suspend fun selectAccount(accountId: String): AccountMutationResult
    suspend fun removeAccount(accountId: String): AccountMutationResult
}
