package services.storage.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlDataSource implements DataSourceProvider {

    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public PostgresqlDataSource() {

//        this.driver = System.getenv("DRIVER");
//        this.url = System.getenv("URL");
//        this.username = System.getenv("USERNAME");
//        this.password = System.getenv("PASSWORD");

        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/index_construction";
        this.username = "postgres";
        this.password = "anAdRAt34f6@JkA9RY3R.@BC";

    }


    @Override
    public Connection getDataSource() {

        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/index_construction?currentSchema=alo",
                    "postgres",
                    "anAdRAt34f6@JkA9RY3R.@BC"
            );


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Unable to create the Driver...?");
        }

    }

    ;

}
