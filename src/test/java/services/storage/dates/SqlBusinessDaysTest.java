package services.storage.dates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.storage.datasources.PostgresqlDataSource;

import java.time.LocalDate;

class SqlBusinessDaysTest {

    final SqlBusinessDaysStorage unit = new SqlBusinessDaysStorage(new PostgresqlDataSource());

    @Test
    void getAllBusinessDays() {

        var allBusinessDays = unit.getAllBusinessDays();

        System.out.println(allBusinessDays.size());
        Assertions.assertEquals(allBusinessDays.size(), 254);
    }


    //TODO: how to divide these 4 cases, so that its verbose if sth fails.

    @Test
    void getPreviousBusinessDay() {

        var existingDate = LocalDate.of(2020, 12, 30);
        var previousDateToExistingDate = LocalDate.of(2020, 12, 29);
        var nonExistingDate = LocalDate.of(2020, 12, 27);
        var dateWithoutPreviousDate = LocalDate.of(2019, 12, 30);

        Assertions.assertEquals(previousDateToExistingDate, unit.getPreviousBusinessDay(existingDate));

//        unit.getPreviousBusinessDay(nonExistingDate);

        unit.getPreviousBusinessDay(dateWithoutPreviousDate);

    }

}