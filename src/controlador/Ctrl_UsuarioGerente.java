package controlador; 

import conexion.Conexion;
import java.sql.Connection;
import modelo.Usuario;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Ctrl_UsuarioGerente {

    // Método para validar el ingreso del Gerente
    public int loginUserGerente(Usuario objeto) {
        int tipoUsuario = 0; 
        Connection cn = Conexion.conectar();
        
        String sql = "select usuario, password, estado from tb_usuario where usuario = '" 
                + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "'";
        
        Statement st;
        
        if (cn != null) {
            try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (rs.next()) {
                    tipoUsuario = rs.getInt("estado"); 
                }
                cn.close(); 

            } catch (SQLException e) {
                System.out.println("Error al Iniciar Sesion Gerente: " + e);
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se pudo establecer conexión con el servidor MySQL.");
        }
        return tipoUsuario; 
    }
}