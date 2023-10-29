package com.nexo.book.service.ws.message;

import com.nexo.book.data.enums.MessageType;

public interface ProcessMessageService {
    MessageType getMessageType();

    void processMassage(String message);
}
