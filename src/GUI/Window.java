/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Alexis
 */
public class Window extends javax.swing.JFrame {

    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Information_Panel = new javax.swing.JPanel();
        Info1 = new javax.swing.JLabel();
        Info2 = new javax.swing.JLabel();
        Info3 = new javax.swing.JLabel();
        Informations_POI = new javax.swing.JLabel();
        Scroll_Information = new javax.swing.JScrollPane();
        Text_Information = new javax.swing.JTextArea();
        Picture_POI = new javax.swing.JPanel();
        Map_Panel = new javax.swing.JPanel();
        Research_Panel = new javax.swing.JPanel();
        SearchBar = new javax.swing.JTextField();
        Scroll_Research = new javax.swing.JScrollPane();
        Result_Research = new javax.swing.JTextArea();
        Navigation_Panel = new javax.swing.JPanel();
        Combo_Interface = new javax.swing.JComboBox();
        Button_AddPOI = new javax.swing.JButton();
        Button_DelPOI = new javax.swing.JButton();
        Button_Prev = new javax.swing.JButton();
        Button_Next = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        Menu_Lieux = new javax.swing.JMenu();
        Menu_POI = new javax.swing.JMenu();
        Menu_Help = new javax.swing.JMenu();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Information_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Information_Panel.setPreferredSize(new java.awt.Dimension(100, 100));

        Info1.setText("Info1");

        Info2.setText("Info2");

        Info3.setText("Info3");
        Info3.setToolTipText("");

        Informations_POI.setText("Informations POI");

        Text_Information.setEditable(false);
        Text_Information.setColumns(20);
        Text_Information.setRows(4);
        Scroll_Information.setViewportView(Text_Information);

        Picture_POI.setPreferredSize(new java.awt.Dimension(100, 80));

        javax.swing.GroupLayout Picture_POILayout = new javax.swing.GroupLayout(Picture_POI);
        Picture_POI.setLayout(Picture_POILayout);
        Picture_POILayout.setHorizontalGroup(
            Picture_POILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        Picture_POILayout.setVerticalGroup(
            Picture_POILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Information_PanelLayout = new javax.swing.GroupLayout(Information_Panel);
        Information_Panel.setLayout(Information_PanelLayout);
        Information_PanelLayout.setHorizontalGroup(
            Information_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Information_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Information_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Information_PanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Informations_POI)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Information_PanelLayout.createSequentialGroup()
                        .addComponent(Scroll_Information, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(Information_PanelLayout.createSequentialGroup()
                        .addComponent(Picture_POI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(Information_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Info3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Info2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Info1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        Information_PanelLayout.setVerticalGroup(
            Information_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Information_PanelLayout.createSequentialGroup()
                .addComponent(Informations_POI)
                .addGroup(Information_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Information_PanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Info1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Info2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Info3))
                    .addGroup(Information_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Picture_POI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(Scroll_Information, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        Map_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout Map_PanelLayout = new javax.swing.GroupLayout(Map_Panel);
        Map_Panel.setLayout(Map_PanelLayout);
        Map_PanelLayout.setHorizontalGroup(
            Map_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Map_PanelLayout.setVerticalGroup(
            Map_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Research_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        SearchBar.setText("Recherche");

        Result_Research.setColumns(20);
        Result_Research.setRows(5);
        Scroll_Research.setViewportView(Result_Research);

        javax.swing.GroupLayout Research_PanelLayout = new javax.swing.GroupLayout(Research_Panel);
        Research_Panel.setLayout(Research_PanelLayout);
        Research_PanelLayout.setHorizontalGroup(
            Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Research_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchBar)
                    .addComponent(Scroll_Research))
                .addContainerGap())
        );
        Research_PanelLayout.setVerticalGroup(
            Research_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Research_PanelLayout.createSequentialGroup()
                .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_Research))
        );

        Navigation_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Combo_Interface.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Button_AddPOI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/addmark.PNG")));
        Button_AddPOI.setBorderPainted(false);
        Button_AddPOI.setContentAreaFilled(false);

        Button_DelPOI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/delmark.PNG")));
        Button_DelPOI.setBorderPainted(false);
        Button_DelPOI.setContentAreaFilled(false);

        Button_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/fleche_suivant.png")));
        Button_Prev.setBorderPainted(false);
        Button_Prev.setContentAreaFilled(false);

        Button_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/fleche_precedent.png")));
        Button_Next.setBorderPainted(false);
        Button_Next.setContentAreaFilled(false);

        javax.swing.GroupLayout Navigation_PanelLayout = new javax.swing.GroupLayout(Navigation_Panel);
        Navigation_Panel.setLayout(Navigation_PanelLayout);
        Navigation_PanelLayout.setHorizontalGroup(
            Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Combo_Interface, 0, 297, Short.MAX_VALUE)
                    .addGroup(Navigation_PanelLayout.createSequentialGroup()
                        .addComponent(Button_AddPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_DelPOI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Prev, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Navigation_PanelLayout.setVerticalGroup(
            Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation_PanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(Navigation_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Prev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_Next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_AddPOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_DelPOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Combo_Interface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        Menu_Lieux.setText("Lieux");
        MenuBar.add(Menu_Lieux);

        Menu_POI.setText("POI");
        MenuBar.add(Menu_POI);

        Menu_Help.setText("Help");
        MenuBar.add(Menu_Help);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Map_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Navigation_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Information_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(Research_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Map_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Information_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Navigation_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Research_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_AddPOI;
    private javax.swing.JButton Button_DelPOI;
    private javax.swing.JButton Button_Next;
    private javax.swing.JButton Button_Prev;
    private javax.swing.JComboBox Combo_Interface;
    public javax.swing.JLabel Info1;
    public javax.swing.JLabel Info2;
    public javax.swing.JLabel Info3;
    private javax.swing.JPanel Information_Panel;
    private javax.swing.JLabel Informations_POI;
    private javax.swing.JPanel Map_Panel;
    private javax.swing.JMenuBar MenuBar;
    public javax.swing.JMenu Menu_Help;
    public javax.swing.JMenu Menu_Lieux;
    public javax.swing.JMenu Menu_POI;
    private javax.swing.JPanel Navigation_Panel;
    private javax.swing.JPanel Picture_POI;
    private javax.swing.JPanel Research_Panel;
    private javax.swing.JTextArea Result_Research;
    private javax.swing.JScrollPane Scroll_Information;
    private javax.swing.JScrollPane Scroll_Research;
    public javax.swing.JTextField SearchBar;
    private javax.swing.JTextArea Text_Information;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
