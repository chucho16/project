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
import javax.swing.JOptionPane;
import nave_espacial.modelo.Gasolinera;
import nave_espacial.modelo.Nebulosa;
import nave_espacial.modelo.Planeta;
import nave_espacial.modelo.SistemaPlanetario;
import nave_espacial.modelo.TeleTransportador;
import static nave_espacial.controlador.ControladorInicio.nave;

/**
 *
 * @author Lenovo
 */
public class PanelNebulosa extends javax.swing.JPanel {

    private ImageIcon imagenEnemigo;
    private ImageIcon imagenRele;
    private ImageIcon imagenGasolinera;
    private ImageIcon imagenSistemaPlanetario;
    private ImageIcon imagenAtras;
    private List<ImageIcon> imagenesFondo;
    private List<SistemaPlanetario> listaDeSistemasPlanetaRios;
    private int indiceImagen;
    private int lineaTransicion[];
    private LinkedList<LinkedList<Integer>> costos_rutas;
    private boolean atras;
    private Nebulosa nebulosa;

    /**
     * Creates new form PanelNebulosa
     */
    public PanelNebulosa() {
        initComponents();
        cargarImagenes();
        this.crearRele.requestFocusInWindow();
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
        this.costos_rutas = new LinkedList<>();
    }

    private void cargarImagenes() {
        indiceImagen = 0;
        imagenesFondo = new LinkedList<>();
        imagenAtras = new ImageIcon(getClass().getResource("imagenes/volver.png"));
        imagenEnemigo = new ImageIcon(getClass().getResource("imagenes/logos/lenemigo.png"));
        imagenRele = new ImageIcon(getClass().getResource("imagenes/logos/lrele.png"));
        imagenGasolinera = new ImageIcon(getClass().getResource("imagenes/logos/lgasolina.png"));
        imagenSistemaPlanetario = new ImageIcon(getClass().getResource("imagenes/nebulosa/estrella.png"));
        for (int i = 0; i < 27; i++) {
            ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes/nebulosa/Snapshot_" + i + ".png"));
            imagenesFondo.add(imagen);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        pintarFondo(g);
        pintarTransiciones(g);
        pintaSistemaPlanetarios(g);
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
            Thread.sleep(150);
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
        if (indiceImagen < 27) {
            g.drawImage(imagenesFondo.get(indiceImagen).getImage(), 0, 0, 900, 506, this);
            indiceImagen++;
        } else {
            indiceImagen = 0;
        }

    }

    private void pintaSistemaPlanetarios(Graphics g) {
        if (listaDeSistemasPlanetaRios != null) {
            for (SistemaPlanetario sistemaPlanetario : listaDeSistemasPlanetaRios) {
                if (sistemaPlanetario.planetaConEnemigos()) {
                    pintarSeñalEnemigo(g, sistemaPlanetario);
                }
                if (sistemaPlanetario.planetaConGasolinera()) {
                    pintarSeñalGasolinera(g, sistemaPlanetario);
                }
                if (sistemaPlanetario.planetaConTeletrasportador()) {
                    pintarSeñalRele(g, sistemaPlanetario);
                }
                g.drawImage(imagenSistemaPlanetario.getImage(), sistemaPlanetario.getPosX() - 107, sistemaPlanetario.getPosY() - 115, this);
                g.setColor(Color.white);
                g.drawString(sistemaPlanetario.getNombre(), sistemaPlanetario.getPosX(), sistemaPlanetario.getPosY() + 25);

            }
        }
    }

    private void pintarSeñalEnemigo(Graphics g, SistemaPlanetario Splaneta) {
        g.drawImage(imagenEnemigo.getImage(), Splaneta.getPosX() - 40, Splaneta.getPosY() - 35, 25, 34, this);
    }

    private void pintarSeñalRele(Graphics g, SistemaPlanetario Splaneta) {
        g.drawImage(imagenRele.getImage(), Splaneta.getPosX() - 13, Splaneta.getPosY() - 52, 25, 34, this);
    }

    private void pintarSeñalGasolinera(Graphics g, SistemaPlanetario Splaneta) {
        g.drawImage(imagenGasolinera.getImage(), Splaneta.getPosX() + 15, Splaneta.getPosY() - 35, 25, 34, this);
    }

    private void pintarCrearTransicion(Graphics g) {
        g.drawLine(this.lineaTransicion[0], this.lineaTransicion[1], this.lineaTransicion[2], this.lineaTransicion[3]);
    }

    private void pintarTransiciones(Graphics g) {
        g.setColor(Color.blue);
        if (this.costos_rutas != null) {
            for (int i = 0; i < costos_rutas.size(); i++) {
                for (int j = 0; j < costos_rutas.get(i).size(); j++) {
                    if (costos_rutas.get(i).get(j) >= 0) {
                        SistemaPlanetario a = listaDeSistemasPlanetaRios.get(i);
                        SistemaPlanetario b = listaDeSistemasPlanetaRios.get(j);
                        int coordenadas[] = calcularCoordenada(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY());
                        g.setColor(Color.white);
                        g.drawLine(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY());
                        g.setColor(Color.cyan);
                        g.drawString(costos_rutas.get(i).get(j).toString(), coordenadas[0], coordenadas[1]);
                    }
                }

            }
        }
    }

    private int[] calcularCoordenada(int aposx, int aposy, int bposx, int bposy) {
        int[] posicion = new int[2];

        int promX = Math.abs(aposx - bposx);
        int promY = Math.abs(aposy - bposy);

        // pos x del texto
        if (aposx > bposx) {
            posicion[0] = aposx - (promX / 2);
        } else {
            posicion[0] = aposx + (promX / 2);
        }
        //´pos y del texto
        if (aposy > bposy) {
            posicion[1] = aposy - (promY / 2);
        } else {
            posicion[1] = aposy + (promY / 2);
        }
        return posicion;
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

        crearRele = new javax.swing.JButton();
        CrearEnemigos = new javax.swing.JButton();

        crearRele.setText(" Teletransportador o Gasolinera");
        crearRele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearReleActionPerformed(evt);
            }
        });

        CrearEnemigos.setText("Crear Enemigos");
        CrearEnemigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearEnemigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crearRele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CrearEnemigos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(crearRele)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CrearEnemigos)
                .addContainerGap(223, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void crearReleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearReleActionPerformed
        String seleccion = JOptionPane.showInputDialog("Desea crear:\n Teletransportador(T) \n o \n Gasolinera(G)");
        if (seleccion.toUpperCase().startsWith("T")) {
            if (!nebulosa.isBanderaTeletransportador()) {
                if (nave == null) {
                    CrearTeletransportador dialog = new CrearTeletransportador(new javax.swing.JFrame(), true);
                    TeleTransportador teletransportador = new TeleTransportador();
                    dialog.teletransportador = teletransportador;
                    dialog.listaDeSistemasPlanetaRios = listaDeSistemasPlanetaRios;
                    dialog.cargarListaSistemasPlanetarios();
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un teletransportador..");
            }
        } else if (seleccion.toUpperCase().startsWith("G")) {
            if (!nebulosa.isBanderaGasolinera()) {
                if (nave == null) {
                    CrearGasolinera dialog = new CrearGasolinera(new javax.swing.JFrame(), true);
                    Gasolinera gasolinera = new Gasolinera();
                    dialog.gasolinera = gasolinera;
                    dialog.listaDeSistemasPlanetaRios = listaDeSistemasPlanetaRios;
                    dialog.cargarListaSistemasPlanetarios();
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe una gasolinera..");
            }
        }
    }//GEN-LAST:event_crearReleActionPerformed

    private void CrearEnemigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearEnemigosActionPerformed
        CrearEnemigos dialog = new CrearEnemigos(new javax.swing.JFrame(), true);
        dialog.listaDeSistemasPlanetaRios = listaDeSistemasPlanetaRios;
        dialog.cargarListaSistemasPlanetarios();
        dialog.setVisible(true);


    }//GEN-LAST:event_CrearEnemigosActionPerformed

    /**
     * @param listaDeSistemasPlanetaRios the listaDeSistemasPlanetaRios to set
     */
    public void setListaDeSistemasPlanetaRios(List<SistemaPlanetario> listaDeSistemasPlanetaRios) {
        this.listaDeSistemasPlanetaRios = listaDeSistemasPlanetaRios;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearEnemigos;
    private javax.swing.JButton crearRele;
    // End of variables declaration//GEN-END:variables

    /**
     * @param lineaTransicion the lineaTransicion to set
     */
    public void setLineaTransicion(int[] lineaTransicion) {
        this.lineaTransicion = lineaTransicion;
    }

    /**
     * @param costos_rutas the costos_rutas to set
     */
    public void setCostos_rutas(LinkedList<LinkedList<Integer>> costos_rutas) {
        this.costos_rutas = costos_rutas;
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

    /**
     * @param nebulosa the nebulosa to set
     */
    public void setNebulosa(Nebulosa nebulosa) {
        this.nebulosa = nebulosa;
    }

    /**
     * @return the CrearEnemigos
     */
    public javax.swing.JButton getCrearEnemigos() {
        return CrearEnemigos;
    }

    /**
     * @return the crearRele
     */
    public javax.swing.JButton getCrearRele() {
        return crearRele;
    }

}
