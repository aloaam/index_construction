package models;

import lombok.ToString;

import java.time.LocalDate;
import java.util.Map;

/*
   The variable yearMonth contains just the first day of a business month,
   but it is used only to keep track of the year and the month of these indexes.
 */
@ToString
public class IndexStockMembers {



    private final LocalDate yearMonth;
    private final Map<String, Double> stockMembersWeight;

    public IndexStockMembers(LocalDate yearMonth, Map<String, Double> stockMembersWeight) {
        this.yearMonth = yearMonth;
        this.stockMembersWeight = stockMembersWeight;
    }
}
