package com.example.controller;

// import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
// import com.example.entity.Message;
import com.example.service.AccountService;
// import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController 
public class SocialMediaController {

    @Autowired
    public AccountService accountService;
    // 
    // private MessageService messageService;

    @PostMapping("register")
     public ResponseEntity<Account> register(@RequestBody Account account) {
        if(accountService.exists(account) == false && account.getUsername() != "" && account.getPassword().length() >= 4) {
            Account reg = accountService.register(account);
            return new ResponseEntity<>(reg, HttpStatus.OK);         
        }
        else if (accountService.exists(account) == true) return ResponseEntity.status(409).body(null);
        else return ResponseEntity.status(400).body(null);
     }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account log = accountService.login(account);
        if(log != null) return new ResponseEntity<Account>(log, HttpStatus.OK);
        return ResponseEntity.status(401).body(null);
    }
}
