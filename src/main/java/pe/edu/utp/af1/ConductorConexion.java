package pe.edu.utp.af1;

import java.sql.*;

public class ConductorConexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/estacionamiento";
    private static final String USER = "estacionamiento";
    private static final String PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertarConductor(String nombre, String dni) throws SQLException {
        String query = "INSERT INTO Conductor (nombre, dni) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, dni);
            preparedStatement.executeUpdate();
        }
    }

    public Conductor obtenerConductorPorId(int idConductor) throws SQLException {
        String query = "SELECT * FROM Conductor WHERE idConductor = ?";
        Conductor conductor = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idConductor);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conductor = new Conductor(
                        resultSet.getInt("idConductor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("dni")
                );
            }
        }

        return conductor;
    }

}
