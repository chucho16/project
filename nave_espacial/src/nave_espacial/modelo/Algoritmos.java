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

    public static List<Object> ubicarCualquierPlaneta(Planeta planetaCualquiera) {
        List<Object> ubicacion = new LinkedList<>();
        for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
            for (SistemaPlanetario sPlanetario : nebulosa.getLista_sistemas_planetarios()) {
                for (Planeta planeta : sPlanetario.getLista_planetas()) {
                    if (planeta.getNombre().equalsIgnoreCase(planetaCualquiera.getNombre())) {
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
                if ((nave.getNumero_sondas() + 1) > 100) {
                    break;
                }
                nave.setNumero_sondas(nave.getNumero_sondas() + 1);
                for (String llave : precioGasolina.keySet()) {
                    materialSonda.put(llave, materialSonda.get(llave) - precioSonda.get(llave));
                }
            }

            while (evaluarPrecioRecurso(precioGasolina, materialGasolina)) {
                if ((nave.getCantidad_conbustible() + 1000) > 200000) {
                    break;
                }
                nave.setCantidad_conbustible(nave.getCantidad_conbustible() + 1000);
                for (String llave : precioGasolina.keySet()) {
                    materialGasolina.put(llave, materialGasolina.get(llave) - precioGasolina.get(llave));
                }
            }
            for (String llave : material.keySet()) {
                material.put(llave, materialGasolina.get(llave) + materialSonda.get(llave));
            }
            nave.setCantidad_material_tienenave(material);
        } else if (nave.getCantidad_conbustible() <= 1000) {
            while (evaluarPrecioRecurso(precioGasolina, material)) {
                if ((nave.getCantidad_conbustible() + 1000) > 200000) {
                    return;
                }
                nave.setCantidad_conbustible(nave.getCantidad_conbustible() + 1000);
                for (String llave : precioGasolina.keySet()) {
                    material.put(llave, material.get(llave) - precioGasolina.get(llave));
                }
            }
            nave.setCantidad_material_tienenave(material);
        } else if (nave.getNumero_sondas() < 15) {
            while (evaluarPrecioRecurso(precioSonda, material)) {
                if ((nave.getNumero_sondas() + 1) > 100) {
                    return;
                }
                nave.setNumero_sondas(nave.getNumero_sondas() + 1);
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

    public static Object [] calcularRutaGasolinera(Nebulosa nebulosa, SistemaPlanetario sPActual, int gasolinaActual) {
        Object [] arreglo = new Object[2];
        LinkedList<String> rutas = new LinkedList<>();
        LinkedList<Integer> costos = new LinkedList<>();
        String rutaR = "";
        calculoRutaRecursivo(nebulosa.getLista_sistemas_planetarios(), nebulosa.getCostos_rutas(), sPActual, rutas,rutaR,costos, gasolinaActual );
        int indiceMejor = indiceCostoMayor(costos);
        arreglo[0] = rutas.get(indiceMejor);
        arreglo[1] = costos.get(indiceMejor);
        return arreglo;
    }
    
    private static int indiceCostoMayor(LinkedList<Integer> costos){
        int mayor =0;
        for(Integer costo : costos){
            if(costo > costos.indexOf(mayor)){
                mayor = costos.indexOf(costo);
            }
        }
        return mayor;
    }

    private static void calculoRutaRecursivo(LinkedList<SistemaPlanetario> lista_sistemas_planetarios, LinkedList<LinkedList<Integer>> costosRutas, SistemaPlanetario sPactual, LinkedList<String> rutas, String rutaR, LinkedList<Integer> costos, int gasolinaActual) {
        if (!sPactual.equals(null)) {
            if (!sPactual.planetaConGasolinera()) {
                int indiceSp = lista_sistemas_planetarios.indexOf(sPactual);
                for (int i = 0; i < costosRutas.size(); i++) {
                    if (costosRutas.get(indiceSp).get(i) > -1 && !rutaR.contains(sPactual.getNombre())) {
                        String copiaRuta = rutaR+"-"+sPactual.getNombre();
                        int gasolina = gasolinaActual - costosRutas.get(indiceSp).get(i);
                        calculoRutaRecursivo(lista_sistemas_planetarios, costosRutas, lista_sistemas_planetarios.get(i), rutas, copiaRuta, costos, gasolina);
                    }
                }
            }else{
                rutas.add(rutaR+"-"+sPactual.getNombre());
                costos.add(gasolinaActual);
            }

        }
     
    }

}
