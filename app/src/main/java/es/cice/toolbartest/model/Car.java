package es.cice.toolbartest.model;

/**
 * Created by cice on 20/1/17.
 */

public class Car {

    private String modelo;
    private String fabricante;
    private String descripcion;
    private int miniatura; //la miniatura
    private int imagen;

    public Car(String descripcion, String fabricante, int imagen, int miniatura, String modelo) {
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.imagen = imagen;
        this.miniatura = miniatura;
        this.modelo = modelo;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
