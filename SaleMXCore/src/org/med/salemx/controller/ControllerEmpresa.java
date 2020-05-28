/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.controller;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.med.salemx.datos.Conexion;
import org.med.salemx.model.Empresa;
import org.med.salemx.model.Sucursal;

/**
 *
 * @author Zerox
 */
public class ControllerEmpresa {

    public int insert(Empresa e) throws Exception {
        String sql = "{call insertEmpresa(?,?,?,?,?,?,?,?,?,?,?)}";
        int id;
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        System.out.println(e.toString());
        try {
            conn = objConn.abrir();
            //conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, e.getNombre());
            cstmt.setString(2, e.getDomicilio());
            cstmt.setString(3, e.getLogo());
            cstmt.setString(4, e.getFoto());
            cstmt.setString(5, e.getContacto());
            cstmt.setString(6, e.getNomUsuario());
            cstmt.setString(7, e.getCorreo());
            cstmt.setString(8, e.getContrasenia());
            cstmt.setString(9, e.getRol());
            cstmt.setInt(10, e.getEstatus());

            cstmt.registerOutParameter(11, Types.INTEGER);

            cstmt.executeUpdate();
            id = cstmt.getInt(11);
            e.setIdEmpresa(id);
            //conn.commit();
            //result = 1;
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {

                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }

        return id;
    }

    /*} catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch(SQLException st1) {
                st1.printStackTrace();
            }
        } finally {
            conn.setAutoCommit(true);
            objConn.cerrar();
        }
        return result;
    }*/
    public int update(Empresa e) throws Exception {
        String sql = "{call updateEmpresa(?,?,?,?,?,?,?,?,?)}";
        int result;
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, e.getIdEmpresa());
            cstmt.setString(2, e.getNombre());
            cstmt.setString(3, e.getDomicilio());
            cstmt.setString(4, e.getLogo());
            cstmt.setString(5, e.getFoto());
            cstmt.setString(6, e.getContacto());
            cstmt.setString(7, e.getNomUsuario());
            cstmt.setString(8, e.getCorreo());
            cstmt.setString(9, e.getContrasenia());
            cstmt.executeUpdate();
            conn.commit();
            result = 1;
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException st1) {
                st1.printStackTrace();
            }
        } finally {
            conn.setAutoCommit(true);
            objConn.cerrar();
        }
        return result;
    }

    public int delete(int idE) throws Exception {
        String sql = "{call deleteEmpresa(?)}";
        int result;
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, idE);
            cstmt.executeUpdate();
            conn.commit();
            result = 1;
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException st1) {
                st1.printStackTrace();
            }
        } finally {
            conn.setAutoCommit(true);
            objConn.cerrar();
        }
        return result;
    }

    public List selectAll() throws Exception {
        String sql = "SELECT * FROM consultaEmpresaA order by idEmpresa;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Empresa> empresa = new ArrayList<Empresa>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Empresa objE = new Empresa(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8)
                    );
                    empresa.add(objE);
                    i++;
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

    public static void main(String[] args) throws Exception {
        Gson json = new Gson();
        ControllerEmpresa c = new ControllerEmpresa();
        List<Empresa> sers = new ArrayList<>();
        sers = c.selectAll();
        System.out.println(json.toJson(c.selectAll()));
    }
}
