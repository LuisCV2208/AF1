<%--
  Created by IntelliJ IDEA.
  User: Jose
  Date: 11/09/2024
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection, java.sql.ResultSet, java.sql.PreparedStatement, java.sql.Timestamp" %>
<%@ page import="pe.edu.utp.af1.Conexion" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporte de Estacionamiento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header class="bg-primary text-white text-center py-4">
    <h1>Reporte de Estacionamiento</h1>
</header>

<nav class="bg-dark text-white text-center py-2">
    <a href="estacionamiento.jsp" class="text-white me-3">Inicio</a>
    <a href="Reporte.jsp" class="text-white">Reporte</a>
</nav>

<main class="container mt-5">
    <section>
        <h2 class="text-center mb-4">Reporte de Vehículos Estacionados</h2>

        <!-- Filtros para el reporte -->
        <form method="GET" class="row g-3 mb-4">
            <article class="col-md-4">
                <label for="tipoVehiculo" class="form-label">Tipo de Vehículo</label>
                <select class="form-select" id="tipoVehiculo" name="tipoVehiculo">
                    <option value="">Todos</option>
                    <option value="Auto">Auto</option>
                    <option value="Moto">Moto</option>
                    <option value="Bicicleta">Bicicleta</option>
                    <option value="Camión">Camión</option>
                    <option value="Otro">Otro</option>
                </select>
            </article>

            <article class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Aplicar Filtros</button>
            </article>
        </form>

        <%
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = Conexion.getConnection();

                String tipoVehiculo = request.getParameter("tipoVehiculo");

                String query = "SELECT c.nombre, c.dni, v.tipo, v.placa, e.fechaHoraIngreso, e.fechaHoraSalida, e.montoCobro, e.comentario " +
                        "FROM Conductor c " +
                        "JOIN Vehiculo v ON c.idConductor = v.idConductor " +
                        "JOIN Estacionamiento e ON v.idVehiculo = e.idVehiculo";

                if (tipoVehiculo != null && !tipoVehiculo.isEmpty()) {
                    query += " WHERE v.tipo = ?";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, tipoVehiculo);
                } else {
                    pstmt = conn.prepareStatement(query);
                }

                rs = pstmt.executeQuery();
        %>

        <!-- Tabla de resultados -->
        <section>
            <table class="table table-striped table-bordered text-center">
                <thead class="table-dark">
                <tr>
                    <th>Nombre del Conductor</th>
                    <th>DNI</th>
                    <th>Tipo de Vehículo</th>
                    <th>Número de Placa</th>
                    <th>Fecha de Ingreso</th>
                    <th>Fecha de Salida</th>
                    <th>Monto a Cobrar</th>
                    <th>Observaciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("nombre") %></td>
                    <td><%= rs.getString("dni") %></td>
                    <td><%= rs.getString("tipo") %></td>
                    <td><%= rs.getString("placa") %></td>
                    <td><%= rs.getTimestamp("fechaHoraIngreso") %></td>
                    <td><%= rs.getTimestamp("fechaHoraSalida") %></td>
                    <td><%= rs.getDouble("montoCobro") %></td>
                    <td><%= rs.getString("comentario") %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </section>

        <%
            } catch (Exception e) {
        %>
            <div class="alert alert-danger mt-4">Error al cargar el reporte: <%= e.getMessage() %></div>
        <%
            } finally {
                if (rs != null) try { rs.close(); } catch (Exception e) {}
                if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
                if (conn != null) try { conn.close(); } catch (Exception e) {}
            }
        %>
    </section>
</main>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <p>&copy; 2024 Estacionamiento CarCave. Todos los derechos reservados.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>