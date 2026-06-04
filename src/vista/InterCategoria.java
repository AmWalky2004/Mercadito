package vista;

import controlador.Ctrl_Categoria;
import java.awt.Dimension;
import java.util.HashSet;
import javax.swing.JOptionPane;
import modelo.Categoria;

public class InterCategoria extends javax.swing.JInternalFrame {

    public InterCategoria() {
        initComponents();
        this.setSize(new Dimension(400, 200));
        this.setTitle("Nueva Categoria");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nueva Categoria");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Desc. Categoria");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txt_descripcion.addActionListener(this::txt_descripcionActionPerformed);
        getContentPane().add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, 170, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 90, 30));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/header1.jpg"))); // NOI18N
        wallpaper.setPreferredSize(new java.awt.Dimension(400, 200));
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Categoria categoria = new Categoria();
        Ctrl_Categoria controlCategoria = new Ctrl_Categoria();

        //validamos espacios vacios
        if (txt_descripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");

        } else {
            if (!controlCategoria.existeCategoria(txt_descripcion.getText().trim())) {
                categoria.setDescripcion(txt_descripcion.getText().trim());
                categoria.setEstado(1);
                if (controlCategoria.guardar(categoria)) {
                    JOptionPane.showMessageDialog(null, "Ha sido registrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La Categoria ya esta registrada");
            }

            //limpiar campo
            txt_descripcion.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
}
