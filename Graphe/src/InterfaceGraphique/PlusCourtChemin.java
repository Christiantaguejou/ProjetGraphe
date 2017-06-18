/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

import Communes.Commune;
import Communes.CsvCommunes;
import Graphes.Aetoile;
import Graphes.Dijkstra;
import Graphes.Graphe;
import static Graphes.Graphe.choixTri.MAX;
import static Graphes.Graphe.choixTri.MIN;
import static Graphes.Graphe.triPar.DISTANCEOISEAU;
import static Graphes.Graphe.triPar.DISTANCEREELLE;
import static Graphes.Graphe.triPar.POPULATION;
import Graphes.Sommet;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.ZoomService;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.ZoomListener;
import static com.teamdev.jxbrowser.chromium.internal.ipc.ChannelType.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import org.json.JSONException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.ZoomEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.json.JSONException;

/**
 *
 * @author markk
 */
public class PlusCourtChemin extends javax.swing.JFrame {

    Browser browser = new Browser();
    BrowserView view = new BrowserView(browser);
    Browser browser1 = new Browser();
    BrowserView view1 = new BrowserView(browser1);
    private static final String API_KEY = "AIzaSyBDD2w5eLMyagQpQq945LJep2PGr2i5ZuU";
    BrowserContext context = browser.getContext();
    ZoomService zoomService = context.getZoomService();
    BrowserContext context1 = browser1.getContext();
    ZoomService zoomService1 = context1.getZoomService();

    /**
     * Creates new form PlusCourtChemin
     */
    public PlusCourtChemin() {
        initComponents();
        this.jComboBox4.setEnabled(false);
        this.jTextField1.setEnabled(false);
        this.jComboBox5.setEnabled(false);
        this.jComboBox6.setEnabled(false);
        this.jTextField2.setEnabled(false);
        zoomService.addZoomListener(new ZoomListener() {
            @Override
            public void onZoomChanged(com.teamdev.jxbrowser.chromium.events.ZoomEvent ze) {
            }

            public void onZoomChanged(ZoomEvent event) {
            }

        });
        browser.addLoadListener(new LoadAdapter() {
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    event.getBrowser().setZoomLevel(2.0);
                }
            }
        });
        //this.jPanel2.add(view,BorderLayout.CENTER);
        this.jTabbedPane1.setComponentAt(0, view);
        this.jTabbedPane1.setComponentAt(1, view1);
        //browser.loadURL("http://www.google.com");
        // browser.loadURL("https://maps.googleapis.com/maps/api/staticmap?center=Boston,MA&visible=77+Massachusetts+Ave,Cambridge,MA%7CHarvard+Square,Cambridge,MA&size=512x512");

    }
    // Listen to zoom changed events

//
    Map myAttribute;
    List<String> liste = CsvCommunes.readFile(new File("../doc/CommunesFrance.csv"));
    ArrayList<Commune> listeCommunes = CsvCommunes.tableau(liste);
    //System.out.println("suis la");
    Commune paris = new Commune("paris", "PARIS", 2243833, 2.34445, 48.86);
    Sommet sParis = new Sommet(paris);
//    Graphe graphe = new Graphe(listeCommunes, Graphe.triPar.POPULATION, Graphe.choixTri.MIN, 50000, sParis);
    Graphe g;
    boolean mintripop = false;
    boolean maxtripop = false;
    int valuetripop = 0;
    int valuetriOiseau = 0;
    boolean mintridist = false;
    boolean maxtridist = false;
    int valuetrireel = 0;
    int typetridistance = 0;

//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(809, 593));

        jLabel1.setText("Départ :");

        jLabel2.setText("Arrivée :");

        jLabel3.setText("Choix d'algo :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dijkstra", "A Etoile" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton1.setText("GO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Tri Pop");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MIN", "MAX", " " }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jTextField1.setText("50000");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Valeur");

        jCheckBox2.setText("Tri Distance");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vol d'oiseau", "Distance réelle", " " }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel5.setText("MAX/MIN");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MIN", "MAX" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel6.setText("Valeur :");

        jTextField2.setText("160");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Visualisation Graphe", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Visualisation Trajet", jPanel2);

        jTextField3.setText("grasse");

        jTextField4.setText("saint-quentin");

        jButton2.setText("Visualiser Graphe");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("zoom+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("zoom-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBox2)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<Sommet> liste = new ArrayList<>();
        if (jTextField3.getText().equals("") || jTextField4.getText().equals("")) {

        } else {
            System.out.println("ici3");
            Sommet depart = new Sommet(new Commune(jTextField3.getText()));
            Sommet arrive = new Sommet(new Commune(jTextField4.getText()));
           boolean dep = g.getSommets().contains(depart);
           boolean arr = g.getSommets().contains(arrive);
           //boolean dep=true;
           //boolean arr= true;
            if (dep && arr) {
                System.out.println("ici4");
                System.out.println(this.g.getSommets().indexOf(depart));
                depart = this.g.getSommets().get(this.g.getSommets().indexOf(depart));
                arrive = this.g.getSommets().get(this.g.getSommets().indexOf(arrive));
                System.out.println("ici5");
                switch(this.jComboBox3.getSelectedIndex()){
                    case 0:
                        liste=Dijkstra._Dijkstra(g, depart, arrive);
                        break;
                    case 1:
                        liste=Aetoile.algo(depart, arrive, typetridistance);
                        break;
                    default:break;
                
                }
            browser1.loadURL("https://maps.googleapis.com/maps/api/staticmap?" + "center=46.6,1.9&maptype=roadmap&size=800x440&zoom=5&" + this.generateMarkers(liste)+this.generatePath((LinkedList<Sommet>) liste) + "&key=" + API_KEY);

            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (!checkBox.isSelected()) {
            this.jComboBox4.setEnabled(false);
            this.jTextField1.setEnabled(false);
        } else {
            this.jComboBox4.setEnabled(true);
            this.jTextField1.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        JComboBox comboBox = (JComboBox) evt.getSource();
        if (comboBox.getSelectedItem().equals("MIN")) {
            this.mintripop = true;
        } else if (comboBox.getSelectedItem().equals("MAX")) {
            this.maxtripop = true;
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
        JComboBox comboBox = (JComboBox) evt.getSource();
        if (comboBox.getSelectedIndex() == 0) {
            this.typetridistance = 1;//vol d'oiseau
        } else if (comboBox.getSelectedIndex() == 1) {
            this.typetridistance = 2;//distance réelle
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
        JComboBox comboBox = (JComboBox) evt.getSource();
        if (comboBox.getSelectedIndex() == 0) {
            this.mintridist = true;//vol d'oiseau
        } else if (comboBox.getSelectedIndex() == 1) {
            this.maxtridist = true;//dist reelle
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (!checkBox.isSelected()) {
            this.jComboBox5.setEnabled(false);
            this.jComboBox6.setEnabled(false);
            this.jTextField2.setEnabled(false);
        } else {
            this.jComboBox5.setEnabled(true);
            this.jComboBox6.setEnabled(true);
            this.jTextField2.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        JTextField jTextField = (JTextField) evt.getSource();
        this.valuetripop = Integer.parseInt(jTextField.getText());
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        JTextField jTextField = (JTextField) evt.getSource();
        this.valuetripop = Integer.parseInt(jTextField.getText());
    }//GEN-LAST:event_jTextField2ActionPerformed

    private String generateMarkers(List<Sommet> list) {
        StringBuilder sb = new StringBuilder();
        String markerStyle = "markers=size:tiny|color:red";
        sb.append(markerStyle);
        for (Sommet s : list) {
            sb.append("|").append(s.getCommune().getLatitude()).append(",").append(s.getCommune().getLongitude());
        }
        System.out.println(sb);
        return sb.toString();
    }

    private String generatePath(LinkedList<Sommet> list) {
        StringBuilder sb = new StringBuilder();
        String pathStyle = "color:blue|weight:1";
        int i = 0, j = 0;
        sb.append("&path=");
        sb.append(pathStyle);
        while (!list.isEmpty() ) {
            Sommet s = list.remove();
            sb.append("|").append(s.getCommune().getLatitude()).append(",").append(s.getCommune().getLongitude());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Graphe.choixTri tri1 = MIN;
        Graphe.choixTri tri2 = MIN;
        boolean choixTriPop = false;
        boolean choixTriDist = false;
        int val1 = 0;
        int val2 = 0;
        Graphe.triPar typetridist = DISTANCEOISEAU;
        if (this.jCheckBox1.isSelected()) {
            choixTriPop = true;
            if (this.maxtripop) {
                tri1 = MAX;
            } else if (this.mintripop) {
                tri1 = MIN;
            }
            val1 = Integer.parseInt(this.jTextField1.getText());
        }
        if (this.jCheckBox2.isSelected()) {
            choixTriDist = true;
            if (this.typetridistance == 1) {
                typetridist = DISTANCEOISEAU;
            } else if (this.typetridistance == 2) {
                typetridist = DISTANCEREELLE;
            }
            if (this.maxtridist) {
                tri2 = MAX;
            } else if (this.mintridist) {
                tri2 = MIN;
            }
            val2 = Integer.parseInt(this.jTextField2.getText());
        }
        if (this.jCheckBox2.isSelected() || this.jCheckBox1.isSelected()) {
            try {
                this.g = new Graphe(this.listeCommunes, choixTriPop, tri1, choixTriDist, typetridist, tri2, val1, val2);
            } catch (IOException ex) {
                Logger.getLogger(PlusCourtChemin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(PlusCourtChemin.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(g.getSommets());
            System.out.println("icihein");
            browser.loadURL("https://maps.googleapis.com/maps/api/staticmap?" + "center=46.6,1.9&maptype=roadmap&size=800x440&zoom=5&" + this.generateMarkers(g.getSommets()) + "&key=" + API_KEY);
            System.out.println("fini");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //JButton j = (JButton) evt.getSource();
        //if(j.isSelected()) {
        // System.out.println("hey");
        this.browser.zoomIn();
        //  }  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.browser.zoomOut();
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
            java.util.logging.Logger.getLogger(PlusCourtChemin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlusCourtChemin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlusCourtChemin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlusCourtChemin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlusCourtChemin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
