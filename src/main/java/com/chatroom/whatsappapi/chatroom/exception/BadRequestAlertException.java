package com.chatroom.whatsappapi.chatroom.exception;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * all responses rather than success treated as bad request
 * */
public class BadRequestAlertException extends RuntimeException {

    public BadRequestAlertException(String message){
        super(message);
    }

    public BadRequestAlertException(String message, Throwable causes){
        super(message, causes);
    }

}
