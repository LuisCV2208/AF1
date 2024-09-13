package pe.edu.utp.af1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String connectionString;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("path/to/app.properties");
            properties.load(input);
            connectionString = properties.getProperty("cnxString");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(connectionString);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MariaDB", e);
        }
    }
}
