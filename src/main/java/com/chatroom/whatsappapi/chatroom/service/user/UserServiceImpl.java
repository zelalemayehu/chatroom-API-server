package com.chatroom.whatsappapi.chatroom.service.user;

import com.chatroom.whatsappapi.chatroom.entity.Contact;
import com.chatroom.whatsappapi.chatroom.entity.User;
import com.chatroom.whatsappapi.chatroom.repository.ContactRepository;
import com.chatroom.whatsappapi.chatroom.repository.UserRepository;
import com.chatroom.whatsappapi.chatroom.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private  final ContactRepository contactRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ContactRepository contactRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        Contact contact = new Contact();

        contact.setMobileNumber(userDTO.getMobile());
        contact.setName(userDTO.getName());
        contact = contactRepository.save(contact);

        List<Contact> contacts= new ArrayList<>();
        contacts.add(contact);
        user.setContacts(contacts);

        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
}
