package services.storage.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDataSource implements DataSourceProvider {

    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public MySqlDataSource() {

        this.driver = System.getenv("DRIVER");
        this.url = System.getenv("URL");
        this.username = System.getenv("USERNAME");
        this.password = System.getenv("PASSWORD");

    }


    @Override
    public Connection getDataSource() {

        try {
            Class.forName(driver);

            return DriverManager.getConnection(url, username, password);


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Unable to create the Driver...?");
        }

    };

}
