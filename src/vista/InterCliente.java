package vista;

import controlador.Ctrl_Cliente;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class InterCliente extends javax.swing.JInternalFrame {

    public InterCliente() {
        initComponents();
        this.setSize(new Dimension(400, 330));
        this.setTitle("Nuevo Cliente");

        limpiarCampos();
    }


    // ============================================================
    // LIMPIAR CAMPOS
    // ============================================================
    private void limpiarCampos() {
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_cedula.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");

        txt_nombre.setBackground(Color.WHITE);
        txt_apellido.setBackground(Color.WHITE);
        txt_cedula.setBackground(Color.WHITE);
        txt_telefono.setBackground(Color.WHITE);
        txt_direccion.setBackground(Color.WHITE);

        txt_nombre.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_cedula = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        jButton_Guardar = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nuevo Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellido:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 90, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cédula:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 90, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Teléfono:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 90, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Dirección:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 90, -1));

        txt_nombre.addActionListener(this::txt_nombreActionPerformed);
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 170, -1));

        txt_apellido.addActionListener(this::txt_apellidoActionPerformed);
        getContentPane().add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 170, -1));

        txt_cedula.addActionListener(this::txt_cedulaActionPerformed);
        getContentPane().add(txt_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 170, -1));

        txt_telefono.addActionListener(this::txt_telefonoActionPerformed);
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 170, -1));

        txt_direccion.addActionListener(this::txt_direccionActionPerformed);
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 170, -1));

        jButton_Guardar.setBackground(new java.awt.Color(0, 204, 204));
        jButton_Guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(this::jButton_GuardarActionPerformed);
        getContentPane().add(jButton_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 210, 90, 30));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cedulaActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        String nombre = txt_nombre.getText().trim();
        String apellido = txt_apellido.getText().trim();
        String cedula = txt_cedula.getText().trim();
        String telefono = txt_telefono.getText().trim();
        String direccion = txt_direccion.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Complete los campos obligatorios:\n• Nombre\n• Apellido\n• Cédula",
                    "Campos Incompletos",
                    JOptionPane.ERROR_MESSAGE);

            if (nombre.isEmpty()) {
                txt_nombre.setBackground(Color.RED);
            }
            if (apellido.isEmpty()) {
                txt_apellido.setBackground(Color.RED);
            }
            if (cedula.isEmpty()) {
                txt_cedula.setBackground(Color.RED);
            }

            return;
        }

        Ctrl_Cliente controlCliente = new Ctrl_Cliente();

        if (controlCliente.existeCliente(cedula)) {
            JOptionPane.showMessageDialog(this,
                    "El cliente con cédula " + cedula + " ya está registrado.",
                    "Cliente Duplicado",
                    JOptionPane.WARNING_MESSAGE);
            txt_cedula.setBackground(Color.YELLOW);
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setEstado(1);

        if (controlCliente.guardar(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente");

            // ✅ Mostrar feedback con color verde
            txt_nombre.setBackground(new Color(144, 238, 144));
            txt_apellido.setBackground(new Color(144, 238, 144));
            txt_cedula.setBackground(new Color(144, 238, 144));
            txt_telefono.setBackground(new Color(144, 238, 144));
            txt_direccion.setBackground(new Color(144, 238, 144));

            // ✅ Limpiar campos (restablece a BLANCO)
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente");
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed

    private void txt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
