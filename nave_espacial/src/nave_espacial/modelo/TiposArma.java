/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Lenovo
 */
public class TiposArma {
  private Arma cañonThanix;
  private Arma escudoMultinucleo;
  private Arma blindajeNavesPesadas;
  private Arma propulsorOnix;
  private Arma cañonPlasma;
  private Arma capacidadDeDepositos;
  private Arma vidaInfinity;
  private Arma capacidadDeCombustible;
    
    

    public TiposArma() {
        
        escudoMultinucleo= new Arma(1,"escudo Multinucleo","alto",cargarPrecioEscudoMultinucleo(),1);
        blindajeNavesPesadas =new Arma(2,"blindaje Naves Pesadas","alto",cargarPrecioBlindajeNavesPesadas(),1);
        cañonThanix= new Arma(3,"cañon Thanix","alto",cargarPrecioThanix(),8);
        propulsorOnix=new Arma(4,"propulsor Onix","alto",cargarPrecioPropulsorOnix(),1);
        cañonPlasma=new Arma(5,"cañon Plasma","alto",cargarPrecioCañonPlasma(),1);
        capacidadDeDepositos=new Arma(6,"capacidad de depositos","alto",cargarPrecioCapacidadDeDepositos(),1);
        vidaInfinity=new Arma(7,"vida Infinity","alto",cargarPrecioVidaInfinity(),1);
        capacidadDeCombustible=new Arma(8,"capacidad de combustible","alto",cargarPrecioCapacidadDeCombustible(),1);
                     
    }

    public TiposArma(Arma escudoMultinucleo, Arma blindajeNavesPesadas, Arma cañonThanix, Arma cañonPlasma, Arma capacidadDeDepositos, Arma vidaInfinity, Arma capacidadDeCombustible) {
        this.escudoMultinucleo = escudoMultinucleo;
        this.blindajeNavesPesadas = blindajeNavesPesadas;
        this.cañonThanix = cañonThanix;
        this.cañonPlasma=cañonPlasma;
        this.capacidadDeDepositos=capacidadDeDepositos;
        this.vidaInfinity=vidaInfinity;
        this.capacidadDeCombustible=capacidadDeCombustible;
    }
    
       
    
    private HashMap<String,Integer> cargarPrecioEscudoMultinucleo(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 1800);
        precios.put("paladio", 1200);
        precios.put("platino", 1600);
        precios.put("zero", 500);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioBlindajeNavesPesadas(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 3500);
        precios.put("paladio", 5500);
        precios.put("platino",5100);
        precios.put("zero", 4000);
        return precios;
    }
        
    private HashMap<String,Integer> cargarPrecioThanix(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 6000);
        precios.put("paladio", 6000);
        precios.put("platino", 6000);
        precios.put("zero", 4000);
       return precios;
    }
    
    

    
    private HashMap<String,Integer> cargarPrecioPropulsorOnix(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 1200);
        precios.put("paladio", 800);
        precios.put("platino", 1500);
        precios.put("zero", 1000);
        return precios;
    }
      
    private HashMap<String,Integer> cargarPrecioCañonPlasma(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 2800);
        precios.put("paladio", 3000);
        precios.put("platino", 3500);
        precios.put("zero", 2500);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioCapacidadDeDepositos(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 4000);
        precios.put("paladio", 4000);
        precios.put("platino",4000);
        precios.put("zero", 4000);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioVidaInfinity(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 1000);
        precios.put("paladio", 1000);
        precios.put("platino", 1000);
        precios.put("zero", 500);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioCapacidadDeCombustible(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 1500);
        precios.put("paladio", 2000);
        precios.put("platino", 1000);
        precios.put("zero",1500);
        return precios;
    }

    /**
     * @return the cañonThanix
     */
    public Arma getCañonThanix() {
        return cañonThanix;
    }

    /**
     * @param cañonThanix the cañonThanix to set
     */
    public void setCañonThanix(Arma cañonThanix) {
        this.cañonThanix = cañonThanix;
    }

    /**
     * @return the escudoMultinucleo
     */
    public Arma getEscudoMultinucleo() {
        return escudoMultinucleo;
    }

    /**
     * @param escudoMultinucleo the escudoMultinucleo to set
     */
    public void setEscudoMultinucleo(Arma escudoMultinucleo) {
        this.escudoMultinucleo = escudoMultinucleo;
    }

    /**
     * @return the blindajeNavesPesadas
     */
    public Arma getBlindajeNavesPesadas() {
        return blindajeNavesPesadas;
    }

    /**
     * @param blindajeNavesPesadas the blindajeNavesPesadas to set
     */
    public void setBlindajeNavesPesadas(Arma blindajeNavesPesadas) {
        this.blindajeNavesPesadas = blindajeNavesPesadas;
    }

    /**
     * @return the propulsorOnix
     */
    public Arma getPropulsorOnix() {
        return propulsorOnix;
    }

    /**
     * @param propulsorOnix the propulsorOnix to set
     */
    public void setPropulsorOnix(Arma propulsorOnix) {
        this.propulsorOnix = propulsorOnix;
    }

    /**
     * @return the cañonPlasma
     */
    public Arma getCañonPlasma() {
        return cañonPlasma;
    }

    /**
     * @param cañonPlasma the cañonPlasma to set
     */
    public void setCañonPlasma(Arma cañonPlasma) {
        this.cañonPlasma = cañonPlasma;
    }

    /**
     * @return the capacidadDeDepositos
     */
    public Arma getCapacidadDeDepositos() {
        return capacidadDeDepositos;
    }

    /**
     * @param capacidadDeDepositos the capacidadDeDepositos to set
     */
    public void setCapacidadDeDepositos(Arma capacidadDeDepositos) {
        this.capacidadDeDepositos = capacidadDeDepositos;
    }

    /**
     * @return the vidaInfinity
     */
    public Arma getVidaInfinity() {
        return vidaInfinity;
    }

    /**
     * @param vidaInfinity the vidaInfinity to set
     */
    public void setVidaInfinity(Arma vidaInfinity) {
        this.vidaInfinity = vidaInfinity;
    }

    /**
     * @return the capacidadDeCombustible
     */
    public Arma getCapacidadDeCombustible() {
        return capacidadDeCombustible;
    }

    /**
     * @param capacidadDeCombustible the capacidadDeCombustible to set
     */
    public void setCapacidadDeCombustible(Arma capacidadDeCombustible) {
        this.capacidadDeCombustible = capacidadDeCombustible;
    }

  
}
