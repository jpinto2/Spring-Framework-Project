package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.GeneralException;

@Service
public class AccountService {

    @Autowired
    public AccountRepository accountRepository;

    public Account register(Account account) {
        // decided to move error check to media controller
        return accountRepository.save(account);
    }

    public Account login(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    public Boolean exists(Account account){
        Account duplicate = accountRepository.findByUsername(account.getUsername());
        if(duplicate != null) return true;
        else return false;
    }

    public Boolean exists(int id){
        Account account = accountRepository.findById(id);
        if(account != null) return true;
        else return false;
    }


}
