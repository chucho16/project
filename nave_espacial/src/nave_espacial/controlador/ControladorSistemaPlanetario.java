/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import java.awt.Point;
import java.awt.Rectangle;
import nave_espacial.modelo.*;
import nave_espacial.vista.FrameSistemaPlanetario;
import nave_espacial.vista.FrameSistemaPlanetarioJugar;
import static nave_espacial.controlador.ControladorInicio.viaLactea;

/**
 *
 * @author Lenovo
 */
public class ControladorSistemaPlanetario {

    private Nebulosa nebulosa;
    private SistemaPlanetario sPlanetario;
    private ControladorPlaneta planetaControl;
    public int[] lineaTransicion;
    public FrameSistemaPlanetarioJugar frameJ;
public FrameSistemaPlanetarioJugar frame;
    public ControladorSistemaPlanetario() {

    }
        public void destruirFrame(){
        this.frameJ.dispose();
    }

    public ControladorSistemaPlanetario(SistemaPlanetario sPlanetario, Nebulosa nebulosa) {
        this.sPlanetario = sPlanetario;
        this.nebulosa = nebulosa;
        crearFrameSistemaPlanetario("cualquier cosa");
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
    }
//sobrecarga de metodo para que tenga el nuevo atributo el constructor

    public ControladorSistemaPlanetario(SistemaPlanetario sPlanetario, String parametro) {
        this.sPlanetario = sPlanetario;
        crearFrameSistemaPlanetario(parametro);
    }

    private void crearFrameSistemaPlanetario(String parametro) {
        if (parametro.equalsIgnoreCase("cualquier cosa")) {
            FrameSistemaPlanetario frame = new FrameSistemaPlanetario();
            frame.getPanelSistemaPlanetario().setListaPlanetas(getsPlanetario().getLista_planetas());
            frame.getPanelSistemaPlanetario().setRutas_planetas(sPlanetario.getRutas_planetas());
            frame.definirFrame(this);
        } else {
           frameJ = new FrameSistemaPlanetarioJugar();
            frameJ.getPanelSistemaPlanetario().setListaPlanetas(getsPlanetario().getLista_planetas());
            frameJ.getPanelSistemaPlanetario().setRutas_planetas(sPlanetario.getRutas_planetas());
            frameJ.definirFrame(this);

        }
    }
 

    private void crearFrameSistemaPlanetario() {
        FrameSistemaPlanetario frame = new FrameSistemaPlanetario();
        frame.getPanelSistemaPlanetario().setListaPlanetas(sPlanetario.getLista_planetas());
        frame.definirFrame(this);
    }

    /**
     * *
     * Zona de metodos personalizados
     */
    public Planeta reconocerPlaneta(Point punto) {
        if (getsPlanetario().getLista_planetas() != null) {
            for (Planeta planeta : getsPlanetario().getLista_planetas()) {
                if (validarColisionMousePlaneta(planeta, punto)) {
                    return planeta;
                }
            }
        }
        return null;
    }

    public void crearPlaneta(Planeta planeta) {
        if (planeta != null) {
            new ControladorPlaneta(planeta, sPlanetario, nebulosa);
        }
    }

    public void cambiarPosicionPlaneta(Point punto) {
        Planeta planetaEliminar = null;
        for (Planeta planeta : this.sPlanetario.getLista_planetas()) {
            if (validarColisionMousePlanetaMovimiento(planeta, punto)) {
                planeta.setPosX((int) punto.getX());
                planeta.setPosY((int) punto.getY());
                planetaEliminar = (validarEliminacionPlaneta(planeta) ? planeta : null);
            }
        }

        if (planetaEliminar != null) {
            this.sPlanetario.eliminarPlaneta(planetaEliminar);
            new ControladorDeCarga().guardarCambiosArchivos();
        }
    }//sobre carga del metodo para la opcion jugar

    public void crearPlaneta(Planeta planeta, String parametro) {
        if (planeta != null) {
            new ControladorPlaneta(planeta, parametro);
        }
    }

    private Boolean validarColisionMousePlaneta(Planeta planeta, Point punto) {
        if (planeta != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(planeta.getPosX() - 15, planeta.getPosY() - 15, 30, 30)));
        }
        return false;
    }

    private Boolean validarColisionMousePlanetaMovimiento(Planeta planeta, Point punto) {
        if (planeta != null && punto != null) {
            return (new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(planeta.getPosX() - 37, planeta.getPosY() - 37, 75, 75)));
        }
        return false;
    }

    private boolean validarEliminacionPlaneta(Planeta planeta) {
        if (planeta != null) {
            if (planeta.getPosX() >= 0) {
                if (planeta.getPosX() < 890) {
                    if (planeta.getPosY() >= 0) {
                        if (planeta.getPosY() < 490) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void agregarPlaneta(Planeta planeta) {
        sPlanetario.agregarPlaneta(planeta);
        new ControladorDeCarga().guardarCambiosArchivos();
    }

    public void evaluarTransicion() {
        Planeta a = reconocerPlaneta(new Point(lineaTransicion[0], lineaTransicion[1]));
        Planeta b = reconocerPlaneta(new Point(lineaTransicion[2], lineaTransicion[3]));
        if (a != null && b != null) {
            this.sPlanetario.agregarTransicion(a, b);
            new ControladorDeCarga().guardarCambiosArchivos();
        }
        this.lineaTransicion = new int[]{-1, -1, -1, -1};
    }

    public void regresar() {
        new ControladorNebulosa(nebulosa);
    }

    /**
     * @return the sPlanetario
     */
    public SistemaPlanetario getsPlanetario() {
        return sPlanetario;
    }

    public ViaLactea getViaLactea() {
        return viaLactea;
    }

}
