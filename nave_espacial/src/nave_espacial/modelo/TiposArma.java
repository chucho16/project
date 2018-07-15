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
  private Arma propulsor;
  private Arma armaEnergeticaLuz;
  private Arma reactor;
  private Arma invisibilidad;
  private Arma armaHielo;
    
    

    public TiposArma() {
        cañonThanix= new Arma(1,"cañon Thanix","alto",cargarPrecioThanix(),8);
        escudoMultinucleo= new Arma(2,"escudo Multinucleo","alto",cargarPrecioEscudoMultinucleo(),1);
        blindajeNavesPesadas =new Arma(3,"blindaj eNavesPesadas","alto",cargarPrecioBlindajeNavesPesadas(),1);
        propulsor=new Arma(4,"propulsor","alto",cargarPrecioPropulsor(),1);
        armaEnergeticaLuz=new Arma(5,"arma Energetica Luz","bajo",cargarPrecioArmaEnergetica(),30);
        reactor=new Arma(6,"reactor","alto",cargarPrecioReactor(),1);
        invisibilidad=new Arma(7,"invisibilidad","medio",cargarPrecioInvisibilidad(),2);
        armaHielo=new Arma(8,"arma Hielo","medio",cargarPrecioArmaDeHielo(),10);      
    }

    public TiposArma(Arma cañonThanix, Arma escudoMultinucleo, Arma blindajeNavesPesadas, Arma propulsor, Arma armaEnergeticaLuz, Arma reactor, Arma invisibilidad, Arma armaHielo) {
        this.cañonThanix = cañonThanix;
        this.escudoMultinucleo = escudoMultinucleo;
        this.blindajeNavesPesadas = blindajeNavesPesadas;
        this.propulsor = propulsor;
        this.armaEnergeticaLuz = armaEnergeticaLuz;
        this.reactor = reactor;
        this.invisibilidad = invisibilidad;
        this.armaHielo = armaHielo;
    }
    
    
    private HashMap<String,Integer> cargarPrecioThanix(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 2093);
        precios.put("paladio", 2254);
        precios.put("platino", 0);
        precios.put("zero", 1987);
       return precios;
    }
    
     private HashMap<String,Integer> cargarPrecioEscudoMultinucleo(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 1000);
        precios.put("paladio", 250);
        precios.put("platino", 530);
        precios.put("zero", 2000);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioBlindajeNavesPesadas(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 2000);
        precios.put("paladio", 1500);
        precios.put("platino",800);
        precios.put("zero", 0);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioPropulsor(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 300);
        precios.put("paladio", 500);
        precios.put("platino", 1000);
        precios.put("zero", 2000);
        return precios;
    }
      
    private HashMap<String,Integer> cargarPrecioArmaEnergetica(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 0);
        precios.put("paladio", 240);
        precios.put("platino", 400);
        precios.put("zero", 25);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioReactor(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 5000);
        precios.put("paladio", 100);
        precios.put("platino",20);
        precios.put("zero", 10);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioArmaDeHielo(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 700);
        precios.put("paladio", 1200);
        precios.put("platino", 2000);
        precios.put("zero", 270);
        return precios;
    }
    
    private HashMap<String,Integer> cargarPrecioInvisibilidad(){
        HashMap<String, Integer> precios = new HashMap<>();
        precios.put("iridio", 900);
        precios.put("paladio", 300);
        precios.put("platino", 856);
        precios.put("zero", 708);
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
     * @return the propulsor
     */
    public Arma getPropulsor() {
        return propulsor;
    }

    /**
     * @param propulsor the propulsor to set
     */
    public void setPropulsor(Arma propulsor) {
        this.propulsor = propulsor;
    }

    /**
     * @return the armaEnergeticaLuz
     */
    public Arma getArmaEnergeticaLuz() {
        return armaEnergeticaLuz;
    }

    /**
     * @param armaEnergeticaLuz the armaEnergeticaLuz to set
     */
    public void setArmaEnergeticaLuz(Arma armaEnergeticaLuz) {
        this.armaEnergeticaLuz = armaEnergeticaLuz;
    }

    /**
     * @return the reactor
     */
    public Arma getReactor() {
        return reactor;
    }

    /**
     * @param reactor the reactor to set
     */
    public void setReactor(Arma reactor) {
        this.reactor = reactor;
    }

    /**
     * @return the invisibilidad
     */
    public Arma getInvisibilidad() {
        return invisibilidad;
    }

    /**
     * @param invisibilidad the invisibilidad to set
     */
    public void setInvisibilidad(Arma invisibilidad) {
        this.invisibilidad = invisibilidad;
    }

    /**
     * @return the armaHielo
     */
    public Arma getArmaHielo() {
        return armaHielo;
    }

    /**
     * @param armaHielo the armaHielo to set
     */
    public void setArmaHielo(Arma armaHielo) {
        this.armaHielo = armaHielo;
    }
    
}
