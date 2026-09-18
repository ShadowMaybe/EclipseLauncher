package me.shadow.eclipselauncher.authenticator.listener;

import me.shadow.eclipselauncher.authenticator.accounts.Account;

public interface LoginListener{
    void onLoginDone(Account account);
    void onLoginError(Throwable errorMessage);
    void onLoginProgress(int step);
    void setMaxLoginProgress(int max);
}
