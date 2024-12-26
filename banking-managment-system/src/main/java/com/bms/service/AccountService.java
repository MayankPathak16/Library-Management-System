package com.bms.service;

import com.bms.entity.Account;

import java.util.Optional;

public interface AccountService {
    //Creating an account
    public Account CreateAccount(Account account);
    //Getting an account by Id
    public Optional<Account> getAccount(Long id);
    //Depositing money into an account
    public Account deposit(Long id, double amount);
    //withdraw money into an account
    public Account withdraw(Long id, double amount);
}
