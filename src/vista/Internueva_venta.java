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
import util.AutoCompletadoCB;
import modelo.DetalleVenta;

public class Internueva_venta extends JInternalFrame {

    // Componentes para Cliente
    private JTextField txtCedula, txtNombreCliente, txtTelefonoCliente;
    private JButton btnBuscarCliente;

    // Componentes para Producto
    private JComboBox<String> cmbProducto;
    private JTextField txtNombreProd, txtPrecioProd, txtStockProd;
    private AutoCompletadoCB autoCompletadoProducto;
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
    private Ctrl_Cliente controlCliente;
    private Ctrl_Producto controlProducto;
    private Ctrl_Venta controlVenta;

    // Variables de selección
    private int idClienteSeleccionado = 0;
    private int idProductoSeleccionado = 0;
    private String nombreProductoSeleccionado = "";
    private String descripcionProductoSeleccionado = "";
    private int filaEditando = -1;
    private int stockProductoEditando = 0;
    private double precioProductoActual = 0;
    private int stockProductoActual = 0;

    // Constante para el IVA
    private static final double PORCENTAJE_IVA = 0.16;

    public Internueva_venta() {
        super("Nueva Venta - Facturación", true, true, true, true);

        // Inicializar controladores
        controlCliente = new Ctrl_Cliente();
        controlProducto = new Ctrl_Producto();
        controlVenta = new Ctrl_Venta();

        initComponents();
        cargarProductosEnCombo();
        configurarEventos();

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
        panelProducto.add(new JLabel("Producto:"), gbc);
        cmbProducto = new JComboBox<>();
        cmbProducto.setEditable(true);
        cmbProducto.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        panelProducto.add(cmbProducto, gbc);
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
        String[] columnas = {"ID", "Producto", "Descripción", "Cantidad", "Precio Unitario ($)", "Subtotal ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDetalle = new JTable(modeloTabla);
        // Al hacer clic en la tabla, cargar el producto para modificar
        tablaDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tablaDetalle.getSelectedRow();
                if (fila >= 0) {
                    cargarProductoParaEditar(fila);
                }
            }
        });
// Deseleccionar al hacer clic en el panel principal
        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cancelarEdicionSilenciosamente();
            }
        });
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
    }

    // ==========================================
    // EVENTOS
    // ==========================================
    private void cargarProductosEnCombo() {
        // 1. Crear una lista vacía para guardar los nombres de productos
        List<String> nombresProductos = new ArrayList<>();

        // 2. Obtener todos los productos de la base de datos
        List<Producto> productos = controlProducto.listarProductos();

        // 3. Recorrer cada producto y guardar solo su nombre en la lista
        for (Producto p : productos) {
            nombresProductos.add(p.getNombre());
        }

        // 4. Crear el autocompletado y pasarle la lista de nombres
        autoCompletadoProducto = new AutoCompletadoCB(cmbProducto);
        autoCompletadoProducto.setItems(nombresProductos);
    }

    private void configurarEventos() {
        // 1. Buscar Cliente
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buscarCliente();
            }
        });

        // 2. Buscar Producto
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buscarProducto();
            }
        });

        // 3. Agregar Producto a la Factura
        btnAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarProducto();
            }
        });

        // 4. Quitar Producto de la Factura
        btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                quitarProducto();
            }
        });

        // 5. Registrar Venta
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                registrarVenta();
            }
        });

        // 6. Presionar Enter en campos de búsqueda
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buscarCliente();
            }
        });

        cmbProducto.getEditor().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (idProductoSeleccionado != 0 && !txtNombreProd.getText().isEmpty()) {
                    agregarProducto();
                } else {
                    buscarProducto();
                }
            }
        });
        spinCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    agregarProducto();
                }
            }
        });
        btnAgregarProd.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    agregarProducto();
                }
            }
        });

    }
    // ==========================================
    // MÉTODOS FUNCIONALES
    // ==========================================

    private void cancelarEdicionSilenciosamente() {
        if (filaEditando >= 0) {
            filaEditando = -1;
            stockProductoEditando = 0;
            btnAgregarProd.setText("Agregar a Factura");
            btnAgregarProd.setBackground(new Color(34, 139, 34));
            tablaDetalle.clearSelection();
            limpiarCamposProducto();
        }
    }

    private void buscarCliente() {
        String cedula = txtCedula.getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cedula/RIF", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = controlCliente.buscarPorCedula(cedula);
        if (cliente != null) {
            txtNombreCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
            txtTelefonoCliente.setText(cliente.getTelefono());
            idClienteSeleccionado = cliente.getIdCliente();
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado: " + cedula, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarProducto() {
        String nombre = cmbProducto.getEditor().getItem().toString().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto producto = controlProducto.buscarProductoPorNombre(nombre);
        if (producto != null) {
            txtNombreProd.setText(producto.getDescripcion());
            txtPrecioProd.setText(String.format("%.2f", producto.getPrecio()).replace(",", "."));
            txtStockProd.setText(String.valueOf(producto.getCantidad()));
            idProductoSeleccionado = producto.getIdProducto();
            nombreProductoSeleccionado = producto.getNombre();
            descripcionProductoSeleccionado = producto.getDescripcion();
            precioProductoActual = producto.getPrecio();
            stockProductoActual = producto.getCantidad();
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado: " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void agregarProducto() {
    if (txtNombreProd.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Primero busque un producto", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Usar variables guardadas en lugar de leer los campos de texto
        double precio = precioProductoActual;
        int stock = stockProductoActual;
        int cantidad = (int) spinCantidad.getValue();

        // Si estamos editando un producto de la tabla, usar el stock de edicion
        if (filaEditando >= 0) {
            stock = stockProductoEditando;
        }

        // Verificar que el precio no sea 0
        if (precio == 0) {
            JOptionPane.showMessageDialog(this, "Precio del producto no disponible. Busque el producto nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cantidad > stock) {
            JOptionPane.showMessageDialog(this, "Stock insuficiente. Disponible: " + stock,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si estamos editando un producto de la tabla
        if (filaEditando >= 0) {
            if (cantidad > stockProductoEditando) {
                JOptionPane.showMessageDialog(this,
                        "Stock insuficiente. Stock disponible: " + stockProductoEditando,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = precio * cantidad;
            modeloTabla.setValueAt(String.valueOf(cantidad), filaEditando, 3);
            modeloTabla.setValueAt(String.format("%.2f", subtotal).replace(",", "."), filaEditando, 5);
            btnAgregarProd.setText("Agregar a Factura");
            btnAgregarProd.setBackground(new Color(34, 139, 34));
            filaEditando = -1;
            stockProductoEditando = 0;

            calcularTotales();
            limpiarCamposProducto();
            return;
        }

        // Buscar si el producto ya está en la tabla
        int filaExistente = -1;
        String codigoBuscar = nombreProductoSeleccionado.isEmpty() ? cmbProducto.getEditor().getItem().toString() : nombreProductoSeleccionado;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 1).equals(codigoBuscar)) {
                filaExistente = i;
                break;
            }
        }

        if (filaExistente >= 0) {
            // Si existe, actualizar la cantidad
            int cantidadActual = Integer.parseInt(modeloTabla.getValueAt(filaExistente, 2).toString());
            int nuevaCantidad = cantidadActual + cantidad;

            if (nuevaCantidad > stock) {
                JOptionPane.showMessageDialog(this,
                        "No puede agregar mas. Stock disponible: " + stock
                        + " | Actual: " + cantidadActual,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = precio * nuevaCantidad;
            modeloTabla.setValueAt(String.valueOf(nuevaCantidad), filaExistente, 3);
            modeloTabla.setValueAt(String.format("%.2f", subtotal).replace(",", "."), filaExistente, 5);

            if (filaEditando == filaExistente) {
                filaEditando = -1;
                btnAgregarProd.setText("Agregar a Factura");
                btnAgregarProd.setBackground(new Color(34, 139, 34));
            }
        } else {
            // Si no existe, agregar nuevo producto
            double subtotal = precio * cantidad;
            modeloTabla.addRow(new Object[]{
                String.valueOf(idProductoSeleccionado),
                nombreProductoSeleccionado.isEmpty() ? cmbProducto.getEditor().getItem().toString() : nombreProductoSeleccionado,
                descripcionProductoSeleccionado.isEmpty() ? txtNombreProd.getText() : descripcionProductoSeleccionado,
                String.valueOf(cantidad),
                String.format("%.2f", precio).replace(",", "."),
                String.format("%.2f", subtotal).replace(",", ".")
            });
        }

        calcularTotales();
        limpiarCamposProducto();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Error en los datos del producto. Verifique el precio y stock.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void cargarProductoParaEditar(int fila) {
        try {
            int idProducto = obtenerIdProductoDeTabla(fila);
            String codigo = (String) modeloTabla.getValueAt(fila, 1);
            String descripcion = (String) modeloTabla.getValueAt(fila, 2);
            int cantidad = Integer.parseInt(modeloTabla.getValueAt(fila, 3).toString());
            String precioStr = (String) modeloTabla.getValueAt(fila, 4);
            precioStr = precioStr.replace(",", ".");
            double precio = Double.parseDouble(precioStr);

            //Buscar por ID en lugar de por nombre
            Producto producto = controlProducto.buscarProducto(idProducto);
            if (producto != null) {
                stockProductoEditando = producto.getCantidad();
                nombreProductoSeleccionado = producto.getNombre();
                descripcionProductoSeleccionado = producto.getDescripcion();
                precioProductoActual = producto.getPrecio();
                stockProductoActual = producto.getCantidad();
            } else {
                stockProductoEditando = 999;
            }

            cmbProducto.getEditor().setItem(codigo);
            txtNombreProd.setText(descripcion);
            txtPrecioProd.setText(String.valueOf(precio));
            txtStockProd.setText(String.valueOf(stockProductoEditando));
            spinCantidad.setValue(cantidad);

            filaEditando = fila;
            btnAgregarProd.setText("Actualizar Producto");
            btnAgregarProd.setBackground(new Color(255, 165, 0));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitarProducto() {
        int fila = tablaDetalle.getSelectedRow();
        if (fila >= 0) {
            modeloTabla.removeRow(fila);
            calcularTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void registrarVenta() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Agregue al menos un producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (idClienteSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Registrar venta por $" + txtTotal.getText() + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Venta venta = new Venta();
                venta.setIdCliente(idClienteSeleccionado);
                venta.setValorpagar(Double.parseDouble(txtTotal.getText()));
                venta.setEstado(1);

                List<DetalleVenta> detalles = new ArrayList<>();
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    DetalleVenta detalle = new DetalleVenta();

                    // Obtener el ID del producto (columna 0 si la tienes)
                    detalle.setIdProducto(obtenerIdProductoDeTabla(i));
                    detalle.setCantidad(Integer.parseInt(modeloTabla.getValueAt(i, 3).toString()));
                    detalle.setPreciounitario(Double.parseDouble(
                            ((String) modeloTabla.getValueAt(i, 4)).replace(",", ".")
                    ));
                    detalle.setSubtotal(Double.parseDouble(
                            ((String) modeloTabla.getValueAt(i, 5)).replace(",", ".")
                    ));
                    detalle.setDescuento(0.0);
                    detalle.setIva(Double.parseDouble(txtIva.getText()));
                    detalle.setTotalpagar(Double.parseDouble(txtTotal.getText()));
                    detalle.setEstado(1);
                    detalles.add(detalle);
                }

                int idVenta = controlVenta.guardar(venta, detalles);

                if (idVenta > 0) {
                    JOptionPane.showMessageDialog(this, "Venta registrada exitosamente. ID: " + idVenta,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTodo();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar la venta",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void calcularTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String subtotalStr = (String) modeloTabla.getValueAt(i, 5);
            subtotalStr = subtotalStr.replace(",", ".");
            subtotal += Double.parseDouble(subtotalStr);
        }
        txtSubtotal.setText(String.format("%.2f", subtotal).replace(",", "."));
        txtIva.setText(String.format("%.2f", subtotal * PORCENTAJE_IVA).replace(",", "."));
        txtTotal.setText(String.format("%.2f", subtotal * (1 + PORCENTAJE_IVA)).replace(",", "."));
    }

    private void limpiarCamposProducto() {
        cmbProducto.getEditor().setItem("");
        txtNombreProd.setText("");
        txtPrecioProd.setText("");
        txtStockProd.setText("");
        spinCantidad.setValue(1);
        idProductoSeleccionado = 0;
        nombreProductoSeleccionado = "";
        descripcionProductoSeleccionado = "";
        filaEditando = -1;
        stockProductoEditando = 0;
        btnAgregarProd.setText("Agregar a Factura");
        btnAgregarProd.setBackground(new Color(34, 139, 34));
        cmbProducto.requestFocus();
        precioProductoActual = 0;
        stockProductoActual = 0;
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

    private int obtenerIdProductoDeTabla(int fila) {
        try {
            return Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        } catch (Exception e) {
            return 0;
        }
    }
}
