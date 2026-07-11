package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Historial;

public class Ctrl_historial {

 
    public boolean guardar(Historial objeto) {
        boolean respuesta = false;
        String sql = "INSERT INTO historial (accion, modulo) VALUES (?, ?)";
        
        try (Connection con = conexion.Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getAccion());
            ps.setString(2, objeto.getModulo());
            
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al guardar historial en el controlador: " + e.getMessage());
        }
        return respuesta;
    }

    // Retorna una lista de modelos para pintar la JTable
    public List<Historial> listarHistorial() {
        List<Historial> lista = new ArrayList<>();
        String sql = "SELECT id_historial, accion, modulo, fecha_hora FROM historial ORDER BY fecha_hora DESC";
        
        try (Connection con = conexion.Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Historial h = new Historial();
                h.setId_historial(rs.getInt("id_historial"));
                h.setAccion(rs.getString("accion"));
                h.setModulo(rs.getString("modulo"));
                h.setFecha_hora(rs.getTimestamp("fecha_hora"));
                lista.add(h);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar historial en el controlador: " + e.getMessage());
        }
        return lista;
    }
}