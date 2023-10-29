package com.nexo.book.service.ws.message.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexo.book.data.enums.MessageType;
import com.nexo.book.data.messagebook.BookMessage;
import com.nexo.book.service.ws.message.ProcessMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
public class BookMessageService implements ProcessMessageService {

  private final ObjectMapper mapper = new ObjectMapper();
  private final OrderBookService orderBookService;

    @Override
    public MessageType getMessageType() {
        return MessageType.MESSAGE_BOOK;
    }

    @Override
    public void processMassage(String message) {
        try {
            ArrayList<Object> objects = mapper.readValue(message, new TypeReference<>() {});

            BookMessage bookMessage = new BookMessage();
            Map<String, Object> results = (Map<String, Object>) objects.get(1);
            if (results.containsKey("a")) {
                bookMessage.setAsks((List) results.get("a"));
            }
            if (results.containsKey("b")) {
                bookMessage.setBids((List) results.get("b"));
            }
            bookMessage.setCurrencies((String) objects.get(3));

            orderBookService.updateOrderBook(bookMessage);
            orderBookService.printOrderBook();
        }catch (JsonProcessingException e){
            log.error("Error while processing message", e);
            throw new RuntimeException(e);
        }
    }
}
