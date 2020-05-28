/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class Promocion {
    
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("dd/MM/yyyy");
    
    private int idPromocion;
    private String titulo;
    private DateTime fechaInicio;
    private DateTime fechaTermino;
    private String fechaInicioAsString;
    private String fechaTerminoAsString;
    private String imagen;
    private String descripcion;
    private double precio;
    private double descuento;
    private int estatus;
    private Sucursal sucursal;
    private ProductoServicio productoservicio; //id de producto o servicio 

    public Promocion() {
    } 

    public Promocion(int idPromocion, String titulo, DateTime fechaInicio, DateTime fechaTermino, String fechaInicioAsString, String fechaTerminoAsString, String imagen, String descripcion, double precio, double descuento, int estatus, Sucursal sucursal, ProductoServicio productoservicio) {
        this.idPromocion = idPromocion;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.fechaInicioAsString = fechaInicioAsString;
        this.fechaTerminoAsString = fechaTerminoAsString;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.estatus = estatus;
        this.sucursal = sucursal;
        this.productoservicio = productoservicio;
    }

    public Promocion(String titulo, String fechaInicioAsString, String fechaTerminoAsString, String imagen, String descripcion, double precio, double descuento, Sucursal sucursal, ProductoServicio productoservicio) {
        this.titulo = titulo;
        this.fechaInicioAsString = fechaInicioAsString;
        this.fechaTerminoAsString = fechaTerminoAsString;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.sucursal = sucursal;
        this.productoservicio = productoservicio;
    }

    public Promocion(int idPromocion, String titulo, String fechaInicioAsString, String fechaTerminoAsString, String imagen, String descripcion, double precio, double descuento, Sucursal sucursal, ProductoServicio productoservicio) {
        this.idPromocion = idPromocion;
        this.titulo = titulo;
        this.fechaInicioAsString = fechaInicioAsString;
        this.fechaTerminoAsString = fechaTerminoAsString;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.sucursal = sucursal;
        this.productoservicio = productoservicio;
    }

    public Promocion(String titulo, String fechaInicioAsString, String fechaTerminoAsString, String imagen, String descripcion, double precio, double descuento) {
        this.titulo = titulo;
        this.fechaInicioAsString = fechaInicioAsString;
        this.fechaTerminoAsString = fechaTerminoAsString;
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

    public DateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(DateTime fechaInicio) {
       this.fechaInicio=fechaInicio;
       fechaInicioAsString=fechaInicio!=null?DTF.print(fechaInicio):null;
    }

    public DateTime getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(DateTime fechaTermino) {
        this.fechaTermino=fechaTermino;
        fechaTerminoAsString=fechaTermino!=null?DTF.print(fechaTermino):null;
    }

    public String getFechaInicioAsString() {
        return  fechaInicioAsString;
    }

    public void setFechaInicioAsString(String fechaInicioAsString) {
        this.fechaInicioAsString = fechaInicioAsString;
    }

    public String getFechaTerminoAsString() {
        return fechaTerminoAsString;
    }

    public void setFechaTerminoAsString(String fechaTerminoAsString) {
        this.fechaTerminoAsString = fechaTerminoAsString;
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

    public ProductoServicio getProductoServicio() {
        return productoservicio;
    }

    public void setProductoServicio(ProductoServicio productoservicio) {
        this.productoservicio = productoservicio;
    }

    @Override
    public String toString() {
        return "Promocion{" + "idPromocion=" + idPromocion + ", titulo=" + titulo + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", fechaInicioAsString=" + fechaInicioAsString + ", fechaTerminoAsString=" + fechaTerminoAsString + ", imagen=" + imagen + ", descripcion=" + descripcion + ", precio=" + precio + ", descuento=" + descuento + ", estatus=" + estatus + ", sucursal=" + sucursal + ", productoservicio=" + productoservicio + '}';
    }
    
}
