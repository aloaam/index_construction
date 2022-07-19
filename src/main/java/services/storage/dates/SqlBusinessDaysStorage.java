package services.storage.dates;

import services.storage.datasources.DataSourceProvider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SqlBusinessDaysStorage implements BusinessDaysStorage {

    private DataSourceProvider dataSource;

    public SqlBusinessDaysStorage(DataSourceProvider dataSource) {
        this.dataSource = dataSource;
    }

    public List<LocalDate> getAllBusinessDays() {

        String sqlQuery = "SELECT * FROM business_days";

        try (Connection connection = dataSource.getDataSource();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            final List<LocalDate> businessDays = new ArrayList<>();
            while (resultSet.next())
                businessDays.add(resultSet.getDate("date").toLocalDate());

            return businessDays;

        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve business days", e);
        }

    }

    public LocalDate getPreviousBusinessDay(LocalDate date) {

        String sqlQuery = "SELECT * FROM business_days WHERE date <= ? ORDER BY date DESC LIMIT 2 ";

        try (Connection connection = dataSource.getDataSource();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            final List<LocalDate> days = new ArrayList<>();

            if (resultSet.next())
                if (!resultSet.getDate("date").toLocalDate().equals(date))
                    throw new RuntimeException("The date sent was not found: " + date);

            if (resultSet.next())
                return resultSet.getDate("date").toLocalDate();

            else
                throw new RuntimeException("Previous date of date sent as parameter was not found in db: " + date);


        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve business days", e);
        }

    }

}
