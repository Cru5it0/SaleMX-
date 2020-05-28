package org.med.salemx.model;


import org.med.salemx.model.Empresa;

public class ProductoServicio {
    private int idProductoServicio;
    private String nombre;
    private String foto;
    private double precio;
    private String descripcion;
    private int estatus;
    private Empresa empresa;

    public ProductoServicio(int idProductoServicio, String nombre, String foto, double precio, String descripcion, int estatus, Empresa empresa) {
        this.idProductoServicio = idProductoServicio;
        this.nombre = nombre;
        this.foto = foto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.empresa = empresa;
    }

    public ProductoServicio() {
    }

    
    public int getIdProductoServicio() {
        return idProductoServicio;
    }

    public void setIdProductoServicio(int idProductoServicio) {
        this.idProductoServicio = idProductoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    

    
}
