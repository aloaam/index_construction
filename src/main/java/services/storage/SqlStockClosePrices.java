package services.storage;

import models.StockClosePrice;
import services.storage.datasources.DataSourceProvider;
import services.storage.datasources.MySqlDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SqlStockClosePrices {


    private DataSourceProvider dataSource;

    public SqlStockClosePrices(DataSourceProvider dataSource) {
        this.dataSource = dataSource;
    }

    //TODO: ask how to design a query which can take a parameter or none, for example
    // I wanted to create a function that could query the 'stock_prices' by date and/or ticker
    // but I run into the problem that I add the where clause, then i have to add a parameter, but sometimes i
    // would like to retrieve everything.

    public List<StockClosePrice> getPricesByDateAndTickers(LocalDate date, Set<String> tickers) {

        String sqlQuery = "SELECT * FROM stock_close_prices WHERE date = ? AND ticker = ANY(?)";

        try (Connection connection = dataSource.getDataSource();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setArray(2, connection.createArrayOf("text", tickers.toArray()));
            ResultSet resultSet = preparedStatement.executeQuery();

            final List<StockClosePrice> stockPrices = new ArrayList<>();
            while (resultSet.next()) {
                stockPrices.add(buildStockClosePriceFrom(resultSet));
            }
            return stockPrices;

        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve prices for date, tickers", e);
        }

    }

    private StockClosePrice buildStockClosePriceFrom(ResultSet resultSet) throws SQLException {
        return new StockClosePrice(
                resultSet.getDate("date").toLocalDate(),
                resultSet.getString("ticker"),
                resultSet.getBigDecimal("close"));
    }

}