/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.vista;

import nave_espacial.controlador.ControladorSistemaPlanetario;
import static nave_espacial.controlador.ControladorInicio.nave;

/**
 *
 * @author Lenovo
 */
public class FrameSistemaPlanetarioJugar extends javax.swing.JFrame {

    private ControladorSistemaPlanetario sistemaPlanetarioControl;

    /**
     * Creates new form FrameSistemaPlanetarioJugar
     */
    public FrameSistemaPlanetarioJugar() {
        initComponents();
        this.setResizable(false);
        definirFrame(sistemaPlanetarioControl);
    }

    public void definirFrame(ControladorSistemaPlanetario controlador) {
        sistemaPlanetarioControl = controlador;
        parametrizarFrame();
        this.setVisible(true);
    }

    private void parametrizarFrame() {
        this.setSize(900, 506);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSistemaPlanetario1 = new nave_espacial.vista.PanelSistemaPlanetario();
        panelSistemaPlanetario2 = new nave_espacial.vista.PanelSistemaPlanetario();

        javax.swing.GroupLayout panelSistemaPlanetario1Layout = new javax.swing.GroupLayout(panelSistemaPlanetario1);
        panelSistemaPlanetario1.setLayout(panelSistemaPlanetario1Layout);
        panelSistemaPlanetario1Layout.setHorizontalGroup(
            panelSistemaPlanetario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelSistemaPlanetario1Layout.setVerticalGroup(
            panelSistemaPlanetario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        panelSistemaPlanetario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSistemaPlanetario2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSistemaPlanetario2Layout = new javax.swing.GroupLayout(panelSistemaPlanetario2);
        panelSistemaPlanetario2.setLayout(panelSistemaPlanetario2Layout);
        panelSistemaPlanetario2Layout.setHorizontalGroup(
            panelSistemaPlanetario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelSistemaPlanetario2Layout.setVerticalGroup(
            panelSistemaPlanetario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSistemaPlanetario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSistemaPlanetario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelSistemaPlanetario2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSistemaPlanetario2MouseClicked
    
    }//GEN-LAST:event_panelSistemaPlanetario2MouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == 67) {
            ComprarArma dialog = new ComprarArma(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(FrameSistemaPlanetarioJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameSistemaPlanetarioJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameSistemaPlanetarioJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameSistemaPlanetarioJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameSistemaPlanetarioJugar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private nave_espacial.vista.PanelSistemaPlanetario panelSistemaPlanetario1;
    private nave_espacial.vista.PanelSistemaPlanetario panelSistemaPlanetario2;
    // End of variables declaration//GEN-END:variables
   /**
     * @return the panelSistemaPlanetario1
     */
    public nave_espacial.vista.PanelSistemaPlanetario getPanelSistemaPlanetario() {
        return panelSistemaPlanetario2;
    }
}
