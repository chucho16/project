/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Arma {
    private int id;
    private String Nombre;
    private String poder_de_ataque;
    private HashMap<String,Integer> precio_del_arma;//material llave, precio cantidad del material
    private int cantidad_municiones;
  
    public Arma() {
    }

    public Arma(int id, String Nombre, String poder_de_ataque, int cantidad_municiones) {
        this.id = id;
        this.Nombre = Nombre;
        this.poder_de_ataque = poder_de_ataque;
        this.cantidad_municiones = cantidad_municiones;
    }
    
    public Arma(int id, String Nombre, String poder_de_ataque, HashMap<String, Integer> precio_del_arma, int cantidad_municiones) {
        this.id = id;
        this.Nombre = Nombre;
        this.poder_de_ataque = poder_de_ataque;
        this.precio_del_arma = precio_del_arma;
        this.cantidad_municiones = cantidad_municiones;
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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the poder_de_ataque
     */
    public String getPoder_de_ataque() {
        return poder_de_ataque;
    }

    /**
     * @param poder_de_ataque the poder_de_ataque to set
     */
    public void setPoder_de_ataque(String poder_de_ataque) {
        this.poder_de_ataque = poder_de_ataque;
    }

    /**
     * @return the precio_del_arma
     */
    public HashMap<String,Integer> getPrecio_del_arma() {
        return precio_del_arma;
    }

    /**
     * @param precio_del_arma the precio_del_arma to set
     */
    public void setPrecio_del_arma(HashMap<String,Integer> precio_del_arma) {
        this.precio_del_arma = precio_del_arma;
    }

    /**
     * @return the cantidad_municiones
     */
    public int getCantidad_municiones() {
        return cantidad_municiones;
    }

    /**
     * @param cantidad_municiones the cantidad_municiones to set
     */
    public void setCantidad_municiones(int cantidad_municiones) {
        this.cantidad_municiones = cantidad_municiones;
    }
}
