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
import static nave_espacial.controlador.ControladorInicio.nave;
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
    private List<Object> ubicacion;//solo ubica la tierra
    private List<Object> ubicacionCualquiera;
    private String dondeEstoy;
    private ControladorPlaneta controlPlaneta;
    private ControladorSistemaPlanetario controlSP;
    private ControladorNebulosa controlNebulosa;

//crear un sistema ofencivo que determina que arma usar ante un ataque
    public Nave() {
        ubicacion = Algoritmos.ubicarTierra();        
        cantidad_material_tienenave = new HashMap<>();
        cantidad_material_tienenave.put("iridio", 0);
        cantidad_material_tienenave.put("paladio", 0);
        cantidad_material_tienenave.put("platino",0);
        cantidad_material_tienenave.put("zero", 0);
        armas = new LinkedList<>();
        armas.add(new Arma(0, "Escudo multinúcleo", "baja", 0));
        armas.add(new Arma(0, "Blindaje para naves pesadas", "media", 0));
        armas.add(new Arma(0, "Cañón Thanix", "alta", 0));
        imagenI = new ImageIcon(getClass().getResource("../vista/imagenes/nave/nave3.png"));
        imagenD = new ImageIcon(getClass().getResource("../vista/imagenes/nave/nave32.png"));
        existe = true;
        sentido = true;
//        x = ((Planeta) ubicacion.get(2)).getPosX();
//        y = ((Planeta) ubicacion.get(2)).getPosY();
        x= 450;
        y=50;
//        xDestino = x;
//        yDestino = 220;
//        xOrigen = xDestino;
//        yOrigen = 220;
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
        ubicarNaveSalidaPlaneta();
           while(true){
        switch (dondeEstoy) {
              case "Planeta":
                  if((!((Planeta) ubicacion.get(2)).getNombre().equalsIgnoreCase("Tierra"))&&(!((SistemaPlanetario) ubicacion.get(1)).equals(Algoritmos.ubicarTierra().get(1)))){
                 ubicacion.add(2,((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().getFirst());
                  }
                  entrarPlaneta((Planeta) ubicacion.get(2));
                  try {
                    Thread.sleep(5000);
                    
                } catch (Exception e) {
                }///hacer aqui una simulacion alguna cosa que muestre que se extraen materiales
                  ubicacionCualquiera = Algoritmos.ubicarCualquierPlaneta((Planeta) ubicacion.get(2)); 
                  for (int i = 0; i < ((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().size(); i++) {
                    if(((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getNombre()==((Planeta) ubicacionCualquiera.get(2)).getNombre()){
                    for (String llave : nave.getCantidad_material_tienenave().keySet()) {
                    if (nave.getCantidad_material_tienenave().get(llave) <= 2000000) {
                       nave.getCantidad_material_tienenave().put("iridio",(nave.getCantidad_material_tienenave().get("iridio"))+((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().get("iridio"));    
                      ((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().put("iridio",0);
                       nave.getCantidad_material_tienenave().put("paladio",(nave.getCantidad_material_tienenave().get("paladio"))+((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().get("paladio"));
                      ((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().put("paladio",0);
                       nave.getCantidad_material_tienenave().put("platino",(nave.getCantidad_material_tienenave().get("platino"))+((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().get("platino"));                      
                      ((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().put("platino",0);    
                       nave.getCantidad_material_tienenave().put("zero",(nave.getCantidad_material_tienenave().get("zero"))+((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().get("zero"));                      
                      ((SistemaPlanetario) ubicacionCualquiera.get(1)).getLista_planetas().get(i).getMateriales().put("zero",0);              
                  }}}}
                  ubicacion.remove(2);
                  ubicarNaveSalidaPlaneta();
                  controlPlaneta.destruirFrame();
                  animacionSalir();
                  ubicarNaveSalidaPlaneta();
                  dondeEstoy="SistemaPlanetario";
                  
                break;
            case "SistemaPlanetario":
             if (cantidadTotalMaterialNave() <= 2000000) {   //20000 porque 5000 por cada material
                entrarSistemaP((SistemaPlanetario) ubicacion.get(1));              
                Planeta objetivo = elegirMejorPlanetaAledaño();
                moverNave(new Point(objetivo.getPosX(), objetivo.getPosY()));
                int calculo = 0;
                int conty = 0;
                int contx = 0;
                try {
                    Thread.sleep(3000);
                    
                } catch (Exception e) {
                }
                moverA(objetivo, contx, conty);
                ubicarNaveSalidaSistemaPlanetario();
                ubicacion.add(2, objetivo);
                controlSP.destruirFrame();
                if(((SistemaPlanetario) ubicacion.get(1)).getTotalMateriales()>100){
                dondeEstoy="Planeta";
                }else{
                 ubicacion.remove(1);
                 ubicacion.add(1,((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().getFirst());
                dondeEstoy="Nebulosa";  
                }
                break;
                  }
            case "Nebulosa":
                ubicacionCualquiera = Algoritmos.ubicarCualquierPlaneta((Planeta) ubicacion.get(2)); 
                if (cantidadTotalMaterialNave() <= 2000000) {
                entrarNebulosa((Nebulosa) ubicacionCualquiera.get(0));
                SistemaPlanetario objetivo = elegirMejorSPAledaño();
                moverNave(new Point(objetivo.getPosX(), objetivo.getPosY()));
                int calculo = 0;
                int conty = 0;
                int contx = 0;
                try {
                    Thread.sleep(3000);
                    
                } catch (Exception e) {
                }
                moverB(objetivo, contx, conty);
                ubicarNaveSalidaNebulosa();
                ubicacion.add(1, objetivo);
                ubicacion.add(2,((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().getFirst());
                controlNebulosa.destruirFrame();
                dondeEstoy="SistemaPlanetario";
                break;
                }
        }
        }
    }
    
    
  private Integer cantidadTotalMaterialNave(){
        Integer cantidadSuma=0;   
        for (String llave : nave.getCantidad_material_tienenave().keySet()) {
            cantidadSuma=cantidadSuma+nave.getCantidad_material_tienenave().get(llave);       
        }
        return cantidadSuma;
   }
 

    private void moverA(Planeta objetivo, int contx, int conty) {
        int variable=1;
           while (variable==1) {
            if (x != xDestino) {
               x = contx;
                
            }else{
                conty = 0;
            }
            if (y != yDestino) {
                y =(contx*yOrigen - contx*yDestino +xOrigen * yDestino- xDestino*yOrigen)/(xOrigen - xDestino);
                if(!sentido){
                    contx++;
                }else{
                    contx--;
                }
            }   
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            if(((x==objetivo.getPosX()-1)||(x==objetivo.getPosX()))||(y==objetivo.getPosY())){
                variable=2;
            }
           }
    }
    
    private void moverB(SistemaPlanetario objetivo, int contx, int conty) {
        int variable=1;
           while (variable==1) {
            if (x != xDestino) {
               x = contx;
                
            }else{
                conty = 0;
            }
            if (y != yDestino) {
                y =(contx*yOrigen - contx*yDestino +xOrigen * yDestino- xDestino*yOrigen)/(xOrigen - xDestino);
                if(!sentido){
                    contx++;
                }else{
                    contx--;
                }
            }   
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            if(((x==objetivo.getPosX()-1)||(x==objetivo.getPosX()))||(y==objetivo.getPosY())){
                variable=2;
            }
           }
    }
 

    private Planeta elegirMejorPlanetaAledaño() {
        int indicePlaneta = controlSP.getsPlanetario().getLista_planetas().indexOf((Planeta) ubicacion.get(2));
        int indiceMayor = indicePlaneta;
        for (int i = 0; i < controlSP.getsPlanetario().getRutas_planetas().get(indicePlaneta).size(); i++) {
            if (controlSP.getsPlanetario().getRutas_planetas().get(indicePlaneta).get(i) == 1) {
                Planeta a = controlSP.getsPlanetario().getLista_planetas().get(indiceMayor);
                Planeta b = controlSP.getsPlanetario().getLista_planetas().get(i);
                if (a.getTotalMaterial() < b.getTotalMaterial()) {
                    indiceMayor = i;
                }
            }
        }
        Planeta objetivo = controlSP.getsPlanetario().getLista_planetas().get(indiceMayor);
        return objetivo;
    }
        private SistemaPlanetario elegirMejorSPAledaño() {
        int indiceSP = controlNebulosa.getNebulosa().getLista_sistemas_planetarios().indexOf((SistemaPlanetario) ubicacion.get(1));
        int indiceMayor = indiceSP;
        for (int i = 0; i < controlNebulosa.getNebulosa().getCostos_rutas().get(indiceSP).size(); i++) {
            if (controlNebulosa.getNebulosa().getCostos_rutas().get(indiceSP).get(i) > -1) {
                SistemaPlanetario a = controlNebulosa.getNebulosa().getLista_sistemas_planetarios().get(indiceMayor);
                SistemaPlanetario b = controlNebulosa.getNebulosa().getLista_sistemas_planetarios().get(i);
                if (a.getTotalMateriales() < b.getTotalMateriales()) {
                    indiceMayor = i;
                }
            }
        }
        SistemaPlanetario objetivo = controlNebulosa.getNebulosa().getLista_sistemas_planetarios().get(indiceMayor);
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
        y+=7;
        if (x < -100) {
            siSalio = true;
            if (controlPlaneta != null) {
                controlPlaneta.destruirFrame();
                dondeEstoy = "SistemaPlanetario";
         
                
            }else if (controlSP != null) {
                controlSP.destruirFrame();
                dondeEstoy = "Planeta";

            } else if (controlNebulosa != null) {
                controlNebulosa.destruirFrame();
                dondeEstoy = "SistemaPlanetario";
            }

            controlNebulosa = null;
            controlSP = null;
            controlPlaneta = null;
        }

    }
}


