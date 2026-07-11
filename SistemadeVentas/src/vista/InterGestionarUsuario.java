package vista;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import controlador.Ctrl_Categoria;
import controlador.Ctrl_Usuario;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Categoria;
import modelo.Usuario;

public class InterGestionarUsuario extends javax.swing.JInternalFrame {

    private int idUsuario;

    public InterGestionarUsuario() {
        initComponents();
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Usuarios");
        
        this.CargarTablaUsuarios();


        //insertar imagen
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, java.awt.Image.SCALE_DEFAULT));
        jlabel_wallpaper.setIcon(icono);
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_usuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel_apellido = new javax.swing.JLabel();
        txt_contraseña = new javax.swing.JTextField();
        jLabel_contraseña = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_usuario = new javax.swing.JLabel();
        jLabel_nombre = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_usuario = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jlabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administrar Usuarios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable_usuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_usuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 870, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_apellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_apellido.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_apellido.setText("Apellido:");
        jPanel3.add(jLabel_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 90, -1));

        txt_contraseña.setBackground(new java.awt.Color(204, 204, 204));
        txt_contraseña.setForeground(new java.awt.Color(0, 0, 0));
        txt_contraseña.addActionListener(this::txt_contraseñaActionPerformed);
        jPanel3.add(txt_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 170, -1));

        jLabel_contraseña.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_contraseña.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_contraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_contraseña.setText("Contraseña:");
        jPanel3.add(jLabel_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel_telefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_telefono.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_telefono.setText("Telefono:");
        jPanel3.add(jLabel_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 90, -1));

        jLabel_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_usuario.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_usuario.setText("Usuario:");
        jPanel3.add(jLabel_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, -1));

        jLabel_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nombre.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_nombre.setText("Nombre:");
        jPanel3.add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        txt_apellido.setBackground(new java.awt.Color(204, 204, 204));
        txt_apellido.setForeground(new java.awt.Color(0, 0, 0));
        txt_apellido.addActionListener(this::txt_apellidoActionPerformed);
        jPanel3.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 170, -1));

        txt_telefono.setBackground(new java.awt.Color(204, 204, 204));
        txt_telefono.setForeground(new java.awt.Color(0, 0, 0));
        txt_telefono.addActionListener(this::txt_telefonoActionPerformed);
        jPanel3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 170, -1));

        txt_usuario.setBackground(new java.awt.Color(204, 204, 204));
        txt_usuario.setForeground(new java.awt.Color(0, 0, 0));
        txt_usuario.addActionListener(this::txt_usuarioActionPerformed);
        jPanel3.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 170, -1));

        txt_nombre.setBackground(new java.awt.Color(204, 204, 204));
        txt_nombre.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombre.addActionListener(this::txt_nombreActionPerformed);
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 170, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(51, 204, 0));
        jButton_Actualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(0, 0, 0));
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(this::jButton_ActualizarActionPerformed);
        jPanel3.add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

        jButton_Eliminar.setBackground(new java.awt.Color(255, 51, 51));
        jButton_Eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_Eliminar.setForeground(new java.awt.Color(0, 0, 0));
        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.addActionListener(this::jButton_EliminarActionPerformed);
        jPanel3.add(jButton_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 90, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 100));
        getContentPane().add(jlabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contraseñaActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        // TODO add your handling code here:
        if (txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty()
                || txt_usuario.getText().isEmpty() || txt_contraseña.getText().isEmpty() || txt_telefono.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "¡Seleccione un Usuario!");

        } else {

            // 1. Cambiamos Cliente por Usuario
            Usuario usuario = new Usuario();
            Ctrl_Usuario controlUsuario = new Ctrl_Usuario(); // O el nombre exacto de tu controlador de usuarios

            // 2. Mapeamos los datos al objeto usuario
            usuario.setNombre(txt_nombre.getText().trim());
            usuario.setApellido(txt_apellido.getText().trim());
            usuario.setUsuario(txt_usuario.getText().trim());      // <- Asegúrate de que tu modelo tenga setUsuario o setCedula
            usuario.setPassword(txt_contraseña.getText().trim());  // <- Aquí sí va la contraseña
            usuario.setTelefono(txt_telefono.getText().trim());    // <- Y aquí el teléfono

            // 3. Enviamos el objeto usuario junto con el idUsuario
            if (controlUsuario.actualizar(usuario, idUsuario)) {
                JOptionPane.showMessageDialog(null, "¡Datos del usuario actualizados!");
                this.CargarTablaUsuarios(); // <- Recuerda cambiar este método para refrescar usuarios
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error al actualizar usuario!");
            }
        }
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
            Ctrl_Usuario controlUsuario = new Ctrl_Usuario();

            if (idUsuario == 0) {
                JOptionPane.showMessageDialog(null, "¡Seleccione un usuario de la tabla!");
            } else {
                if (controlUsuario.eliminar(idUsuario)) {
                    JOptionPane.showMessageDialog(null, "¡Usuario Eliminado!");
                    this.CargarTablaUsuarios();
                    this.Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "¡Error al eliminar usuario!");
                    this.Limpiar();
                }
            }
    }

        // El método CargarTablaUsuarios y Limpiar los pones aquí abajo, antes de la última llave:
        private void Limpiar() {
            txt_nombre.setText("");
            txt_apellido.setText("");
            txt_usuario.setText("");
            txt_contraseña.setText("");
            txt_telefono.setText("");
            idUsuario = 0;
        }
       //ver usuarios registrados
        private void CargarTablaUsuarios() {
            Connection con = Conexion.conectar();
            DefaultTableModel model = new DefaultTableModel();
            String sql = "select idUsuario, nombre, apellido, usuario, telefono, password, estado from tb_usuario";
            Statement st;
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                jTable_usuarios.setModel(model);

                model.addColumn("ID");
                model.addColumn("Nombre");
                model.addColumn("Apellido");
                model.addColumn("Usuario");
                model.addColumn("Teléfono");
                model.addColumn("Contraseña");
                model.addColumn("Estado");

                while (rs.next()) {
                    Object fila[] = new Object[7];
                    for (int i = 0; i < 7; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al llenar la tabla de usuarios: " + e);
            }

            jTable_usuarios.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int fila_point = jTable_usuarios.rowAtPoint(e.getPoint());
                    int columna_point = 0;
                    if (fila_point > -1) {
                        idUsuario = (int) model.getValueAt(fila_point, columna_point);
                        EnviarDatosUsuarioSeleccionado(idUsuario);
                    }
                }
            });
        }

        public void EnviarDatosUsuarioSeleccionado(int idUsuario) {
            try {
                Connection con = Conexion.conectar();
                PreparedStatement pst = con.prepareStatement("select * from tb_usuario where idUsuario = ?");
                pst.setInt(1, idUsuario);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    txt_nombre.setText(rs.getString("nombre"));
                    txt_apellido.setText(rs.getString("apellido"));
                    txt_usuario.setText(rs.getString("usuario"));
                    txt_contraseña.setText(rs.getString("password"));
                    txt_telefono.setText(rs.getString("telefono"));
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al seleccionar usuario: " + e);
            }
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_apellido;
    private javax.swing.JLabel jLabel_contraseña;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JLabel jLabel_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_usuarios;
    private javax.swing.JLabel jlabel_wallpaper;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_contraseña;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables



}
