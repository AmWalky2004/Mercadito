package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author PC
 */
public class Ctrl_Cliente {

    // ============ GUARDAR UN NUEVO CLIENTE ============
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            // CONECTAMOS CON MYSQL
            String sql = "INSERT INTO tb_cliente (nombre, apellido, cedula, telefono, direccion, estado) VALUES (?, ?, ?, ?, ?, ?)";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR: " + e.getMessage());

            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "El Cliente '" + objeto.getNombre() + "' ya existe");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
            }
            e.printStackTrace();
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
    // En Ctrl_Cliente

    public Cliente buscarPorCedula(String cedula) {
        Cliente cliente = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT idcliente, nombre, apellido, cedula, telefono, direccion, estado FROM tb_cliente WHERE cedula = ? AND estado = 1";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, cedula);
            rs = consulta.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
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
        return cliente;
    }

    // ============ VERIFICAR SI EL CLIENTE EXISTE ============
    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT cedula FROM tb_cliente WHERE cedula = '" + cedula + "';";
            consulta = cn.prepareStatement(sql);
            rs = consulta.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL CONSULTAR CLIENTE: " + e.getMessage());
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

    // ============ BUSCAR CLIENTE POR ID ============
    public Cliente buscarPorId(int id) {
        Cliente cliente = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT idcliente, nombre, apellido, cedula, telefono, direccion, estado FROM tb_cliente WHERE idcliente = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, id);
            rs = consulta.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
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
        return cliente;
    }

    //================= ACTUALIZAR CLIENTE ====================
    public boolean actualizar(Cliente cliente, int idCliente) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();
            String sql = "UPDATE tb_cliente SET nombre = ?, apellido = ?, cedula = ?, telefono = ?, direccion = ?, estado = ? WHERE idcliente = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getCedula());
            consulta.setString(4, cliente.getTelefono());
            consulta.setString(5, cliente.getDireccion());
            consulta.setInt(6, cliente.getEstado());
            consulta.setInt(7, idCliente);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
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
    //============= ELIMINAR CLIENTE ==============
    public boolean eliminar(int idCliente) {
    boolean respuesta = false;
    Connection cn = null;
    PreparedStatement consulta = null;

    try {
        cn = Conexion.conectar();
        String sql = "DELETE FROM tb_cliente WHERE idcliente = ?";
        consulta = cn.prepareStatement(sql);
        consulta.setInt(1, idCliente);

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }

    } catch (SQLException e) {
        System.out.println("Error al eliminar cliente: " + e.getMessage());
    } finally {
        try {
            if (consulta != null) consulta.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return respuesta;
}

}
