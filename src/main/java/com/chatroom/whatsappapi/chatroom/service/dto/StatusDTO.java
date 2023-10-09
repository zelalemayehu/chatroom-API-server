package com.chatroom.whatsappapi.chatroom.service.dto;

import com.chatroom.whatsappapi.chatroom.service.enumeration.MessageStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDTO {
    Long messageId;
    MessageStatusEnum messageStatusEnum;
}
