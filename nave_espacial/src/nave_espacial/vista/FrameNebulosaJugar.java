/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.vista;

import nave_espacial.controlador.ControladorNebulosa;
import nave_espacial.modelo.SistemaPlanetario;
import static nave_espacial.controlador.ControladorInicio.nave;

/**
 *
 * @author Lenovo
 */
public class FrameNebulosaJugar extends javax.swing.JFrame {

    private ControladorNebulosa nebulosaControl;

    /**
     * Creates new form FrameNebulosaJugar
     */
    public FrameNebulosaJugar() {
        initComponents();
        this.setResizable(false);
        definirFrame(nebulosaControl);
        setResizable(false);
        this.panelNebulosa1.getCrearRele().setVisible(false);
        this.panelNebulosa1.getCrearEnemigos().setVisible(false);
    }

    public void definirFrame(ControladorNebulosa controlador) {
        this.nebulosaControl = controlador;
        parametrizarFrame();
        this.setVisible(true);
        this.toFront();
        this.requestFocus();
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

        panelNebulosa1 = new nave_espacial.vista.PanelNebulosa();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        panelNebulosa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNebulosa1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNebulosa1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNebulosa1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelNebulosa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNebulosa1MouseClicked
        SistemaPlanetario sPlanetario;
        if ((sPlanetario = nebulosaControl.reconocerSistemaPlanetario(evt.getPoint())) != null) {
            if (evt.getClickCount() == 2) {
                nebulosaControl.crearSistemaPlanetario(sPlanetario, "jugar");
                this.dispose();
            }
        }
        nave.moverNave(evt.getPoint());
    }//GEN-LAST:event_panelNebulosa1MouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println("tecla"+ evt);
        if(evt.getKeyCode()==67){
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
            java.util.logging.Logger.getLogger(FrameNebulosaJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameNebulosaJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameNebulosaJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameNebulosaJugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameNebulosaJugar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private nave_espacial.vista.PanelNebulosa panelNebulosa1;
    // End of variables declaration//GEN-END:variables
  public nave_espacial.vista.PanelNebulosa getPanelNebulosa() {
        return panelNebulosa1;
    }
}