package com.revature.frittte.message;

import com.revature.frittte.chat.Chat;
import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.exception.InvalidRequestException;
import com.revature.frittte.exception.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService {
    private MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao){
        this.messageDao = messageDao;
    }

    public List<Message> findAll(){
        List<Message> messages = (List <Message>) messageDao.findAll();
        return messages;
    }
    public  boolean deleteById(int id){
        messageDao.deleteById(id);
        return true;
    }
    public Message readById(int id){
        Message message = messageDao.findById(id).get();
        return message;
    }

    public Message update(Message updatedMessage) {
        messageDao.save(updatedMessage);
        return updatedMessage;
    }
    public boolean validateIdNotUsed(int id){
        return messageDao.existsById(id);
    }
    public Message create(Message newMessage){
        if(!validateInput(newMessage)){ // checking if false
            throw new InvalidRequestException("New Message was not validated, either empty String or null values");
        }
        Message persistedMessage = messageDao.save(newMessage);

        if(persistedMessage == null){

            throw new ResourcePersistanceException("Message was not persisted to the database upon registration");

        }
        return persistedMessage;
    }

    public boolean validateInput(Message newMessage) {
        if(newMessage == null) return false;
        if(newMessage.getId()<0) return false;
        if(newMessage.getSender()== null || newMessage.getSender().equals("")) return false;
        if(newMessage.getText()== null || newMessage.getText().trim().equals("")) return false;
        return(newMessage.getCreated_date()!= null || !newMessage.getCreated_date().trim().equals("")) ;

    }

    public Message authenticateMessage(int id, String sender){

        if(id < 0 || sender == null || sender.trim().equals("")) {
            throw new InvalidRequestException("Either id or sender is an invalid entry. Please try logging in again");
        }

        Message authenticatedChat = messageDao.authenticateMessage(id, sender);

        if (authenticatedChat == null){
            throw new AuthenticationException("Unauthenticated message, information provided was not consistent with our database.");
        }

        return authenticatedChat;
    }
}
