package com.nexo.book.data.messagebook;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class PairBook {
    private Set<Ask> asks;
    private Bid bestBid;
    private Ask bestAsk;
    private Set<Bid> bids;
    private LocalDateTime lastUpdateTime;
    private String currencies;


   public PairBook(String currencies) {
        asks = new TreeSet<>();
        bids = new TreeSet<>();
        this.currencies = currencies;
    }

    @Override
    public String toString() {

        return "\n<------------------------------------>" +
                "\nasks : " + asks +
                "\nbest bid: " + bestBid +
                "\nbest ask: " + bestAsk +
                "\nbids: " + bids +
                "\n " + lastUpdateTime +
                "\n " + currencies +
                "\n>------------------------------------<";
    }
}
