package com.chatroom.whatsappapi.chatroom.service.contact;

import com.chatroom.whatsappapi.chatroom.entity.Contact;
import com.chatroom.whatsappapi.chatroom.repository.ContactRepository;
import com.chatroom.whatsappapi.chatroom.service.dto.ContactDTO;
import com.chatroom.whatsappapi.chatroom.service.dto.MessageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements Contactservice{

    private final ContactRepository contactRepository;

    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {

        Contact contact = contactRepository.save(modelMapper.map(contactDTO, Contact.class));

        return modelMapper.map(contact, ContactDTO.class);

    }

    @Override
    public List<ContactDTO> getAllContact() {

        List<Contact> contactList = contactRepository.findAll();

        return contactList.stream().map( contact->modelMapper.map(contact, ContactDTO.class)).toList();
    }

    @Override
    public List<ContactDTO> searchContact(String nameOrPhone) {

        List<Contact> contactList = contactRepository.searchContactByNameOrMobileNumber(nameOrPhone, nameOrPhone);

        return contactList.stream().map(contact->modelMapper.map(contact, ContactDTO.class)).toList();
    }
}
