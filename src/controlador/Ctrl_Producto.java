package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Producto;

/**
 *
 * @author PC
 */
public class Ctrl_Producto {
    /*
    Metodo para guardar un nuevo producto
    */
    public boolean guardar(Producto objeto){
        
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIva());
            consulta.setInt(7, objeto.getIdCategoria());
            consulta.setInt(8, objeto.getEstado());
            
            
            if(consulta.executeUpdate() > 0){
                respuesta = true;
            }
            
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR PRODUCTOS: " + e.getMessage());
        }
        
        return respuesta;
    }
    
   
    
    public boolean existeProducto(String producto){
        
        boolean respuesta = false;
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";
        Statement st;             

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {    
                respuesta = true;
            }
            
            // CORRECCIÓN 3: Cerramos la conexión para no saturar MySQL
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("ERROR AL CONSULTAR PRODUCTO: " + e.getMessage());
        }        
        return respuesta;
    } 
}
