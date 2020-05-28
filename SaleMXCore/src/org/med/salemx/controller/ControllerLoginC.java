/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.med.salemx.datos.Conexion;
import org.med.salemx.model.Cliente;

/**
 *
 * @author DELL
 */
public class ControllerLoginC {
    
    ControllerNormalizar cn = new ControllerNormalizar();
    //Insertar token
    public List search(String u, String c) throws Exception {
        
        String sql = "SELECT * FROM loginC "
                + "WHERE "
                + "correo = '"
                + cn.normalizar(u) + "' and contrasenia = '"
                + cn.normalizar(c) + "';";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Cliente> cliente = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente s = new Cliente();
                    s.setIdCliente(rs.getInt(1));
                    s.setNombreUsuario(rs.getString(2));
                    s.setContrasenia(rs.getString(3));
                    s.setToken();
                    if (rs.getString(4) == null) {
                        insertTokenC(s);
                        cliente.add(s);
                        
                    } else {
                        s.setIdCliente(0);
                        s.setNombreUsuario("");
                        s.setContrasenia("");
                        s.setToken("");
                        cliente.add(s);
                    }

                }
            }
            rs.close();
            stmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (stmt != null) {
                stmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
        return cliente;
    }
    
    public List searchToken(String t) throws Exception {
        String sql = "SELECT * FROM loginC "
                + "WHERE "
                + "token = '"
                + t + "' AND token != '';";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Cliente> usuario = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente s = new Cliente();
                    s.setIdCliente(rs.getInt(1));
                    s.setNombreUsuario(rs.getString(2));
                    s.setContrasenia(rs.getString(3));
                    s.setToken(rs.getString(4));
                    usuario.add(s);
                }
            }
            rs.close();
            stmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (stmt != null) {
                stmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
        return usuario;
    }
    
    public void insertTokenC(Cliente c) throws Exception {
        String sql = "{call insertTokenC(?, ?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, c.getIdCliente());
            cstmt.setString(2, c.getToken());
            cstmt.executeUpdate();

            conn.commit();
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {
                conn.rollback();
                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }        
    }
    
        public void deleteTokenC(int id) throws Exception {
        String sql = "{call deleteTolkenC(?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, id);
            cstmt.executeUpdate();

            conn.commit();
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {
                conn.rollback();
                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
    }        
        
}
