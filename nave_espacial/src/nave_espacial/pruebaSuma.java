/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial;

import static nave_espacial.controlador.ControladorInicio.viaLactea;
import nave_espacial.modelo.Nebulosa;

/**
 *
 * @author Danii
 */
public class pruebaSuma implements Runnable {

    private final Thread hilo;

    public pruebaSuma() {
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Total");
        while (true) {
            if (viaLactea != null) {
                for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
                    System.out.println("Nebulosa: "+nebulosa.getNombre()+"- "+nebulosa.getTotalMateriales());
                }
                System.out.println("---------------------------------------------------------------");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error hilo prueba");
                }
            }
        }
    }
}
