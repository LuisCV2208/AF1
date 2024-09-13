package pe.edu.utp.af1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "estacionamientoServlet", value = "/registrarEstacionamiento")
public class EstacionamientoServlet extends HttpServlet {
    private String jdbcURL = "jdbc:mariadb://localhost:3306/estacionamiento?user=estacionamiento&password=123456";
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoVehiculo = request.getParameter("tipoVehiculo");
        String numeroPlaca = request.getParameter("numeroPlaca");
        String fechaIngreso = request.getParameter("fechaIngreso");
        String fechaSalida = request.getParameter("fechaSalida");
        String nombreConductor = request.getParameter("nombreConductor");
        String dniConductor = request.getParameter("dniConductor");
        String montoCobrarStr = request.getParameter("montoCobrar");
        String observaciones = request.getParameter("observaciones");

        double montoCobrar = Double.parseDouble(montoCobrarStr);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL)) {
                conn.setAutoCommit(false);

                // Insertar el conductor
                String sqlConductor = "INSERT INTO Conductor (nombre, dni) VALUES (?, ?)";
                int idConductor;
                try (PreparedStatement pstmtConductor = conn.prepareStatement(sqlConductor, Statement.RETURN_GENERATED_KEYS)) {
                    pstmtConductor.setString(1, nombreConductor);
                    pstmtConductor.setString(2, dniConductor);
                   pstmtConductor.executeUpdate();

                   try (ResultSet rs = pstmtConductor.getGeneratedKeys()) {
                       if (rs.next()) {
                            idConductor = rs.getInt(1);
                       } else {
                           throw new SQLException("No se pudo obtener el ID del conductor");
                        }
                  }
              }

                // Insertar el vehículo
                String sqlVehiculo = "INSERT INTO Vehiculo (placa, tipo, idConductor) VALUES (?, ?, ?)";
               int idVehiculo;
                try (PreparedStatement pstmtVehiculo = conn.prepareStatement(sqlVehiculo, Statement.RETURN_GENERATED_KEYS)) {
                    pstmtVehiculo.setString(1, numeroPlaca);
                    pstmtVehiculo.setString(2, tipoVehiculo);
                  pstmtVehiculo.setInt(3, idConductor);
                   pstmtVehiculo.executeUpdate();

                    try (ResultSet rs = pstmtVehiculo.getGeneratedKeys()) {
                      if (rs.next()) {
                           idVehiculo = rs.getInt(1);
                       } else {
                            throw new SQLException("No se pudo obtener el ID del vehículo");
                        }
                    }
               }

               // Insertar el estacionamiento
                String sqlEstacionamiento = "INSERT INTO Estacionamiento (fechaHoraIngreso, fechaHoraSalida, montoCobro, comentario, idVehiculo) VALUES (?, ?, ?, ?, ?)";
               try (PreparedStatement pstmtEstacionamiento = conn.prepareStatement(sqlEstacionamiento)) {
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                   LocalDateTime fechaHoraIngreso = LocalDateTime.parse(fechaIngreso, formatter);
                   pstmtEstacionamiento.setTimestamp(1, Timestamp.valueOf(fechaHoraIngreso));

                    if (fechaSalida != null && !fechaSalida.isEmpty()) {
                        LocalDateTime fechaHoraSalida = LocalDateTime.parse(fechaSalida, formatter);
                        pstmtEstacionamiento.setTimestamp(2, Timestamp.valueOf(fechaHoraSalida));
                   } else {
                       pstmtEstacionamiento.setNull(2, Types.TIMESTAMP);
                    }

                    pstmtEstacionamiento.setDouble(3, montoCobrar);
                    pstmtEstacionamiento.setString(4, observaciones);
                   pstmtEstacionamiento.setInt(5, idVehiculo);
                   pstmtEstacionamiento.executeUpdate();
               }
               conn.commit();
                request.getSession().setAttribute("message", "Registro completado con éxito");
           }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Error al registrar los datos: " + e.getMessage());
       }

        response.sendRedirect("estacionamiento.jsp");
   }
}
