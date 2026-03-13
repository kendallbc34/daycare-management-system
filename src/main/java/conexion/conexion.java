package conexion;

import java.sql.*;

public class conexion {

    private Connection con;

    public conexion() {
        // La conexión se inicializa en getConnection() para asegurar que siempre esté abierta
    }

    public Connection getConnection() {
        try {
            // Verifica si la conexión es nula o está cerrada antes de intentar usarla
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdguarderia", "root", "");
                System.out.println("Conexión exitosa");
            }
        } catch (Exception e) {
            System.out.println("No puede conectarse a la BD: " + e.getMessage());
        }
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

