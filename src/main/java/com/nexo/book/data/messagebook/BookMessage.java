package com.nexo.book.data.messagebook;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BookMessage {
    private List<List<BigDecimal>> asks;
    private List<List<BigDecimal>> bids;
    private String currencies;
}
