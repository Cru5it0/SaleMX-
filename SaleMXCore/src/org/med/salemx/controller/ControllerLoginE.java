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
import org.med.salemx.model.Empresa;

/**
 *
 * @author DELL
 */
public class ControllerLoginE {
    
    ControllerNormalizar cn = new ControllerNormalizar();
    
    public List search(String u, String c) throws Exception {
        
        String sql = "SELECT * FROM loginE "
                + "WHERE "
                + "correo = '"
                + cn.normalizar(u) + "' and contrasenia = '"
                + cn.normalizar(c) + "';";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Empresa> empresa = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Empresa s = new Empresa();
                    s.setIdEmpresa(rs.getInt(1));
                    s.setNomUsuario(rs.getString(2));
                    s.setContrasenia(rs.getString(3));
                    s.setToken();
                    if (rs.getString(4) == null) {
                        insertTokenE(s);
                        empresa.add(s);
                        
                    } else {
                        s.setIdEmpresa(0);
                        s.setNomUsuario("");
                        s.setContrasenia("");
                        s.setToken("");
                        empresa.add(s);
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
        return empresa;
    }
    
    public List searchToken(String t) throws Exception {
        String sql = "SELECT * FROM loginE "
                + "WHERE "
                + "token = '"
                + t + "' AND token != '';";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Empresa> usuario = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Empresa s = new Empresa();
                    s.setIdEmpresa(rs.getInt(1));
                    s.setNomUsuario(rs.getString(2));
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
    
    public void insertTokenE(Empresa e) throws Exception {
        String sql = "{call insertTokenE(?, ?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, e.getIdEmpresa());
            cstmt.setString(2, e.getToken());
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
    
        public void deleteToken(int id) throws Exception {
        String sql = "{call deleteTolkenE(?)}";

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
