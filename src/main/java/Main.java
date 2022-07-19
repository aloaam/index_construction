import models.StockClosePrice;
import services.storage.SqlStockClosePrices;
import services.storage.datasources.DataSourceProvider;
import services.storage.datasources.PostgresqlDataSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        //TODO: what do you think about using "var" instead of declaring using the type.

        final DataSourceProvider dataSource = new PostgresqlDataSource();
        final SqlStockClosePrices sqlStockClosePrices = new SqlStockClosePrices(dataSource);

        final var tickers = new HashSet<String>(Arrays.asList("TWTR", "AAPL"));
        LocalDate date = LocalDate.of(2019, 12, 30);

        List<StockClosePrice> stockPrices = sqlStockClosePrices.getPricesByDateAndTickers(date, tickers);
        stockPrices.forEach(System.out::println);

    }
}
