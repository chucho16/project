/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Gasolinera {

    private int id;
    private int posX;
    private int posY;
    private ImageIcon imagen;
    private HashMap<String, Integer> precioGasolina;//llave material y valor cantidad, es lo que me pagan por la gasolina
    private HashMap<String, Integer> precioSonda;//llave material y valor cantidad

    public Gasolinera() {
        this.precioGasolina = new HashMap<>();
        this.precioSonda = new HashMap<>();
        this.imagen = new ImageIcon(getClass().getResource("../vista/imagenes/tiposPlanetas/gasolinera1.png"));
    }

    public Gasolinera(int id, int posX, int posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.precioGasolina = new HashMap<>();
        this.precioSonda = new HashMap<>();
        this.imagen = new ImageIcon(getClass().getResource("../vista/imagenes/tiposPlanetas/gasolinera1.png"));
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the precioGasolina
     */
    public HashMap<String, Integer> getPrecioGasolina() {
        return precioGasolina;
    }

    /**
     * @param precioGasolina the precioGasolina to set
     */
    public void setPrecioGasolina(HashMap<String, Integer> precioGasolina) {
        this.precioGasolina = precioGasolina;
    }

    /**
     * @return the precioSonda
     */
    public HashMap<String, Integer> getPrecioSonda() {
        return precioSonda;
    }

    /**
     * @param precioSonda the precioSonda to set
     */
    public void setPrecioSonda(HashMap<String, Integer> precioSonda) {
        this.precioSonda = precioSonda;
    }

    /**
     * @return the imagen
     */
    public ImageIcon getImagen() {
        return imagen;
    }

}
