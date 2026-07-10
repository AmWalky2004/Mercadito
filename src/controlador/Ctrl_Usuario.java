package controlador;

import conexion.Conexion;
import java.sql.Connection;
import modelo.Usuario;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Ctrl_Usuario {

    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            String sql = "INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono, estado) VALUES (?, ?, ?, ?, ?, ?)";
            consulta = cn.prepareStatement(sql);

            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR: " + e.getMessage());
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
            }
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    //actualizar
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            // Consulta SQL parametrizada para actualizar los datos correspondientes usando el idUsuario
            String sql = "update tb_usuario set nombre=?, apellido=?, usuario=?, password=?, telefono=? where idUsuario = ?";
            consulta = cn.prepareStatement(sql);

            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR USUARIO: " + e.getMessage());
        }
        return respuesta;
    }

    //eliminar
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            String sql = "DELETE FROM tb_usuario WHERE idUsuario = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR USUARIO: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT usuario FROM tb_usuario WHERE usuario = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, usuario);
            rs = consulta.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL CONSULTAR: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    public int loginUser(Usuario objeto) {
        int tipo = 0; // 0 = Error, 1 = Admin, 2 = Gerente, 3 = Cajero
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT estado FROM tb_usuario WHERE usuario = ? AND password = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getUsuario());
            consulta.setString(2, objeto.getPassword());
            rs = consulta.executeQuery();

            if (rs.next()) {
                tipo = rs.getInt("estado"); // 1=Admin, 2=Gerente, 3=Cajero
            }

        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tipo;
    }
}
