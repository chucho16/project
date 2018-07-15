/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class TeleTransportador{
    private int id;
    private List<Nebulosa> mapa_navegacion_rele;

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
     * @return the mapa_navegacion_rele
     */
    public List<Nebulosa> getMapa_navegacion_rele() {
        return mapa_navegacion_rele;
    }

    /**
     * @param mapa_navegacion_rele the mapa_navegacion_rele to set
     */
    public void setMapa_navegacion_rele(List<Nebulosa> mapa_navegacion_rele) {
        this.mapa_navegacion_rele = mapa_navegacion_rele;
    }

}
