package com.nexo.book.service.ws.message.book;

import com.nexo.book.data.messagebook.Ask;
import com.nexo.book.data.messagebook.Bid;
import com.nexo.book.data.messagebook.BookMessage;
import com.nexo.book.data.messagebook.PairBook;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PairBookService {

    public void update(PairBook pairBook, BookMessage message) {
        updateAsks(pairBook, message.getAsks());
        updateBids(pairBook, message.getBids());
        pairBook.setBestBid(pairBook.getBids().stream().findFirst().orElse(null));
        pairBook.setBestAsk(pairBook.getAsks().stream().findFirst().orElse(null));
        pairBook.setLastUpdateTime(LocalDateTime.now());
    }

    private void updateAsks(PairBook pairBook, List<List<BigDecimal>> a) {
        pairBook.getAsks().addAll(getAsks(a));
    }

    private void updateBids(PairBook pairBook, List<List<BigDecimal>> b) {
        pairBook.getBids().addAll(getBids(b));
    }

    private Set<Ask> getAsks(List<List<BigDecimal>> a) {
        if (a == null) {
            return Collections.emptySet();
        }

        Set<Ask> asks = new HashSet<>();

        for (List<BigDecimal> as : a) {
            asks.add(new Ask(new BigDecimal(String.valueOf(as.get(0))), new BigDecimal(String.valueOf(as.get(1))), new BigDecimal(String.valueOf(as.get(2)))));
        }

        return asks;
    }

    private Set<Bid> getBids(List<List<BigDecimal>> b) {
        if (b == null) {
            return Collections.emptySet();
        }

        Set<Bid> bids = new HashSet<>();

        for (List<BigDecimal> bid : b) {
            bids.add(new Bid(new BigDecimal(String.valueOf(bid.get(0))), new BigDecimal(String.valueOf(bid.get(1))), new BigDecimal(String.valueOf(bid.get(2)))));
        }

        return bids;
    }
}
