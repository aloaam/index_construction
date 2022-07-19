package services.storage.dates;

import java.time.LocalDate;
import java.util.List;

public interface BusinessDaysStorage {

    List<LocalDate> getAllBusinessDays();

    LocalDate getPreviousBusinessDay(LocalDate date);

}
