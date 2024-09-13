package pe.edu.utp.af1;

import java.sql.*;

public class EstacionamientoConexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/estacionamiento";
    private static final String USER = "estacionamiento";
    private static final String PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void registrarEstacionamiento(Timestamp fechaIngreso, Timestamp fechaSalida, double montoCobro, String comentario, int idVehiculo) throws SQLException {
        String query = "INSERT INTO Estacionamiento (fechaHoraIngreso, fechaHoraSalida, montoCobro, comentario, idVehiculo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, fechaIngreso);
            preparedStatement.setTimestamp(2, fechaSalida);
            preparedStatement.setDouble(3, montoCobro);
            preparedStatement.setString(4, comentario);
            preparedStatement.setInt(5, idVehiculo);
            preparedStatement.executeUpdate();
        }
    }

    public Estacionamiento obtenerEstacionamientoPorId(int idEstacionamiento) throws SQLException {
        String query = "SELECT * FROM Estacionamiento WHERE idEstacionamiento = ?";
        Estacionamiento estacionamiento = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idEstacionamiento);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                estacionamiento = new Estacionamiento(
                        resultSet.getInt("idEstacionamiento"),
                        resultSet.getTimestamp("fechaHoraIngreso"),
                        resultSet.getTimestamp("fechaHoraSalida"),
                        resultSet.getDouble("montoCobro"),
                        resultSet.getString("comentario"),
                        resultSet.getInt("idVehiculo")
                );
            }
        }

        return estacionamiento;
    }

}
