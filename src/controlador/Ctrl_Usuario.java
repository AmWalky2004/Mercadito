package controlador;

import conexion.Conexion;
import java.sql.Connection;
import modelo.Usuario;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Ctrl_Usuario {
    
    public boolean guardar(Usuario objeto) {
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    try {
        PreparedStatement consulta = cn.prepareStatement("insert into tb_usuario values(?,?,?,?,?,?,?)");
        consulta.setInt(1, 0);//id
        consulta.setString(2, objeto.getNombre());
        consulta.setString(3, objeto.getApellido());
        consulta.setString(4, objeto.getUsuario());
        consulta.setString(5, objeto.getPassword());
        consulta.setString(6, objeto.getTelefono());
        consulta.setInt(7, objeto.getEstado());
        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("ERROR AL GUARDAR EL USUARIO: " + e);
    }
    return respuesta;
}
    
    public boolean existeUsuario(String usuario) {
    boolean respuesta = false;
    String sql = "select usuario from tb_usuario where usuario = '" + usuario + "';";
    Statement st;
    try {
        Connection cn = Conexion.conectar();
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            respuesta = true;
        }
    } catch (SQLException e) {
        System.out.println("EEROR AL CONSULTAR EL USUARIO: " + e);
    }
    return respuesta;
}
    
    
    
    
/*
 METODO PARA INICIAR SESION    
    
    */
    public boolean loginUser(Usuario objeto) {

        boolean respuesta = false;

        Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "'";
        Statement st;
        try {

            st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion");
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");

        }
        return respuesta;

    }
}
