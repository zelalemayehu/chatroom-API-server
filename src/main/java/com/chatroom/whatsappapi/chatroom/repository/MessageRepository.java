package com.chatroom.whatsappapi.chatroom.repository;

import com.chatroom.whatsappapi.chatroom.entity.Message;
import com.chatroom.whatsappapi.chatroom.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findMessagesByReceiverOrSender(Pageable pageable, Long receiver, long Sender);


}
