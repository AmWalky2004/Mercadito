package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection conectar() {
        try {
            // Añadimos ?useSSL=false para quitar la advertencia de SSL
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas?useSSL=false", "root", "123456");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return null;
    }
}