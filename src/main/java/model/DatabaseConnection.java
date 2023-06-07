package model;

import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "janfer";
        String databaseSchema = "atacado";
        String databaseUser = "postgres";
        String databasePassword = "postgres";
        String url = "jdbc:postgresql://localhost:5432/" + databaseName + "?currentSchema=" + databaseSchema;

        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
