package vista;

import controlador.Ctrl_Cliente;
import controlador.Ctrl_Producto;
import controlador.Ctrl_Venta;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.DetalleVenta;

public class Internueva_venta extends JInternalFrame {

    // Componentes para Cliente
    private JTextField txtCedula, txtNombreCliente, txtTelefonoCliente;
    private JButton btnBuscarCliente;

    // Componentes para Producto
    private JTextField txtCodigoProd, txtNombreProd, txtPrecioProd, txtStockProd;
    private JSpinner spinCantidad;
    private JButton btnBuscarProd, btnAgregarProd;

    // Tabla de Detalle
    private JTable tablaDetalle;
    private DefaultTableModel modeloTabla;
    private JButton btnQuitarProd;

    // Totales de Facturación
    private JTextField txtSubtotal, txtIva, txtTotal;
    private JButton btnRegistrarVenta, btnCancelarVenta;

    // Controladores
    //private Ctrl_Cliente controlCliente;
    private Ctrl_Producto controlProducto;
    private Ctrl_Venta controlVenta;

    // Variables de selección
    private int idClienteSeleccionado = 0;
    private int idProductoSeleccionado = 0;

    // Constante para el IVA
    private static final double PORCENTAJE_IVA = 0.16;

    public Internueva_venta() {
        super("Nueva Venta - Facturación", true, true, true, true);
        
        // Inicializar controladores
       // controlCliente = new Ctrl_Cliente();
        controlProducto = new Ctrl_Producto();
        controlVenta = new Ctrl_Venta();
        
        initComponents();
        setSize(1000, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Contenedor principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // ==========================================
        // 1. PANEL SUPERIOR: CLIENTE Y PRODUCTO
        // ==========================================
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 15, 0));
        panelSuperior.setOpaque(false);

        // --- Subpanel Cliente ---
        JPanel panelCliente = new JPanel(new GridBagLayout());
        panelCliente.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Datos del Cliente",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(0, 80, 136)));
        panelCliente.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCliente.add(new JLabel("Cédula/RIF:"), gbc);
        txtCedula = new JTextField(10);
        gbc.gridx = 1;
        panelCliente.add(txtCedula, gbc);
        btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setBackground(new Color(0, 80, 136));
        btnBuscarCliente.setForeground(Color.WHITE);
        gbc.gridx = 2;
        panelCliente.add(btnBuscarCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCliente.add(new JLabel("Nombre:"), gbc);
        txtNombreCliente = new JTextField();
        txtNombreCliente.setEditable(false);
        txtNombreCliente.setBackground(new Color(240, 240, 240));
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelCliente.add(txtNombreCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelCliente.add(new JLabel("Teléfono:"), gbc);
        txtTelefonoCliente = new JTextField();
        txtTelefonoCliente.setEditable(false);
        txtTelefonoCliente.setBackground(new Color(240, 240, 240));
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelCliente.add(txtTelefonoCliente, gbc);

        // --- Subpanel Producto ---
        JPanel panelProducto = new JPanel(new GridBagLayout());
        panelProducto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Agregar Producto",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(0, 80, 136)));
        panelProducto.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelProducto.add(new JLabel("Código:"), gbc);
        txtCodigoProd = new JTextField(10);
        gbc.gridx = 1;
        panelProducto.add(txtCodigoProd, gbc);
        btnBuscarProd = new JButton("Buscar");
        btnBuscarProd.setBackground(new Color(0, 80, 136));
        btnBuscarProd.setForeground(Color.WHITE);
        gbc.gridx = 2;
        panelProducto.add(btnBuscarProd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelProducto.add(new JLabel("Descripción:"), gbc);
        txtNombreProd = new JTextField();
        txtNombreProd.setEditable(false);
        txtNombreProd.setBackground(new Color(240, 240, 240));
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelProducto.add(txtNombreProd, gbc);

        JPanel panelCalculo = new JPanel(new GridLayout(1, 3, 5, 0));
        panelCalculo.setOpaque(false);
        panelCalculo.add(new JLabel("Precio ($)"));
        panelCalculo.add(new JLabel("Stock"));
        panelCalculo.add(new JLabel("Cant."));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panelProducto.add(panelCalculo, gbc);

        JPanel panelDatos = new JPanel(new GridLayout(1, 3, 5, 0));
        panelDatos.setOpaque(false);
        txtPrecioProd = new JTextField();
        txtPrecioProd.setEditable(false);
        txtStockProd = new JTextField();
        txtStockProd.setEditable(false);
        spinCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        panelDatos.add(txtPrecioProd);
        panelDatos.add(txtStockProd);
        panelDatos.add(spinCantidad);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelProducto.add(panelDatos, gbc);

        btnAgregarProd = new JButton("Agregar a Factura");
        btnAgregarProd.setBackground(new Color(34, 139, 34));
        btnAgregarProd.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelProducto.add(btnAgregarProd, gbc);

        panelSuperior.add(panelCliente);
        panelSuperior.add(panelProducto);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL CENTRAL: TABLA
        // ==========================================
        String[] columnas = {"Código", "Descripción", "Cantidad", "Precio Unitario ($)", "Subtotal ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDetalle = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaDetalle);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Detalle de la Venta"));
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // ==========================================
        // 3. PANEL INFERIOR: TOTALES Y ACCIONES
        // ==========================================
        JPanel panelInferior = new JPanel(new BorderLayout(15, 10));
        panelInferior.setOpaque(false);

        JPanel pAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBackground(new Color(0, 128, 128));
        btnRegistrarVenta.setForeground(Color.WHITE);
        btnCancelarVenta = new JButton("Cancelar");
        btnCancelarVenta.addActionListener(e -> dispose());

        // Botón Quitar Producto
        btnQuitarProd = new JButton("Quitar Producto");
        btnQuitarProd.setBackground(new Color(200, 0, 0));
        btnQuitarProd.setForeground(Color.WHITE);
        pAcciones.add(btnQuitarProd);
        pAcciones.add(btnRegistrarVenta);
        pAcciones.add(btnCancelarVenta);

        JPanel pTotales = new JPanel(new GridLayout(3, 2, 5, 5));
        pTotales.add(new JLabel("Subtotal ($):"));
        txtSubtotal = new JTextField("0.00", 8);
        txtSubtotal.setEditable(false);
        pTotales.add(txtSubtotal);
        pTotales.add(new JLabel("IVA (16%):"));
        txtIva = new JTextField("0.00", 8);
        txtIva.setEditable(false);
        pTotales.add(txtIva);
        pTotales.add(new JLabel("TOTAL ($):"));
        txtTotal = new JTextField("0.00", 8);
        txtTotal.setEditable(false);
        pTotales.add(txtTotal);

        panelInferior.add(pAcciones, BorderLayout.WEST);
        panelInferior.add(pTotales, BorderLayout.EAST);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        this.setContentPane(panelPrincipal);

        // ==========================================
        // EVENTOS
        // ==========================================

        // 1. Buscar Cliente
        btnBuscarCliente.addActionListener(e -> buscarCliente());

        // 2. Buscar Producto
        btnBuscarProd.addActionListener(e -> buscarProducto());

        // 3. Agregar Producto a la Factura
        btnAgregarProd.addActionListener(e -> agregarProducto());

        // 4. Quitar Producto de la Factura
        btnQuitarProd.addActionListener(e -> quitarProducto());

        // 5. Registrar Venta
        btnRegistrarVenta.addActionListener(e -> registrarVenta());

        // 6. Presionar Enter en campos de búsqueda
        txtCedula.addActionListener(e -> buscarCliente());
        txtCodigoProd.addActionListener(e -> buscarProducto());
    }

    // ==========================================
    // MÉTODOS FUNCIONALES
    // ==========================================

    private void buscarCliente() {
        String cedula = txtCedula.getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cédula/RIF", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = controlCliente.buscarPorCedula(cedula);
        if (cliente != null) {
            txtNombreCliente.setText(cliente.getNombre());
            txtTelefonoCliente.setText(cliente.getTelefono());
            idClienteSeleccionado = cliente.getIdCliente();
            JOptionPane.showMessageDialog(this, "✅ Cliente encontrado: " + cliente.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarProducto() {
        String codigo = txtCodigoProd.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un código de producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto producto = controlProducto.buscarPorCodigo(codigo);
        if (producto != null) {
            txtNombreProd.setText(producto.getNombre());
            txtPrecioProd.setText(String.format("%.2f", producto.getPrecio()));
            txtStockProd.setText(String.valueOf(producto.getCantidad()));
            idProductoSeleccionado = producto.getIdProducto();
            JOptionPane.showMessageDialog(this, "✅ Producto encontrado: " + producto.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarProducto() {
        if (txtNombreProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero busque un producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(txtPrecioProd.getText());
            int stock = Integer.parseInt(txtStockProd.getText());
            int cantidad = (int) spinCantidad.getValue();

            // Validar stock
            if (cantidad > stock) {
                JOptionPane.showMessageDialog(this, "⚠️ Stock insuficiente. Disponible: " + stock,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que no se duplique el producto
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                if (modeloTabla.getValueAt(i, 0).equals(txtCodigoProd.getText())) {
                    JOptionPane.showMessageDialog(this, "⚠️ Producto ya agregado. Modifique la cantidad.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            double subtotal = precio * cantidad;
            modeloTabla.addRow(new Object[]{
                txtCodigoProd.getText(),
                txtNombreProd.getText(),
                cantidad,
                String.format("%.2f", precio),
                String.format("%.2f", subtotal)
            });

            calcularTotales();
            limpiarCamposProducto();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos del producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitarProducto() {
        int fila = tablaDetalle.getSelectedRow();
        if (fila >= 0) {
            modeloTabla.removeRow(fila);
            calcularTotales();
            JOptionPane.showMessageDialog(this, "Producto eliminado de la factura", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void registrarVenta() {
        // Verificar que haya productos
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Agregue al menos un producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar que haya cliente
        if (idClienteSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Registrar venta por $" + txtTotal.getText() + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Crear objeto Venta
                Venta venta = new Venta();
                venta.setIdCliente(idClienteSeleccionado);
                venta.setIdUsuario(1); // TODO: Obtener del login
                venta.setValorpagar(Double.parseDouble(txtTotal.getText()));
                venta.setEstado(1);

                // Crear lista de detalles
                List<DetalleVenta> detalles = new ArrayList<>();
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    DetalleVenta detalle = new DetalleVenta();
                    // TODO: Obtener ID del producto desde la tabla (necesitas agregar columna ID)
                    detalle.setIdProducto(1); // Temporal
                    detalle.setCantidad((int) modeloTabla.getValueAt(i, 2));
                    detalle.setPreciounitario(Double.parseDouble((String) modeloTabla.getValueAt(i, 3)));
                    detalle.setSubtotal(Double.parseDouble((String) modeloTabla.getValueAt(i, 4)));
                    detalle.setDescuento(0.0);
                    detalle.setIva(Double.parseDouble(txtIva.getText()));
                    detalle.setTotalpagar(Double.parseDouble(txtTotal.getText()));
                    detalle.setEstado(1);
                    detalles.add(detalle);
                }

                // Guardar venta
                int idVenta = controlVenta.guardar(venta, detalles);

                if (idVenta > 0) {
                    JOptionPane.showMessageDialog(this, "✅ Venta registrada exitosamente. ID: " + idVenta,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTodo();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al registrar la venta",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al procesar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calcularTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += Double.parseDouble((String) modeloTabla.getValueAt(i, 4));
        }
        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtIva.setText(String.format("%.2f", subtotal * PORCENTAJE_IVA));
        txtTotal.setText(String.format("%.2f", subtotal * (1 + PORCENTAJE_IVA)));
    }

    private void limpiarCamposProducto() {
        txtCodigoProd.setText("");
        txtNombreProd.setText("");
        txtPrecioProd.setText("");
        txtStockProd.setText("");
        spinCantidad.setValue(1);
        idProductoSeleccionado = 0;
        txtCodigoProd.requestFocus();
    }

    private void limpiarTodo() {
        txtCedula.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        idClienteSeleccionado = 0;
        modeloTabla.setRowCount(0);
        limpiarCamposProducto();
        txtSubtotal.setText("0.00");
        txtIva.setText("0.00");
        txtTotal.setText("0.00");
    }
}