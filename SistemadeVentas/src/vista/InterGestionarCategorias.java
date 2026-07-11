package vista;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import controlador.Ctrl_Categoria;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Categoria;

public class InterGestionarCategorias extends javax.swing.JInternalFrame {

    private int idCategoria;

    public InterGestionarCategorias() {
        initComponents();
        this.setSize(new Dimension(600, 400));
        this.setTitle("Gestionar Categorias");

        this.CargarTablaCategoria();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Categorias = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administrar Categorias");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_Categorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_Categorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 350, 250));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Categoria:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_descripcion.setBackground(new java.awt.Color(204, 204, 204));
        txt_descripcion.setForeground(new java.awt.Color(0, 0, 0));
        txt_descripcion.addActionListener(this::txt_descripcionActionPerformed);
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(51, 204, 0));
        jButton_Actualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(0, 0, 0));
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(this::jButton_ActualizarActionPerformed);
        jPanel3.add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jButton_Eliminar.setBackground(new java.awt.Color(255, 51, 51));
        jButton_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_Eliminar.setForeground(new java.awt.Color(0, 0, 0));
        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.addActionListener(this::jButton_EliminarActionPerformed);
        jPanel3.add(jButton_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 90, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 190, 140));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        // TODO add your handling code here:
        if (!txt_descripcion.getText().isEmpty()) {
            Categoria categoria = new Categoria();
            Ctrl_Categoria controlCategoria = new Ctrl_Categoria();

            categoria.setDescripcion(txt_descripcion.getText().trim());
            if (controlCategoria.actualizar(categoria, idCategoria)) {
                JOptionPane.showMessageDialog(null, "CATEGORIA ACTUALIZADA CON EXITO!");
                txt_descripcion.setText("");
                this.CargarTablaCategoria();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA CATEGORIA");
            }

        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA CATEGORIA");
        }
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
        if (!txt_descripcion.getText().isEmpty()) {
            Categoria categoria = new Categoria();
            Ctrl_Categoria controlCategoria = new Ctrl_Categoria();

            categoria.setDescripcion(txt_descripcion.getText().trim());
            if (controlCategoria.eliminar(idCategoria)) {
                JOptionPane.showMessageDialog(null, "CATEGORIA ELIMINADA CON EXITO!");
                txt_descripcion.setText("");
                this.CargarTablaCategoria();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR LA CATEGORIA");
            }

        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA CATEGORIA");
        }
    }//GEN-LAST:event_jButton_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_Categorias;
    private javax.swing.JTextField txt_descripcion;
    // End of variables declaration//GEN-END:variables

    /**
     * metodo para mostrar todas las categorias registradas
     */
    private void CargarTablaCategoria() {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select idCategoria, descripcion, estado from tb_categoria";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            InterGestionarCategorias.jTable_Categorias = new JTable(model);
            InterGestionarCategorias.jScrollPane1.setViewportView(InterGestionarCategorias.jTable_Categorias);

            model.addColumn("ID");
            model.addColumn("Categoria");
            model.addColumn("Estado");

            while (rs.next()) {
                Object file[] = new Object[3];
                for (int i = 0; i < 3; i++) {
                    file[i] = rs.getObject(i + 1);

                }
                model.addRow(file);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de categorias: " + e);
        }
        jTable_Categorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_Categorias.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idCategoria = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosCategoriasSeleccionada(idCategoria);
                }

            }
        });
    }

    public void EnviarDatosCategoriasSeleccionada(int idCategoria) {
        try {
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "select * from tb_categoria where idCategoria = '" + idCategoria + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_descripcion.setText(rs.getString("descripcion"));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar categoria: " + e);
        }
    }

}
