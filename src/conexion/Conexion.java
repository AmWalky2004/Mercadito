package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Conexion Local

    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String BASE_DATOS = "bd_sistema_ventas";

    // CLAVE Y ROOT MYSQL
    private static final String HOST = "localhost";
    private static final String USUARIO = "root";
    private static final String CLAVE = "123456";

    public static Connection conectar() {
        Connection cn = null;
        try {
            // Carga explícita del driver (evita errores en algunas versiones de NetBeans)
            Class.forName(CONTROLADOR);

            // Armamos la URL dinámicamente
            String url = "jdbc:mysql://" + HOST + "/" + BASE_DATOS + "?useSSL=false&serverTimezone=UTC";

            cn = DriverManager.getConnection(url, USUARIO, CLAVE);
            return cn;

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexion local" + e);
            System.out.println("Error en la conexión local: " + e.getMessage());
        }
        return null;
    }
}
