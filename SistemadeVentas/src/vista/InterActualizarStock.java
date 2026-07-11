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

public class InterActualizarStock extends javax.swing.JInternalFrame {

    private int idUsuario;
    private Ctrl_Producto controlProducto;
    private Ctrl_Categoria controlCategoria;
    private DefaultTableModel modeloTabla;
    private int productoSeleccionadoId = -1;
    private int stockActual = 0;

    public InterActualizarStock() {

        initComponents();
        this.setSize(new Dimension(850, 500));
        this.setTitle("Actualizar Stock");

        //insertar imagen
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(850, 500, java.awt.Image.SCALE_DEFAULT));
        jlabel_wallpaper.setIcon(icono);
        this.repaint();

        controlProducto = new Ctrl_Producto();
        controlCategoria = new Ctrl_Categoria();
        configurarTabla();
        cargarCategorias();
        cargarProductos();
        limpiarCampos();
        this.repaint();

        jButton1_Actualizar.addActionListener(e -> actualizarStock());
        jButton3_Limpiar.addActionListener(e -> limpiarCampos());
        Buscar_Botton.addActionListener(e -> buscarProducto());
    }

    // ============ CONFIGURAR TABLA ============
    private void configurarTabla() {
        String[] columnas = {"ID", "Nombre", "Stock Actual", "Precio", "Descripción", "Categoría", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // ✅ Ninguna celda editable
            }
        };
        jTable_total.setModel(modeloTabla);

        // Ajustar ancho de columnas
        jTable_total.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable_total.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable_total.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable_total.getColumnModel().getColumn(3).setPreferredWidth(100);  // Precio
        jTable_total.getColumnModel().getColumn(4).setPreferredWidth(200);  // Descripción
        jTable_total.getColumnModel().getColumn(5).setPreferredWidth(120);  // Categoría
        jTable_total.getColumnModel().getColumn(6).setPreferredWidth(70);   // Estado
    }

    // ============ CARGAR CATEGORÍAS ============
    private void cargarCategorias() {
        // No es necesario cargar categorías aquí, pero lo dejamos por si acaso
    }

    // ============ OBTENER NOMBRE DE CATEGORÍA ============
    private String obtenerNombreCategoria(int idCategoria) {
        List<Categoria> lista = controlCategoria.listarCategorias();
        for (Categoria cat : lista) {
            if (cat.getIdCategoria() == idCategoria) {
                return cat.getDescripcion();
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
            String categoria = obtenerNombreCategoria(prod.getIdCategoria());
            String precio = String.format("$%.2f", prod.getPrecio());  // ✅ Formato moneda

            Object[] fila = {
                prod.getIdProducto(),
                prod.getNombre(),
                prod.getCantidad(),
                precio,
                prod.getDescripcion(),
                categoria,
                estado
            };
            modeloTabla.addRow(fila);
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
                String categoria = obtenerNombreCategoria(prod.getIdCategoria());
                String precio = String.format("$%.2f", prod.getPrecio());

                Object[] fila = {
                    prod.getIdProducto(),
                    prod.getNombre(),
                    prod.getCantidad(),
                    precio,
                    prod.getDescripcion(),
                    categoria,
                    estado
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    // ============ MOSTRAR PRODUCTO SELECCIONADO ============
    private void mostrarProductoSeleccionado() {
        int fila = jTable_total.getSelectedRow();
        if (fila >= 0) {
            productoSeleccionadoId = (int) modeloTabla.getValueAt(fila, 0);
            String nombre = (String) modeloTabla.getValueAt(fila, 1);
            stockActual = (int) modeloTabla.getValueAt(fila, 2);

            // ASIGNAR VALORES A LOS JLabel
            jLabel1_nombre1.setText(nombre);
            jLabel1_stockactual.setText(String.valueOf(stockActual));

            // Limpiar campo de nuevo stock
            txt_nuevostock.setText("");
            txt_nuevostock.requestFocus();
        }
    }

    // ============ ACTUALIZAR STOCK ============
    private void actualizarStock() {
        if (productoSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla");
            return;
        }

        try {
            String nuevoStockStr = txt_nuevostock.getText().trim();
            if (nuevoStockStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nuevo stock");
                return;
            }

            int nuevoStock = Integer.parseInt(nuevoStockStr);
            if (nuevoStock < 0) {
                JOptionPane.showMessageDialog(this, "El stock no puede ser negativo");
                return;
            }

            // ✅ Mostrar confirmación con nombre del producto
            String nombreProducto = jLabel1_nombre1.getText();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Actualizar stock de '" + nombreProducto + "' de " + stockActual + " a " + nuevoStock + " unidades?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (controlProducto.actualizarStock(productoSeleccionadoId, nuevoStock)) {
                    JOptionPane.showMessageDialog(this, "Stock actualizado a: " + nuevoStock + " unidades");
                    cargarProductos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el stock");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido");
        }
    }

    // ============ LIMPIAR CAMPOS ============
    private void limpiarCampos() {
        productoSeleccionadoId = -1;
        stockActual = 0;
        jLabel1_nombre1.setText("");
        jLabel1_stockactual.setText("");
        txt_nuevostock.setText("");
        JTextField_Buscar.setText("");
        cargarProductos();
        jTable_total.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_total = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel_nombre = new javax.swing.JLabel();
        jLabel_nuevostock = new javax.swing.JLabel();
        txt_nuevostock = new javax.swing.JTextField();
        jButton1_Actualizar = new javax.swing.JButton();
        jButton3_Limpiar = new javax.swing.JButton();
        jLabel1_stockactual = new javax.swing.JLabel();
        jLabel_nombre1 = new javax.swing.JLabel();
        jLabel1_nombre1 = new javax.swing.JLabel();
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 820, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nombre.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_nombre.setText("Stock Actual:");
        jPanel3.add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 90, -1));

        jLabel_nuevostock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nuevostock.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nuevostock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_nuevostock.setText("Nuevo Stock:");
        jPanel3.add(jLabel_nuevostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 90, -1));

        txt_nuevostock.setBackground(new java.awt.Color(204, 204, 204));
        txt_nuevostock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_nuevostock.setForeground(new java.awt.Color(0, 0, 0));
        txt_nuevostock.addActionListener(this::txt_nuevostockActionPerformed);
        jPanel3.add(txt_nuevostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 170, 25));

        jButton1_Actualizar.setText("Actualizar");
        jPanel3.add(jButton1_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 100, -1));

        jButton3_Limpiar.setText("Limpiar");
        jPanel3.add(jButton3_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 100, -1));

        jLabel1_stockactual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1_stockactual.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1_stockactual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_stockactual.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel1_stockactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 170, 20));

        jLabel_nombre1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_nombre1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_nombre1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_nombre1.setText("Producto:");
        jPanel3.add(jLabel_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        jLabel1_nombre1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1_nombre1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1_nombre1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel1_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, 170, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 660, 130));

        JTextField_Buscar.addActionListener(this::JTextField_BuscarActionPerformed);
        getContentPane().add(JTextField_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, -1));

        Buscar_Botton.setText("Buscar");
        Buscar_Botton.addActionListener(this::Buscar_BottonActionPerformed);
        getContentPane().add(Buscar_Botton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));
        getContentPane().add(jlabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 850, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Buscar_BottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_BottonActionPerformed
        buscarProducto();
    }//GEN-LAST:event_Buscar_BottonActionPerformed

    private void JTextField_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextField_BuscarActionPerformed
        buscarProducto();
    }//GEN-LAST:event_JTextField_BuscarActionPerformed

    private void txt_nuevostockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nuevostockActionPerformed
        actualizarStock();
    }//GEN-LAST:event_txt_nuevostockActionPerformed

    private void jTable_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_totalMouseClicked
        mostrarProductoSeleccionado();
    }//GEN-LAST:event_jTable_totalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_Botton;
    private javax.swing.JTextField JTextField_Buscar;
    private javax.swing.JButton jButton1_Actualizar;
    private javax.swing.JButton jButton3_Limpiar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1_nombre1;
    private javax.swing.JLabel jLabel1_stockactual;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_nombre1;
    private javax.swing.JLabel jLabel_nuevostock;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_total;
    private javax.swing.JLabel jlabel_wallpaper;
    private javax.swing.JTextField txt_nuevostock;
    // End of variables declaration//GEN-END:variables

}
