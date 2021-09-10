/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;

import valiente.orl2.webconnection.WebConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.Utilities;
import valiente.orl2.Lista.Manejador.ListaAnalyzer;
import valiente.orl2.UI.TextLineNumber;
import valiente.orl2.phyton.error.LexicalError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.UI.CaretControl;
import valiente.orl2.central.FileManager;


/**
 *
 * @author camran1234
 */
public class PhytonFrame extends javax.swing.JFrame {
    private JTextPane textPaneReferenced;
    private JPanel listaPanel;
    private ListasUI listasUI;
    public static JFrame frame= null;
    public static JTextArea areaTexto;
    private boolean isCompilador=true;
    public static final int jumps=4;
    public JPanel errorArea;
    String[] headers = {"No.", "TIPO","Description", "LINEA", "COLUMNA"};    
    DefaultTableModel model = new DefaultTableModel(null, headers);
    private JTable table = new JTable(model);
    private JScrollPane scrollPane = new JScrollPane(table);
     private static DefaultTableModel tableModel;
     private static JTable newTable;
     public static boolean compile=false;
     private String path="";
    private boolean guardado = false;
    private String nombreArchivo;
      /**
     * Creates new form PhytonFrame
     */
    public PhytonFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        newTable = table;
        frame = this;
        this.areaTexto = jTextArea1;
        this.errorArea = panelErrores;
        tableModel = model;
        newTable.setRowSelectionAllowed(false);
        listasUI = new ListasUI(this);
        initWebConnection();
        
        textPaneReferenced.addKeyListener(new KeyListener() {
            
        @Override
        public void keyTyped(KeyEvent arg0) {
            guardado = false;
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            //empty

        }

        @Override
        public void keyPressed(KeyEvent arg0) {
            //emty

        }
});
    }
    
    private void initWebConnection(){
        WebConnection.startServer(8080);
    }

    public void modificarValorPista(String text, String pista){
        this.isCompilador=true;
        labelNombre.setText(pista);
        jComboBox1.setSelectedIndex(0);
        textPaneReferenced.setText(text);
    }
    
    public void modificarValorLista(String text, String lista){
        this.isCompilador=false;
        labelNombre.setText(lista);
        jComboBox1.setSelectedIndex(1);
        textPaneReferenced.setText(text);
    }
    
    public static void resetTable(){
        int rows = tableModel.getRowCount();
        for(int index=0; index<rows; index++){
            tableModel.removeRow(0);
        }
    }
    
    public static void setTable(String[][] content){
        //jTable1.removeRowSelectionInterval(0, jTable1.getRowCount());
        
        
        for(int index=0; index<content.length; index++){
            String[] aux = content[index];
            Vector<Object> data = new Vector<Object>();
            data.add(aux[0]);
            data.add(aux[1]);
            data.add(aux[2]);
            data.add(aux[3]);
            data.add(aux[4]);
            tableModel.addRow(data);
            
        }
        //Adjusting height
        for (int row = 0; row < newTable.getRowCount(); row++){
            int rowHeight = newTable.getRowHeight();
            
            for (int column = 0; column < newTable.getColumnCount(); column++){
                Component comp = newTable.prepareRenderer(newTable.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            if(rowHeight <30){
                rowHeight = 40;
            }
            System.out.println(rowHeight );
            newTable.setRowHeight(row, rowHeight);
        }
        //Adjusting width
        final TableColumnModel columnModel = newTable.getColumnModel();
    for (int column = 0; column < newTable.getColumnCount(); column++) {
        int width = 15; // Min width
        for (int row = 0; row < newTable.getRowCount(); row++) {
            TableCellRenderer renderer = newTable.getCellRenderer(row, column);
            Component comp = newTable.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width +1 , width);
        }
        if(width > 300)
            width=300;
        columnModel.getColumn(column).setPreferredWidth(width);
    }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        textField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelErrores = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelLenguaje = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        labelNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 1000));
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 400));

        textArea.setBackground(new java.awt.Color(255, 255, 255));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Inconsolata Expanded", 0, 16)); // NOI18N
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);
        JTextPane textPane = new JTextPane();
        textPaneReferenced = textPane;
        jScrollPane1 = new JScrollPane(textPane);
        TextLineNumber tln = new TextLineNumber(textPane, textField);
        jScrollPane1.setRowHeaderView( tln );
        textArea.add(jScrollPane1);

        textField.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jScrollPane2.setToolTipText("LOG");

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Source Code Pro Light", 1, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2);

        panelErrores.setBackground(new java.awt.Color(255, 255, 255));
        panelErrores.setForeground(new java.awt.Color(255, 255, 255));
        panelErrores.setLayout(new java.awt.BorderLayout(0, 1));
        jPanel3.add(panelErrores);
        panelErrores.add(scrollPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setFloatable(false);
        jToolBar1.setForeground(new java.awt.Color(153, 153, 153));
        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setPreferredSize(new java.awt.Dimension(614, 32));
        jToolBar1.setOpaque(true);
        jToolBar1.setBackground(Color.WHITE);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 255), new java.awt.Color(204, 204, 255)));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton7.setBackground(new java.awt.Color(255, 255, 102));
        jButton7.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("NUEVO");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("ABRIR");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("GUARDAR");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("COMPILAR");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("GENERAR");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("REPRODUCTOR");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jToolBar1.add(jPanel4);

        panelLenguaje.setBackground(new java.awt.Color(204, 204, 255));
        panelLenguaje.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Seleccionar lenguaje:");

        jComboBox1.setBackground(new java.awt.Color(153, 153, 255));
        jComboBox1.setFont(new java.awt.Font("Noto Sans CJK JP Medium", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pistas", "Listas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        labelNombre.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelLenguajeLayout = new javax.swing.GroupLayout(panelLenguaje);
        panelLenguaje.setLayout(panelLenguajeLayout);
        panelLenguajeLayout.setHorizontalGroup(
            panelLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLenguajeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        panelLenguajeLayout.setVerticalGroup(
            panelLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLenguajeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLenguajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jToolBar1.add(panelLenguaje);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1316, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1316, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(906, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(0, 39, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if(isCompilador){
                generarPistas(false);
            }else{
                generarListas(false);
            }
            
        }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(path.equalsIgnoreCase("")){
            try{
            String path="";
                JFileChooser fileOpener = new JFileChooser();
                fileOpener.addChoosableFileFilter(new FileNameExtensionFilter("txt","txt"));
                fileOpener.setAcceptAllFileFilterUsed(false);
                int seleccion = fileOpener.showOpenDialog(this);
                if(seleccion == APPROVE_OPTION){
                    path = fileOpener.getSelectedFile().getPath();
                    FileManager manager = new FileManager();
                    String text = textPaneReferenced.getText();
                    manager.createFile(path, text);
                    this.guardado = true;
                    this.path = path;
                    File file = new File(path);
                    this.nombreArchivo = file.getName();
                    labelNombre.setText(nombreArchivo);
                }

            }catch(Throwable e){
                e.printStackTrace();
            }
        } else{
            FileManager manager = new FileManager();
            String text = textPaneReferenced.getText();                    
            manager.createFile(path, text);            
            this.guardado = true;
            JOptionPane.showMessageDialog(this, "Archivo Guardado", "",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public boolean youSure(){
        if(guardado){
            guardado = false;
            return true;
        }else{
            if(!textPaneReferenced.getText().equalsIgnoreCase("")){
                int n = JOptionPane.showConfirmDialog(
                                PhytonFrame.frame, "No has guardado tu archivo, si no lo has guardado se perderan tus datos,\n ¿Deseas continuar?",
                                "ALERTA",
                                JOptionPane.YES_NO_OPTION);                    
                if (n == JOptionPane.YES_OPTION) {        
                    guardado = false;
                    return true;
                } else if (n == JOptionPane.NO_OPTION) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(youSure()){
            try{
                String path="";
                JFileChooser fileOpener = new JFileChooser();
                fileOpener.setAcceptAllFileFilterUsed(false);
                int seleccion = fileOpener.showOpenDialog(this);
                if(seleccion == APPROVE_OPTION){
                    path = fileOpener.getSelectedFile().getPath();
                    FileManager manager = new FileManager();
                    String text = manager.downloadFile(path);
                    textPaneReferenced.setText(text);
                    this.path = path;
                    this.guardado=true;
                    File file = new File(path);
                    this.nombreArchivo = file.getName();
                    labelNombre.setText(nombreArchivo);
                }

            }catch(Throwable e){
                e.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(youSure()){ 
                try{
                    String path="";
                    JFileChooser fileOpener = new JFileChooser();
                    fileOpener.addChoosableFileFilter(new FileNameExtensionFilter("txt","txt"));
                    fileOpener.setAcceptAllFileFilterUsed(false);
                    int seleccion = fileOpener.showOpenDialog(this);
                    if(seleccion == APPROVE_OPTION){
                        path = fileOpener.getSelectedFile().getPath();
                        FileManager manager = new FileManager();
                        manager.createFile(path, "");
                        textPaneReferenced.setText("");
                        this.path = path;
                        this.guardado = true;
                        File file = new File(path);
                        this.nombreArchivo = file.getName();
                        labelNombre.setText(nombreArchivo);
                    }

                }catch(Throwable e){
                    e.printStackTrace();
                }
            }
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void generarPistas(boolean mode){
        try {          
            compile = mode;            
            int rows = tableModel.getRowCount();
            for(int index=0; index<rows; index++){            
                tableModel.removeRow(0);
            }            
            this.jTextArea1.setText("");
            String text = textPaneReferenced.getText();            
            text = text + "\n\n\n\n";
            int totalCharacters = textPaneReferenced.getText().length();             
            int lineCount = (totalCharacters == 0) ? 1 : 0;
            int offset = totalCharacters;             
            while (offset > 0) {
                offset = Utilities.getRowStart(textPaneReferenced, offset) - 1;                
                lineCount++;
            }                                    
            SyntaxError.lastLine =lineCount-jumps;                       
            if(text.equalsIgnoreCase("")){
                throw new Exception("Texto vacio");            
            }                
            Phyton parser = new Phyton();            
            parser.parse(text);
        } catch (Exception e) {       
            JOptionPane.showMessageDialog(this, e.getMessage());            
        }
    }
    
    private void generarListas(boolean mode){
        try {          
            compile = mode;            
            int rows = tableModel.getRowCount();
            for(int index=0; index<rows; index++){            
                tableModel.removeRow(0);
            }            
            //Es el log
            this.jTextArea1.setText("");
            String text = textPaneReferenced.getText();            
            //Para obtener la linea
            int totalCharacters = textPaneReferenced.getText().length();             
            int lineCount = (totalCharacters == 0) ? 1 : 0;
            int offset = totalCharacters;             
            while (offset > 0) {
                offset = Utilities.getRowStart(textPaneReferenced, offset) - 1;                
                lineCount++;
            }                                    
            SyntaxError.lastLine =lineCount;                       
            SyntaxError.lastColumn = 0;
            
            if(text.equalsIgnoreCase("")){
                throw new Exception("Texto vacio");            
            }                
            ListaAnalyzer analyzer = new ListaAnalyzer(text);
            analyzer.execute();
        } catch (Exception e) {       
            JOptionPane.showMessageDialog(this, e.getMessage());            
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(isCompilador){
            generarPistas(true);
        }else{
            generarListas(true);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String text = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
        if(text.equalsIgnoreCase("Listas")){
            this.isCompilador=false;
        }else{
            this.isCompilador=true;
        } 
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        listasUI.initAll();
        listasUI.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhytonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhytonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhytonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhytonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhytonFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JPanel panelErrores;
    private javax.swing.JPanel panelLenguaje;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
