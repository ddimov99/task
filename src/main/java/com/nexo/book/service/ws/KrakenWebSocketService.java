package com.nexo.book.service.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexo.book.data.subscribe.SubscribeRequest;
import com.nexo.book.data.subscribe.Subscription;
import com.nexo.book.service.ws.message.ProcessMessageFactory;
import com.nexo.book.service.ws.message.book.BookMessageService;
import com.nexo.book.service.ws.message.book.OrderBookService;
import com.nexo.book.service.ws.message.book.PairBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class KrakenWebSocketService {

    public void subscribeToOrderBook() {

        WebSocketHandler client = new WebSocketHandler(new ProcessMessageFactory(List.of(new BookMessageService(new OrderBookService(new PairBookService())))));
        client.connect(connect(client));

        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setEvent("subscribe");
        subscribeRequest.setPair(List.of("BTC/USD", "ETH/USD"));
        subscribeRequest.setSubscription(new Subscription("book"));
        try {
            String json = new ObjectMapper().writeValueAsString(subscribeRequest);
            client.sendMessage(json);
        } catch (JsonProcessingException e) {
            log.error("Error while trying to subscribe", e);
            throw new RuntimeException(e);
        }
    }

    private WebSocketSession connect(WebSocketHandler client) {
        try {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            return webSocketClient.doHandshake(client, new WebSocketHttpHeaders(), URI.create("ws://beta-ws.kraken.com")).get();
        } catch (Exception e) {
            log.error("Exception while creating websockets", e);
            throw new RuntimeException(e);
        }
    }
}
