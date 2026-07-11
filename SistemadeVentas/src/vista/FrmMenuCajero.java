package vista;

import com.sun.tools.javac.Main;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import reportes.Reportes;
import static vista.FrmMenu.jDesktopPane_menu;

public class FrmMenuCajero extends javax.swing.JFrame {

    public static JDesktopPane jDesktopPane_menu;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmMenuCajero.class.getName());

    public FrmMenuCajero() {
        initComponents();
        this.setSize(new Dimension(1200, 700)); // TAMAÑO DEL PANEL
        this.setExtendedState(this.MAXIMIZED_BOTH); //MAXIMIZAR LA VENTANA
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema de Ventas - MENU PRINCIPAL");
        this.setLayout(null);
        jDesktopPane_menu = new JDesktopPane();

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        this.jDesktopPane_menu.setBounds(0, 0, ancho, (alto - 110));
        this.add(jDesktopPane_menu);
        this.repaint();

    }

    public java.awt.Font usarMontserrat(int estilo, float tamano) {
        try {
            java.io.InputStream is = getClass().getResourceAsStream("/fuentetxt/Montserrat-Medium.ttf");
            java.awt.Font base = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
            return base.deriveFont(estilo, tamano);
        } catch (Exception e) {
            // Si falla en otra laptop, te devuelve la fuente por defecto del sistema para que no explote
            return new java.awt.Font("Arial", estilo, (int) tamano);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        nueva_venta = new javax.swing.JMenuItem();
        gestionar_venta = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        nuevo_cliente = new javax.swing.JMenuItem();
        gestionar_cliente = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        ver_historial = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        setIconImage(getIconImage());

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrito.png"))); // NOI18N
        jMenu5.setText("Facturar");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu5.setMinimumSize(new java.awt.Dimension(171, 50));
        jMenu5.setPreferredSize(new java.awt.Dimension(171, 50));

        nueva_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
        nueva_venta.setText("Nueva Venta");
        nueva_venta.setPreferredSize(new java.awt.Dimension(180, 30));
        nueva_venta.addActionListener(this::nueva_ventaActionPerformed);
        jMenu5.add(nueva_venta);

        gestionar_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/configuraciones.png"))); // NOI18N
        gestionar_venta.setText("Gestionar Venta");
        gestionar_venta.setPreferredSize(new java.awt.Dimension(180, 30));
        gestionar_venta.addActionListener(this::gestionar_ventaActionPerformed);
        jMenu5.add(gestionar_venta);

        jMenuBar1.add(jMenu5);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        jMenu3.setText("Cliente");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(171, 50));

        nuevo_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-cliente.png"))); // NOI18N
        nuevo_cliente.setText("Nuevo Cliente");
        nuevo_cliente.setPreferredSize(new java.awt.Dimension(180, 30));
        nuevo_cliente.addActionListener(this::nuevo_clienteActionPerformed);
        jMenu3.add(nuevo_cliente);

        gestionar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        gestionar_cliente.setText("Gestionar Cliente");
        gestionar_cliente.setPreferredSize(new java.awt.Dimension(180, 30));
        gestionar_cliente.addActionListener(this::gestionar_clienteActionPerformed);
        jMenu3.add(gestionar_cliente);

        jMenuBar1.add(jMenu3);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/historial1.png"))); // NOI18N
        jMenu7.setText("Historial");
        jMenu7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu7.setHideActionText(true);
        jMenu7.setPreferredSize(new java.awt.Dimension(171, 50));

        ver_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/historial1.png"))); // NOI18N
        ver_historial.setText("Ver Historial");
        ver_historial.setPreferredSize(new java.awt.Dimension(180, 30));
        ver_historial.addActionListener(this::ver_historialActionPerformed);
        jMenu7.add(ver_historial);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gestionar_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_ventaActionPerformed
        Intergestionar_venta interGestionarVenta = new Intergestionar_venta();
        jDesktopPane_menu.add(interGestionarVenta);
        interGestionarVenta.setVisible(true);
        interGestionarVenta.toFront();
    }//GEN-LAST:event_gestionar_ventaActionPerformed

    private void nueva_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueva_ventaActionPerformed
        Internueva_venta interNuevaVenta = new Internueva_venta();
        jDesktopPane_menu.add(interNuevaVenta);
        interNuevaVenta.setVisible(true);
        interNuevaVenta.toFront();
    }//GEN-LAST:event_nueva_ventaActionPerformed

    private void ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_historialActionPerformed
      
//Creamos la instancia de la vista
InterHistorial ventanaHistorial = new InterHistorial();

// Agregamos la ventana al contenedor de tu menú
jDesktopPane_menu.add(ventanaHistorial);

// declaramos las dimensiones correctas antes de mostrarla
ventanaHistorial.setSize(600, 400); 

// La hacemos visible, la traemos al frente y la seleccionamos
ventanaHistorial.setVisible(true);
ventanaHistorial.toFront();

try {
    ventanaHistorial.setSelected(true); 
} catch (java.beans.PropertyVetoException e) {
    System.out.println("Error al enfocar la ventana: " + e.getMessage());
}
    }//GEN-LAST:event_ver_historialActionPerformed

    private void nuevo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_clienteActionPerformed
        InterCliente intercliente = new InterCliente();
        jDesktopPane_menu.add(intercliente);
        intercliente.setVisible(true);
    }//GEN-LAST:event_nuevo_clienteActionPerformed

    private void gestionar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_clienteActionPerformed
        InterGestionarCliente gestion = new InterGestionarCliente();
    jDesktopPane_menu.add(gestion);
    gestion.setVisible(true);
    }//GEN-LAST:event_gestionar_clienteActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmMenuCajero().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem gestionar_cliente;
    private javax.swing.JMenuItem gestionar_venta;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem nueva_venta;
    private javax.swing.JMenuItem nuevo_cliente;
    private javax.swing.JMenuItem ver_historial;
    // End of variables declaration//GEN-END:variables
}
