package com.chatroom.whatsappapi.chatroom.service.contact;

import com.chatroom.whatsappapi.chatroom.service.dto.ContactDTO;

import java.util.List;

public interface Contactservice {

    ContactDTO createContact(ContactDTO contactDTO);

    List<ContactDTO> getAllContact();

    List<ContactDTO> searchContact(String nameOrPhone);

}