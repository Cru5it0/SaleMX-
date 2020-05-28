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
public class Sucursal {
    
    private int idSucursal;
    private String nombre;
    private String domicilio;
    private double latitud;
    private double longitud;
    private int estatus;
    private String foto;
    private int idEmpresa;

    public Sucursal() {
    }
    //por promocion

    public Sucursal(int idSucursal, String nombre, int idEmpresa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.idEmpresa = idEmpresa;
    }
    
    
    
    public Sucursal(String nombre, String domicilio, double latitud, double longitud, String foto, int idEmpresa) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.foto = foto;
        this.idEmpresa = idEmpresa;
    }

    public Sucursal(int idSucursal, String nombre, String domicilio, double latitud, double longitud, String foto, int idEmpresa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.foto = foto;
        this.idEmpresa = idEmpresa;
    }

    public Sucursal(int idSucursal, String nombre, String domicilio, double latitud, double longitud, int estatus, String foto, int idEmpresa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estatus = estatus;
        this.foto = foto;
        this.idEmpresa = idEmpresa;
    }

    public Sucursal(int idSucursal, String nombre, String domicilio, double latitud, double longitud, String foto) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.foto = foto;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "idSucursal=" + idSucursal + ", nombre=" + nombre + ", domicilio=" + domicilio + ", latitud=" + latitud + ", longitud=" + longitud + ", foto=" + foto + ", idEmpresa=" + idEmpresa + '}';
    }
    
}
