package com.chatroom.whatsappapi.chatroom.service.message;

import com.chatroom.whatsappapi.chatroom.service.dto.MessageDTO;
import com.chatroom.whatsappapi.chatroom.entity.Message;
import com.chatroom.whatsappapi.chatroom.service.dto.StatusDTO;
import com.chatroom.whatsappapi.chatroom.service.enumeration.MessageStatusEnum;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MessageService {

    Page<MessageDTO> getContactMessage(Pageable pageable, Long contactId);

    MessageDTO sentTextMessage(MessageDTO messageDTO);

    MessageDTO sentMultipartFileMessage(MultipartFile file, MessageDTO messageDTO) throws IOException;

    MessageDTO  changeMessageStatus(StatusDTO statusDTO);

    Resource downloadFile(Long messageId) throws IOException;
}
