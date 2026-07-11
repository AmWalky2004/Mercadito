package reportes;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class Reportes {

    // ==========================================
    // REPORTE DE CATEGORÍAS
    // ==========================================
    public void generarReporteCategorias() {
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
        
        try {
            String ruta = "Reporte_Categorias.pdf";
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(ruta));
            documento.open();

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph(
                "REPORTE DE CATEGORÍAS REGISTRADAS\n\n", 
                com.itextpdf.text.FontFactory.getFont("Arial", 16, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.BLACK)
            );
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            java.util.Date fecha = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            com.itextpdf.text.Paragraph fechaP = new com.itextpdf.text.Paragraph(
                "Fecha: " + sdf.format(fecha) + "\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 10, com.itextpdf.text.Font.ITALIC)
            );
            fechaP.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            documento.add(fechaP);

            com.itextpdf.text.pdf.PdfPTable tabla = new com.itextpdf.text.pdf.PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.addCell("ID Categoría");
            tabla.addCell("Descripción");

            try (Connection cn = Conexion.conectar();
                 PreparedStatement pst = cn.prepareStatement("SELECT idCategoria, descripcion FROM tb_categoria WHERE estado = 1 ORDER BY idCategoria");
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    tabla.addCell(String.valueOf(rs.getInt("idCategoria")));
                    tabla.addCell(rs.getString("descripcion"));
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "¡Reporte de Categorías generado exitosamente!");
            abrirArchivo(ruta);

        } catch (Exception e) {
            System.out.println("Error en Reporte de Categorías: " + e.getMessage());
        }
    }

    // ==========================================
    // REPORTE DE CLIENTES
    // ==========================================
    public void generarReporteClientes() {
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
        
        try {
            String ruta = "Reporte_Clientes.pdf";
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(ruta));
            documento.open();

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph(
                "REPORTE DE CLIENTES REGISTRADOS\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 16, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.BLACK)
            );
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            java.util.Date fecha = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            com.itextpdf.text.Paragraph fechaP = new com.itextpdf.text.Paragraph(
                "Fecha: " + sdf.format(fecha) + "\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 10, com.itextpdf.text.Font.ITALIC)
            );
            fechaP.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            documento.add(fechaP);

            com.itextpdf.text.pdf.PdfPTable tabla = new com.itextpdf.text.pdf.PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("ID");
            tabla.addCell("Nombre Completo");
            tabla.addCell("Cédula");
            tabla.addCell("Teléfono");
            tabla.addCell("Dirección");

            try (Connection cn = Conexion.conectar();
                 PreparedStatement pst = cn.prepareStatement("SELECT idcliente, nombre, apellido, cedula, telefono, direccion FROM tb_cliente WHERE estado = 1 ORDER BY nombre");
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    tabla.addCell(String.valueOf(rs.getInt("idcliente")));
                    tabla.addCell(rs.getString("nombre") + " " + rs.getString("apellido"));
                    tabla.addCell(rs.getString("cedula"));
                    tabla.addCell(rs.getString("telefono"));
                    tabla.addCell(rs.getString("direccion"));
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "¡Reporte de Clientes generado exitosamente!");
            abrirArchivo(ruta);

        } catch (Exception e) {
            System.out.println("Error en Reporte de Clientes: " + e.getMessage());
        }
    }

    // ==========================================
    // REPORTE DE PRODUCTOS
    // ==========================================
    public void generarReporteProductos() {
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
        
        try {
            String ruta = "Reporte_Productos.pdf";
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(ruta));
            documento.open();

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph(
                "INVENTARIO GENERAL DE PRODUCTOS\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 16, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.BLACK)
            );
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            java.util.Date fecha = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            com.itextpdf.text.Paragraph fechaP = new com.itextpdf.text.Paragraph(
                "Fecha: " + sdf.format(fecha) + "\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 10, com.itextpdf.text.Font.ITALIC)
            );
            fechaP.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            documento.add(fechaP);

            com.itextpdf.text.pdf.PdfPTable tabla = new com.itextpdf.text.pdf.PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("Código");
            tabla.addCell("Producto");
            tabla.addCell("Stock");
            tabla.addCell("Precio ($)");
            tabla.addCell("Descripción");

            try (Connection cn = Conexion.conectar();
                 PreparedStatement pst = cn.prepareStatement("SELECT idProducto, nombre, cantidad, precio, descripcion FROM tb_producto WHERE estado = 1 ORDER BY nombre");
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    tabla.addCell(String.valueOf(rs.getInt("idProducto")));
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(String.valueOf(rs.getInt("cantidad")));
                    tabla.addCell(String.format("%.2f", rs.getDouble("precio")));
                    tabla.addCell(rs.getString("descripcion"));
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "¡Reporte de Productos generado exitosamente!");
            abrirArchivo(ruta);

        } catch (Exception e) {
            System.out.println("Error en Reporte de Productos: " + e.getMessage());
        }
    }

    // ==========================================
    // REPORTE DE VENTAS
    // ==========================================
    public void generarReporteVentas() {
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
        
        try {
            String ruta = "Reporte_Ventas.pdf";
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(ruta));
            documento.open();

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph(
                "HISTORIAL GENERAL DE VENTAS\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 16, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.BLACK)
            );
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            java.util.Date fecha = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            com.itextpdf.text.Paragraph fechaP = new com.itextpdf.text.Paragraph(
                "Fecha: " + sdf.format(fecha) + "\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 10, com.itextpdf.text.Font.ITALIC)
            );
            fechaP.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            documento.add(fechaP);

            com.itextpdf.text.pdf.PdfPTable tabla = new com.itextpdf.text.pdf.PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("N° Factura");
            tabla.addCell("Cliente");
            tabla.addCell("Total ($)");
            tabla.addCell("Fecha");
            tabla.addCell("Estado");

            try (Connection cn = Conexion.conectar();
                 PreparedStatement pst = cn.prepareStatement(
                    "SELECT v.idCabeceraVenta, v.fechaventa, v.valorpagar, v.estado, "
                    + "c.nombre, c.apellido, c.cedula "
                    + "FROM tb_cabeceradv v "
                    + "INNER JOIN tb_cliente c ON v.idCliente = c.idcliente "
                    + "ORDER BY v.idCabeceraVenta DESC");
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    tabla.addCell("FAC-" + String.format("%04d", rs.getInt("idCabeceraVenta")));
                    tabla.addCell(rs.getString("nombre") + " " + rs.getString("apellido"));
                    tabla.addCell(String.format("%.2f", rs.getDouble("valorpagar")));
                    tabla.addCell(rs.getString("fechaventa"));
                    tabla.addCell(rs.getInt("estado") == 1 ? "Activa" : "Anulada");
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "¡Reporte de Ventas generado exitosamente!");
            abrirArchivo(ruta);

        } catch (Exception e) {
            System.out.println("Error en Reporte de Ventas: " + e.getMessage());
        }
    }

    // ==========================================
    // MÉTODO PARA ABRIR ARCHIVO
    // ==========================================
    private void abrirArchivo(String ruta) {
        try {
            java.io.File archivo = new java.io.File(ruta);
            if (archivo.exists()) {
                java.awt.Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {
            System.out.println("Error al abrir archivo: " + e.getMessage());
        }
    }
}