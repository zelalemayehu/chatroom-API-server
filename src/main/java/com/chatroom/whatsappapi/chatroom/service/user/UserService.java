package com.chatroom.whatsappapi.chatroom.service.user;

import com.chatroom.whatsappapi.chatroom.entity.User;
import com.chatroom.whatsappapi.chatroom.service.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
}

