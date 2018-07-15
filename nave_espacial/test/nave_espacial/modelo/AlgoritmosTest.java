/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Danii
 */
public class AlgoritmosTest {
    
    
    @Test
    public void testLlegoGasolinaFaltaMaterialAmbasPartes() {
        Nave nave = new Nave();
        
        nave.setNumero_sondas(0);
        nave.setCantidad_conbustible(0);
        
        Gasolinera gasolina = new Gasolinera(0, 0, 0);
        
        HashMap<String, Integer> precioGas = new HashMap<>();
        HashMap<String, Integer> precioSonda = new HashMap<>();
        HashMap<String, Integer> material = new HashMap<>();
        
        precioGas.put("iridio", 10);
        precioGas.put("paladio", 15);
        precioGas.put("platino", 18);
        precioGas.put("zero", 9);
        
        precioSonda.put("iridio", 20);
        precioSonda.put("paladio", 15);
        precioSonda.put("platino", 18);
        precioSonda.put("zero", 39);
        
      
        gasolina.setPrecioGasolina(precioGas);
        gasolina.setPrecioSonda(precioSonda);
        
        Algoritmos.llegoGasolina(nave, gasolina);
        
        assertEquals(200000, nave.getCantidad_conbustible(), 0);
        assertEquals(100, nave.getNumero_sondas(), 0);
        
    }
    
    @Test
    public void testLlegoGasolinaFaltaGasolina() {
        Nave nave = new Nave();
        
        nave.setNumero_sondas(15);
        nave.setCantidad_conbustible(0);
        
        Gasolinera gasolina = new Gasolinera(0, 0, 0);
        
        HashMap<String, Integer> precioGas = new HashMap<>();
        HashMap<String, Integer> precioSonda = new HashMap<>();
        HashMap<String, Integer> material = new HashMap<>();
        
        precioGas.put("iridio", 10);
        precioGas.put("paladio", 15);
        precioGas.put("platino", 18);
        precioGas.put("zero", 9);
        
        precioSonda.put("iridio", 20);
        precioSonda.put("paladio", 15);
        precioSonda.put("platino", 18);
        precioSonda.put("zero", 39);
        
      
        gasolina.setPrecioGasolina(precioGas);
        gasolina.setPrecioSonda(precioSonda);
        
        Algoritmos.llegoGasolina(nave, gasolina);
        
        assertEquals(200000, nave.getCantidad_conbustible(), 0);
        assertEquals(15, nave.getNumero_sondas(), 0);
        
    }
    
     @Test
    public void testLlegoGasolinaFaltaSondas() {
        Nave nave = new Nave();
        
        nave.setNumero_sondas(0);
        nave.setCantidad_conbustible(100000);
        
        Gasolinera gasolina = new Gasolinera(0, 0, 0);
        
        HashMap<String, Integer> precioGas = new HashMap<>();
        HashMap<String, Integer> precioSonda = new HashMap<>();
        HashMap<String, Integer> material = new HashMap<>();
        
        precioGas.put("iridio", 10);
        precioGas.put("paladio", 15);
        precioGas.put("platino", 18);
        precioGas.put("zero", 9);
        
        precioSonda.put("iridio", 20);
        precioSonda.put("paladio", 15);
        precioSonda.put("platino", 18);
        precioSonda.put("zero", 39);
        
      
        gasolina.setPrecioGasolina(precioGas);
        gasolina.setPrecioSonda(precioSonda);
        
        Algoritmos.llegoGasolina(nave, gasolina);
        
        assertEquals(100000, nave.getCantidad_conbustible(), 0);
        assertEquals(100, nave.getNumero_sondas(), 0);
        
    }
    
}
