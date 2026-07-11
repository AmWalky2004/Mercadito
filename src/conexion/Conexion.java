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

    // Establece y retorna la conexion activa con la base de datos MySQL
    public static Connection conectar() {
        //objeto de conexion configurado, o null sin falla
        
        Connection cn = null;
        try {
            // Carga explícita del driver (evita errores en algunas versiones de NetBeans)
            Class.forName(CONTROLADOR);

            // Armamos la URL dinámicamente
            String url = "jdbc:mysql://" + HOST + "/" + BASE_DATOS + "?useSSL=false&serverTimezone=UTC";

            // Intenta establecer el puente de comunicacion usando las credenciales globales
            cn = DriverManager.getConnection(url, USUARIO, CLAVE);
            return cn;

        } catch (ClassNotFoundException e) {
            // Se ejecuta si no se ha agregado la libreria del conector
            System.out.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            // Se ejecuta ante fallas de red, bases de datos inexistentes o usuario/clave incorrectos
            System.out.println("Error en la conexion local" + e);
            System.out.println("Error en la conexión local: " + e.getMessage());
        }
        return null;
    }
}
