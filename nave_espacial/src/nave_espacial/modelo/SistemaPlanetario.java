/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.LinkedList;

/**
 *
 * @author Lenovo
 */
public class SistemaPlanetario implements Runnable {

    private String nombre;
    private int posX;
    private int posY;
    private int totalMateriales;
    private LinkedList<Planeta> lista_planetas;
    private LinkedList<LinkedList<Integer>> rutas_planetas;
    private Thread hilo;

    public SistemaPlanetario() {
        this.lista_planetas = new LinkedList<>();
        this.rutas_planetas = new LinkedList<>();
        hilo = new Thread(this);
        hilo.start();
    }

    public SistemaPlanetario(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        this.lista_planetas = new LinkedList<>();
        this.rutas_planetas = new LinkedList<>();
        hilo = new Thread(this);
        hilo.start();
    }

    public void agregarPlaneta(Planeta planeta) {
        getLista_planetas().addLast(planeta);
        for (int i = 0; i < getRutas_planetas().size(); i++) {
            getRutas_planetas().get(i).addLast(0);
        }
        LinkedList<Integer> temporal = new LinkedList<>();
        for (int i = 0; i < getRutas_planetas().size() + 1; i++) {
            temporal.addLast(0);
        }
        getRutas_planetas().addLast(temporal);
    }

    public void agregarTransicion(Planeta a, Planeta b) {
        int indiceA = lista_planetas.indexOf(a);
        int indiceB = lista_planetas.indexOf(b);
        this.rutas_planetas.get(indiceA).set(indiceB, 1);
        this.rutas_planetas.get(indiceB).set(indiceA, 1);
    }

    private void recalcularMatrizRutas() {
        this.rutas_planetas = new LinkedList<>();
        for (int i = 0; i < this.lista_planetas.size(); i++) {
            LinkedList<Integer> listaTemporal = new LinkedList<>();
            for (int j = 0; j < this.lista_planetas.size(); j++) {
                listaTemporal.addLast(0);
            }
            rutas_planetas.addLast(listaTemporal);
        }
    }

    public void eliminarPlaneta(Planeta planeta) {
        int indiceEliminar = this.lista_planetas.indexOf(planeta);
        this.rutas_planetas.remove(indiceEliminar);
        for (LinkedList<Integer> ruta : this.rutas_planetas) {
            ruta.remove(indiceEliminar);
        }
        this.lista_planetas.remove(indiceEliminar);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the lista_planetas
     */
    public LinkedList<Planeta> getLista_planetas() {
        return lista_planetas;
    }

    /**
     * @param lista_planetas the lista_planetas to set
     */
    public void setLista_planetas(LinkedList<Planeta> lista_planetas) {
        this.lista_planetas = lista_planetas;
        recalcularMatrizRutas();
    }

    /**
     * @return the rutas_planetas
     */
    public LinkedList<LinkedList<Integer>> getRutas_planetas() {
        return rutas_planetas;
    }

    /**
     * @param rutas_planetas the rutas_planetas to set
     */
    public void setRutas_planetas(LinkedList<LinkedList<Integer>> rutas_planetas) {
        this.rutas_planetas = rutas_planetas;
    }

    public boolean planetaConEnemigos() {
        for (Planeta planeta : this.lista_planetas) {
            if (planeta.conEnemigos()) {
                return true;
            }
        }
        return false;
    }

    public boolean planetaConTeletrasportador() {
        for (Planeta planeta : this.lista_planetas) {
            if (planeta.conTeletransportador()) {
                return true;
            }
        }
        return false;
    }

    public boolean planetaConGasolinera() {
        for (Planeta planeta : this.lista_planetas) {
            if (planeta.conGasolinera()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            if (lista_planetas != null) {
                int sumaMateriales = 0;
                for (Planeta planeta : lista_planetas) {
                    sumaMateriales += planeta.getTotalMaterial();
                }
                this.totalMateriales = sumaMateriales;
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.err.println("Error en hilo planeta:"+e);
                }
            }
        }

    }

    /**
     * @return the totalMateriales
     */
    public int getTotalMateriales() {
        return totalMateriales;
    }
}
