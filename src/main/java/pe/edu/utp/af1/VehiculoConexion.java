package pe.edu.utp.af1;

import java.sql.*;

public class VehiculoConexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/estacionamiento";
    private static final String USER = "estacionamiento";
    private static final String PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void insertarVehiculo(String placa, String tipo, int idConductor) throws SQLException {
        String query = "INSERT INTO Vehiculo (placa, tipo, idConductor) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, placa);
            preparedStatement.setString(2, tipo);
            preparedStatement.setInt(3, idConductor);
            preparedStatement.executeUpdate();
        }
    }

    public Vehiculo obtenerVehiculoPorId(int idVehiculo) throws SQLException {
        String query = "SELECT * FROM Vehiculo WHERE idVehiculo = ?";
        Vehiculo vehiculo = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVehiculo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vehiculo = new Vehiculo(
                        resultSet.getInt("idVehiculo"),
                        resultSet.getString("placa"),
                        resultSet.getString("tipo"),
                        resultSet.getInt("idConductor")
                );
            }
        }

        return vehiculo;
    }
}

//hecjo
