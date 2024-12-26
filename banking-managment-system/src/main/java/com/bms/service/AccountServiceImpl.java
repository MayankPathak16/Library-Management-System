package com.bms.service;

import com.bms.entity.Account;
import com.bms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account CreateAccount(Account account) {
        //I am already passing Account here So need to save using repository save method
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account deposit(Long id, double amount) {
        //first need to check whether account exists or not, if yes then deposit the money
        Account account = getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
        //since we are depositing amount so need to set the balance
       account.setBalance(account.getBalance()+amount);
      return   accountRepository.save(account);

    }

    @Override
    public Account withdraw(Long id, double amount) {
        //first need to check whether account exists or not, if yes then deposit the money
        Account account = getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
        //Let us check if we have enough balance to withdraw the amount
        if(account.getBalance()>=amount){
    account.setBalance(account.getBalance()-amount);
            return accountRepository.save(account);
        }else{
            throw new RuntimeException("Insufficient balance");
        }
    }
}
