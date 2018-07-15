/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import nave_espacial.modelo.*;
import nave_espacial.vista.FrameNebulosa;
import nave_espacial.vista.FrameViaLactea;
import nave_espacial.vista.FrameNebulosaJugar;


/**
 *
 * @author Lenovo
 */
public class ControladorNebulosa {

    private Nebulosa nebulosa;
    public int[] lineaTransicion;
    FrameNebulosaJugar framej;

    public ControladorNebulosa() {
    }

    public ControladorNebulosa(Nebulosa nebulosa) {
        this.nebulosa = nebulosa;
        crearFrameNebulosa("cualquier cosa");
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
    }

    public ControladorNebulosa(Nebulosa nebulosa, String parametro) {
        this.nebulosa = nebulosa;
        crearFrameNebulosa(parametro);
    }
   public void destruirFrame(){
        this.framej.dispose();
    }

    private void crearFrameNebulosa(String parametro) {
        if (parametro.equalsIgnoreCase("cualquier cosa")) {
            FrameNebulosa frame = new FrameNebulosa();
            frame.getPanelNebulosa().setListaDeSistemasPlanetaRios(nebulosa.getLista_sistemas_planetarios());
            frame.getPanelNebulosa().setCostos_rutas(nebulosa.getCostos_rutas());
            frame.getPanelNebulosa().setNebulosa(nebulosa);
            frame.definirFrame(this);
        } else {
             framej = new FrameNebulosaJugar();
            framej.getPanelNebulosa().setListaDeSistemasPlanetaRios(nebulosa.getLista_sistemas_planetarios());
            framej.getPanelNebulosa().setCostos_rutas(nebulosa.getCostos_rutas());
            framej.getPanelNebulosa().setNebulosa(nebulosa);
            framej.definirFrame(this);

        }
    }
    /**
     * *
     * Zona de metodos personalizados
     */
    /**
     * *
     * metodo que reconoce si existe un sistema planetario en dicha posicion
     *
     * @param punto
     * @return
     */
    public SistemaPlanetario reconocerSistemaPlanetario(Point punto) {
        SistemaPlanetario sistemaPlanetarioClick = null;
        for (SistemaPlanetario sistemaPlanetario : nebulosa.getLista_sistemas_planetarios()) {
            if (validarColisionMouseSP(sistemaPlanetario, punto)) {
                return sistemaPlanetario;
            }
        }
        return null;
    }

    public void crearSistemaPlanetario(SistemaPlanetario sPlanetario) {
        if (sPlanetario != null) {
            new ControladorSistemaPlanetario(sPlanetario,nebulosa);
        }
    }

    //sobrecarga
    public void crearSistemaPlanetario(SistemaPlanetario sPlanetario,String parametro) {
        if (sPlanetario != null) {
            new ControladorSistemaPlanetario(sPlanetario, parametro);
        }
    }

    public void cambiarPosicionSistemaPlanetario(Point punto) {
        SistemaPlanetario sistemaPlanetarioEliminar = null;
        for (SistemaPlanetario sistemaPlanetario : this.nebulosa.getLista_sistemas_planetarios()) {
            if (validarColisionMouseSPMovimiento(sistemaPlanetario, punto)) {
                sistemaPlanetario.setPosX((int) punto.getX());
                sistemaPlanetario.setPosY((int) punto.getY());
                sistemaPlanetarioEliminar = (validarEliminacionSp(sistemaPlanetario) ? sistemaPlanetario : null);
                break;
            }
        }

        if (sistemaPlanetarioEliminar != null) {
            this.nebulosa.eliminarSistemaPlanetario(sistemaPlanetarioEliminar);
        }
        new ControladorDeCarga().guardarCambiosArchivos();
    }

    public void evaluarTransicion() {
        SistemaPlanetario a = reconocerSistemaPlanetario(new Point(lineaTransicion[0], lineaTransicion[1]));
        SistemaPlanetario b = reconocerSistemaPlanetario(new Point(lineaTransicion[2], lineaTransicion[3]));
        if (a != null && b != null && a != b) {
            String costo = JOptionPane.showInputDialog("Costo ruta:");
            if (!costo.equals("")) {
                this.nebulosa.agregarTransicion(a, b, Integer.parseInt(costo));
                new ControladorDeCarga().guardarCambiosArchivos();
            }
        }
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
    }

    private boolean validarEliminacionSp(SistemaPlanetario sPlanetario) {
        if (sPlanetario != null) {
            if (sPlanetario.getPosX() >= 0) {
                if (sPlanetario.getPosX() < 890) {
                    if (sPlanetario.getPosY() >= 0) {
                        if (sPlanetario.getPosY() < 490) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private Boolean validarColisionMouseSP(SistemaPlanetario sistemaPlanetario, Point punto) {
        if (sistemaPlanetario != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(sistemaPlanetario.getPosX() - 15, sistemaPlanetario.getPosY() - 15, 30, 30)));
        }
        return false;
    }

    private Boolean validarColisionMouseSPMovimiento(SistemaPlanetario sistemaP, Point punto) {
        if (nebulosa != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(sistemaP.getPosX() - 37, sistemaP.getPosY() - 37, 75, 75)));
        }
        return false;
    }

    public void agregarSistemaPlanetario(SistemaPlanetario sistemaPlanetario) {
        nebulosa.agregarSistemaPlanetario(sistemaPlanetario);
        new ControladorDeCarga().guardarCambiosArchivos();
    }

    public void regresar(){
         new FrameViaLactea().setVisible(true);
    }

    public Nebulosa getNebulosa() {
        return nebulosa;
    }
}
