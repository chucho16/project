/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ViaLactea {
    private String Nombre;
    private LinkedList<Nebulosa> lista_nebulosas;
    private Nave nave;

    public ViaLactea() {
    }

    public ViaLactea(String Nombre, LinkedList<Nebulosa> lista_nebulosas, Nave nave) {
        this.Nombre = Nombre;
        this.lista_nebulosas = lista_nebulosas;
        this.nave = nave;
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
     * @return the lista_nebulosas
     */
    public LinkedList<Nebulosa> getLista_nebulosas() {
        return lista_nebulosas;
    }

    /**
     * @param lista_nebulosas the lista_nebulosas to set
     */
    public void setLista_nebulosas(LinkedList<Nebulosa> lista_nebulosas) {
        this.lista_nebulosas = lista_nebulosas;
    }

    /**
     * @return the nave
     */
    public Nave getNave() {
        return nave;
    }

    /**
     * @param nave the nave to set
     */
    public void setNave(Nave nave) {
        this.nave = nave;
    }

  
  
}
