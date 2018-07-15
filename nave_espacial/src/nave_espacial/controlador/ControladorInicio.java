/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import nave_espacial.modelo.Nave;
import nave_espacial.modelo.ViaLactea;

/**
 *
 * @author Danii
 */
public class ControladorInicio {
    public static ViaLactea viaLactea;
    public static Nave nave;
    

    public ControladorInicio() {
        viaLactea = new ControladorDeCarga().cargarParametros();
    }
    
}
