package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Conexion Local
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas", "root", "Dalvis@123");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local" + e);
        }
        return null;
    }
}
