/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import nave_espacial.modelo.Nebulosa;
import nave_espacial.modelo.Planeta;
import nave_espacial.modelo.SistemaPlanetario;
import nave_espacial.vista.FramePlaneta;
import nave_espacial.vista.FramePlanetaJugar;

/**
 *
 * @author Lenovo
 */
public class ControladorPlaneta {

    private Nebulosa nebulosa;
    private SistemaPlanetario sPlanetario;
    private Planeta planeta;
    private FramePlaneta frame ;
    private FramePlanetaJugar frameJ;

    public ControladorPlaneta() {
    }

    public ControladorPlaneta(Planeta planeta, String parametro) {
        this.planeta = planeta;
        crearFramePlaneta(parametro);
    }

    public ControladorPlaneta(Planeta planeta, SistemaPlanetario sp, Nebulosa nebulosa) {
        this.planeta = planeta;
        this.sPlanetario = sp;
        this.nebulosa = nebulosa;
        crearFramePlaneta();
    }

    public void destruirFrame(){
        this.frameJ.dispose();
    }
    
    private void crearFramePlaneta() {
        crearFramePlaneta("cualquier cosa");
    }//sobrecarga del metodo para sistema planetario

    private void crearFramePlaneta(String parametro) {
        if (parametro.equalsIgnoreCase("cualquier cosa")) {
            frame = new FramePlaneta();
            frame.getPanelPlaneta().setPlaneta(getPlaneta());
            frame.getPanelPlaneta().setMayorCantidadMaterial(mayorCantidadMaterial());
            frame.definirFrame(this);
        } else {
            frameJ = new FramePlanetaJugar();
            frameJ.getPanelPlaneta().setPlaneta(getPlaneta());
            frameJ.getPanelPlaneta().setMayorCantidadMaterial(mayorCantidadMaterial());
            frameJ.definirFrame(this);
        }
    }

    private int mayorCantidadMaterial() {
        try {
            
        int mayor = 0;
        String nombre = "";
        HashMap<String, Integer> materiales = new HashMap<>();
        materiales = getPlaneta().getMateriales();
        if (materiales != null) {
            for (String llave : materiales.keySet()) {
                if (materiales.get(llave) > mayor) {
                    mayor = materiales.get(llave);
                    nombre = llave;
                }
            }
        }
        return mayor;
        } catch (Exception e) {
        }
        return 0;
    }

    public Boolean validarColisionMouseGasolinera(Point punto) {
        if (this.getPlaneta().getGasolinera_del_sistema() != null && punto != null) {
            return ((new Rectangle(punto.x, punto.y, 5, 5).intersects(new Rectangle(this.getPlaneta().getGasolinera_del_sistema().getPosX(), this.getPlaneta().getGasolinera_del_sistema().getPosY(), 80, 80))));
        }
        return false;
    }

    public void regresar() {
        new ControladorSistemaPlanetario(sPlanetario, nebulosa);
    }

    /**
     * @return the planeta
     */
    public Planeta getPlaneta() {
        return planeta;
    }
}
