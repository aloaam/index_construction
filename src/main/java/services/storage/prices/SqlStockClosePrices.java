package services.storage.prices;

import models.StockClosePrice;
import services.storage.datasources.DataSourceProvider;
import services.storage.dates.BusinessDaysStorage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SqlStockClosePrices {

    private DataSourceProvider sqlDataSource;
    private BusinessDaysStorage sqlBusinessDays;

    public SqlStockClosePrices(DataSourceProvider sqlDataSource, BusinessDaysStorage sqlBusinessDays) {
        this.sqlDataSource = sqlDataSource;
        this.sqlBusinessDays = sqlBusinessDays;
    }

    //TODO: ask how to design a query which can take a parameter or none, for example
    // I wanted to create a function that could query the 'stock_prices' by date and/or ticker
    // but I run into the problem that I add the where clause, then i have to add a parameter, but sometimes i
    // would like to retrieve everything.

    public List<StockClosePrice> getPricesByDateAndTickers(LocalDate date, Set<String> tickers) {

        String sqlQuery = "SELECT * FROM stock_close_prices WHERE date = ? AND ticker = ANY(?)";

        try (Connection connection = sqlDataSource.getDataSource();
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

    public List<StockClosePrice> getPricesByDate(LocalDate date) {

        String sqlQuery = "SELECT * FROM stock_close_prices WHERE date = ?";

        try (Connection connection = sqlDataSource.getDataSource();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setDate(1, Date.valueOf(date));
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

    public List<StockClosePrice> getPricesPreviousDate(LocalDate date) {

        return getPricesByDate(sqlBusinessDays.getPreviousBusinessDay(date));
    }

    private StockClosePrice buildStockClosePriceFrom(ResultSet resultSet) throws SQLException {
        return new StockClosePrice(
                resultSet.getDate("date").toLocalDate(),
                resultSet.getString("ticker"),
                resultSet.getBigDecimal("close"));
    }

}