package me.shadow.eclipse.feature.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.shadow.eclipse.core.accounts.Account
import me.shadow.eclipse.core.accounts.AccountMutationResult
import me.shadow.eclipse.core.accounts.AccountNameError
import me.shadow.eclipse.core.accounts.AccountRepository

data class AccountsUiState(
    val accounts: List<Account> = emptyList(),
    val selectedAccountId: String? = null,
    val isSaving: Boolean = false,
)

enum class AccountEvent {
    ADDED,
    DUPLICATE_NAME,
    INVALID_BLANK_NAME,
    INVALID_LONG_NAME,
    SELECTED,
    REMOVED,
    NOT_FOUND,
    FAILED,
}

class AccountsViewModel(
    private val repository: AccountRepository,
) : ViewModel() {
    private val saving = MutableStateFlow(false)
    private val eventFlow = MutableSharedFlow<AccountEvent>(extraBufferCapacity = 1)
    val events = eventFlow.asSharedFlow()

    val uiState: StateFlow<AccountsUiState> = combine(
        repository.accounts,
        repository.selectedAccountId,
        saving,
    ) { accounts, selectedAccountId, isSaving ->
        AccountsUiState(
            accounts = accounts,
            selectedAccountId = selectedAccountId,
            isSaving = isSaving,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AccountsUiState(),
    )

    fun addLocalAccount(displayName: String) {
        viewModelScope.launch {
            saving.value = true
            val event = when (val result = repository.addLocalAccount(displayName)) {
                AccountMutationResult.Added -> AccountEvent.ADDED
                AccountMutationResult.DuplicateName -> AccountEvent.DUPLICATE_NAME
                is AccountMutationResult.InvalidName -> when (result.error) {
                    AccountNameError.BLANK -> AccountEvent.INVALID_BLANK_NAME
                    AccountNameError.TOO_LONG -> AccountEvent.INVALID_LONG_NAME
                }
                AccountMutationResult.NotFound,
                AccountMutationResult.Selected,
                AccountMutationResult.Removed,
                is AccountMutationResult.Failure -> AccountEvent.FAILED
            }
            saving.value = false
            eventFlow.emit(event)
        }
    }

    fun selectAccount(accountId: String) {
        viewModelScope.launch {
            val event = when (repository.selectAccount(accountId)) {
                AccountMutationResult.Selected -> AccountEvent.SELECTED
                AccountMutationResult.NotFound -> AccountEvent.NOT_FOUND
                else -> AccountEvent.FAILED
            }
            eventFlow.emit(event)
        }
    }

    fun removeAccount(accountId: String) {
        viewModelScope.launch {
            val event = when (repository.removeAccount(accountId)) {
                AccountMutationResult.Removed -> AccountEvent.REMOVED
                AccountMutationResult.NotFound -> AccountEvent.NOT_FOUND
                else -> AccountEvent.FAILED
            }
            eventFlow.emit(event)
        }
    }
}

class AccountsViewModelFactory(
    private val repository: AccountRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(AccountsViewModel::class.java)) {
            "Unsupported ViewModel: ${modelClass.name}"
        }
        return AccountsViewModel(repository) as T
    }
}
