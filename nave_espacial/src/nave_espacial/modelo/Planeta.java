/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Planeta implements Runnable {

    /**
     * @return the totalMaterial
     */
    public int getTotalMaterial() {
        return totalMaterial;
    }

    private String nombre;
    private int posX;
    private int posY;
    private List<Enemigo> enemigos;
    private HashMap<String, Integer> materiales;
    private TeleTransportador teletransportador;
    private Gasolinera gasolinera_del_sistema;
    private ImageIcon imagenPlaneta;
    private ImageIcon imagenPlanetaEnemigo;
    private int totalMaterial;
    private int visitado=0;
    

    private Thread hilo;

    public Planeta() {
        enemigos = new LinkedList<>();
        this.imagenPlaneta = cargarImagen();
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    public Planeta(String nombre, int posX, int posY, String teletransportador, String gasolinera) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        this.enemigos = new LinkedList<>();
        this.materiales = new HashMap<>();
        if (teletransportador.equalsIgnoreCase("1")) {
            crearTeletransportador();
        }
        if (gasolinera.equalsIgnoreCase("1")) {
            this.gasolinera_del_sistema = new Gasolinera();
        }
        this.imagenPlaneta = cargarImagen();
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    private ImageIcon cargarImagen() {
        Random r = new Random();
        int dato = (r.nextInt(6) + 1);
        setImagenPlaneta(new ImageIcon(getClass().getResource("../vista/imagenes/tiposPlanetas/Planeta" + dato + ".png")));
        return getImagenPlaneta();
    }

    public void crearTeletransportador() {
        this.setTeletransportador(new TeleTransportador());
    }

    public void crearEnemigo(Enemigo enemigo) {
        if (this.enemigos != null && enemigo != null) {
            this.enemigos.add(enemigo);
        }
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
     * @return the enemigos
     */
    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    /**
     * @param enemigos the enemigos to set
     */
    public void setEnemigos(List<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    /**
     * @return the materiales
     */
    public HashMap<String, Integer> getMateriales() {
        return materiales;
    }

    /**
     * @param materiales the materiales to set
     */
    public void setMateriales(HashMap<String, Integer> materiales) {
        this.materiales = materiales;
    }

    /**
     * @return the teletransportador
     */
    public TeleTransportador getTeletransportador() {
        return teletransportador;
    }

    /**
     * @param teletransportador the teletransportador to set
     */
    public void setTeletransportador(TeleTransportador teletransportador) {
        this.teletransportador = teletransportador;
    }

    /**
     * @return the imagenPlaneta
     */
    public ImageIcon getImagenPlaneta() {
        return (enemigos.size() > 0) ? imagenPlanetaEnemigo : imagenPlaneta;
    }

    /**
     * @param imagenPlaneta the imagenPlaneta to set
     */
    public void setImagenPlaneta(ImageIcon imagenPlaneta) {
        this.imagenPlaneta = imagenPlaneta;
    }

    /**
     * @param imagenPlanetaEnemigo the imagenPlanetaEnemigo to set
     */
    public void setImagenPlanetaEnemigo(ImageIcon imagenPlanetaEnemigo) {
        this.imagenPlanetaEnemigo = imagenPlanetaEnemigo;
    }

    /**
     * @return the gasolinera_del_sistema
     */
    public Gasolinera getGasolinera_del_sistema() {
        return gasolinera_del_sistema;
    }

    /**
     * @param gasolinera_del_sistema the gasolinera_del_sistema to set
     */
    public void setGasolinera_del_sistema(Gasolinera gasolinera_del_sistema) {
        this.gasolinera_del_sistema = gasolinera_del_sistema;
    }

    public boolean conTeletransportador() {
        return (this.teletransportador != null);
    }

    public boolean conGasolinera() {
        return (this.gasolinera_del_sistema != null);
    }

    public boolean conEnemigos() {
        return (this.enemigos.size() > 0);
    }

    @Override
    public void run() {
        while (true) {
            if (materiales != null) {
                int sumaMateriales = 0;
                for (String llave : materiales.keySet()) {
                    sumaMateriales += materiales.get(llave);
                }
                this.totalMaterial = sumaMateriales;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.err.println("Error en hilo planeta:" + e);
            }
        }
    }

    /**
     * @return the visitado
     */
    public int getVisitado() {
        return visitado;
    }

    /**
     * @param visitado the visitado to set
     */
    public void setVisitado(int visitado) {
        this.visitado = visitado;
    }
}
