/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author DELL
 */
public class Empresa {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private int idEmpresa;
    private String nombre;
    private String contacto;
    private String domicilio;
    private String logo;
    private String foto;
    private String nomUsuario;
    private String correo;
    private String contrasenia;
    private String rol = "Empresa";
    private int estatus;
    private String token;

    public Empresa(int idEmpresa, String nombre, String contacto, String domicilio, String logo, String foto, String nomUsuario, String correo, String contrasenia) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.logo = logo;
        this.foto = foto;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    
    
    public Empresa() {
    }

    public Empresa(int idEmpresa, String nombre, String contacto, String domicilio, String logo, String foto, String nomUsuario, String correo, String contrasenia, int estatus) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.logo = logo;
        this.foto = foto;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.estatus = estatus;
    }

    public Empresa(int idEmpresa, String nombre, String contacto, String domicilio, String logo, String nomUsuario, String correo, String contrasenia, int estatus, String token) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.logo = logo;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.estatus = estatus;
        this.token = token;
    }

    public Empresa(String nombre, String contacto, String domicilio, String logo, String foto, String nomUsuario, String correo, String contrasenia, int estatus, String token) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.logo = logo;
        this.foto = foto;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.estatus = estatus;
        this.token = token;
    }

    public Empresa(String nombre, String contacto, String domicilio, String logo, String foto, String nomUsuario, String correo, String contrasenia) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.logo = logo;
        this.foto = foto;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Empresa(int idEmpresa, String nombre, String contacto, String domicilio, String logo, String nomUsuario, String correo, String contrasenia) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.logo = logo;
        this.nomUsuario = nomUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Empresa(int idEmpresa, String nombre) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
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
        String u = this.getNomUsuario();
        String p = this.getContrasenia();
        String x = (DigestUtils.sha256Hex(u + ";" + p + sdf.format(timestamp)));
        this.token = x;
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", contacto=" + contacto + ", domicilio=" + domicilio + ", logo=" + logo + ", nomUsuario=" + nomUsuario + ", correo=" + correo + ", contrasenia=" + contrasenia + ", rol=" + rol + ", estatus=" + estatus + ", token=" + token + '}';
    }

}
