/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static nave_espacial.controlador.ControladorInicio.nave;
import nave_espacial.controlador.ControladorNebulosa;
import nave_espacial.controlador.ControladorPlaneta;
import nave_espacial.controlador.ControladorSistemaPlanetario;

/**
 *
 * @author Lenovo
 */
public class Nave implements Runnable {

    private String Nombre = "infinity";
    private Integer cantidad_conbustible = 30;
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
    private String rutaGasolinera;
    private Boolean banderaNaveExtraccion=false;

//crear un sistema ofencivo que determina que arma usar ante un ataque
    public Nave() {
        ubicacion = Algoritmos.ubicarTierra();
        cantidad_material_tienenave = new HashMap<>();
        cantidad_material_tienenave.put("iridio", 0);
        cantidad_material_tienenave.put("paladio", 0);
        cantidad_material_tienenave.put("platino", 0);
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
        x = 450;
        y = 50;
//        xDestino = x;
//        yDestino = 220;
//        xOrigen = xDestino;
//        yOrigen = 220;
        entrarPlaneta((Planeta) ubicacion.get(2));
        dondeEstoy = "Planeta";

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

    public void setCantidad_conbustible(int cantidad) {
        cantidad_conbustible = cantidad;
    }

    /**
     * @return the numero_sondas
     */
    public int getNumero_sondas() {
        return numero_sondas;
    }

    
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

    private void pintarExtraccionMaterial(int x,int y,Graphics g){

        g.drawLine(x, y,250,10);
    }

    private Integer cantidadTotalMaterialNave() {
        Integer cantidadSuma = 0;
        for (String llave : nave.getCantidad_material_tienenave().keySet()) {
            cantidadSuma = cantidadSuma + nave.getCantidad_material_tienenave().get(llave);
        }
        return cantidadSuma;
    }

    private int[] moverNaveAObjetivo(Object objetivo, String tipoObjetivo) {
        int diferenciales[] = new int[2];
        switch (tipoObjetivo) {
            case "Planeta":
                Planeta destino = (Planeta) objetivo;
                diferenciales[0] = Math.round(Math.abs(destino.getPosX() - x));
                diferenciales[1] = Math.round(Math.abs(destino.getPosY() - y));
                break;
            case "SistemaPlanetario":
                break;
            case "Nebulosa":
                break;
        }
        return diferenciales;
    }

    private void moverC(int xDestino, int yDestino, String tipo, int[] diferenciales) {
        int numeroPasos = 50;
        int porcionDistancia[] = {diferenciales[0] / numeroPasos, diferenciales[1] / numeroPasos};
        int i = 0;
        while (i < numeroPasos) {
            if (x < xDestino && y > yDestino) {
                x += porcionDistancia[0];
                y -= porcionDistancia[1];
            } else if (x < xDestino && y < yDestino) {
                x += porcionDistancia[0];
                y += porcionDistancia[1];
            } else if (x > xDestino && y > yDestino) {
                x -= porcionDistancia[0];
                y -= porcionDistancia[1];
            } else if (x > xDestino && y < yDestino) {
                x -= porcionDistancia[0];
                y += porcionDistancia[1];
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            i++;
        }
        x = xDestino;
        y = yDestino;
    }

    private void moverA(Planeta objetivo, int contx, int conty) {
        int variable = 1;
        while (variable == 1) {
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
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            if (((x == objetivo.getPosX() - 1) || (x == objetivo.getPosX())) || (y == objetivo.getPosY())) {
                variable = 2;
            }
        }
    }

    private void moverB(SistemaPlanetario objetivo, int contx, int conty) {
        int variable = 1;
        while (variable == 1) {
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
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            if (((x == objetivo.getPosX() - 1) || (x == objetivo.getPosX())) || (y == objetivo.getPosY())) {
                variable = 2;
            }
        }
    }

    private Planeta elegirMejorPlanetaAledaño() {
        int indicePlaneta = ((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().indexOf((Planeta) ubicacion.get(2));
        int indiceMayor = indicePlaneta;
        for (int i = 0; i < ((SistemaPlanetario) ubicacion.get(1)).getRutas_planetas().get(indicePlaneta).size(); i++) {
            if (((SistemaPlanetario) ubicacion.get(1)).getRutas_planetas().get(indicePlaneta).get(i) == 1) {
                Planeta a = ((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().get(indiceMayor);
                Planeta b = ((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().get(i);
                if (a.getTotalMaterial() < b.getTotalMaterial()) {
                    indiceMayor = i;
                }
            }
        }
        Planeta objetivo = ((SistemaPlanetario) ubicacion.get(1)).getLista_planetas().get(indiceMayor);
        if (objetivo.getTotalMaterial() > 0) {
            return objetivo;
        }
        return null;
    }

    private Object elegirMejorSistemaPlanetario() {
        int indiceSistemaP = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().indexOf(((SistemaPlanetario) ubicacion.get(1)));
        int indiceSistemaPMayor = indiceSistemaP;
        for (int i = 0; i < ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().size(); i++) {
            if (((Nebulosa) ubicacion.get(0)).getCostos_rutas().get(indiceSistemaP).get(i) > -1 && this.cantidad_conbustible >= ((Nebulosa) ubicacion.get(0)).getCostos_rutas().get(indiceSistemaP).get(i)) {
                SistemaPlanetario a = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().get(indiceSistemaP);
                SistemaPlanetario b = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().get(i);
                if (a.getTotalMateriales() < b.getTotalMateriales()) {
                    indiceSistemaPMayor = i;
                }
            }

        }
        SistemaPlanetario objetivo = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().get(indiceSistemaPMayor);
        if (objetivo.getTotalMateriales() > 0) {
            Object[] analisis = Algoritmos.calcularRutaGasolinera((Nebulosa) ubicacion.get(0), objetivo, this.cantidad_conbustible - ((Nebulosa) ubicacion.get(0)).getCostos_rutas().get(indiceSistemaP).get(indiceSistemaPMayor));
            int resultado = (int) analisis[1];
            if (resultado >= 0) {
                int y = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().indexOf(objetivo);
                int x = ((Nebulosa) ubicacion.get(0)).getLista_sistemas_planetarios().indexOf((SistemaPlanetario) ubicacion.get(1));
                this.cantidad_conbustible -= ((Nebulosa) ubicacion.get(0)).getCostos_rutas().get(x).get(y);
                return objetivo; // retorno objeto de tipo sistema planetario
            } else {
                analisis = Algoritmos.calcularRutaGasolinera((Nebulosa) ubicacion.get(0), (SistemaPlanetario) ubicacion.get(1), getCantidad_conbustible());
            }

            return analisis;

        }
        return null;
    }

    private void ubicarNaveSalidaPlaneta() {
        dondeEstoy = "SistemaPlanetario";
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
        sentido = true;
        while (true) {
            if (!siSalio) {
                salir();

            } else {
                siSalio = false;
                break;
            }
            try {
                Thread.sleep(30);
            } catch (Exception e) {
            }
        }
    }

    private void animacionSalirSistemaP() {
        sentido = true;
        while (true) {
            if (!siSalio) {
                salirSistemaP();

            } else {
                siSalio = false;
                break;
            }
            try {
                Thread.sleep(30);
            } catch (Exception e) {
            }
        }
    }

    private void entrarPlaneta(Planeta planeta) {
        dondeEstoy = "Planeta";
        controlPlaneta = new ControladorPlaneta(planeta, "jugar");
    }

    private void entrarSistemaP(SistemaPlanetario sPlanetario) {
        dondeEstoy = "SistemaPlanetario";
        controlSP = new ControladorSistemaPlanetario(sPlanetario, "jugar");
    }

    private void entrarNebulosa(Nebulosa nebulosa) {
        dondeEstoy = "Nebulosa";
        controlNebulosa = new ControladorNebulosa(nebulosa, "jugar");
    }

    private void salir() {
        switch (dondeEstoy) {
            case "Planeta":
                x -= 10;
                if (x < -100) {
                    siSalio = true;
                    if (controlPlaneta != null) {
                        controlPlaneta.destruirFrame();
                    }
                }
                break;
            case "SistemaPlanetario":

                if (controlSP != null) {
                    controlSP.destruirFrame();
                }
                break;
            case "Nebulosa":
                if (controlNebulosa != null) {
                    siSalio = false;
                    controlNebulosa.destruirFrame();
                }
                break;
        }
    }

    private void salirSistemaP() {
        x -= 10;
        if (x < -100) {
            siSalio = true;

            if (controlSP != null) {
                controlSP.destruirFrame();
            }
        }
    }

    private void extraerMateriales() {
        Planeta actual = (Planeta) ubicacion.get(2);
        int consumoSondas = 0;
        for (String materia : actual.getMateriales().keySet()) {
            if (actual.getMateriales().get(materia) > 0) {
                if (consumoSondas == 0) {
                    this.numero_sondas--;
                }
                consumoSondas++;
                this.cantidad_material_tienenave.put(materia, this.cantidad_material_tienenave.get(materia) + actual.getMateriales().get(materia));
                actual.getMateriales().put(materia, 0);
            }
            if (consumoSondas == 2) {
                consumoSondas = 0;
            }
        }

    }

    private void salirSistemaPlanetario() {
        animacionSalirSistemaP();
        siSalio = true;
        ubicarNaveSalidaSistemaPlanetario();
        entrarNebulosa((Nebulosa) ubicacion.get(0));
    }

    @Override
    public void run() {
        animacionSalir();
        ubicarNaveSalidaPlaneta();
        while (true) {
            switch (dondeEstoy) {
                case "SistemaPlanetario":
                    if (cantidadTotalMaterialNave() <= 2000000) {   //20000 porque 5000 por cada material
                        entrarSistemaP((SistemaPlanetario) ubicacion.get(1));
                        Planeta objetivo = elegirMejorPlanetaAledaño();
                        if (objetivo != null) {
                            moverNave(new Point(objetivo.getPosX(), objetivo.getPosY()));
                            try {
                                Thread.sleep(3000);

                            } catch (Exception e) {
                            }
                            int[] datos = moverNaveAObjetivo(objetivo, "Planeta");
                            moverC(objetivo.getPosX(), objetivo.getPosY(), "Planeta", datos);
                            ubicacion.remove(2);
                            ubicacion.add(2, objetivo);
                            try {
                                Thread.sleep(2000);

                            } catch (Exception e) {
                            }
                            salir();
                            entrarPlaneta(objetivo);

                        } else {
                            salirSistemaPlanetario();
                        }
                    }
                    break;
                case "Nebulosa":
                    Object resultado = elegirMejorSistemaPlanetario();

                    if (resultado != null && resultado.getClass().equals(new SistemaPlanetario().getClass())) {
                        SistemaPlanetario objetivo = (SistemaPlanetario) resultado;
                        moverNave(new Point(objetivo.getPosX(), objetivo.getPosY()));
                        try {
                            Thread.sleep(3000);

                        } catch (Exception e) {
                        }
                        int[] datos = moverNaveAObjetivo(objetivo, "SistemaPlanetario");
                        moverC(objetivo.getPosX(), objetivo.getPosY(), "SistemaPlanetario", datos);
                        try {
                            Thread.sleep(3000);

                        } catch (Exception e) {
                        }
                        ubicacion.remove(1);
                        ubicacion.remove(1);
                        ubicacion.add(1, objetivo);
                        ubicacion.add(2, objetivo.getLista_planetas().get(0));
                        try {
                            Thread.sleep(2000);

                        } catch (Exception e) {
                        }
                        salir();
                        ubicarNaveSalidaPlaneta();
                        dondeEstoy = "SistemaPlanetario";
                    } else if (resultado != null) {
                        Object[] r = (Object[]) resultado;
                        dondeEstoy = "buscarGasolinera";
                        rutaGasolinera = (String) r[0];
                    }
                    break;

                case "Planeta":
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    setBanderaNaveExtraccion((Boolean) true);
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }///hacer aqui una simulacion alguna cosa que muestre que se extraen materiales
                    extraerMateriales();
                    setBanderaNaveExtraccion((Boolean) false);
                    if(((Planeta) ubicacion.get(2)).conGasolinera()){
                        Algoritmos.llegoGasolina(this,((Planeta) ubicacion.get(2)).getGasolinera_del_sistema());
                    }
                    animacionSalir();
                    ubicarNaveSalidaPlaneta();
                    break;

                case "buscarGasolinera":
                    String arreglo []= rutaGasolinera.split("-");
                    if(arreglo[arreglo.length-1].equals(((SistemaPlanetario)ubicacion.get(1)).getNombre())){
                        JOptionPane.showMessageDialog(null, "Entrar en la gasolinera");
                    }else{
                        JOptionPane.showMessageDialog(null, "buscar "+arreglo[1]);
                    }
                    break;
            }
        }
    }

    /**
     * @return the banderaNaveExtraccion
     */
    public Boolean getBanderaNaveExtraccion() {
        return banderaNaveExtraccion;
    }

    /**
     * @param banderaNaveExtraccion the banderaNaveExtraccion to set
     */
    public void setBanderaNaveExtraccion(Boolean banderaNaveExtraccion) {
        this.banderaNaveExtraccion = banderaNaveExtraccion;
    }
}
