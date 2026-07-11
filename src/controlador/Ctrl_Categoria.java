package controlador;

import conexion.Conexion;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class Ctrl_Categoria {

    /*
    Metodo para guardar las categorias
     */
    public boolean guardar(Categoria objeto) {

        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?, ?, ?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR LA CATEGORIA: " + e.getMessage());
        }

        return respuesta;
    }

    public boolean existeCategoria(String categoria) {

        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

            // Cerramos la conexión para no saturar MySQL
            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR AL CONSULTAR LA CATEGORIA: " + e.getMessage());
        }
        return respuesta;
    }

    /*
    Metodo para actualizar las categorias
     */
    public boolean actualizar(Categoria objeto, int idCategoria) {

        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            // CORRECCIÓN 1: Usamos '?' para el ID de forma segura y sin comillas de texto
            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria = ?");
            consulta.setString(1, objeto.getDescripcion());
            consulta.setInt(2, idCategoria);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR LA CATEGORIA: " + e.getMessage());
        }

        return respuesta;
    }

    /*
    Metodo para eliminar las categorias
     */
    public boolean eliminar(int idCategoria) {

        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // CORRECCIÓN 1: Usamos '?' para pasar el ID numérico correctamente
            PreparedStatement consulta = cn.prepareStatement("delete from tb_categoria where idCategoria = ?");
            consulta.setInt(1, idCategoria);

            // CORRECCIÓN 2: Eliminamos la línea duplicada de executeUpdate()
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR LA CATEGORIA: " + e.getMessage());
        }

        return respuesta;
    }

    /*
Metodo para listar todas las categorías activas
     */
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            cn = Conexion.conectar();
            st = cn.createStatement();
            String sql = "SELECT * FROM tb_categoria WHERE estado = 1 ORDER BY descripcion";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setDescripcion(rs.getString("descripcion"));
                cat.setEstado(rs.getInt("estado"));
                lista.add(cat);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
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

}
