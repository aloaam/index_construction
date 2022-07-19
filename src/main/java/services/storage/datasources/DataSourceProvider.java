package services.storage.datasources;

import java.sql.Connection;

public interface DataSourceProvider {
    Connection getDataSource();
}
