
package vista;
import javax.swing.table.DefaultTableModel;


public final class InterHistorial extends javax.swing.JInternalFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterHistorial.class.getName());

    /**
     * Creates new form InterHistorial
     */
    // Variable para manejar el modelo de la tabla de forma dinámica
    DefaultTableModel modeloTabla;

    // Modifica tu constructor para que quede así:
    public InterHistorial() {
    initComponents();
    this.setTitle("Historial de Sistema - Mercadito");
    
    // 1. Activar los botones de cerrar y minimizar en la barra superior
this.setClosable(true);      // Muestra la (X) para cerrar
this.setIconifiable(true);  // Muestra el (-) para minimizar
this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
// 2. Hacer que las celdas y el fondo blanco ocupen TODO el espacio disponible
id.setFillsViewportHeight(true);
      
    id.setShowHorizontalLines(true);
    id.setShowVerticalLines(true);
    id.setGridColor(new java.awt.Color(210, 210, 210));
    id.setRowHeight(24);
    
    
    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) id.getModel();
    if (modelo.getRowCount() < 15) {
        int filasFaltantes = 15 - modelo.getRowCount();
        for (int i = 0; i < filasFaltantes; i++) {
            modelo.addRow(new Object[]{"", "", "", ""}); 
        }
    }

    // Cambiar colores de la tabla
    id.setBackground(java.awt.Color.WHITE);
 
    id.getTableHeader().setForeground(java.awt.Color.BLACK);


    jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);

        
        // Cargamos la estructura y los datos de la BD al abrir la ventana
        cargarEstructuraTabla();
        listarActividades();
        
    }
    

    // 1. Definimos las columnas que verá el usuario en la interfaz gráfica
    private void cargarEstructuraTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Módulo");
        modeloTabla.addColumn("Acción Realizada");
        modeloTabla.addColumn("Fecha y Hora");
        
        // Asignamos el modelo estructural a nuestra JTable de la interfaz
        this.tbl_historial.setModel(modeloTabla);
        
        // Ajustamos tamaños de columna de forma opcional
        tbl_historial.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_historial.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_historial.getColumnModel().getColumn(2).setPreferredWidth(280);
        tbl_historial.getColumnModel().getColumn(3).setPreferredWidth(170);
    }

    //  Conectamos la vista con el Controlador para traer los datos
    public void listarActividades() {
        controlador.Ctrl_historial controlador = new controlador.Ctrl_historial();
        java.util.List<modelo.Historial> lista = controlador.listarHistorial();
        
        // Limpiamos la tabla antes de rellenar para evitar duplicados visuales
        modeloTabla.setRowCount(0);
        
        // Creamos un vector de objetos para insertar fila por fila
        Object[] fila = new Object[4];
        
        for (modelo.Historial h : lista) {
            fila[0] = h.getId_historial();
            fila[1] = h.getModulo();
            fila[2] = h.getAccion();
            fila[3] = h.getFecha_hora(); // Se mostrará la fecha exacta del cambio
            
            // Agregamos la fila cargada a la tabla visual
            modeloTabla.addRow(fila);
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        id = new javax.swing.JTable();
        jColorChooser1 = new javax.swing.JColorChooser();
        jColorChooser2 = new javax.swing.JColorChooser();
        jColorChooser3 = new javax.swing.JColorChooser();
        jColorChooser4 = new javax.swing.JColorChooser();
        jColorChooser5 = new javax.swing.JColorChooser();
        jColorChooser6 = new javax.swing.JColorChooser();
        jColorChooser7 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();
        button1 = new java.awt.Button();
        jColorChooser8 = new javax.swing.JColorChooser();
        jColorChooser9 = new javax.swing.JColorChooser();
        jColorChooser10 = new javax.swing.JColorChooser();

        tbl_historial.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_historial);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        id.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createCompoundBorder())));
        id.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "MODULO", "ACCION REALIZADA", "FECHA/ HORA"
            }
        ));
        jScrollPane2.setViewportView(id);
        if (id.getColumnModel().getColumnCount() > 0) {
            id.getColumnModel().getColumn(0).setHeaderValue("ID");
            id.getColumnModel().getColumn(1).setHeaderValue("MODULO");
            id.getColumnModel().getColumn(2).setHeaderValue("ACCION REALIZADA");
            id.getColumnModel().getColumn(3).setHeaderValue("FECHA/ HORA");
        }

        jButton1.setText("jButton1");

        button1.setLabel("button1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new InterHistorial().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JTable id;
    private javax.swing.JButton jButton1;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JColorChooser jColorChooser10;
    private javax.swing.JColorChooser jColorChooser2;
    private javax.swing.JColorChooser jColorChooser3;
    private javax.swing.JColorChooser jColorChooser4;
    private javax.swing.JColorChooser jColorChooser5;
    private javax.swing.JColorChooser jColorChooser6;
    private javax.swing.JColorChooser jColorChooser7;
    private javax.swing.JColorChooser jColorChooser8;
    private javax.swing.JColorChooser jColorChooser9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_historial;
    // End of variables declaration//GEN-END:variables

   

        
    }

