/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.util.Random;
import nave_espacial.modelo.Gasolinera;
import nave_espacial.modelo.Planeta;
import nave_espacial.modelo.ViaLactea;
import static nave_espacial.controlador.ControladorInicio.nave;

/**
 *
 * @author Lenovo
 */
public class PanelPlaneta extends javax.swing.JPanel {

    private List<ImageIcon> imagenesFondo;
    private List<ImageIcon> imagenesBarraM;
    private Gasolinera gasolinera;
    private ImageIcon imagenAtras;
    private ViaLactea viaLactea;
    private Planeta planeta;
    private ImageIcon imagenPlanetaAletoria;
    private ImageIcon imagenEnemigosNodriza;
    private ImageIcon imagenEnemigosAvanzada;
    private ImageIcon imagenEnemigosExplorada;
    private int indiceImagen;
    private int mayorCantidadMaterial;
    private ImageIcon imagenTeletransportador;
    private boolean atras;

    /**
     * Creates new form PanelPlaneta
     */
    public PanelPlaneta() {
        initComponents();
        cargarImagenes();
    }

    private void cargarImagenes() {
        indiceImagen = 0;
        imagenesFondo = new LinkedList<>();
        imagenesBarraM = new LinkedList<>();
        imagenAtras = new ImageIcon(getClass().getResource("imagenes/volver.png"));
        Random r = new Random();
        int dato = (r.nextInt(4) + 1);
        imagenEnemigosNodriza = new ImageIcon(getClass().getResource("imagenes/Enemigos/naveNodriza.png"));
        imagenEnemigosAvanzada = new ImageIcon(getClass().getResource("imagenes/Enemigos/naveAvanzada.png"));
        imagenEnemigosExplorada = new ImageIcon(getClass().getResource("imagenes/Enemigos/naveExplorada.png"));
        imagenTeletransportador = new ImageIcon(getClass().getResource("imagenes/tiposPlanetas/teletransportador.png"));
        imagenPlanetaAletoria = new ImageIcon(getClass().getResource("imagenes/tiposPlanetas/Planeta" + dato + ".png"));
        for (int i = 0; i < 36; i++) {
            ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes/fondoPlanetas/" + i + ".gif"));
            imagenesFondo.add(imagen);
        }
        for (int i = 0; i < 6; i++) {
            ImageIcon imagenBarra = new ImageIcon(getClass().getResource("imagenes/tiposPlanetas/barraMaterial/" + i + ".png"));
            imagenesBarraM.add(imagenBarra);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        pintarFondo(g);
        pintaPlanetaElegido(g);
        pintarMaterial(g);
        pintaRele(g);
        pintaGasolinera(g);
        pintarAtras(g);
        pintaEnemigo(g);
        pintarnave(g);

        if (nave != null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 436, 900, 50);
            pintarDatosnave(g);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanelViaLactea.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }

    private void pintarGasolinera(Graphics g) {
        if (gasolinera != null) {
            g.drawImage(gasolinera.getImagen().getImage(), 800, 520, this);
        }
    }

    private void pintarnave(Graphics g) {
        if (nave != null) {
            g.drawImage(nave.getImagen().getImage(), nave.getX(), nave.getY(), 100, 46, this);
        }
    }

    private void pintarAtras(Graphics g) {
        if (isAtras()) {
            g.drawImage(this.imagenAtras.getImage(), 0, 253, this);
        }
    }

    private void pintarFondo(Graphics g) {
        if (indiceImagen < 36) {
            g.drawImage(imagenesFondo.get(indiceImagen).getImage(), 0, 0, 1366, 768, this);
            indiceImagen++;
        } else {
            indiceImagen = 0;
        }
    }

    private void pintaPlanetaElegido(Graphics g) {
        g.drawImage(planeta.getImagenPlaneta().getImage(), (1366 / 2) - 275, 0, 550, 550, this);
    }

    private void pintarMaterial(Graphics g) {
        int indicePosXInicial = 10;
        int indicePosYInicial = 30;
        int indiceMaterialMostrado = 0;
        HashMap<String, Integer> materiales = this.planeta.getMateriales();
        double indiceCrecimientoBarra = ((double) mayorCantidadMaterial / 5);
        for (String llave : materiales.keySet()) {
            g.setColor(Color.WHITE);
            g.drawString(llave + " " + materiales.get(llave), indicePosXInicial, indicePosYInicial + (60 * indiceMaterialMostrado) - 5);
            int indiceBarra = (int) (materiales.get(llave) / indiceCrecimientoBarra);
            g.drawImage(imagenesBarraM.get(indiceBarra).getImage(), indicePosXInicial, indicePosYInicial + (60 * indiceMaterialMostrado), this);
            indiceMaterialMostrado++;
        }
    }

    private void pintaRele(Graphics g) {
        if (planeta.getTeletransportador() != null) {
            g.drawImage(imagenTeletransportador.getImage(), 10, 330, 100, 100, this);
        }
    }

    private void pintaGasolinera(Graphics g) {
        if (planeta.getGasolinera_del_sistema() != null) {
            g.drawImage(planeta.getGasolinera_del_sistema().getImagen().getImage(), 767, 330, 100, 100, this);
        }
    }

    private void pintaEnemigo(Graphics g) {
        if (planeta.getEnemigos() != null) {
            for (int i = 0; i < planeta.getEnemigos().size(); i++) {
                if (planeta.getEnemigos().get(i).getTipoenemigo().equalsIgnoreCase("Nave nodriza")) {
                    g.drawImage(imagenEnemigosNodriza.getImage(), planeta.getEnemigos().get(i).getPosX(), planeta.getEnemigos().get(i).getPosY(), 100, 100, this);
                }

                if (planeta.getEnemigos().get(i).getTipoenemigo().equalsIgnoreCase("Nave avanzada")) {
                    g.drawImage(imagenEnemigosAvanzada.getImage(), planeta.getEnemigos().get(i).getPosX(), planeta.getEnemigos().get(i).getPosY(), 100, 100, this);
                }

                if (planeta.getEnemigos().get(i).getTipoenemigo().equalsIgnoreCase("Nave explorada")) {
                    g.drawImage(imagenEnemigosExplorada.getImage(), planeta.getEnemigos().get(i).getPosX(), planeta.getEnemigos().get(i).getPosY(), 100, 100, this);
                }
            }
        }
    }

    private void pintarDatosnave(Graphics g) {
        g.setColor(Color.white);
        int i = 0;
        for (String llave : nave.getCantidad_material_tienenave().keySet()) {
            g.drawString(llave + ":", 60 * i, 446);
            g.drawString(nave.getCantidad_material_tienenave().get(llave).toString(), 60 * i, 460);
            i++;
        }
        int posTituInicialdos = 420;
        int posTituInicialtres = 420;
        int posTituInicial = 420;
        for (int j = 0; j < nave.getArmas().size(); j++) {
            if (j < 3) {
                g.drawString(nave.getArmas().get(j).getNombre(), posTituInicial, 446);
                posTituInicial += (nave.getArmas().get(j).getNombre().length() * 8) + 10;
                i++;
            }
            if (j >= 3 && j < 6) {
                g.drawString(nave.getArmas().get(j).getNombre(), posTituInicialdos, 459);
                posTituInicialdos += (nave.getArmas().get(j).getNombre().length() * 8) + 10;
                i++;
            }
            if (j > 6 && j < 9) {
                g.drawString(nave.getArmas().get(j).getNombre(), posTituInicialtres, 473);
                posTituInicialtres += (nave.getArmas().get(j).getNombre().length() * 8) + 10;
                i++;
            }
        }
        g.drawString("Cantidad Combustible" + ":", 225, 446);
        g.drawString(nave.getCantidad_conbustible().toString(), 360, 446);
        g.drawString("Cantidad Sondas" + ":", 225, 459);
        g.drawString(String.valueOf(nave.getNumero_sondas()), 360, 459);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param viaLactea the viaLactea to set
     */
    public void setViaLactea(ViaLactea viaLactea) {
        this.viaLactea = viaLactea;
    }

    /**
     * @return the mayorCantidadMaterial
     */
    public int getMayorCantidadMaterial() {
        return mayorCantidadMaterial;
    }

    /**
     * @param mayorCantidadMaterial the mayorCantidadMaterial to set
     */
    public void setMayorCantidadMaterial(int mayorCantidadMaterial) {
        this.mayorCantidadMaterial = mayorCantidadMaterial;
    }

    /**
     * @param planeta the planeta to set
     */
    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * @return the atras
     */
    public boolean isAtras() {
        return atras;
    }

    /**
     * @param atras the atras to set
     */
    public void setAtras(boolean atras) {
        this.atras = atras;
    }
}
