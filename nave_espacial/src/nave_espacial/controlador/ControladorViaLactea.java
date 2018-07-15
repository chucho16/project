/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import java.awt.Point;
import java.awt.Rectangle;
import nave_espacial.modelo.*;
import nave_espacial.vista.FrameViaLactea;
import nave_espacial.vista.FrameViaLacteaJugar;
import static nave_espacial.controlador.ControladorInicio.viaLactea;
/**
 *
 * @author Danii
 */
public class ControladorViaLactea {
    private FrameViaLacteaJugar frame1;
    private FrameViaLactea frame;
    

    public ControladorViaLactea(FrameViaLactea frame) {
        this.frame = frame;
        parametrizarPanelViaLactea();
    }
    //sobre carga de metodo controladorvialactea porque necesito llamarlo con framevialacteajuagar
    public ControladorViaLactea(FrameViaLacteaJugar frame1) {
        this.frame1 = frame1;
        parametrizarPanelViaLacteaJugar();
    }

    private void parametrizarPanelViaLactea() {
        this.frame.getPanelViaLactea().setListaNebulosa(viaLactea.getLista_nebulosas());
    }
     private void parametrizarPanelViaLacteaJugar() {
        this.frame1.getPanelViaLactea1().setListaNebulosa(viaLactea.getLista_nebulosas());
    }
   
    /**
     * Zona de metodos personalizados
     */
    /**
     * *
     * Metodo que valida con todas las nebulosas existentes si alguna coincide
     * con el clic dado.
     *
     * @param punto
     * @return verdadero si se intersepta el clic del mouse con la nevulosa y si
     * fueron 2 clics
     */
    public Nebulosa reconocerNebulosa(Point punto) {

        for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
            if (validarColisionMouseNebulosa(nebulosa, punto)) {
                return nebulosa;
            }
        }
        return null;
    }

    public void crearNebulosa(Nebulosa nebulosa) {
        if (nebulosa != null) {
            new ControladorNebulosa(nebulosa);
        }
    }
    //sobrecarga
    public void crearNebulosa(Nebulosa nebulosa,String parametro) {
        if (nebulosa != null) {
            new ControladorNebulosa(nebulosa, parametro);
        }
    }

    public void cambiarPosicionNebulosa(Point punto) {
        Nebulosa nebulosaEliminar = null;
        for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
            if (validarColisionMouseNebulosaMovimiento(nebulosa, punto)) {
                nebulosa.setPosX((int) punto.getX());
                nebulosa.setPosY((int) punto.getY());
                nebulosaEliminar = (validarEliminacionNebulosa(nebulosa) ? nebulosa : null);
                break;
            }
        }

        if (nebulosaEliminar != null) {
            viaLactea.getLista_nebulosas().remove(nebulosaEliminar);
        }
        new ControladorDeCarga().guardarCambiosArchivos();
    }

    private boolean validarEliminacionNebulosa(Nebulosa nebulosa) {
        if (nebulosa != null) {
            if (nebulosa.getPosX() >= 0) {
                if (nebulosa.getPosX() < 890) {
                    if (nebulosa.getPosY() >= 0) {
                        if (nebulosa.getPosY() < 490) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private Boolean validarColisionMouseNebulosa(Nebulosa nebulosa, Point punto) {
        if (nebulosa != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(nebulosa.getPosX() - 15, nebulosa.getPosY() - 15, 30, 30)));
        }
        return false;
    }

    private Boolean validarColisionMouseNebulosaMovimiento(Nebulosa nebulosa, Point punto) {
        if (nebulosa != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(nebulosa.getPosX() - 75, nebulosa.getPosY() - 75, 150, 150)));
        }
        return false;
    }

    public void agregarNebulosa(Nebulosa nebulosa) {
        viaLactea.getLista_nebulosas().add(nebulosa);
        new ControladorDeCarga().guardarCambiosArchivos();
    }

}
