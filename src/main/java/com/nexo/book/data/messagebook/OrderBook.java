package com.nexo.book.data.messagebook;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class OrderBook {
    private Map<String, PairBook> orderBook;
}
