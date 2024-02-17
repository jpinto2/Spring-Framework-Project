package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    public MessageRepository messageRepository;

    public Message postMessage(Message message) {

            return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public Message getMessageById(int id) {
        Message message = messageRepository.findById(id);
        if (message != null) return message;
        else return null;
    }

    public List<Message> getMessageByUser(int id){
        List<Message> list = messageRepository.findAllByPostedBy(id);
        if(list != null) return list;
        else return null;
    }

    public Message deleteMessage(int id){
        Message message = messageRepository.findById(id);
        if(message != null) {
            messageRepository.deleteById(id);
            return message;
        }
        else return null;
    }

    public Message updateMessage(Message message, int id){
        Message first = messageRepository.findById(id);
        if(first != null && message.getMessage_text() != "" && message.getMessage_text().length() < 255){
            first.setMessage_text(message.getMessage_text());
            return messageRepository.save(first);
        }
        else return null;
    }
}
