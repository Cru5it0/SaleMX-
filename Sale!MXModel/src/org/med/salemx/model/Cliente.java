package org.med.salemx.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.codec.digest.DigestUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author angel
 */
public class Cliente {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    int idCliente;
    /*Persona*/
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String genero;
    String domicilio;
    String telefono;
    String nombreUsuario;
    String correo;
    String contrasenia;
    String rol;
    int estatus;
    String token;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String genero, String domicilio, String telefono, String nombreUsuario, String correo, String contrasenia, String rol, int estatus, String token) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.estatus = estatus;
        this.token = token;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setToken() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String u = this.getNombreUsuario();
        String p = this.getContrasenia();
        String x = (DigestUtils.sha256Hex(u + ";" + p + sdf.format(timestamp)));
        this.token = x;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", genero=" + genero + ", domicilio=" + domicilio + ", telefono=" + telefono + ", nombreUsuario=" + nombreUsuario + ", correo=" + correo + ", contrasenia=" + contrasenia + ", rol=" + rol + ", estatus=" + estatus + ", token=" + token + '}';
    }
}
