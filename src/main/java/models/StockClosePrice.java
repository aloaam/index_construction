package models;

import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


@ToString
public class StockClosePrice {

    private final LocalDate date;
    private final String ticker;
    private final BigDecimal price;

    public StockClosePrice(LocalDate date, String ticker, BigDecimal closePrice) {
        this.date = date;
        this.ticker = ticker;
        this.price = closePrice;
    }
}
