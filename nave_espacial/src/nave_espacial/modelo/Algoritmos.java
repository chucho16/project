/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import static nave_espacial.controlador.ControladorInicio.viaLactea;

/**
 *
 * @author Danii
 */
public class Algoritmos {
    
    public static List<Object> ubicarTierra() {
        List<Object> ubicacion = new LinkedList<>();
        for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
            for (SistemaPlanetario sPlanetario : nebulosa.getLista_sistemas_planetarios()) {
                for (Planeta planeta : sPlanetario.getLista_planetas()) {
                    if (planeta.getNombre().equalsIgnoreCase("Tierra")) {
                        ubicacion.add(0, nebulosa);
                        ubicacion.add(1, sPlanetario);
                        ubicacion.add(2, planeta);
                        return ubicacion;
                    }
                }
            }
        }
        return null;
    }

    public static void llegoGasolina(Nave nave, Gasolinera gasolinera) {
        HashMap<String, Integer> material = nave.getCantidad_material_tienenave();
        HashMap<String, Integer> precioGasolina = gasolinera.getPrecioGasolina();
        HashMap<String, Integer> precioSonda = gasolinera.getPrecioSonda();
        
        if (nave.getCantidad_conbustible() <= 1000 && nave.getNumero_sondas() < 15) {
            HashMap<String, Integer> materialGasolina = new HashMap<>();
            HashMap<String, Integer> materialSonda = new HashMap<>();
            for (String llave : material.keySet()) {
                materialGasolina.put(llave, material.get(llave) / 2);
                materialSonda.put(llave, material.get(llave) / 2);
            }
            if (!evaluarPrecioRecurso(precioGasolina, materialGasolina)) {
                if (!evaluarPrecioRecurso(precioSonda, materialSonda)) {
                    JOptionPane.showMessageDialog(null, "Los recursos son insuficientes para la gasolina y la sonda");
                    return;
                }
            }

            while (evaluarPrecioRecurso(precioSonda, materialSonda)) {
                if((nave.getNumero_sondas()+1)> 100){
                   break;
               }
                nave.setNumero_sondas(nave.getNumero_sondas() + 1);
                for (String llave : precioGasolina.keySet()) {
                    materialSonda.put(llave, materialSonda.get(llave) - precioSonda.get(llave));
                }
            }

            while (evaluarPrecioRecurso(precioGasolina, materialGasolina)) {
                if((nave.getCantidad_conbustible() + 1000)> 200000){
                    break;
                }
                nave.setCantidad_conbustible(nave.getCantidad_conbustible() + 1000);
                for (String llave : precioGasolina.keySet()) {
                    materialGasolina.put(llave, materialGasolina.get(llave) - precioGasolina.get(llave));
                }
            }
            for(String llave : material.keySet()){
                material.put(llave,materialGasolina.get(llave)+ materialSonda.get(llave));
            }
            nave.setCantidad_material_tienenave(material);
        } else if (nave.getCantidad_conbustible() <= 1000) {
            while (evaluarPrecioRecurso(precioGasolina, material)) {
                if((nave.getCantidad_conbustible() + 1000)> 200000){
                    return;
                }
                nave.setCantidad_conbustible(nave.getCantidad_conbustible() + 1000);
                for (String llave : precioGasolina.keySet()) {
                    material.put(llave, material.get(llave) - precioGasolina.get(llave));
                }
            }
            nave.setCantidad_material_tienenave(material);
        }else if(nave.getNumero_sondas() < 15){
           while (evaluarPrecioRecurso(precioSonda, material)) {
               if((nave.getNumero_sondas()+1)> 100){
                   return;
               }
                nave.setNumero_sondas(nave.getNumero_sondas()+1);
                for (String llave : precioGasolina.keySet()) {
                    material.put(llave, material.get(llave) - precioSonda.get(llave));
                }
            }
            nave.setCantidad_material_tienenave(material); 
        }
    }

    private static boolean evaluarPrecioRecurso(HashMap<String, Integer> precioGasolina, HashMap<String, Integer> recursos) {
        for (String llave : precioGasolina.keySet()) {
            if (precioGasolina.get(llave) > recursos.get(llave)) {
                return false;
            }
        }
        return true;
    }

}
