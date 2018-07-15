/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nave_espacial.modelo;

/**
 *
 * @author Lenovo
 */
public class Enemigo {
    private int id;
    private int posX;
    private int posY;
    private String tipoenemigo;
    private String nivel_amenaza;

    public Enemigo() {
    }

    public Enemigo(int id, int posX, int posY, String tipoenemigo, String nivel_amenaza) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.tipoenemigo = tipoenemigo;
        this.nivel_amenaza = nivel_amenaza;
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
     * @return the tipoenemigo
     */
    public String getTipoenemigo() {
        return tipoenemigo;
    }

    /**
     * @param tipoenemigo the tipoenemigo to set
     */
    public void setTipoenemigo(String tipoenemigo) {
        this.tipoenemigo = tipoenemigo;
    }

    /**
     * @return the nivel_amenaza
     */
    public String getNivel_amenaza() {
        return nivel_amenaza;
    }

    /**
     * @param nivel_amenaza the nivel_amenaza to set
     */
    public void setNivel_amenaza(String nivel_amenaza) {
        this.nivel_amenaza = nivel_amenaza;
    }

    
}
