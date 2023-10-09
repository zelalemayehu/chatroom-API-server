package com.chatroom.whatsappapi.chatroom.controller;

import com.chatroom.whatsappapi.chatroom.service.contact.Contactservice;
import com.chatroom.whatsappapi.chatroom.service.dto.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final Contactservice contactservice;

    public ContactController(Contactservice contactservice) {

        this.contactservice = contactservice;
    }

    @PostMapping("/createContact")
    public ResponseEntity<ContactDTO> creteContact(@RequestBody ContactDTO contactDTO ){

        ContactDTO contact = contactservice.createContact(contactDTO);

        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @GetMapping("/allContact")
    public ResponseEntity<List<ContactDTO>> getAllContact(){

        return ResponseEntity.ok(contactservice.getAllContact());
    }

    @GetMapping("/search/{nameOrPhone}")
    public ResponseEntity<List<ContactDTO>> searchContact(@PathVariable("nameOrPhone") String nameOrPhone){

        return ResponseEntity.ok(contactservice.searchContact(nameOrPhone));

    }

}
