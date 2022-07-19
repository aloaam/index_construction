package models;

import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
public class IndexPrice {

    private final LocalDate date;
    private final String ticker;
    private final BigDecimal price;

    public IndexPrice(LocalDate date, String ticker, BigDecimal closePrice) {
        this.date = date;
        this.ticker = ticker;
        this.price = closePrice;
    }
}
