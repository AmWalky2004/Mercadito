package vista;

import com.sun.tools.javac.Main;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class FrmMenu extends javax.swing.JFrame {

    public static JDesktopPane jDesktopPane_menu;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmMenu.class.getName());

    public FrmMenu() {
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
        jMenu1 = new javax.swing.JMenu();
        nuevo_usuario = new javax.swing.JMenuItem();
        gestionar_usuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        nuevo_producto = new javax.swing.JMenuItem();
        gestionar_producto = new javax.swing.JMenuItem();
        actualizar_stock = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        nueva_categoria = new javax.swing.JMenuItem();
        gestionar_categoria = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        nuevo_cliente = new javax.swing.JMenuItem();
        gestionar_cliente = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        nueva_venta = new javax.swing.JMenuItem();
        gestionar_venta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        reportes_clientes = new javax.swing.JMenuItem();
        reportes_categorias = new javax.swing.JMenuItem();
        reportes_productos = new javax.swing.JMenuItem();
        reportes_ventas = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        ver_historial = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        setIconImage(getIconImage());

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        jMenu1.setText("Usuario");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(171, 50));

        nuevo_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-cliente.png"))); // NOI18N
        nuevo_usuario.setText("Nuevo Usuario");
        nuevo_usuario.setPreferredSize(new java.awt.Dimension(180, 30));
        nuevo_usuario.addActionListener(this::nuevo_usuarioActionPerformed);
        jMenu1.add(nuevo_usuario);

        gestionar_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/configuraciones.png"))); // NOI18N
        gestionar_usuario.setText("Gestionar Usuario");
        gestionar_usuario.setPreferredSize(new java.awt.Dimension(180, 30));
        gestionar_usuario.addActionListener(this::gestionar_usuarioActionPerformed);
        jMenu1.add(gestionar_usuario);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        jMenu2.setText("Producto");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(171, 50));

        nuevo_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-producto.png"))); // NOI18N
        nuevo_producto.setText("Nuevo Producto");
        nuevo_producto.setPreferredSize(new java.awt.Dimension(180, 30));
        nuevo_producto.addActionListener(this::nuevo_productoActionPerformed);
        jMenu2.add(nuevo_producto);

        gestionar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        gestionar_producto.setText("Gestionar Producto");
        gestionar_producto.setPreferredSize(new java.awt.Dimension(180, 30));
        gestionar_producto.addActionListener(this::gestionar_productoActionPerformed);
        jMenu2.add(gestionar_producto);

        actualizar_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        actualizar_stock.setText("Actualizar Stock");
        actualizar_stock.setPreferredSize(new java.awt.Dimension(180, 30));
        actualizar_stock.addActionListener(this::actualizar_stockActionPerformed);
        jMenu2.add(actualizar_stock);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        jMenu4.setText("Categorias");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu4.setMinimumSize(new java.awt.Dimension(171, 50));
        jMenu4.setPreferredSize(new java.awt.Dimension(171, 50));

        nueva_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        nueva_categoria.setText("Nueva Categoria");
        nueva_categoria.setPreferredSize(new java.awt.Dimension(180, 30));
        nueva_categoria.addActionListener(this::nueva_categoriaActionPerformed);
        jMenu4.add(nueva_categoria);

        gestionar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        gestionar_categoria.setText("Gestionar Categoria");
        gestionar_categoria.setPreferredSize(new java.awt.Dimension(180, 30));
        gestionar_categoria.addActionListener(this::gestionar_categoriaActionPerformed);
        jMenu4.add(gestionar_categoria);

        jMenuBar1.add(jMenu4);

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
        jMenu3.add(gestionar_cliente);

        jMenuBar1.add(jMenu3);

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

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes.png"))); // NOI18N
        jMenu6.setText("Reportes");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu6.setMinimumSize(new java.awt.Dimension(171, 50));
        jMenu6.setPreferredSize(new java.awt.Dimension(171, 50));

        reportes_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reporte1.png"))); // NOI18N
        reportes_clientes.setText("Reportes Clientes");
        reportes_clientes.setPreferredSize(new java.awt.Dimension(180, 30));
        jMenu6.add(reportes_clientes);

        reportes_categorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reporte1.png"))); // NOI18N
        reportes_categorias.setText("Reportes Categorias");
        reportes_categorias.setPreferredSize(new java.awt.Dimension(180, 30));
        reportes_categorias.addActionListener(this::reportes_categoriasActionPerformed);
        jMenu6.add(reportes_categorias);

        reportes_productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reporte1.png"))); // NOI18N
        reportes_productos.setText("Reportes Productos");
        reportes_productos.setPreferredSize(new java.awt.Dimension(180, 30));
        jMenu6.add(reportes_productos);

        reportes_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reporte1.png"))); // NOI18N
        reportes_ventas.setText("Reportes Ventas");
        reportes_ventas.setPreferredSize(new java.awt.Dimension(180, 30));
        reportes_ventas.addActionListener(this::reportes_ventasActionPerformed);
        jMenu6.add(reportes_ventas);

        jMenuBar1.add(jMenu6);

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
            .addGap(0, 649, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevo_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_usuarioActionPerformed
        // TODO add your handling code here:
        InterNuevoUsuario interNuevoUsuario = new InterNuevoUsuario();
        jDesktopPane_menu.add(interNuevoUsuario);
        interNuevoUsuario.setVisible(true);
    }//GEN-LAST:event_nuevo_usuarioActionPerformed

    private void gestionar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_usuarioActionPerformed
        InterGestionarUsuario interGestionarUsuario = new InterGestionarUsuario();
        jDesktopPane_menu.add(interGestionarUsuario);
        interGestionarUsuario.setVisible(true);
    }//GEN-LAST:event_gestionar_usuarioActionPerformed

    private void actualizar_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_stockActionPerformed
        InterActualizarStock interActualizarStock = new InterActualizarStock();
        jDesktopPane_menu.add(interActualizarStock);
        interActualizarStock.setVisible(true);
    }//GEN-LAST:event_actualizar_stockActionPerformed

    private void gestionar_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_ventaActionPerformed
        Intergestionar_venta interGestionarVenta = new Intergestionar_venta();
        jDesktopPane_menu.add(interGestionarVenta);
        interGestionarVenta.setVisible(true);
        interGestionarVenta.toFront();
    }//GEN-LAST:event_gestionar_ventaActionPerformed

    private void reportes_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportes_categoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportes_categoriasActionPerformed

    private void reportes_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportes_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportes_ventasActionPerformed

    private void ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_historialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ver_historialActionPerformed

    private void nueva_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueva_categoriaActionPerformed
        InterCategoria interCategoria = new InterCategoria();
        jDesktopPane_menu.add(interCategoria);
        interCategoria.setVisible(true);
    }//GEN-LAST:event_nueva_categoriaActionPerformed

    private void gestionar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_categoriaActionPerformed
        // TODO add your handling code here:
        InterGestionarCategorias interGestionarCategorias = new InterGestionarCategorias();
        jDesktopPane_menu.add(interGestionarCategorias);
        interGestionarCategorias.setVisible(true);

    }//GEN-LAST:event_gestionar_categoriaActionPerformed

    private void nuevo_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_productoActionPerformed
        // TODO add your handling code here:
        InterProducto interProducto = new InterProducto();
        jDesktopPane_menu.add(interProducto);
        interProducto.setVisible(true);
    }//GEN-LAST:event_nuevo_productoActionPerformed

    private void gestionar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_productoActionPerformed
        InterGestionarProductos interGestionarProductos = new InterGestionarProductos();
        jDesktopPane_menu.add(interGestionarProductos);
        interGestionarProductos.setVisible(true);
    }//GEN-LAST:event_gestionar_productoActionPerformed

    private void nueva_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueva_ventaActionPerformed
        Internueva_venta interNuevaVenta = new Internueva_venta();
        jDesktopPane_menu.add(interNuevaVenta);
        interNuevaVenta.setVisible(true);
        interNuevaVenta.toFront();
    }//GEN-LAST:event_nueva_ventaActionPerformed

    private void nuevo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_clienteActionPerformed
        InterCliente intercliente = new InterCliente();
        jDesktopPane_menu.add(intercliente);
        intercliente.setVisible(true);
    }//GEN-LAST:event_nuevo_clienteActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem actualizar_stock;
    private javax.swing.JMenuItem gestionar_categoria;
    private javax.swing.JMenuItem gestionar_cliente;
    private javax.swing.JMenuItem gestionar_producto;
    private javax.swing.JMenuItem gestionar_usuario;
    private javax.swing.JMenuItem gestionar_venta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem nueva_categoria;
    private javax.swing.JMenuItem nueva_venta;
    private javax.swing.JMenuItem nuevo_cliente;
    private javax.swing.JMenuItem nuevo_producto;
    private javax.swing.JMenuItem nuevo_usuario;
    private javax.swing.JMenuItem reportes_categorias;
    private javax.swing.JMenuItem reportes_clientes;
    private javax.swing.JMenuItem reportes_productos;
    private javax.swing.JMenuItem reportes_ventas;
    private javax.swing.JMenuItem ver_historial;
    // End of variables declaration//GEN-END:variables
}
