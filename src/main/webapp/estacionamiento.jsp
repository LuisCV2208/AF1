<%--
  Created by IntelliJ IDEA.
  User: Jose
  Date: 7/09/2024
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Estacionamiento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header class="bg-primary text-white text-center py-4">
    <h1>Registro de Ingreso y Salida de Vehículos</h1>
</header>

<nav class="bg-dark text-white text-center py-2">
    <a href="estacionamiento.jsp" class="text-white me-3">Inicio</a>
    <a href="Reporte.jsp" class="text-white">Reporte</a>
</nav>

<main class="container mt-5">
    <section>
        <article>
            <form action="registrarEstacionamiento" method="POST">
                <section class="mb-3">
                    <label for="tipoVehiculo" class="form-label">Tipo de Vehículo</label>
                    <select class="form-select" id="tipoVehiculo" name="tipoVehiculo" required>
                        <option value="" disabled selected>Seleccione el tipo de vehículo</option>
                        <option value="Auto">Auto</option>
                        <option value="Moto">Moto</option>
                        <option value="Bicicleta">Bicicleta</option>
                        <option value="Camión">Camión</option>
                        <option value="Otro">Otro</option>
                    </select>
                </section>

                <section class="mb-3">
                    <label for="numeroPlaca" class="form-label">Número de Placa</label>
                    <input type="text" class="form-control" id="numeroPlaca" name="numeroPlaca" required>
                </section>

                <section class="mb-3">
                    <label for="fechaIngreso" class="form-label">Fecha y Hora de Ingreso</label>
                    <input type="datetime-local" class="form-control" id="fechaIngreso" name="fechaIngreso" required>
                </section>

                <section class="mb-3">
                    <label for="fechaSalida" class="form-label">Fecha y Hora de Salida</label>
                    <input type="datetime-local" class="form-control" id="fechaSalida" name="fechaSalida">
                </section>

                <section class="mb-3">
                    <label for="nombreConductor" class="form-label">Nombre del Conductor</label>
                    <input type="text" class="form-control" id="nombreConductor" name="nombreConductor" required>
                </section>

                <section class="mb-3">
                    <label for="dniConductor" class="form-label">DNI del Conductor</label>
                    <input type="text" class="form-control" id="dniConductor" name="dniConductor" required>
                </section>

                <section class="mb-3">
                    <label for="montoCobrar" class="form-label">Monto a Cobrar</label>
                    <input type="number" step="0.01" class="form-control" id="montoCobrar" name="montoCobrar" required>
                </section>

                <section class="mb-3">
                    <label for="observaciones" class="form-label">Observaciones</label>
                    <textarea class="form-control" id="observaciones" name="observaciones" rows="3"></textarea>
                </section>

                <section class="text-center">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </section>
            </form>

            <!-- Mostrar mensaje de éxito o error -->
            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
            %>
                <div class="alert <%= message.startsWith("Error") ? "alert-danger" : "alert-success" %> mt-3" role="alert">
                    <%= message %>
                </div>
            <%
                    session.removeAttribute("message");
                }
            %>

        </article>
    </section>
</main>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <p>&copy; 2024 Estacionamiento CarCave. Todos los derechos reservados.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
