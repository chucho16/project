/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import nave_espacial.controlador.ControladorNebulosa;
import nave_espacial.controlador.ControladorPlaneta;
import nave_espacial.controlador.ControladorSistemaPlanetario;
import static nave_espacial.modelo.Algoritmos.*;

/**
 *
 * @author Lenovo
 */
public class Nave implements Runnable {

    private String Nombre = "infinity";
    private Integer cantidad_conbustible = 0;
    private int numero_sondas = 15;
    private List<Arma> armas;
    private HashMap<String, List<HashMap>> mapa_navegacion;
    private HashMap<String, Integer> cantidad_material_tienenave;
    private String modo;//manual o automatico
    private boolean existe;
    public ImageIcon imagenI;
    public ImageIcon imagenD;
    private int ancho;
    private int alto;
    private int x;
    private int y;
    private int xDestino;
    private int yDestino;
    private int xOrigen;
    private int yOrigen;
    private boolean sentido;
    private boolean siSalio;
    private Thread hilo;
    private List<Object> ubicacion;
    private String dondeEstoy;
    private ControladorPlaneta controlPlaneta;
    private ControladorSistemaPlanetario controlSP;
    private ControladorNebulosa controlNebulosa;

    //cambio backup
//crear un sistema ofencivo que determina que arma usar ante un ataque
    public Nave() {
        ubicacion = Algoritmos.ubicarTierra();
        cantidad_material_tienenave = new HashMap<>();
        cantidad_material_tienenave.put("iridio", 8000);
        cantidad_material_tienenave.put("paladio", 8000);
        cantidad_material_tienenave.put("platino", 8000);
        cantidad_material_tienenave.put("zero", 8000);
        armas = new LinkedList<>();
        armas.add(new Arma(0, "Escudo multinúcleo", "baja", 0));
        armas.add(new Arma(0, "Blindaje para naves pesadas", "media", 0));
        armas.add(new Arma(0, "Cañón Thanix", "alta", 0));
        imagenI = new ImageIcon(getClass().getResource("../vista/imagenes/nave/nave3.png"));
        imagenD = new ImageIcon(getClass().getResource("../vista/imagenes/nave/nave32.png"));
        existe = true;
        sentido = true;
        x = ((Planeta) ubicacion.get(2)).getPosX();
        y = ((Planeta) ubicacion.get(2)).getPosY();
        xDestino = x;
        yDestino = 220;
        xOrigen = xDestino;
        yOrigen = 220;
        entrarPlaneta((Planeta) ubicacion.get(2));
        dondeEstoy = "planeta";
    }

    public void iniciarHilo() {
        hilo = new Thread(this);
        hilo.start();
    }

    public Nave(List<Arma> armas, HashMap<String, List<HashMap>> mapa_navegacion, HashMap<String, Integer> cantidad_material_tienenave, String modo) {
        this.armas = armas;
        this.mapa_navegacion = mapa_navegacion;
        this.cantidad_material_tienenave = cantidad_material_tienenave;
        this.modo = modo;
    }

    public void moverNave(Point punto) {
        if (x > punto.x) {
            sentido = true;     // true es que x se va a restar (Se va a devolver)
        } else {
            sentido = false;    // false es que x se va a sumar (va a avanzar)
        }
        xOrigen = x;
        yOrigen = y;
        xDestino = punto.x;
        yDestino = punto.y;
    }

    public boolean existeNave() {
        return existe;
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
        Nombre = Nombre;
    }

    /**
     * @return the cantidad_conbustible
     */
    public Integer getCantidad_conbustible() {
        return cantidad_conbustible;
    }

    /**
     * @param cantidad_conbustible the cantidad_conbustible to set
     */
    public void setCantidad_conbustible(int cantidad) {
        cantidad_conbustible = cantidad;
    }

    /**
     * @return the numero_sondas
     */
    public int getNumero_sondas() {
        return numero_sondas;
    }

    /**
     * @param numero_sondas the numero_sondas to set
     */
    public void setNumero_sondas(int numero) {
        numero_sondas = numero;
    }

    /**
     * @return the armas
     */
    public List<Arma> getArmas() {
        return armas;
    }

    /**
     * @param armas the armas to set
     */
    public void setArmas(List<Arma> armas) {
        armas = armas;
    }

    /**
     * @return the mapa_navegacion
     */
    public HashMap<String, List<HashMap>> getMapa_navegacion() {
        return mapa_navegacion;
    }

    /**
     * @param mapa_navegacion the mapa_navegacion to set
     */
    public void setMapa_navegacion(HashMap<String, List<HashMap>> mapa_navegacion) {
        mapa_navegacion = mapa_navegacion;
    }

    /**
     * @return the modo
     */
    public String getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(String modo) {
        modo = modo;
    }

    /**
     * @return the cantidad_material_tienenave
     */
    public HashMap<String, Integer> getCantidad_material_tienenave() {
        return cantidad_material_tienenave;
    }

    /**
     * @param cantidad_material_tienenave the cantidad_material_tienenave to set
     */
    public void setCantidad_material_tienenave(HashMap<String, Integer> cantidad_material_tienenave) {
        cantidad_material_tienenave = cantidad_material_tienenave;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param aAncho the ancho to set
     */
    public void setAncho(int aAncho) {
        ancho = aAncho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param aAlto the alto to set
     */
    public void setAlto(int aAlto) {
        alto = aAlto;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param aX the x to set
     */
    public void setX(int aX) {
        x = aX;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param aY the y to set
     */
    public void setY(int aY) {
        y = aY;
    }

    public ImageIcon getImagen() {
        if (sentido) {
            return imagenD;
        } else {
            return imagenI;
        }
    }

    @Override
    public void run() {
        animacionSalir();
        entrarSistemaP((SistemaPlanetario) ubicacion.get(1));
        ubicarNaveSalidaPlaneta();
        switch (dondeEstoy) {
            case "SistemaPlanetario":
                Planeta objetivo = elegirMejorPlanetaAledaño();
                moverNave(new Point(objetivo.getPosX(), objetivo.getPosY()));
                int calculo = 0;
                int conty = 0;
                int contx = 0;
                try {
                    Thread.sleep(3000);
                    
                } catch (Exception e) {
                }
                while(moverA(objetivo, contx, conty));
                ubicacion.remove(2);
                ubicacion.add(2, objetivo);

                break;
            case "Nebulosa":
                break;
        }

    }

    private boolean moverA(Planeta objetivo, int contx, int conty) {
        boolean respuesta = false;
        if (x != xDestino) {
            x = contx;

        } else {
            conty = 0;
        }
        if (y != yDestino) {
            y = (contx * yOrigen - contx * yDestino + xOrigen * yDestino - xDestino * yOrigen) / (xOrigen - xDestino);
            if (!sentido) {
                contx++;

            } else {
                contx--;
            }
            try {
                Thread.sleep(2500);
            } catch (Exception e) {
            }
            respuesta = true;
        }
        return respuesta;
    }

    private Planeta elegirMejorPlanetaAledaño() {
        int indicePlaneta = controlSP.getsPlanetario().getLista_planetas().indexOf((Planeta) ubicacion.get(2));
        int indiceMayor = indicePlaneta;
        for (int i = 0; i < controlSP.getsPlanetario().getRutas_planetas().get(indicePlaneta).size(); i++) {
            if (controlSP.getsPlanetario().getRutas_planetas().get(indicePlaneta).get(i) == 1) {
                Planeta a = controlSP.getsPlanetario().getLista_planetas().get(indiceMayor);;
                Planeta b = controlSP.getsPlanetario().getLista_planetas().get(i);
                if (a.getTotalMaterial() < b.getTotalMaterial()) {
                    indiceMayor = i;
                }
            }
        }
        Planeta objetivo = controlSP.getsPlanetario().getLista_planetas().get(indiceMayor);
        return objetivo;
    }

    private void ubicarNaveSalidaPlaneta() {
        this.x = ((Planeta) ubicacion.get(2)).getPosX();
        this.y = ((Planeta) ubicacion.get(2)).getPosY();
    }

    private void ubicarNaveSalidaNebulosa() {
        this.x = ((Nebulosa) ubicacion.get(0)).getPosX();
        this.y = ((Nebulosa) ubicacion.get(0)).getPosY();
    }

    private void ubicarNaveSalidaSistemaPlanetario() {
        this.x = ((SistemaPlanetario) ubicacion.get(1)).getPosX();
        this.y = ((SistemaPlanetario) ubicacion.get(1)).getPosY();
    }

    private void animacionSalir() {
        while (true) {
            if (!siSalio) {
                salir();
            } else {
                break;
            }
            try {
                Thread.sleep(30);
            } catch (Exception e) {
            }
        }
    }

    private void entrarPlaneta(Planeta planeta) {
        controlPlaneta = new ControladorPlaneta(planeta, "jugar");
    }

    private void entrarSistemaP(SistemaPlanetario sPlanetario) {
        controlSP = new ControladorSistemaPlanetario(sPlanetario, "jugar");
    }

    private void entrarNebulosa(Nebulosa nebulosa) {
        controlNebulosa = new ControladorNebulosa(nebulosa, "jugar");
    }

    private void salir() {
        x -= 10;
        if (x < -100) {
            siSalio = true;
            if (controlPlaneta != null) {
                controlPlaneta.destruirFrame();
                dondeEstoy = "SistemaPlanetario";
            } else if (controlSP != null) {

            } else if (controlNebulosa != null) {

            }
            controlNebulosa = null;
            controlSP = null;
            controlPlaneta = null;
        }

    }

}
