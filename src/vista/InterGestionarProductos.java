package vista;

import controlador.Ctrl_Producto;
import controlador.Ctrl_Categoria;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.Categoria;

public class InterGestionarProductos extends javax.swing.JInternalFrame {

    private int idUsuario;
    private Ctrl_Producto controlProducto;
    private Ctrl_Categoria controlCategoria;
    private DefaultTableModel modeloTabla;
    private int productoSeleccionadoId = -1;

    public InterGestionarProductos() {

        initComponents();
        this.setSize(new Dimension(900, 600));
        this.setTitle("Gestionar Productos");

        //insertar imagen
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 600, java.awt.Image.SCALE_DEFAULT));
        jlabel_wallpaper.setIcon(icono);
        this.repaint();

        controlProducto = new Ctrl_Producto();
        controlCategoria = new Ctrl_Categoria();
        configurarTabla();
        cargarCategorias();
        cargarProductos();
        limpiarCampos();
        this.repaint();

// Eventos para los botones
        jButton1_Actualizar.addActionListener(e -> actualizarProducto());
        jButton2_Eliminar.addActionListener(e -> eliminarProducto());
        jButton3_Limpiar.addActionListener(e -> limpiarCampos());
        Buscar_Botton.addActionListener(e -> buscarProducto());
        JTextField_Buscar.addActionListener(e -> buscarProducto());
    }
    // ============ CONFIGURAR TABLA ============

    private void configurarTabla() {
        String[] columnas = {"ID", "Nombre", "Cantidad", "Precio", "Descripción", "IVA", "Categoría", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable_total.setModel(modeloTabla);

        jTable_total.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable_total.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable_total.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable_total.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable_total.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTable_total.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable_total.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable_total.getColumnModel().getColumn(7).setPreferredWidth(60);
    }

    // ============ CARGAR CATEGORÍAS EN EL COMBO ============
    private void cargarCategorias() {
        jComboBox3_categorias.removeAllItems();
        List<Categoria> lista = controlCategoria.listarCategorias();
        if (lista != null) {
            for (Categoria cat : lista) {
                jComboBox3_categorias.addItem(cat.getDescripcion());
            }
        }
    }

    // ============ OBTENER ID DE CATEGORÍA POR NOMBRE ============
    private int obtenerIdCategoriaPorNombre(String nombre) {
        List<Categoria> lista = controlCategoria.listarCategorias();
        if (lista != null) {
            for (Categoria cat : lista) {
                if (cat.getDescripcion().equals(nombre)) {
                    return cat.getIdCategoria();
                }
            }
        }
        return 0;
    }

    // ============ OBTENER NOMBRE DE CATEGORÍA POR ID ============
    private String obtenerNombreCategoriaPorId(int id) {
        List<Categoria> lista = controlCategoria.listarCategorias();
        if (lista != null) {
            for (Categoria cat : lista) {
                if (cat.getIdCategoria() == id) {
                    return cat.getDescripcion();
                }
            }
        }
        return "Sin categoría";
    }

    // ============ CARGAR PRODUCTOS EN LA TABLA ============
    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        List<Producto> lista = controlProducto.listarProductos();

        for (Producto prod : lista) {
            String estado = prod.getEstado() == 1 ? "Activo" : "Inactivo";
            String iva = prod.getPorcentajeIVA() + "%";
            String categoria = obtenerNombreCategoriaPorId(prod.getIdCategoria());

            Object[] fila = {
                prod.getIdProducto(),
                prod.getNombre(),
                prod.getCantidad(),
                prod.getPrecio(),
                prod.getDescripcion(),
                iva,
                categoria,
                estado
            };
            modeloTabla.addRow(fila);
        }
    }

    // ============ MOSTRAR PRODUCTO SELECCIONADO ============
    private void mostrarProductoSeleccionado() {
        int fila = jTable_total.getSelectedRow();
        if (fila >= 0) {
            productoSeleccionadoId = (int) modeloTabla.getValueAt(fila, 0);
            txt_nombre.setText((String) modeloTabla.getValueAt(fila, 1));
            txt_cantidad.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
            txt_precio.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
            txt_descripcion.setText((String) modeloTabla.getValueAt(fila, 4));

            String iva = (String) modeloTabla.getValueAt(fila, 5);
            jComboBox1_iva.setSelectedItem(iva);

            String categoria = (String) modeloTabla.getValueAt(fila, 6);
            jComboBox3_categorias.setSelectedItem(categoria);

            String estado = (String) modeloTabla.getValueAt(fila, 7);
            jComboBox2_Estado.setSelectedItem(estado);
        }
    }

    // ============ BUSCAR PRODUCTO ============
    private void buscarProducto() {
        String busqueda = JTextField_Buscar.getText().trim().toLowerCase();
        if (busqueda.isEmpty()) {
            cargarProductos();
            return;
        }

        modeloTabla.setRowCount(0);
        List<Producto> lista = controlProducto.listarProductos();

        for (Producto prod : lista) {
            if (prod.getNombre().toLowerCase().contains(busqueda)) {
                String estado = prod.getEstado() == 1 ? "Activo" : "Inactivo";
                String iva = prod.getPorcentajeIVA() + "%";
                String categoria = obtenerNombreCategoriaPorId(prod.getIdCategoria());

                Object[] fila = {
                    prod.getIdProducto(),
                    prod.getNombre(),
                    prod.getCantidad(),
                    prod.getPrecio(),
                    prod.getDescripcion(),
                    iva,
                    categoria,
                    estado
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    // ============ ACTUALIZAR PRODUCTO ============
    private void actualizarProducto() {
        if (productoSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
            return;
        }

        if (txt_nombre.getText().isEmpty() || txt_cantidad.getText().isEmpty() || txt_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, Cantidad y Precio son obligatorios");
            return;
        }

        try {
            Producto producto = new Producto();
            producto.setIdProducto(productoSeleccionadoId);
            producto.setNombre(txt_nombre.getText().trim());
            producto.setCantidad(Integer.parseInt(txt_cantidad.getText().trim()));
            producto.setPrecio(Double.parseDouble(txt_precio.getText().trim()));
            producto.setDescripcion(txt_descripcion.getText().trim());

            String ivaSeleccionado = (String) jComboBox1_iva.getSelectedItem();
            int iva = 0;
            if (ivaSeleccionado.equals("12%")) {
                iva = 12;
            } else if (ivaSeleccionado.equals("14%")) {
                iva = 14;
            }
            producto.setPorcentajeIVA(iva);

            String categoriaSeleccionada = (String) jComboBox3_categorias.getSelectedItem();
            producto.setIdCategoria(obtenerIdCategoriaPorNombre(categoriaSeleccionada));

            String estadoSeleccionado = (String) jComboBox2_Estado.getSelectedItem();
            int estado = estadoSeleccionado.equals("Activo") ? 1 : 0;
            producto.setEstado(estado);

            if (controlProducto.actualizar(producto)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado");
                cargarProductos();
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad y Precio deben ser números válidos");
        }
    }

// ============ ELIMINAR PRODUCTO ============
    private void eliminarProducto() {
        if (productoSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de ELIMINAR este producto?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controlProducto.eliminarFisicamente(productoSeleccionadoId)) {
                JOptionPane.showMessageDialog(this, "Eliminado");
                cargarProductos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    // ============ LIMPIAR CAMPOS ============
    private void limpiarCampos() {
        txt_nombre.setText("");
        txt_cantidad.setText("");
        txt_precio.setText("");
        txt_descripcion.setText("");
        jComboBox1_iva.setSelectedIndex(0);
        jComboBox3_categorias.setSelectedIndex(0);
        jComboBox2_Estado.setSelectedIndex(0);
        productoSeleccionadoId = -1;
        JTextField_Buscar.setText("");
        cargarProductos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_total = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel_apellido = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel_contraseña = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_usuario = new javax.swing.JLabel();
        jLabel_nombre = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        jLabel_usuario1 = new javax.swing.JLabel();
        jLabel_usuario2 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jButton1_Actualizar = new javax.swing.JButton();
        jButton2_Eliminar = new javax.swing.JButton();
        jButton3_Limpiar = new javax.swing.JButton();
        jComboBox1_iva = new javax.swing.JComboBox<>();
        jComboBox2_Estado = new javax.swing.JComboBox<>();
        jComboBox3_categorias = new javax.swing.JComboBox<>();
        JTextField_Buscar = new javax.swing.JTextField();
        Buscar_Botton = new javax.swing.JButton();
        jlabel_wallpaper = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable_total.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_totalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_total);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 870, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_apellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_apellido.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_apellido.setText("IVA:");
        jPanel3.add(jLabel_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 90, -1));

        txt_precio.setBackground(new java.awt.Color(204, 204, 204));
        txt_precio.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio.addActionListener(this::txt_precioActionPerformed);
        jPanel3.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 170, -1));

        jLabel_contraseña.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_contraseña.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_contraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_contraseña.setText("Precio:");
        jPanel3.add(jLabel_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel_telefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_telefono.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_telefono.setText("Estado:");
        jPanel3.add(jLabel_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 90, -1));

        jLabel_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_usuario.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_usuario.setText("Descripcion:");
        jPanel3.add(jLabel_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 90, -1));

        jLabel_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nombre.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_nombre.setText("Nombre:");
        jPanel3.add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        txt_descripcion.setBackground(new java.awt.Color(204, 204, 204));
        txt_descripcion.setForeground(new java.awt.Color(0, 0, 0));
        txt_descripcion.addActionListener(this::txt_descripcionActionPerformed);
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 170, -1));

        txt_nombre.setBackground(new java.awt.Color(204, 204, 204));
        txt_nombre.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombre.addActionListener(this::txt_nombreActionPerformed);
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 170, -1));

        jLabel_usuario1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_usuario1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_usuario1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_usuario1.setText("Categoria:");
        jPanel3.add(jLabel_usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 90, -1));

        jLabel_usuario2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_usuario2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_usuario2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_usuario2.setText("Cantidad:");
        jPanel3.add(jLabel_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 90, -1));

        txt_cantidad.setBackground(new java.awt.Color(204, 204, 204));
        txt_cantidad.setForeground(new java.awt.Color(0, 0, 0));
        txt_cantidad.addActionListener(this::txt_cantidadActionPerformed);
        jPanel3.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 170, -1));

        jButton1_Actualizar.setText("Actualizar");
        jPanel3.add(jButton1_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 10, 100, -1));

        jButton2_Eliminar.setText("Eliminar");
        jPanel3.add(jButton2_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 50, 100, -1));

        jButton3_Limpiar.setText("Limpiar");
        jPanel3.add(jButton3_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 90, 100, -1));

        jComboBox1_iva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Grava IVA", "12%", "14%" }));
        jPanel3.add(jComboBox1_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 170, -1));

        jComboBox2_Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jPanel3.add(jComboBox2_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 170, -1));

        jPanel3.add(jComboBox3_categorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 160));

        JTextField_Buscar.addActionListener(this::JTextField_BuscarActionPerformed);
        getContentPane().add(JTextField_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, -1));

        Buscar_Botton.setText("Buscar");
        Buscar_Botton.addActionListener(this::Buscar_BottonActionPerformed);
        getContentPane().add(Buscar_Botton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));
        getContentPane().add(jlabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 890, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        txt_descripcion.requestFocus();
    }//GEN-LAST:event_txt_precioActionPerformed

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        jComboBox1_iva.requestFocus();
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        txt_cantidad.requestFocus();
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void Buscar_BottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_BottonActionPerformed
        buscarProducto();
    }//GEN-LAST:event_Buscar_BottonActionPerformed

    private void JTextField_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextField_BuscarActionPerformed
        buscarProducto();
    }//GEN-LAST:event_JTextField_BuscarActionPerformed

    private void txt_cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantidadActionPerformed
        txt_precio.requestFocus();
    }//GEN-LAST:event_txt_cantidadActionPerformed

    private void jTable_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_totalMouseClicked
        mostrarProductoSeleccionado();
        System.out.println("🖱️ Click en la tabla");
    }//GEN-LAST:event_jTable_totalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_Botton;
    private javax.swing.JTextField JTextField_Buscar;
    private javax.swing.JButton jButton1_Actualizar;
    private javax.swing.JButton jButton2_Eliminar;
    private javax.swing.JButton jButton3_Limpiar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1_iva;
    private javax.swing.JComboBox<String> jComboBox2_Estado;
    private javax.swing.JComboBox<String> jComboBox3_categorias;
    private javax.swing.JLabel jLabel_apellido;
    private javax.swing.JLabel jLabel_contraseña;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JLabel jLabel_usuario;
    private javax.swing.JLabel jLabel_usuario1;
    private javax.swing.JLabel jLabel_usuario2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_total;
    private javax.swing.JLabel jlabel_wallpaper;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables

}
