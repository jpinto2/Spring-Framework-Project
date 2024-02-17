package com.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;


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
    @Autowired
    public MessageService messageService;

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

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){

        if(accountService.exists(message.getPosted_by()) && message.getMessage_text() != "" && message.getMessage_text().length() < 255) {
                Message m2 = messageService.postMessage(message);
                return new ResponseEntity<>(m2, HttpStatus.OK);
            }
        else return ResponseEntity.status(400).body(null);
        
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> list = messageService.getAllMessages();
        if (list != null) return ResponseEntity.status(200).body(list);
        else return null;
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") int id){
        Message message = messageService.getMessageById(id);
        if(message != null) return ResponseEntity.status(200).body(null);
        else return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessageByUser(@PathVariable("account_id") int id) {
        List<Message> list = messageService.getMessageByUser(id);

        if (list != null) return ResponseEntity.status(200).body(list);
        else return ResponseEntity.status(200).body(null);
      
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable("message_id") int id) {
        Message message = messageService.deleteMessage(id);
        if(message != null) return ResponseEntity.status(200).body(1);
        else return ResponseEntity.status(200).body(null);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@RequestBody Message message, @PathVariable("message_id") int id){
        Message update = messageService.updateMessage(message, id);

        if(update != null) return ResponseEntity.status(200).body(1);
        else return ResponseEntity.status(400).body(null);
    }
}
