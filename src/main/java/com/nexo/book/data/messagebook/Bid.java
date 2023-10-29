package com.nexo.book.data.messagebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Comparator;

@AllArgsConstructor
@Getter
@Setter
public class Bid implements Comparable<Bid>{
    private BigDecimal valOne;
    private BigDecimal valTwo;
    private BigDecimal valThree;

    @Override
    public int compareTo(Bid o) {
        return Comparator.comparing(Bid::getValOne, Comparator.reverseOrder())
                .thenComparing(Bid::getValTwo, Comparator.reverseOrder())
                .thenComparing(Bid::getValThree, Comparator.reverseOrder())
                .compare(this, o);
    }

    @Override
    public String toString() {
        return "\n[" + valOne +
                ", " + valTwo +
                ", " + valThree +
                "]\n";
    }
}
