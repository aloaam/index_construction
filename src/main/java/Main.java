import models.StockClosePrice;
import services.storage.datasources.PostgresqlDataSource;
import services.storage.dates.SqlBusinessDaysStorage;
import services.storage.prices.SqlStockClosePrices;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        //TODO: what do you think about using "var" instead of declaring using the type.

        //TODO: how to make this better.
        final SqlStockClosePrices sqlStockClosePrices = new SqlStockClosePrices(
                new PostgresqlDataSource(),
                new SqlBusinessDaysStorage(new PostgresqlDataSource()));

        final var tickers = new HashSet<String>(Arrays.asList("TWTR", "AAPL"));
        LocalDate date = LocalDate.of(2019, 12, 31);

//        List<StockClosePrice> stockPrices = sqlStockClosePrices.getPricesByDateAndTickers(date, tickers);
        List<StockClosePrice> stockPricesCurrent = sqlStockClosePrices.getPricesByDate(date);
        List<StockClosePrice> stockPricesPrevious = sqlStockClosePrices.getPricesPreviousDate(date);

        System.out.println(stockPricesCurrent);
        System.out.println(stockPricesPrevious);

    }
}
