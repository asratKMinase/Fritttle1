package com.revature.frittte.message;

import com.revature.frittte.chat.Chat;
import com.revature.frittte.chat.ChatInitializer;
import com.revature.frittte.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
public class MessageServlet {
    private final MessageService messageServices;

    @Autowired
    public MessageServlet(MessageService messageServices) {
        this.messageServices = messageServices;
    }

    @GetMapping("/findAllMessage")
    public ResponseEntity<List> findAllMessage() {
        return new ResponseEntity<>(messageServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findMessage")
    public ResponseEntity<Message> findMessage(@RequestBody int id) {
        return new ResponseEntity<>(messageServices.readById(id), HttpStatus.FOUND);
    }

    @PutMapping("/updateMessage")
    public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
        Message newMessage = messageServices.update(message);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMessage")
    public void deleteMessage(@RequestBody int id) {
        boolean newMessage = messageServices.deleteById(id);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Message> CreateChat(@RequestBody MessageInitializer initMessage, HttpSession req) {

        Message newMessage = new Message();
        System.out.println(newMessage + "1");

        Customer authCustomer = (Customer) req.getAttribute("authCustomer");
        //CCInitializer initCC = mapper.readValue(req.getInputStream(), CCInitializer.class); // from JSON to Java Object (Pokemon)
        //Message id = messageService.readById(initChat.getId());
        newMessage.setId(initMessage.getId());
        System.out.println(newMessage + "2");
        newMessage.setSender(authCustomer);
        newMessage.setText(initMessage.getText());
        System.out.println(newMessage + "12");
        newMessage.setCreated_date(initMessage.getCreated_date());
        System.out.println(newMessage + "123");

        Message persistedMessage = messageServices.create(newMessage);
        System.out.println(newMessage + "1234");

        return new ResponseEntity<>(persistedMessage, HttpStatus.CREATED);
    }
}

