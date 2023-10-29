package com.nexo.book;

import com.nexo.book.service.ws.KrakenWebSocketService;
import com.nexo.book.service.ws.WebSocketHandler;
import com.nexo.book.service.ws.message.ProcessMessageFactory;
import com.nexo.book.service.ws.message.book.BookMessageService;
import com.nexo.book.service.ws.message.book.OrderBookService;
import com.nexo.book.service.ws.message.book.PairBookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NexoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexoApplication.class, args);

        new KrakenWebSocketService().subscribeToOrderBook();
    }
}
