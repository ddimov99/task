package com.nexo.book.service.ws.message.book;

import com.nexo.book.data.messagebook.BookMessage;
import com.nexo.book.data.messagebook.OrderBook;
import com.nexo.book.data.messagebook.PairBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OrderBookService {

    private final OrderBook orderBook = new OrderBook(new HashMap<>());
    private final PairBookService pairBookService;

    public void updateOrderBook(BookMessage message) {
        String currencies = message.getCurrencies();
        PairBook pairBook = orderBook.getOrderBook().get(currencies);

        if (pairBook != null) {
            pairBookService.update(pairBook, message);
        } else {
            PairBook book = new PairBook(currencies);
            pairBookService.update(book, message);
            orderBook.getOrderBook().put(currencies, book);
        }
    }

    public void printOrderBook() {
        orderBook.getOrderBook().forEach((key, value) -> System.out.println(value.toString()));
    }
}
