package pe.edu.utp.af1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/estacionamiento";
   private static final String USER = "estacionamiento";
   private static final String PASSWORD = "123456";

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Cargar el controlador MariaDB
     Class.forName("org.mariadb.jdbc.Driver");
        // Devolver la conexión
       return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}