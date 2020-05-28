/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.model;

/**
 *
 * @author DELL
 */
public class Promociones {
    
    private int idPromocion;
    private String titulo;
    private String fechaInicio;
    private String fechaTermino;
    private String imagen;
    private String descripcion;
    private double precio;
    private double descuento;
    private int estatus;
    private Sucursal sucursal;
    private ProductoServicio productoservicio; //id de producto o servicio 

    public Promociones() {
    }

    public Promociones(int idPromocion, String titulo, String fechaInicio, String fechaTermino, String imagen, String descripcion, double precio, double descuento, int estatus, Sucursal sucursal, ProductoServicio productoservicio) {
        this.idPromocion = idPromocion;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.estatus = estatus;
        this.sucursal = sucursal;
        this.productoservicio = productoservicio;
    }

    public Promociones(String titulo, String fechaInicio, String fechaTermino, String imagen, String descripcion, double precio, double descuento, int estatus, ProductoServicio productoservicio) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.estatus = estatus;
        this.productoservicio = productoservicio;
    }

    public Promociones(String titulo, String fechaInicio, String fechaTermino, String imagen, String descripcion, double precio, double descuento) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public ProductoServicio getProductoservicio() {
        return productoservicio;
    }

    public void setProductoservicio(ProductoServicio productoservicio) {
        this.productoservicio = productoservicio;
    }

    @Override
    public String toString() {
        return "Promociones{" + "idPromocion=" + idPromocion + ", titulo=" + titulo + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", imagen=" + imagen + ", descripcion=" + descripcion + ", precio=" + precio + ", descuento=" + descuento + ", estatus=" + estatus + ", sucursal=" + sucursal + ", productoservicio=" + productoservicio + '}';
    }    
    
}
