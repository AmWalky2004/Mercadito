package vista;

import javax.swing.JDesktopPane;

public class FrmMenuGerente extends javax.swing.JFrame {

    // 1. Declaramos la variable del DesktopPane aquí arriba de forma global
    public static JDesktopPane jDesktopPane_menu;

    /**
     * Constructor de la interfaz del Gerente
     */
    public FrmMenuGerente() {
        initComponents();
        this.setSize(800, 600); // Tamaño del panel
        this.setResizable(false); // Fijo
        this.setLocationRelativeTo(null); // Centrado en pantalla
        this.setTitle("MERCADITO - PANEL DE GERENTE"); // Título oficial
        
        // 2. Inicializamos el DesktopPane y lo añadimos a la ventana
        jDesktopPane_menu = new JDesktopPane();
        jDesktopPane_menu.setBounds(0, 0, 800, 600); // Que ocupe toda la ventana
        this.add(jDesktopPane_menu);
    }
    
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(150, 50));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        jMenu1.setText("Adminitrar producto");
        jMenu1.setPreferredSize(new java.awt.Dimension(160, 50));

        jMenuItem1.setText("Agregar producto");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Gestionar producto");
        jMenu1.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemadeVentas/src/img/ee.png"))); // NOI18N
        jMenu2.setText("Control de stock y inventario");
        jMenu2.setMinimumSize(new java.awt.Dimension(190, 50));
        jMenu2.setPreferredSize(new java.awt.Dimension(200, 50));

        jMenuItem2.setText("Stock");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("inventario");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button_no_15721 (1).png"))); // NOI18N
        jMenu3.setText("Anular comprar");
        jMenu3.setPreferredSize(new java.awt.Dimension(150, 50));
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chart_marketing_report_shop_graph_business_sales_shopping_analytics_finance_icon_231909 (1).png"))); // NOI18N
        jMenu4.setText("Reportes finacieros ");
        jMenu4.setPreferredSize(new java.awt.Dimension(150, 50));
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/medical_history_tasks_to_do_list_icon_149282.png"))); // NOI18N
        jMenu5.setText("Historial");
        jMenu5.setPreferredSize(new java.awt.Dimension(150, 50));
        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button_blank_red_14987.png"))); // NOI18N
        jMenu6.setText("cierre");
        jMenu6.setPreferredSize(new java.awt.Dimension(150, 50));
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed
 
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String args[]) {
        /* Configuración del tema visual Nimbus */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Ejecutar y mostrar el formulario */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmMenuGerente().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
