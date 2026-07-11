package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;


/**
 * Formulario Interno para la gestión, búsqueda y anulación de ventas. Diseñado
 * para integrarse en un JDesktopPane.
 */
public class Intergestionar_venta extends JInternalFrame {

    // Componentes de búsqueda y filtrado
    private JTextField txtBuscarFactura, txtBuscarCliente;
    private JComboBox<String> cbEstado;
    private JButton btnBuscar, btnLimpiarFiltros;
    // Tabla de visualización del historial de ventas
    private JTable tablaVentas;
    private DefaultTableModel modeloVentas;

    //Controladores
    private controlador.Ctrl_Venta controlVenta;

    // Botones de control y acción
    private JButton btnVerDetalle, btnAnularVenta;

    public Intergestionar_venta() {
        // Título, Redimensionable, Cerrable, Maximizable, Minimizable
        super("Gestionar Ventas - Historial", true, true, true, true);

        // Inicializar controlador
        controlVenta = new controlador.Ctrl_Venta();

        initComponents();
        setSize(950, 550); // Ajuste de dimensiones perfecto para el panel azul
        cargarVentasReales();
    }

    private void initComponents() {
        // Contenedor principal con espaciado uniforme
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // ==========================================
        // 1. PANEL SUPERIOR: FILTROS DE BÚSQUEDA
        // ==========================================
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Criterios de Búsqueda / Filtros",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(0, 80, 136)));
        panelFiltros.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Filtro por número de factura
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFiltros.add(new JLabel("Nº Factura:"), gbc);
        txtBuscarFactura = new JTextField(10);
        gbc.gridx = 1;
        panelFiltros.add(txtBuscarFactura, gbc);

        // Filtro por número de cédula del cliente
        gbc.gridx = 2;
        panelFiltros.add(new JLabel("Cédula Cliente:"), gbc);
        txtBuscarCliente = new JTextField(12);
        gbc.gridx = 3;
        panelFiltros.add(txtBuscarCliente, gbc);

        // Filtro por estado del documento
        gbc.gridx = 4;
        panelFiltros.add(new JLabel("Estado:"), gbc);
        cbEstado = new JComboBox<>(new String[]{"Todos", "Activa", "Anulada"});
        gbc.gridx = 5;
        panelFiltros.add(cbEstado, gbc);

        // Subpanel para los botones de búsqueda
        JPanel panelBotonesBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotonesBusqueda.setOpaque(false);

        btnBuscar = new JButton("Filtrar / Buscar");
        btnBuscar.setBackground(new Color(0, 80, 136));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnLimpiarFiltros = new JButton("Restablecer");
        btnLimpiarFiltros.setBackground(Color.LIGHT_GRAY);

        panelBotonesBusqueda.add(btnBuscar);
        panelBotonesBusqueda.add(btnLimpiarFiltros);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        panelFiltros.add(panelBotonesBusqueda, gbc);

        panelPrincipal.add(panelFiltros, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL CENTRAL: TABLA DE HISTORIAL
        // ==========================================
        String[] columnas = {"Nº Factura", "Fecha de Venta", "Cédula Cliente", "Cliente", "Total Factura ($)", "Estado"};
        modeloVentas = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva edición manual sobre las celdas de la tabla
            }
        };
        tablaVentas = new JTable(modeloVentas);
        tablaVentas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaVentas.setRowHeight(25);
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollTabla = new JScrollPane(tablaVentas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Transacciones"));
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // ==========================================
        // 3. PANEL INFERIOR: ACCIONES
        // ==========================================
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelAcciones.setOpaque(false);

        btnVerDetalle = new JButton("Ver Detalle de Factura");
        btnVerDetalle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnVerDetalle.setBackground(new Color(0, 128, 128));
        btnVerDetalle.setForeground(Color.WHITE);
        btnVerDetalle.setPreferredSize(new Dimension(180, 40));

        btnAnularVenta = new JButton("Anular Factura");
        btnAnularVenta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAnularVenta.setBackground(new Color(205, 92, 92));
        btnAnularVenta.setForeground(Color.WHITE);
        btnAnularVenta.setPreferredSize(new Dimension(150, 40));

        panelAcciones.add(btnVerDetalle);
        panelAcciones.add(btnAnularVenta);

        panelPrincipal.add(panelAcciones, BorderLayout.SOUTH);

        this.setContentPane(panelPrincipal);

        // ==========================================
        // EVENTOS Y EVENT LISTENERS
        // ==========================================
        // Listener para aplicar filtros de búsqueda
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        // Listener para limpiar y restablecer filtros
        btnLimpiarFiltros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBuscarFactura.setText("");
                txtBuscarCliente.setText("");
                cbEstado.setSelectedIndex(0);
                cargarVentasReales(); // Cambiar por datos reales
            }
        });

        // Listener para abrir los detalles específicos de una factura seleccionada
        btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaVentas.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una factura de la tabla.");
                    return;
                }

                String nroFactura = (String) tablaVentas.getValueAt(fila, 0);
                String cliente = (String) tablaVentas.getValueAt(fila, 3);
                String total = (String) tablaVentas.getValueAt(fila, 4);

                mostrarVentanaDetalle(nroFactura, cliente, total);
            }
        });

        // Listener para anular la transacción seleccionada
        btnAnularVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaVentas.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione la factura que desea anular.");
                    return;
                }

                String estado = (String) tablaVentas.getValueAt(fila, 5);
                if (estado.equals("Anulada")) {
                    JOptionPane.showMessageDialog(null, "Esta factura ya se encuentra anulada.");
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de que desea ANULAR la factura " + tablaVentas.getValueAt(fila, 0) + "?",
                        "Confirmar Anulación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    String idStr = ((String) tablaVentas.getValueAt(fila, 0)).replace("FAC-", "");
                    int idVenta = Integer.parseInt(idStr);

                    if (controlVenta.anularVenta(idVenta)) {
                        JOptionPane.showMessageDialog(null, "La factura ha sido anulada con éxito.");
                        cargarVentasReales(); // Recargar datos
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al anular la factura.");
                    }
                }
            }
        });
    }

    // Inicializa datos de prueba de facturación
    private void cargarVentasReales() {
        modeloVentas.setRowCount(0); // Limpiar tabla

        List<modelo.Venta> ventas = controlVenta.listarVentas();
 
       // Obtener datos del cliente
        for (modelo.Venta venta : ventas) {
            
            // Busca los datos del cliente asociado a la venta
            controlador.Ctrl_Cliente ctrlCliente = new controlador.Ctrl_Cliente();
            // Busca el objeto del cliente usando la I almacenada
            modelo.Cliente cliente = ctrlCliente.buscarPorId(venta.getIdCliente());

            // Si el cliente existe continua el programa, si no aparece un mensaje de error
            String nombreCliente = (cliente != null) ? cliente.getNombre() + " " + cliente.getApellido() : "Cliente no encontrado";
            String cedulaCliente = (cliente != null) ? cliente.getCedula() : "N/A";

            // Evalua el estado, si es uno esta activa, de lo contrario  se considera anulada
            String estado = (venta.getEstado() == 1) ? "Activa" : "Anulada";
            
           // Agrega una nueva fila al componente visual de la tabla con los datos formateados
            modeloVentas.addRow(new Object[]{
                // Formatea el ID de la venta con ceros a la izquierda
                "FAC-" + String.format("%04d", venta.getIdCabeceraVenta()),
                venta.getFechaventa(),
                cedulaCliente,
                nombreCliente,
                //Formatea el valor monetario
                String.format("%.2f", venta.getValorpagar()),
                estado
            });
        }
    }

    // Lógica para filtrar registros dinámicamente en memoria
    private void aplicarFiltros() {
        String fFactura = txtBuscarFactura.getText().trim().toLowerCase();
        String fCliente = txtBuscarCliente.getText().trim();
        String fEstado = (String) cbEstado.getSelectedItem();

        // Si todos los filtros están vacíos, mostrar todas las ventas
        if (fFactura.isEmpty() && fCliente.isEmpty() && fEstado.equals("Todos")) {
            cargarVentasReales();
            return;
        }

        // Cargar todas las ventas y luego filtrar
        List<modelo.Venta> ventas = controlVenta.listarVentas();
        // Recupera la lista completa de ventas desde la capa controladora
        modeloVentas.setRowCount(0);
        
         // Recorre cada una de la ventas obtenidas
        for (modelo.Venta venta : ventas) {
            // Instancia para accecder a la busqueda de clientes
            controlador.Ctrl_Cliente ctrlCliente = new controlador.Ctrl_Cliente();
            // Busca el cliente a traves del ID guadado
            modelo.Cliente cliente = ctrlCliente.buscarPorId(venta.getIdCliente());
            
            // genera el codigo de la factura
            String nroFactura = "FAC-" + String.format("%04d", venta.getIdCabeceraVenta());
            // Si el cliente existe, continua el programa si no da error
            String cedulaCliente = (cliente != null) ? cliente.getCedula() : "N/A";
            //Si la cedula cliente existe continua el programa, si no aparece un mensaje de error
            String nombreCliente = (cliente != null) ? cliente.getNombre() + " " + cliente.getApellido() : "Cliente no encontrado";
            // estado numerico
            String estado = (venta.getEstado() == 1) ? "Activa" : "Anulada";
            
            // Valida si cada paraemtro coincide con lo buscado
            boolean cumpleFactura = fFactura.isEmpty() || nroFactura.toLowerCase().contains(fFactura);
            boolean cumpleCliente = fCliente.isEmpty() || cedulaCliente.equals(fCliente);
            boolean cumpleEstado = fEstado.equals("Todos") || estado.equals(fEstado);
            
            // Evalua si el regitro cumple con las tres condiciones en paralelo
            if (cumpleFactura && cumpleCliente && cumpleEstado) {
                // agrega fila al componente visual unicacmente si paso los filtros anteriores
                modeloVentas.addRow(new Object[]{
                    nroFactura,
                    venta.getFechaventa(),
                    cedulaCliente,
                    nombreCliente,
                    // formatea el monto final de la venta a dos posiciones decimales
                    String.format("%.2f", venta.getValorpagar()),
                    estado
                });
            }
        }
    }
    // Ventana de diálogo modal para mostrar los artículos correspondientes a cada factura

    private void mostrarVentanaDetalle(String nroFactura, String cliente, String total) {
        // Extraer el ID de la factura del formato "FAC-0001"
        String idStr = nroFactura.replace("FAC-", "");
        int idVenta = Integer.parseInt(idStr);

        JDialog dialogoDetalle = new JDialog((Frame) null, "Detalle de Factura - " + nroFactura, true);
        dialogoDetalle.setSize(550, 400);
        dialogoDetalle.setLocationRelativeTo(this);

        JPanel pContenido = new JPanel(new BorderLayout(10, 10));
        pContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pContenido.setBackground(Color.WHITE);

        // Encabezado
        JPanel pEncabezado = new JPanel(new GridLayout(2, 1));
        pEncabezado.setOpaque(false);
        JLabel lblCliente = new JLabel("Cliente: " + cliente);
        lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pEncabezado.add(lblCliente);
        pEncabezado.add(new JLabel("Detalle de Artículos Adquiridos:"));
        pContenido.add(pEncabezado, BorderLayout.NORTH);

        // Tabla de detalles
        String[] colsDetalle = {"Código", "Producto", "Cantidad", "Precio Unitario ($)", "Subtotal ($)"};
        // Iniciando el encabezado y empezando en cero filas
        DefaultTableModel modDetalle = new DefaultTableModel(colsDetalle, 0);
        //Inicia componente visual
        JTable tDetalle = new JTable(modDetalle);
        // Definicion de altura
        tDetalle.setRowHeight(20);

        // Cargar detalles reales desde la base de datos
        List<modelo.DetalleVenta> detalles = controlVenta.listarDetalles(idVenta);
        //Instacia del contralador de producto para consultar descripcion de la venta
        controlador.Ctrl_Producto ctrlProducto = new controlador.Ctrl_Producto();

        // Recorre cada uno de los articulos pertenencientes a la venta
        for (modelo.DetalleVenta detalle : detalles) {
            modelo.Producto producto = ctrlProducto.buscarProducto(detalle.getIdProducto());
            String nombreProducto = (producto != null) ? producto.getNombre() : "Producto no encontrado";

            modDetalle.addRow(new Object[]{
                "P-" + String.format("%03d", detalle.getIdProducto()),
                nombreProducto,
                detalle.getCantidad(),
                String.format("%.2f", detalle.getPreciounitario()),
                String.format("%.2f", detalle.getSubtotal())
            });
        }

        pContenido.add(new JScrollPane(tDetalle), BorderLayout.CENTER);

        // Total
        JLabel lblTotalDialogo = new JLabel("Total Facturado: " + total + " $", JLabel.RIGHT);
        lblTotalDialogo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalDialogo.setForeground(new Color(180, 0, 0));
        pContenido.add(lblTotalDialogo, BorderLayout.SOUTH);

        dialogoDetalle.add(pContenido);
        dialogoDetalle.setVisible(true);
    }
}
