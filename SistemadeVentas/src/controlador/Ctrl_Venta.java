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
import modelo.Venta;
import modelo.DetalleVenta;

public class Ctrl_Venta {
    
    // ============ GUARDAR VENTA ============
    public int guardar(Venta venta, List<DetalleVenta> detalles) {
        int idVentaGenerado = 0;
        Connection cn = null;
        PreparedStatement consultaVenta = null;
        PreparedStatement consultaDetalle = null;
        PreparedStatement consultaStock = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.conectar();
            cn.setAutoCommit(false);  // Iniciar transacción
            
            // 1. INSERTAR CABECERA DE VENTA
            String sqlVenta = "INSERT INTO tb_cabeceradv (idCliente, valorpagar, fechaventa, estado) VALUES (?, ?, CURDATE(), ?)";
            consultaVenta = cn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            consultaVenta.setInt(1, venta.getIdCliente());
            consultaVenta.setDouble(2, venta.getValorpagar());
            consultaVenta.setInt(3, venta.getEstado());
            
            int filasVenta = consultaVenta.executeUpdate();
            
            if (filasVenta > 0) {
                rs = consultaVenta.getGeneratedKeys();
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                }
            }
            
            if (idVentaGenerado == 0) {
                throw new SQLException("No se pudo generar el ID de la venta");
            }
            
            // 2. INSERTAR DETALLES DE VENTA
            String sqlDetalle = "INSERT INTO tb_detalledv (idCabeceraVenta, idProducto, cantidad, preciounitario, subtotal, descuento, iva, totalpagar, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            consultaDetalle = cn.prepareStatement(sqlDetalle);
            
            String sqlStock = "UPDATE tb_producto SET cantidad = cantidad - ? WHERE idProducto = ?";
            consultaStock = cn.prepareStatement(sqlStock);
            
            for (DetalleVenta detalle : detalles) {
                // Insertar detalle
                consultaDetalle.setInt(1, idVentaGenerado);
                consultaDetalle.setInt(2, detalle.getIdProducto());
                consultaDetalle.setInt(3, detalle.getCantidad());
                consultaDetalle.setDouble(4, detalle.getPreciounitario());
                consultaDetalle.setDouble(5, detalle.getSubtotal());
                consultaDetalle.setDouble(6, detalle.getDescuento());
                consultaDetalle.setDouble(7, detalle.getIva());
                consultaDetalle.setDouble(8, detalle.getTotalpagar());
                consultaDetalle.setInt(9, detalle.getEstado());
                consultaDetalle.executeUpdate();
                
                // Actualizar stock del producto
                consultaStock.setInt(1, detalle.getCantidad());
                consultaStock.setInt(2, detalle.getIdProducto());
                consultaStock.executeUpdate();
            }
            
            cn.commit();
            System.out.println("Venta guardada exitosamente. ID: " + idVentaGenerado);
            
        } catch (SQLException e) {
            try {
                if (cn != null) cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Error al guardar venta: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar venta: " + e.getMessage());
            idVentaGenerado = 0;
        } finally {
            try {
                if (rs != null) rs.close();
                if (consultaVenta != null) consultaVenta.close();
                if (consultaDetalle != null) consultaDetalle.close();
                if (consultaStock != null) consultaStock.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idVentaGenerado;
    }
    
    // ============ LISTAR VENTAS ============
    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.conectar();
            st = cn.createStatement();
            String sql = "SELECT * FROM tb_cabeceradv ORDER BY idCabeceraVenta DESC";
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdCabeceraVenta(rs.getInt("idCabeceraVenta"));
                venta.setIdCliente(rs.getInt("idCliente"));
                venta.setValorpagar(rs.getDouble("valorpagar"));
                venta.setFechaventa(rs.getDate("fechaventa"));
                venta.setEstado(rs.getInt("estado"));
                lista.add(venta);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
    
    // ============ BUSCAR VENTA POR ID ============
    public Venta buscarVenta(int id) {
        Venta venta = null;
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.conectar();
            String sql = "SELECT * FROM tb_cabeceradv WHERE idCabeceraVenta = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, id);
            rs = consulta.executeQuery();
            
            if (rs.next()) {
                venta = new Venta();
                venta.setIdCabeceraVenta(rs.getInt("idCabeceraVenta"));
                venta.setIdCliente(rs.getInt("idCliente"));
                venta.setValorpagar(rs.getDouble("valorpagar"));
                venta.setFechaventa(rs.getDate("fechaventa"));
                venta.setEstado(rs.getInt("estado"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar venta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (consulta != null) consulta.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return venta;
    }
    
    // ============ LISTAR DETALLES DE UNA VENTA ============
    public List<DetalleVenta> listarDetalles(int idVenta) {
        List<DetalleVenta> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;
        
        try {
            cn = Conexion.conectar();
            String sql = "SELECT * FROM tb_detalledv WHERE idCabeceraVenta = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idVenta);
            rs = consulta.executeQuery();
            
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalledeVenta(rs.getInt("idDetalledeVenta"));
                detalle.setIdCabeceraVenta(rs.getInt("idCabeceraVenta"));
                detalle.setIdProducto(rs.getInt("idProducto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPreciounitario(rs.getDouble("preciounitario"));
                detalle.setSubtotal(rs.getDouble("subtotal"));
                detalle.setDescuento(rs.getDouble("descuento"));
                detalle.setIva(rs.getDouble("iva"));
                detalle.setTotalpagar(rs.getDouble("totalpagar"));
                detalle.setEstado(rs.getInt("estado"));
                lista.add(detalle);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (consulta != null) consulta.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
    
    // ============ ANULAR VENTA ============
    public boolean anularVenta(int idVenta) {
        boolean respuesta = false;
        Connection cn = null;
        PreparedStatement consulta = null;
        
        try {
            cn = Conexion.conectar();
            String sql = "UPDATE tb_cabeceradv SET estado = 0 WHERE idCabeceraVenta = ?";
            consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idVenta);
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Venta anulada");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al anular venta: " + e.getMessage());
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