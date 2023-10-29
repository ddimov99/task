package com.nexo.book.service.ws;

import com.nexo.book.data.enums.MessageType;
import com.nexo.book.service.ws.message.ProcessMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private WebSocketSession clientSession;
    private final ProcessMessageFactory processMessageFactory;

    public void connect(WebSocketSession clientSession){
        this.clientSession = clientSession;
    }

    public void sendMessage(String msg) {
        try {
            this.clientSession.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            log.error("Failed to send message", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        String message = textMessage.getPayload();
        MessageType type = getMessageType(message);
        if(type == MessageType.MESSAGE_BOOK){    //currently do nothing with other types
           processMessageFactory.getService(type).processMassage(message);
        }
    }

    private MessageType getMessageType(String message){
        if (message.contains("systemStatus")) {
            return MessageType.SYSTEM_STATUS;
        } else if (message.contains("subscriptionStatus")) {
            return MessageType.SUBSCRIPTION_STATUS;
        } else if (message.contains("heartbeat")) {
            return MessageType.HEARTBEAT;
        } else if (message.contains("\"as\":") || message.contains("\"bs\":")) {
            return MessageType.BOOK_CHECKSUM;
        } else if (message.contains("\"a\":") || message.contains("\"b\":")) {
            return MessageType.MESSAGE_BOOK;
        } else {
            throw new RuntimeException("Unknown message type: " + message);
        }
    }
}
