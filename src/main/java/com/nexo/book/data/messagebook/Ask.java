package com.nexo.book.data.messagebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Comparator;

@AllArgsConstructor
@Getter
@Setter
public class Ask implements Comparable<Ask>{
    private BigDecimal valOne;
    private BigDecimal valTwo;
    private BigDecimal valThree;

    @Override
    public int compareTo(Ask o) {
        return Comparator.comparing(Ask::getValOne, Comparator.reverseOrder())
                .thenComparing(Ask::getValTwo, Comparator.reverseOrder())
                .thenComparing(Ask::getValThree, Comparator.reverseOrder())
                .compare(this, o);
    }

    @Override
    public String toString() {
        return "\n[" + valOne +
                ", " + valTwo +
                ", " + valThree +
                ']';
    }
}
