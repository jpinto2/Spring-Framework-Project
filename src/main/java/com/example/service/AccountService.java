package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.GeneralException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        // decided to move error check to media controller
        return accountRepository.save(account);
    }

    public Account login(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}
