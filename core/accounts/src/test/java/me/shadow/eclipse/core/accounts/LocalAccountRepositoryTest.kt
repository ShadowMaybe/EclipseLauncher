package me.shadow.eclipse.core.accounts

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import java.io.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalAccountRepositoryTest {
    @Test
    fun localAccountsCanBeCreatedSelectedAndRemoved() = runBlocking {
        val file = File.createTempFile("eclipse-accounts-test", ".preferences_pb")
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val dataStore = PreferenceDataStoreFactory.create(
            scope = scope,
            produceFile = { file },
        )
        val repository = LocalAccountRepository(dataStore)

        try {
            assertEquals(AccountMutationResult.Added, repository.addLocalAccount("  Player  "))
            val first = repository.accounts.first().single()
            assertEquals("Player", first.displayName)
            assertEquals(AccountType.LOCAL, first.type)
            assertEquals(first.id, repository.selectedAccountId.first())

            assertEquals(
                AccountMutationResult.DuplicateName,
                repository.addLocalAccount("player"),
            )
            assertEquals(
                AccountMutationResult.Added,
                repository.addLocalAccount("Second"),
            )
            val accounts = repository.accounts.first()
            assertEquals(2, accounts.size)
            assertEquals("Second", accounts.last().displayName)

            assertEquals(AccountMutationResult.Selected, repository.selectAccount(first.id))
            assertEquals(first.id, repository.selectedAccountId.first())
            assertEquals(AccountMutationResult.Removed, repository.removeAccount(first.id))
            assertEquals(accounts.last().id, repository.selectedAccountId.first())
            assertEquals(AccountMutationResult.Removed, repository.removeAccount(accounts.last().id))
            assertTrue(repository.accounts.first().isEmpty())
            assertNull(repository.selectedAccountId.first())
        } finally {
            scope.cancel()
            file.delete()
        }
    }

    @Test
    fun invalidNamesAreRejectedBeforePersistence() = runBlocking {
        val file = File.createTempFile("eclipse-accounts-validation", ".preferences_pb")
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val dataStore = PreferenceDataStoreFactory.create(
            scope = scope,
            produceFile = { file },
        )
        val repository = LocalAccountRepository(dataStore)

        try {
            assertEquals(
                AccountMutationResult.InvalidName(AccountNameError.BLANK),
                repository.addLocalAccount("   "),
            )
            assertEquals(
                AccountMutationResult.InvalidName(AccountNameError.TOO_LONG),
                repository.addLocalAccount("x".repeat(65)),
            )
            assertTrue(repository.accounts.first().isEmpty())
        } finally {
            scope.cancel()
            file.delete()
        }
    }
}
