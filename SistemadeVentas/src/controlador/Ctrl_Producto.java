package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Producto;

public class Ctrl_Producto {

    // ============ GUARDAR PRODUCTO ============
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            // CONECTAMOS CON MYSQL
            String sql = "INSERT INTO tb_producto (nombre, cantidad, precio, descripcion, porcentajeIVA, idCategoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            consulta = cn.prepareStatement(sql);

            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getPorcentajeIVA());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());

            System.out.println("GUARDANDO PRODUCTO: " + objeto.getNombre());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("PRODUCTO GUARDADO");
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR: " + e.getMessage());

            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "El producto '" + objeto.getNombre() + "' ya existe");
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

    // ============ VERIFICAR SI EL PRODUCTO EXISTE ============
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT nombre FROM tb_producto WHERE nombre = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, producto);
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

    // ============ LISTAR TODOS LOS PRODUCTOS ============
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            st = cn.createStatement();
            String sql = "SELECT * FROM tb_producto ORDER BY idProducto";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPorcentajeIVA(rs.getInt("porcentajeIVA"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setEstado(rs.getInt("estado"));
                lista.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    // ============ BUSCAR PRODUCTO POR ID ============
    public Producto buscarProducto(int id) {
        Producto producto = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT * FROM tb_producto WHERE idProducto = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, id);
            rs = consulta.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPorcentajeIVA(rs.getInt("porcentajeIVA"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
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
        return producto;
    }
    // ============ BUSCAR PRODUCTO POR NOMBRE ============

    public Producto buscarProductoPorNombre(String nombre) {
        Producto producto = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            String sql = "SELECT idProducto, nombre, cantidad, precio, descripcion, porcentajeIVA, idCategoria, estado FROM tb_producto WHERE nombre LIKE ? AND estado = 1";
            consulta = cn.prepareStatement(sql);
            consulta.setString(1, "%" + nombre + "%");
            rs = consulta.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPorcentajeIVA(rs.getInt("porcentajeIVA"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
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
        return producto;
    }
    // ============ ACTUALIZAR PRODUCTO ============

    public boolean actualizar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            String sql = "UPDATE tb_producto SET nombre = ?, cantidad = ?, precio = ?, descripcion = ?, porcentajeIVA = ?, idCategoria = ?, estado = ? WHERE idProducto = ?";
            consulta = cn.prepareStatement(sql);

            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getPorcentajeIVA());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());
            consulta.setInt(8, objeto.getIdProducto());

            System.out.println("Actualizando producto: " + objeto.getNombre());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Producto actualizado");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
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
// ============ DESACTIVAR PRODUCTO ============

    public boolean desactivar(int idProducto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            String sql = "UPDATE tb_producto SET estado = 0 WHERE idProducto = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idProducto);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Producto desactivado: ID " + idProducto);
            }

        } catch (SQLException e) {
            System.out.println("Error al desactivar: " + e.getMessage());
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
    // ============ ELIMINAR PRODUCTO ============

    public boolean eliminarFisicamente(int idProducto) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            String sql = "DELETE FROM tb_producto WHERE idProducto = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idProducto);

            System.out.println("Eliminando el producto ID: " + idProducto);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Producto Eliminado");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
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

    // ============ ACTUALIZAR STOCK ============
    public boolean actualizarStock(int idProducto, int nuevoStock) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;

        try {
            cn = Conexion.conectar();

            String sql = "UPDATE tb_producto SET cantidad = ? WHERE idProducto = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, nuevoStock);
            consulta.setInt(2, idProducto);

            System.out.println("Actualizando stock - ID: " + idProducto + " → Nuevo stock: " + nuevoStock);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Stock actualizado");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar stock: " + e.getMessage());
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
}
