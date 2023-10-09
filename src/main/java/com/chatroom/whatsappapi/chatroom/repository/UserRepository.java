package com.chatroom.whatsappapi.chatroom.repository;

import com.chatroom.whatsappapi.chatroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
