/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import valiente.orl2.central.Central;
import valiente.orl2.reproduccion.ListaReproduccion;
import valiente.orl2.reproduccion.PistaReproduccion;
import valiente.orl2.reproductor.Reproductor;
import valiente.orl2.reproductor.Sound;
/**
 *
 * @author camran1234
 */
public class ListasUI extends javax.swing.JFrame {

    private Central central ;
    private Reproductor reproductor;
    private PhytonFrame frame;
    public static String actualPista="";
    public static String actualLista="";
    //Para pistas
    DefaultListModel lmPistas = new DefaultListModel();
    //Para duracion de pistas
    DefaultListModel lmDuration = new DefaultListModel();
    //Para listas
    DefaultListModel lmList = new DefaultListModel();
    //Para la playList
    DefaultListModel lmPlayList = new DefaultListModel();
    /**
     * Creates new form ListasUI
     */
    public ListasUI(PhytonFrame frame) {
        initComponents();
        this.frame = frame;
        initAll();
    }
    
    public void initAll(){
        reproductor = null;
        central = new Central();
        this.actualPista = "";
        this.actualLista = "";
        frame.setVisible(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        addCanciones(central.getPistas());
        addPlayList(central.getPlayList().getlistas());
    }
    
    /**
     * Actualiza los valores de central
     */
    public void update(){
        central = new Central();
    }
    
    private void removeFromPistas(){
        int index= this.listaPistas.getSelectedIndex();
        lmPistas.remove(index);
        lmDuration.remove(index);
        actualPista="";
    }
    
    private void removeFromListas(){
        int index=this.listaListas.getSelectedIndex();
        if(actualLista.equals(listaListas.getSelectedValue())){
            lmPlayList.removeAllElements();
        }
        lmList.remove(index);
    }
    
    public void addCanciones(ArrayList<PistaReproduccion> pistas){
        try {
            lmPistas.removeAllElements();
            lmDuration.removeAllElements();
            for(PistaReproduccion pista:pistas){
                lmPistas.addElement(pista.getName());
                int duracion = pista.getDuracionTotal()/1000;
                int minutos = duracion/60;
                int segundos = (duracion%60)*60;
                lmDuration.addElement(minutos+":"+duracion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addPlayList(ArrayList<ListaReproduccion> listas){
        try {
            lmList.removeAllElements();
            for(ListaReproduccion lista:listas){
                lmList.addElement(lista.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Limpia todas las listas
     */
    public void clearLists(){
        actualPista = "";
        actualLista="";
        try {
            lmPistas.removeAllElements();
        } catch (Exception e) {
        }
        try {
            lmDuration.removeAllElements();
        } catch (Exception e) {
        }
        try {
            lmList.removeAllElements();
        } catch (Exception e) {
        }
        try {
            lmPlayList.removeAllElements();
        } catch (Exception e) {
        }
        
    }
    
    public void showPlayList(String playListName){
        lmPlayList.removeAllElements();
        ArrayList<ListaReproduccion> listas = central.getPlayList().getlistas();
        ArrayList<String> pistas = new ArrayList();
        String isRandom = "[NO ES RANDOM]";
        String isCircular = "[NO ES CIRCULAR]";
        for(ListaReproduccion lista: listas){
            if(playListName.equals(lista.getNombre())){
                pistas = lista.getPistas();
                if(lista.getCircular()){
                    isCircular = "[ES CIRCULAR]";
                }
                if(lista.getRandom()){
                    isRandom = "[ES RANDOM]";
                }
                break;
            }
        }
        for(String pista:pistas){
            lmPlayList.addElement(pista);
        }
        lmPlayList.addElement(isRandom);
        lmPlayList.addElement(isCircular);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaPistas = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaDuracion = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        butonDetenerPista = new javax.swing.JButton();
        buttonModificarPista = new javax.swing.JButton();
        buttonElimnarPista = new javax.swing.JButton();
        butonReproducirPista = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaListas = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaReproduccion = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        buttonModificarLista = new javax.swing.JButton();
        buttonEliminarLista = new javax.swing.JButton();
        buttonReproducirLista = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1316, 955));
        setMinimumSize(new java.awt.Dimension(1316, 955));
        setPreferredSize(new java.awt.Dimension(1316, 955));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(1316, 955));

        jTabbedPane1.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listaPistas.setBackground(new java.awt.Color(51, 51, 51));
        listaPistas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        listaPistas.setFont(new java.awt.Font("Noto Sans CJK JP DemiLight", 0, 14)); // NOI18N
        listaPistas.setForeground(new java.awt.Color(153, 255, 153));
        listaPistas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    try{
                        ListasUI.actualPista = listaPistas.getSelectedValue().toString();
                    }catch(Exception ex){

                    }
                }
            }
        });
        jScrollPane1.setViewportView(listaPistas);
        listaPistas.setModel(lmPistas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 530, 650));

        listaDuracion.setBackground(new java.awt.Color(51, 51, 51));
        listaDuracion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        listaDuracion.setFont(new java.awt.Font("Noto Sans CJK JP DemiLight", 1, 14)); // NOI18N
        listaDuracion.setForeground(new java.awt.Color(153, 255, 153));
        listaDuracion.setEnabled(false);
        jScrollPane2.setViewportView(listaDuracion);
        listaDuracion.setModel(lmDuration);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 159, 650));

        jLabel1.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PISTA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        jLabel2.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DURACION");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 9, -1, -1));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 5));

        butonDetenerPista.setBackground(new java.awt.Color(255, 0, 0));
        butonDetenerPista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        butonDetenerPista.setForeground(new java.awt.Color(255, 255, 255));
        butonDetenerPista.setText("PARAR");
        butonDetenerPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonDetenerPistaActionPerformed(evt);
            }
        });

        buttonModificarPista.setBackground(new java.awt.Color(0, 153, 0));
        buttonModificarPista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        buttonModificarPista.setForeground(new java.awt.Color(255, 255, 255));
        buttonModificarPista.setText("MODIFICAR");
        buttonModificarPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarPistaActionPerformed(evt);
            }
        });

        buttonElimnarPista.setBackground(new java.awt.Color(204, 0, 0));
        buttonElimnarPista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        buttonElimnarPista.setForeground(new java.awt.Color(255, 255, 255));
        buttonElimnarPista.setText("ELIMINAR");
        buttonElimnarPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonElimnarPistaActionPerformed(evt);
            }
        });

        butonReproducirPista.setBackground(new java.awt.Color(51, 51, 255));
        butonReproducirPista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        butonReproducirPista.setForeground(new java.awt.Color(255, 255, 255));
        butonReproducirPista.setText("REPRODUCIR");
        butonReproducirPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonReproducirPistaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonModificarPista)
                .addGap(18, 18, 18)
                .addComponent(buttonElimnarPista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(butonReproducirPista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butonDetenerPista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificarPista)
                    .addComponent(buttonElimnarPista)
                    .addComponent(butonReproducirPista)
                    .addComponent(butonDetenerPista))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 680, 70));

        jTabbedPane1.addTab("Canciones", jPanel2);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PLAYLIST");

        listaListas.setBackground(new java.awt.Color(51, 51, 51));
        listaListas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        listaListas.setFont(new java.awt.Font("Noto Sans CJK JP DemiLight", 0, 14)); // NOI18N
        listaListas.setForeground(new java.awt.Color(153, 255, 153));
        jScrollPane3.setViewportView(listaListas);
        listaListas.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    try{
                        ListasUI.actualLista = listaListas.getSelectedValue().toString();
                        showPlayList(listaListas.getSelectedValue().toString());
                    }catch(Exception ex){

                    }

                }
            }
        });

        listaListas.setModel(lmList);

        listaReproduccion.setBackground(new java.awt.Color(51, 51, 51));
        listaReproduccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        listaReproduccion.setFont(new java.awt.Font("Noto Sans CJK JP DemiLight", 1, 14)); // NOI18N
        listaReproduccion.setForeground(new java.awt.Color(153, 255, 153));
        listaReproduccion.setEnabled(false);
        jScrollPane4.setViewportView(listaReproduccion);
        listaReproduccion.setModel(lmPlayList);

        jLabel4.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LISTA DE REPRODUCCION");

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 5));

        jButton6.setBackground(new java.awt.Color(255, 0, 0));
        jButton6.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("PARAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        buttonModificarLista.setBackground(new java.awt.Color(0, 153, 0));
        buttonModificarLista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        buttonModificarLista.setForeground(new java.awt.Color(255, 255, 255));
        buttonModificarLista.setText("MODIFICAR");
        buttonModificarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarListaActionPerformed(evt);
            }
        });

        buttonEliminarLista.setBackground(new java.awt.Color(204, 0, 0));
        buttonEliminarLista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        buttonEliminarLista.setForeground(new java.awt.Color(255, 255, 255));
        buttonEliminarLista.setText("ELIMINAR");
        buttonEliminarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarListaActionPerformed(evt);
            }
        });

        buttonReproducirLista.setBackground(new java.awt.Color(51, 51, 255));
        buttonReproducirLista.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 14)); // NOI18N
        buttonReproducirLista.setForeground(new java.awt.Color(255, 255, 255));
        buttonReproducirLista.setText("REPRODUCIR");
        buttonReproducirLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReproducirListaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonModificarLista)
                .addGap(18, 18, 18)
                .addComponent(buttonEliminarLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(buttonReproducirLista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificarLista)
                    .addComponent(buttonEliminarLista)
                    .addComponent(buttonReproducirLista)
                    .addComponent(jButton6))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(85, 85, 85))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listas", jPanel3);

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setRollover(true);

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Noto Sans CJK HK Thin", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setText("EDITOR DE CODIGO");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 10, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(reproductor!=null){
                reproductor.deleteChannels();
                reproductor.interrupt();
            }
        } catch (Exception e) {
        }
        this.setVisible(false);
        frame.setVisible(true);
        try {
            this.clearLists();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonModificarPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarPistaActionPerformed
        //Para modificar una pista
        if(actualPista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            frame.modificarValorPista(central.getTextPista(actualPista), actualPista);
            frame.setVisible(true);
            this.setVisible(false);
            try {
                clearLists();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_buttonModificarPistaActionPerformed

    private void buttonModificarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarListaActionPerformed
        if(actualLista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            frame.modificarValorLista(central.getTextLista(actualLista), actualLista);
            frame.setVisible(true);
            this.setVisible(false);
            try {
                clearLists();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_buttonModificarListaActionPerformed

    private void buttonElimnarPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonElimnarPistaActionPerformed
        if(actualPista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            central.deletePista(actualPista);
            removeFromPistas();
        }
    }//GEN-LAST:event_buttonElimnarPistaActionPerformed

    private void buttonEliminarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarListaActionPerformed
        if(actualLista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            central.deleteLista(actualLista);
            removeFromListas();
        }
    }//GEN-LAST:event_buttonEliminarListaActionPerformed

    private void butonReproducirPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonReproducirPistaActionPerformed
        if(actualPista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            if(reproductor==null){
                try {
                    reproductor = new Reproductor(actualPista);
                    Thread.sleep(50);
                } catch (Exception e) {
                }
            }
            if(reproductor.reproduciendo){                    
                if(!reproductor.getCancionActual().equals(actualPista)){                
                    //Cargamos la nueva cancion y corremos             
                    try {
                        
                        reproductor.deleteChannels();reproductor.interrupt();
                        reproductor = new Reproductor(actualPista);
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    PistaReproduccion pista = central.getPistaReproduccion(actualPista);                    
                    reproductor.deleteChannels();
                    reproductor.generateChannels(pista);                    
                    reproductor.runSong();                                    
                }else{
                    reproductor.startRe();
                    if(!reproductor.reproduciendo){
                        try {
                            reproductor.deleteChannels();reproductor.interrupt();
                            reproductor = new Reproductor(actualPista);
                            Thread.sleep(50);
                        } catch (Exception e) {
                        }
                        PistaReproduccion pista = central.getPistaReproduccion(actualPista);                    
                        reproductor.deleteChannels();
                        reproductor.generateChannels(pista);                    
                        reproductor.runSong();                                    
                    }
                }
            }else{            
                //Cargamos la nueva cancion y corremos                    
                    try {
                        reproductor.deleteChannels();reproductor.interrupt();
                        reproductor = new Reproductor(actualPista);
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    PistaReproduccion pista = central.getPistaReproduccion(actualPista);                    
                    reproductor.deleteChannels();
                    reproductor.generateChannels(pista);                    
                    reproductor.runSong();                    
            }   
        }
    }//GEN-LAST:event_butonReproducirPistaActionPerformed

    private void butonDetenerPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonDetenerPistaActionPerformed
        if(reproductor!=null){
            Sound.reproducir=false;
        }
    }//GEN-LAST:event_butonDetenerPistaActionPerformed

    private void buttonReproducirListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReproducirListaActionPerformed
                
        if(actualLista.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Selecciona una lista primero","Lista no disponible",JOptionPane.WARNING_MESSAGE);
        }else{
            if(reproductor==null){
                try {
                    reproductor = new Reproductor(actualLista);
                    Thread.sleep(50);
                } catch (Exception e) {
                }
                
            }
            if(reproductor.reproduciendo){                    
                if(!reproductor.getCancionActual().equals(actualLista)){                
                    //Cargamos la nueva cancion y corremos             
                    try {
                        reproductor.deleteChannels();reproductor.interrupt();
                        reproductor = new Reproductor(actualLista);
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    ListaReproduccion lista = central.getListaReproduccion(actualLista);                    
                    reproductor.generateListas(lista, central);
                    reproductor.start();
                    
                }else{
                    reproductor.startRe();
                    if(!reproductor.reproduciendo){
                        try {
                        reproductor.deleteChannels();reproductor.interrupt();
                        reproductor = new Reproductor(actualLista);
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    ListaReproduccion lista = central.getListaReproduccion(actualLista);                    
                    reproductor.generateListas(lista, central);
                    reproductor.start();                                 
                    }
                }
            }else{            
                //Cargamos la nueva cancion y corremos                    
                try {
                        reproductor.deleteChannels();reproductor.interrupt();
                        reproductor = new Reproductor(actualLista);
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    ListaReproduccion lista = central.getListaReproduccion(actualLista);                    
                    reproductor.generateListas(lista, central);
                    reproductor.start();
            }   
        }
    }//GEN-LAST:event_buttonReproducirListaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(reproductor!=null){
            Sound.reproducir=false;
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(ListasUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListasUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListasUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListasUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListasUI(new PhytonFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonDetenerPista;
    private javax.swing.JButton butonReproducirPista;
    private javax.swing.JButton buttonEliminarLista;
    private javax.swing.JButton buttonElimnarPista;
    private javax.swing.JButton buttonModificarLista;
    private javax.swing.JButton buttonModificarPista;
    private javax.swing.JButton buttonReproducirLista;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JList<String> listaDuracion;
    private javax.swing.JList<String> listaListas;
    private javax.swing.JList<String> listaPistas;
    private javax.swing.JList<String> listaReproduccion;
    // End of variables declaration//GEN-END:variables
}
