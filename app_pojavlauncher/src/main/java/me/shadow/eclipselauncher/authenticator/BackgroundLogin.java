package me.shadow.eclipselauncher.authenticator;

import androidx.annotation.NonNull;

import me.shadow.eclipselauncher.authenticator.listener.LoginListener;
import me.shadow.eclipselauncher.authenticator.accounts.Account;

public interface BackgroundLogin {
    void createAccount(@NonNull LoginListener loginListener, String code);
    void refreshAccount(@NonNull LoginListener loginListener, Account account);
    interface Creator {
        BackgroundLogin create();
    }
}
