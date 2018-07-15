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
public class Nebulosa implements Runnable{

    private String nombre;
    private int posX;
    private int posY;
    private int totalMateriales;
    private LinkedList<SistemaPlanetario> lista_sistemas_planetarios;
    private LinkedList<LinkedList<Integer>> costos_rutas;
    private Thread hilo;

    public Nebulosa() {
        this.lista_sistemas_planetarios = new LinkedList<>();
        this.costos_rutas = new LinkedList<>();
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    public Nebulosa(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        this.lista_sistemas_planetarios = new LinkedList<>();
        this.costos_rutas = new LinkedList<>();
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    public void agregarTransicion(SistemaPlanetario a, SistemaPlanetario b, int costo) {
        int indiceA = lista_sistemas_planetarios.indexOf(a);
        int indiceB = lista_sistemas_planetarios.indexOf(b);
        costos_rutas.get(indiceA).set(indiceB, costo);
        costos_rutas.get(indiceB).set(indiceA, costo);
    }

    public void agregarSistemaPlanetario(SistemaPlanetario sistema_planetario) {
        getLista_sistemas_planetarios().addLast(sistema_planetario);
        for (int i = 0; i < getCostos_rutas().size(); i++) {
            getCostos_rutas().get(i).addLast(-1);
        }
        LinkedList<Integer> temporal = new LinkedList<>();
        for (int i = 0; i < getCostos_rutas().size() + 1; i++) {
            temporal.addLast(-1);
        }
        getCostos_rutas().addLast(temporal);
    }

    private void recalcularMatrizRutas() {
        this.costos_rutas = new LinkedList<>();
        for (int i = 0; i < this.lista_sistemas_planetarios.size(); i++) {
            LinkedList<Integer> listaTemporal = new LinkedList<>();
            for (int j = 0; j < this.lista_sistemas_planetarios.size(); j++) {
                listaTemporal.addLast(-1);
            }
            costos_rutas.addLast(listaTemporal);
        }
    }

    public void eliminarSistemaPlanetario(SistemaPlanetario sPlanetario) {
        int indiceSplanetario = this.lista_sistemas_planetarios.indexOf(sPlanetario);
        this.costos_rutas.remove(indiceSplanetario);
        for (LinkedList<Integer> lista : this.costos_rutas) {
            lista.remove(indiceSplanetario);
        }
        this.lista_sistemas_planetarios.remove(indiceSplanetario);
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
     * @return the lista_sistemas_planetarios
     */
    public LinkedList<SistemaPlanetario> getLista_sistemas_planetarios() {
        return lista_sistemas_planetarios;
    }

    /**
     * @param lista_sistemas_planetarios the lista_sistemas_planetarios to set
     */
    public void setLista_sistemas_planetarios(LinkedList<SistemaPlanetario> lista_sistemas_planetarios) {
        this.lista_sistemas_planetarios = lista_sistemas_planetarios;
        recalcularMatrizRutas();
    }

    /**
     * @return the costos_rutas
     */
    public LinkedList<LinkedList<Integer>> getCostos_rutas() {
        return costos_rutas;
    }

    /**
     * @param costos_rutas the costos_rutas to set
     */
    public void setCostos_rutas(LinkedList<LinkedList<Integer>> costos_rutas) {
        this.costos_rutas = costos_rutas;
    }

    /**
     * @return the banderaTeletransportador
     */
    public boolean isBanderaTeletransportador() {
        if (this.lista_sistemas_planetarios != null) {
            for (SistemaPlanetario sPlanetario : this.lista_sistemas_planetarios) {
                for (Planeta planeta : sPlanetario.getLista_planetas()) {
                    if (planeta.conTeletransportador()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * @return the banderaGasolinera
     */
    public boolean isBanderaGasolinera() {
        if (this.lista_sistemas_planetarios != null) {
            for (SistemaPlanetario sPlanetario : this.lista_sistemas_planetarios) {
                for (Planeta planeta : sPlanetario.getLista_planetas()) {
                    if (planeta.conGasolinera()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            if (lista_sistemas_planetarios != null) {
                int sumaMateriales = 0;
                for (SistemaPlanetario sPlanetario : lista_sistemas_planetarios) {
                    sumaMateriales += sPlanetario.getTotalMateriales();
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
