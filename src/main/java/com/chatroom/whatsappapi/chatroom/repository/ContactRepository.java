package com.chatroom.whatsappapi.chatroom.repository;

import com.chatroom.whatsappapi.chatroom.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> searchContactByNameOrMobileNumber(String name, String phone );

}
