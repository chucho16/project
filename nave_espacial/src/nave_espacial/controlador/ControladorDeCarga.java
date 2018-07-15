/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.controlador;

import java.util.HashMap;
import java.util.LinkedList;
import nave_espacial.modelo.Enemigo;
import nave_espacial.modelo.Gasolinera;
import nave_espacial.modelo.GestionArchivos;
import nave_espacial.modelo.Nebulosa;
import nave_espacial.modelo.Planeta;
import nave_espacial.modelo.SistemaPlanetario;
import static nave_espacial.controlador.ControladorInicio.viaLactea;
import nave_espacial.modelo.ViaLactea;
/**
 *
 * @author Lenovo
 */
public class ControladorDeCarga {

    public ViaLactea cargarParametros() {
        ViaLactea viaLactea = new ViaLactea();
        viaLactea.setLista_nebulosas(cargarNebulosas());
        cargarSistemasPlanetarios(viaLactea.getLista_nebulosas());
        for (Nebulosa nebulosa : viaLactea.getLista_nebulosas()) {
            cargarPlanetas(nebulosa.getLista_sistemas_planetarios());
            cargarGasolinera(nebulosa.getLista_sistemas_planetarios());
            for (SistemaPlanetario sistemaplanetario : nebulosa.getLista_sistemas_planetarios()) {
                cargarEnemigos(sistemaplanetario.getLista_planetas());
            }
        }
        cargarMapaSistemasPlanetariosPlaneta(viaLactea.getLista_nebulosas());
        cargarMapaNebulosaSP(viaLactea.getLista_nebulosas());

        return viaLactea;
    }

    private void cargarMapaSistemasPlanetariosPlaneta(LinkedList<Nebulosa> listaNebulosa) {
        for (Nebulosa nebulosa : listaNebulosa) {
            for (SistemaPlanetario sPlanetario : nebulosa.getLista_sistemas_planetarios()) {
                sPlanetario.setRutas_planetas(cargarRutasSistemaPlanetarioPlaneta(sPlanetario.getNombre()));
            }
        }
    }

    private void cargarMapaNebulosaSP(LinkedList<Nebulosa> listaNebulosa) {
        for (Nebulosa nebulosa : listaNebulosa) {
            nebulosa.setCostos_rutas(cargarCostosRutasSP(nebulosa.getNombre()));
        }
    }

    private LinkedList<LinkedList<Integer>> cargarCostosRutasSP(String nombre) {
        LinkedList<String> informacionRuta = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\mapaSistemasPlanetarios.csv");
        LinkedList<LinkedList<Integer>> resultado = new LinkedList<>();
        for (int i = 0; i < informacionRuta.size(); i++) {
            if (!informacionRuta.get(i).equalsIgnoreCase("")) {
                String arreglo[] = informacionRuta.get(i).split(";");
                if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                    LinkedList<Integer> lineaCargada = new LinkedList<>();
                    for (int j = 0; j < arreglo.length - 1; j++) {
                        lineaCargada.addLast(Integer.parseInt(arreglo[j]));
                    }
                    resultado.addLast(lineaCargada);
                }
            }
        }
        return resultado;
    }

    private LinkedList<LinkedList<Integer>> cargarRutasSistemaPlanetarioPlaneta(String nombre) {
        LinkedList<String> informacionRuta = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\mapaPlanetas.csv");
        LinkedList<LinkedList<Integer>> resultado = new LinkedList<>();
        for (int i = 0; i < informacionRuta.size(); i++) {
            if (!informacionRuta.get(i).equalsIgnoreCase("")) {
                String arreglo[] = informacionRuta.get(i).split(";");
                if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                    LinkedList<Integer> lineaCargada = new LinkedList<>();
                    for (int j = 0; j < arreglo.length - 1; j++) {
                        lineaCargada.addLast(Integer.parseInt(arreglo[j]));
                    }
                    resultado.addLast(lineaCargada);
                }
            }
        }
        return resultado;
    }

    private void cargarGasolinera(LinkedList<SistemaPlanetario> listaSistemasPlanetarios) {
        for (SistemaPlanetario sistemaPlanetario : listaSistemasPlanetarios) {
            for (Planeta planeta : sistemaPlanetario.getLista_planetas()) {
                if (planeta.getGasolinera_del_sistema() != null) {
                    planeta.setGasolinera_del_sistema(cargarGasolineraPlaneta(planeta.getNombre()));
                }
            }
        }
    }

    private Gasolinera cargarGasolineraPlaneta(String nombre) {
        Gasolinera gasolinera = new Gasolinera();
        LinkedList<String> informacionGasolinera = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\gasolinera.csv");
        for (int i = 0; i < informacionGasolinera.size(); i++) {
            String arreglo[] = informacionGasolinera.get(i).split(";");
            if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                gasolinera = new Gasolinera(Integer.parseInt(arreglo[0]), Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]));
                HashMap<String, Integer> precioGasolina = new HashMap<>();
                precioGasolina.put("iridio", Integer.parseInt(arreglo[3]));
                precioGasolina.put("paladio", Integer.parseInt(arreglo[4]));
                precioGasolina.put("platino", Integer.parseInt(arreglo[5]));
                precioGasolina.put("zero", Integer.parseInt(arreglo[6]));
                HashMap<String, Integer> precioSonda = new HashMap<>();
                precioSonda.put("iridio", Integer.parseInt(arreglo[7]));
                precioSonda.put("paladio", Integer.parseInt(arreglo[8]));
                precioSonda.put("platino", Integer.parseInt(arreglo[9]));
                precioSonda.put("zero", Integer.parseInt(arreglo[10]));
                gasolinera.setPrecioGasolina(precioGasolina);
                gasolinera.setPrecioSonda(precioSonda);
            }
        }
        return gasolinera;
    }

    private void cargarEnemigos(LinkedList<Planeta> ListaPlaneta) {
        for (Planeta planeta : ListaPlaneta) {
            planeta.setEnemigos(cargarEnemigosPlaneta(planeta.getNombre()));
        }
    }

    private LinkedList<Enemigo> cargarEnemigosPlaneta(String nombre) {
        LinkedList<Enemigo> listaEnemigos = new LinkedList<>();
        LinkedList<String> informacionEnemigo = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\enemigo.csv");
        for (int i = 0; i < informacionEnemigo.size(); i++) {
            String arreglo[] = informacionEnemigo.get(i).split(";");
            if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                Enemigo enemigo = new Enemigo(Integer.parseInt(arreglo[0]), Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]), arreglo[3], arreglo[4]);
                listaEnemigos.add(enemigo);
            }
        }
        return listaEnemigos;
    }

    private void cargarPlanetas(LinkedList<SistemaPlanetario> listaSistemasPlanetarios) {
        for (SistemaPlanetario sistemaP : listaSistemasPlanetarios) {
            sistemaP.setLista_planetas(cargarPlanetaSistema(sistemaP.getNombre()));
        }
    }

    private LinkedList<Planeta> cargarPlanetaSistema(String nombre) {
        LinkedList<Planeta> listaPlanetas = new LinkedList<>();
        LinkedList<String> informacionPlaneta = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\planetas.csv");
        for (int i = 0; i < informacionPlaneta.size(); i++) {
            if (!informacionPlaneta.get(i).equals("")) {
                String arreglo[] = informacionPlaneta.get(i).split(";");
                if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                    Planeta planeta = new Planeta(arreglo[0], Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]), arreglo[7], arreglo[8]);
                    HashMap<String, Integer> materiales = new HashMap<>();
                    materiales.put("iridio", Integer.parseInt(arreglo[3]));
                    materiales.put("paladio", Integer.parseInt(arreglo[4]));
                    materiales.put("platino", Integer.parseInt(arreglo[5]));
                    materiales.put("zero", Integer.parseInt(arreglo[6]));
                    planeta.setMateriales(materiales);
                    listaPlanetas.add(planeta);
                }
            }
        }
        return listaPlanetas;
    }

    private void cargarSistemasPlanetarios(LinkedList<Nebulosa> listaNebulosas) {
        for (Nebulosa nebulosa : listaNebulosas) {
            nebulosa.setLista_sistemas_planetarios(cargarSistemaPlanetarioNebulosa(nebulosa.getNombre()));
        }
    }

    private LinkedList<SistemaPlanetario> cargarSistemaPlanetarioNebulosa(String nombre) {
        LinkedList<SistemaPlanetario> listaSistemas = new LinkedList<>();
        LinkedList<String> informacionSistema = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\sistemaPlanetario.csv");
        for (int i = 0; i < informacionSistema.size(); i++) {
            String arreglo[] = informacionSistema.get(i).split(";");
            if (arreglo[arreglo.length - 1].equalsIgnoreCase(nombre)) {
                SistemaPlanetario sistema = new SistemaPlanetario(arreglo[0], Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]));
                listaSistemas.add(sistema);
            }
        }
        return listaSistemas;
    }

    private LinkedList<Nebulosa> cargarNebulosas() {
        LinkedList<Nebulosa> listaNebulosas = new LinkedList<>();
        LinkedList<String> informacionNebulosa = GestionArchivos.leerArchivo("src\\nave_espacial\\archivos\\nebulosa.csv");
        for (int i = 0; i < informacionNebulosa.size(); i++) {
            if (!informacionNebulosa.get(i).equalsIgnoreCase("")) {
                String arreglo[] = informacionNebulosa.get(i).split(";");
                Nebulosa nebulosa = new Nebulosa(arreglo[0], Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]));
                listaNebulosas.add(nebulosa);
            }
        }
        return listaNebulosas;
    }

    public boolean guardarCambiosArchivos() {
        try {
            LinkedList<String> lineas = obtenerLineasNebulosa(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "nebulosa.csv");
            lineas = obtenerLineasSistemaPlanetario(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "sistemaPlanetario.csv");
            lineas = obtenerLineasPlaneta(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "planetas.csv");
            lineas = obtenerLineasEnemigos(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "enemigo.csv");
            lineas = obtenerLineasGasolineras(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "gasolinera.csv");
            lineas = obtenerLineasMapaSistemasPlanetarioPlaneta(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "mapaPlanetas.csv");
            lineas = obtenerLineasMapaNebulosaSP(viaLactea.getLista_nebulosas());
            GestionArchivos.guardarArchivo(lineas, "mapaSistemasPlanetarios.csv");

            return true;
        } catch (Exception e) {
            System.err.println("Error guardar cambios " + e.getMessage());
            return false;
        }
    }

    private LinkedList<String> obtenerLineasMapaNebulosaSP(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebulosa : lista) {
            for (int i = 0; i < nebulosa.getCostos_rutas().size(); i++) {
                String linea = "";
                for (int j = 0; j < nebulosa.getCostos_rutas().get(i).size(); j++) {
                    linea += nebulosa.getCostos_rutas().get(i).get(j) + ";";
                }
                linea += nebulosa.getNombre();
                lineas.addLast(linea);
            }
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasMapaSistemasPlanetarioPlaneta(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebulosa : lista) {
            for (SistemaPlanetario sPlanetario : nebulosa.getLista_sistemas_planetarios()) {
                for (int i = 0; i < sPlanetario.getRutas_planetas().size(); i++) {
                    String linea = "";
                    for (int j = 0; j < sPlanetario.getRutas_planetas().get(i).size(); j++) {
                        linea += sPlanetario.getRutas_planetas().get(i).get(j) + ";";
                    }
                    linea += sPlanetario.getNombre();
                    lineas.addLast(linea);
                }
            }
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasNebulosa(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebulosa : lista) {
            lineas.addLast(nebulosa.getNombre() + ";" + nebulosa.getPosX() + ";" + nebulosa.getPosY());
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasSistemaPlanetario(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebula : lista) {
            for (SistemaPlanetario splaneta : nebula.getLista_sistemas_planetarios()) {
                StringBuilder data = new StringBuilder();
                data.append(splaneta.getNombre());
                data.append(";");
                data.append(splaneta.getPosX());
                data.append(";");
                data.append(splaneta.getPosY());

                data.append(";");
                data.append(nebula.getNombre());
                lineas.add(data.toString());
            }
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasPlaneta(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebula : lista) {
            for (SistemaPlanetario splaneta : nebula.getLista_sistemas_planetarios()) {
                for (Planeta planeta : splaneta.getLista_planetas()) {
                    StringBuilder data = new StringBuilder();
                    data.append(planeta.getNombre());
                    data.append(";");
                    data.append(planeta.getPosX());
                    data.append(";");
                    data.append(planeta.getPosY());
                    data.append(";");
                    data.append(planeta.getMateriales().get("iridio"));
                    data.append(";");
                    data.append(planeta.getMateriales().get("paladio"));
                    data.append(";");
                    data.append(planeta.getMateriales().get("platino"));
                    data.append(";");
                    data.append(planeta.getMateriales().get("zero"));
                    data.append(";");
                    data.append((planeta.getTeletransportador() == (null)) ? "0" : "1");
                    data.append(";");
                    data.append((planeta.getGasolinera_del_sistema() == null) ? "0" : "1");
                    data.append(";");
                    data.append(splaneta.getNombre());
                    lineas.add(data.toString());
                }
            }
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasEnemigos(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebula : lista) {
            for (SistemaPlanetario splaneta : nebula.getLista_sistemas_planetarios()) {
                for (Planeta planeta : splaneta.getLista_planetas()) {
                    for (Enemigo enemigo : planeta.getEnemigos()) {
                        StringBuilder data = new StringBuilder();
                        data.append(enemigo.getId());
                        data.append(";");
                        data.append(enemigo.getPosX());
                        data.append(";");
                        data.append(enemigo.getPosY());
                        data.append(";");
                        data.append(enemigo.getTipoenemigo());
                        data.append(";");
                        data.append(enemigo.getNivel_amenaza());
                        data.append(";");
                        data.append(planeta.getNombre());
                        lineas.add(data.toString());
                    }
                }
            }
        }
        return lineas;
    }

    private LinkedList<String> obtenerLineasGasolineras(LinkedList<Nebulosa> lista) {
        LinkedList<String> lineas = new LinkedList<>();
        for (Nebulosa nebula : lista) {
            for (SistemaPlanetario splaneta : nebula.getLista_sistemas_planetarios()) {
                for (Planeta planeta : splaneta.getLista_planetas()) {
                    if (planeta.getGasolinera_del_sistema() != null) {
                        Gasolinera g = planeta.getGasolinera_del_sistema();
                        StringBuilder data = new StringBuilder();
                        data.append(g.getId());
                        data.append(";");
                        data.append(g.getPosX());
                        data.append(";");
                        data.append(g.getPosY());
                        data.append(";");
                        data.append(g.getPrecioGasolina().get("iridio"));
                        data.append(";");
                        data.append(g.getPrecioGasolina().get("paladio"));
                        data.append(";");
                        data.append(g.getPrecioGasolina().get("platino"));
                        data.append(";");
                        data.append(g.getPrecioGasolina().get("zero"));
                        data.append(";");
                        data.append(g.getPrecioSonda().get("iridio"));
                        data.append(";");
                        data.append(g.getPrecioSonda().get("paladio"));
                        data.append(";");
                        data.append(g.getPrecioSonda().get("platino"));
                        data.append(";");
                        data.append(g.getPrecioSonda().get("zero"));
                        data.append(";");
                        data.append(planeta.getNombre());
                        lineas.add(data.toString());
                    }
                }
            }
        }
        return lineas;
    }

}
