package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Formulario Interno para la gestión, búsqueda y anulación de ventas.
 * Diseñado para integrarse en un JDesktopPane.
 */
public class Intergestionar_venta extends JInternalFrame {

    // Componentes de búsqueda y filtrado
    private JTextField txtBuscarFactura, txtBuscarCliente;
    private JComboBox<String> cbEstado;
    private JButton btnBuscar, btnLimpiarFiltros;

    // Tabla de visualización del historial de ventas
    private JTable tablaVentas;
    private DefaultTableModel modeloVentas;
    
    // Botones de control y acción
    private JButton btnVerDetalle, btnAnularVenta;

    public Intergestionar_venta() {
        // Título, Redimensionable, Cerrable, Maximizable, Minimizable
        super("Gestionar Ventas - Historial", true, true, true, true);
        initComponents();
        setSize(950, 550); // Ajuste de dimensiones perfecto para el panel azul
        cargarDatosSimulados(); // Llena la tabla con registros de prueba
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
        gbc.gridx = 0; gbc.gridy = 0;
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

        gbc.gridx = 0; gbc.gridy = 1;
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
                cargarDatosSimulados(); // Recarga todo el set de datos simulado
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
                        "¿Está seguro de que desea ANULAR la factura Nº " + tablaVentas.getValueAt(fila, 0) + "?\nEsta acción devolverá los artículos al almacén.", 
                        "Confirmar Anulación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    tablaVentas.setValueAt("Anulada", fila, 5);
                    JOptionPane.showMessageDialog(null, "La factura ha sido anulada con éxito.");
                }
            }
        });
    }

    // Inicializa datos de prueba de facturación
    private void cargarDatosSimulados() {
        modeloVentas.setRowCount(0); // Limpia la tabla
        modeloVentas.addRow(new Object[]{"FAC-0001", "15/06/2026", "123456", "Juan Pérez", "15.40", "Activa"});
        modeloVentas.addRow(new Object[]{"FAC-0002", "16/06/2026", "987654", "María Mendoza", "3.90", "Activa"});
        modeloVentas.addRow(new Object[]{"FAC-0003", "17/06/2026", "123456", "Juan Pérez", "12.50", "Anulada"});
        modeloVentas.addRow(new Object[]{"FAC-0004", "17/06/2026", "555111", "Carlos Gómez", "45.00", "Activa"});
    }

    // Lógica para filtrar registros dinámicamente en memoria
    private void aplicarFiltros() {
        String fFactura = txtBuscarFactura.getText().trim().toLowerCase();
        String fCliente = txtBuscarCliente.getText().trim();
        String fEstado = (String) cbEstado.getSelectedItem();

        // Si todos los filtros están vacíos, reiniciamos el listado completo
        if (fFactura.isEmpty() && fCliente.isEmpty() && fEstado.equals("Todos")) {
            cargarDatosSimulados();
            return;
        }

        cargarDatosSimulados(); // Reiniciamos el set de datos para evaluar de cero
        for (int i = modeloVentas.getRowCount() - 1; i >= 0; i--) {
            String factura = ((String) modeloVentas.getValueAt(i, 0)).toLowerCase();
            String cedula = (String) modeloVentas.getValueAt(i, 2);
            String estado = (String) modeloVentas.getValueAt(i, 5);

            boolean cumpleFactura = fFactura.isEmpty() || factura.contains(fFactura);
            boolean cumpleCliente = fCliente.isEmpty() || cedula.equals(fCliente);
            boolean cumpleEstado = fEstado.equals("Todos") || estado.equals(fEstado);

            if (!(cumpleFactura && cumpleCliente && cumpleEstado)) {
                modeloVentas.removeRow(i);
            }
        }
    }

    // Ventana de diálogo modal para mostrar los artículos correspondientes a cada factura
    private void mostrarVentanaDetalle(String nroFactura, String cliente, String total) {
        JDialog dialogoDetalle = new JDialog((Frame) null, "Detalle de Factura - " + nroFactura, true);
        dialogoDetalle.setSize(500, 350);
        dialogoDetalle.setLocationRelativeTo(this);
        
        JPanel pContenido = new JPanel(new BorderLayout(10, 10));
        pContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pContenido.setBackground(Color.WHITE);

        // Encabezado del diálogo
        JPanel pEncabezado = new JPanel(new GridLayout(2, 1));
        pEncabezado.setOpaque(false);
        JLabel lblCliente = new JLabel("Cliente: " + cliente);
        lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pEncabezado.add(lblCliente);
        pEncabezado.add(new JLabel("Detalle de Artículos Adquiridos:"));
        pContenido.add(pEncabezado, BorderLayout.NORTH);

        // Estructura de tabla del diálogo de detalles
        String[] colsDetalle = {"Código", "Producto", "Cantidad", "Precio Unitario ($)", "Subtotal ($)"};
        DefaultTableModel modDetalle = new DefaultTableModel(colsDetalle, 0);
        JTable tDetalle = new JTable(modDetalle);
        tDetalle.setRowHeight(20);
        
        // Simular artículos en base al código de factura seleccionado
        if (nroFactura.equals("FAC-0001")) {
            modDetalle.addRow(new Object[]{"P01", "Harina de Maíz Precocida 1kg", 10, "1.30", "13.00"});
            modDetalle.addRow(new Object[]{"P02", "Arroz Blanco Extra 1kg", 2, "1.10", "2.20"});
        } else if (nroFactura.equals("FAC-0002")) {
            modDetalle.addRow(new Object[]{"P02", "Arroz Blanco Extra 1kg", 3, "1.10", "3.30"});
        } else {
            modDetalle.addRow(new Object[]{"P01", "Harina de Maíz Precocida 1kg", 5, "1.30", "6.50"});
            modDetalle.addRow(new Object[]{"P02", "Arroz Blanco Extra 1kg", 4, "1.10", "4.40"});
        }

        pContenido.add(new JScrollPane(tDetalle), BorderLayout.CENTER);

        // Pie de ventana con monto total de la factura
        JLabel lblTotalDialogo = new JLabel("Total Facturado: " + total + " $", JLabel.RIGHT);
        lblTotalDialogo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalDialogo.setForeground(new Color(180, 0, 0));
        pContenido.add(lblTotalDialogo, BorderLayout.SOUTH);

        dialogoDetalle.add(pContenido);
        dialogoDetalle.setVisible(true);
    }
}