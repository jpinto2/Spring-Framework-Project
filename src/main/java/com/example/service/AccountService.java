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
        Account duplicate = accountRepository.findByUsername(account.getUsername());
        if(duplicate == null && account.getUsername() != null && account.getPassword().length() >= 4) return accountRepository.save(account);
        else throw new GeneralException("Invalid output");
    
    }

    public Account login(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}
