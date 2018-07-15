/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import nave_espacial.modelo.Gasolinera;
import nave_espacial.modelo.Planeta;
import static nave_espacial.controlador.ControladorInicio.nave;

/**
 *
 * @author Lenovo
 */
public class PanelSistemaPlanetario extends javax.swing.JPanel {

    private List<Planeta> listaPlanetas;
    private List<ImageIcon> imagenesFondo;
    private ImageIcon imagenAtras;
    private ImageIcon imagenEnemigo;
    private ImageIcon imagenRele;
    private ImageIcon imagenGasolinera;

    private int lineaTransicion[];
    private LinkedList<LinkedList<Integer>> rutas_planetas;
    int indiceImagen;
    private boolean atras;

    /**
     * Creates new form PanelSistemaPlanetario
     */
    public PanelSistemaPlanetario() {
        initComponents();
        cargarImagenes();
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
    }

    private void cargarImagenes() {
        indiceImagen = 0;
        imagenesFondo = new LinkedList<>();
        imagenAtras = new ImageIcon(getClass().getResource("imagenes/volver.png"));
        imagenEnemigo = new ImageIcon(getClass().getResource("imagenes/logos/lenemigo.png"));
        imagenRele = new ImageIcon(getClass().getResource("imagenes/logos/lrele.png"));
        imagenGasolinera = new ImageIcon(getClass().getResource("imagenes/logos/lgasolina.png"));

        for (int i = 0; i < 14; i++) {
            ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes/sistemaPlanetario/" + i + ".gif"));
            imagenesFondo.add(imagen);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        pintarFondo(g);
        pintarTransiciones(g);
        pintaPlanetas(g);
        pintarCrearTransicion(g);
        pintarAtras(g);
        pintarnave(g);
        if (nave != null) {
            g.setColor(Color.white);
            g.drawString("PRESIONE LA TECLA C PARA COMPRAR UN ARMA", 600, 10);
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
        if (indiceImagen < 14) {
            g.drawImage(imagenesFondo.get(indiceImagen).getImage(), 0, 0, 1366, 768, this);
            indiceImagen++;
        } else {
            indiceImagen = 0;
        }

    }

    private void pintaPlanetas(Graphics g) {
        if (listaPlanetas != null) {
            for (Planeta planeta : listaPlanetas) {
                if (planeta.getImagenPlaneta() != null) {
                    if (planeta.conEnemigos()) {
                        pintarSeñalEnemigo(g, planeta);
                    }
                    if (planeta.conTeletransportador()) {
                        pintarSeñalRele(g, planeta);
                    }
                    if (planeta.conGasolinera()) {
                        pintarSeñalGasolinera(g, planeta);
                    }
                    g.drawImage(planeta.getImagenPlaneta().getImage(), planeta.getPosX() - 25, planeta.getPosY() - 25, 50, 50, this);
                    g.setColor(Color.white);
                    g.drawString(planeta.getNombre(), planeta.getPosX(), planeta.getPosY());
                }
            }
        }

    }

    private void pintarSeñalEnemigo(Graphics g, Planeta planeta) {
        g.drawImage(imagenEnemigo.getImage(), planeta.getPosX() - 40, planeta.getPosY() - 35, 25, 34, this);
    }

    private void pintarSeñalRele(Graphics g, Planeta planeta) {
        g.drawImage(imagenRele.getImage(), planeta.getPosX() - 13, planeta.getPosY() - 52, 25, 34, this);
    }

    private void pintarSeñalGasolinera(Graphics g, Planeta planeta) {
        g.drawImage(imagenGasolinera.getImage(), planeta.getPosX() + 15, planeta.getPosY() - 35, 25, 34, this);
    }

    private void pintarTransiciones(Graphics g) {
        g.setColor(Color.blue);
        if (this.rutas_planetas != null) {
            for (int i = 0; i < rutas_planetas.size(); i++) {
                for (int j = 0; j < rutas_planetas.get(i).size(); j++) {
                    if (rutas_planetas.get(i).get(j) == 1) {
                        Planeta a = listaPlanetas.get(i);
                        Planeta b = listaPlanetas.get(j);
                        g.drawLine(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY());
                    }
                }
            }
        }
    }

    private void pintarCrearTransicion(Graphics g) {
        g.drawLine(this.lineaTransicion[0], this.lineaTransicion[1], this.lineaTransicion[2], this.lineaTransicion[3]);
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
     * @param listaPlanetas the listaPlanetas to set
     */
    public void setListaPlanetas(List<Planeta> listaPlanetas) {
        this.listaPlanetas = listaPlanetas;
    }

    /**
     * @param lineaTransicion the lineaTransicion to set
     */
    public void setLineaTransicion(int[] lineaTransicion) {
        this.lineaTransicion = lineaTransicion;
    }

    /**
     * @param rutas_planetas the rutas_planetas to set
     */
    public void setRutas_planetas(LinkedList<LinkedList<Integer>> rutas_planetas) {
        this.rutas_planetas = rutas_planetas;
    }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
