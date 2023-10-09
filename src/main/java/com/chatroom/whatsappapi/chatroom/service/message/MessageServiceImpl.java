package com.chatroom.whatsappapi.chatroom.service.message;

import com.chatroom.whatsappapi.chatroom.exception.BadRequestAlertException;
import com.chatroom.whatsappapi.chatroom.service.dto.MessageDTO;
import com.chatroom.whatsappapi.chatroom.entity.Message;
import com.chatroom.whatsappapi.chatroom.repository.MessageRepository;
import com.chatroom.whatsappapi.chatroom.service.dto.StatusDTO;
import com.chatroom.whatsappapi.chatroom.service.util.FileDownloadUtil;
import com.chatroom.whatsappapi.chatroom.service.util.FileUploadUtil;
import jakarta.servlet.MultipartConfigElement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService{

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);


    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    private final MultipartConfigElement imageMultipartConfig;

    private final MultipartConfigElement documentMultipartConfig;

    private final MultipartConfigElement videoMultipartConfig;


    @Override
    public Page<MessageDTO> getContactMessage(Pageable pageable, Long contactId) {

        log.debug("Request to get all Messages");

        Pageable sortedByCreatedDate= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("createdDate").descending());

        Page<Message> messages = messageRepository.findMessagesByReceiverOrSender(sortedByCreatedDate, contactId, contactId);

        return  messages.map(message -> modelMapper.map(message, MessageDTO.class));
    }

    @Override
    public MessageDTO sentTextMessage(MessageDTO messageDTO) {

        Message message = messageRepository.save(modelMapper.map(messageDTO, Message.class));

        return modelMapper.map(message, MessageDTO.class);
    }

    @Override
    public MessageDTO sentMultipartFileMessage(MultipartFile multipartFile, MessageDTO messageDTO) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String filePath = null;
        long minSize = 0l;
        if(messageDTO.getMessageType().equals("IMAGE")){
            filePath = imageMultipartConfig.getLocation();

        }
        else if(messageDTO.getMessageType().equals("VIDEO")){

            minSize = videoMultipartConfig.getFileSizeThreshold();

             if(minSize> multipartFile.getSize()){

                 throw new BadRequestAlertException("This Video size less tha minimum value");
             }

            filePath = videoMultipartConfig.getLocation();

        }else{

            filePath = documentMultipartConfig.getLocation();
        }

        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile, filePath);

        messageDTO.setContent(fileCode);

        Message message = messageRepository.save(modelMapper.map(messageDTO, Message.class));

        return modelMapper.map(message, MessageDTO.class);
    }

    @Override
    public MessageDTO  changeMessageStatus(StatusDTO statusDTO){

        Message message = new Message();
        message.setId(statusDTO.getMessageId());
        message.setStatus(statusDTO.getMessageStatusEnum().name());
        message=messageRepository.save(message);
        return  modelMapper.map(message, MessageDTO.class);
    }

    @Override
    public Resource downloadFile(Long messageId) throws IOException {

        String filePath = null;

        FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();

        Optional<Message> message = messageRepository.findById(messageId);

        if(message.isEmpty()){
            throw new BadRequestAlertException("File not exisist");
        }
        if(message.get().getMessageType().equals("IMAGE")){
            filePath = imageMultipartConfig.getLocation();
        }

        else if(message.get().getMessageType().equals("VIDEO")){

            filePath = videoMultipartConfig.getLocation();
        }else {
            filePath = documentMultipartConfig.getLocation();
        }

        return  fileDownloadUtil.getFileAsResource(message.get().getContent(), filePath);
    }
}
