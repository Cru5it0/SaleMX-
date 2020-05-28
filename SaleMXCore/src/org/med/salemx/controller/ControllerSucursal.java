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
 * @author DELL
 */
public class ControllerSucursal {

    public int insert(Sucursal s) throws Exception {
        int id;
        String sql = "{call insertSucursal(?,?,?,?,?,?,?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        System.out.println(s.toString());
        try {
            conn = objConn.abrir();

            cstmt = conn.prepareCall(sql);

            cstmt.setString(1, s.getNombre());
            cstmt.setString(2, s.getDomicilio());
            cstmt.setDouble(3, s.getLatitud());
            cstmt.setDouble(4, s.getLongitud());
            cstmt.setString(5, s.getFoto());
            cstmt.setInt(6, s.getIdEmpresa());
            //Datos de Salida:
            cstmt.registerOutParameter(7, Types.INTEGER);

            cstmt.executeUpdate();
            id = cstmt.getInt(7);
            s.setIdSucursal(id);

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

    public void update(Sucursal s) throws Exception {
        String sql = "{call updateSucursal(?,?,?,?,?,?,?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();

            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, s.getIdSucursal());
            cstmt.setString(2, s.getNombre());
            cstmt.setString(3, s.getDomicilio());
            cstmt.setDouble(4, s.getLatitud());
            cstmt.setDouble(5, s.getLongitud());
            cstmt.setString(6, s.getFoto());
            cstmt.setInt(7, s.getIdEmpresa());

            cstmt.executeUpdate();

            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {

                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
    }

    public void delete(int idS) throws Exception {
        String sql = "{call deleteSucursal(?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, idS);
            cstmt.executeUpdate();
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {
                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
    }

    public void activate(int idS) throws Exception {
        String sql = "{call activateSucursal(?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, idS);
            cstmt.executeUpdate();
            cstmt.close();
            objConn.cerrar();
        } catch (Exception ex) {
            if (cstmt != null) {
                cstmt.close();
            }

            objConn.cerrar();
            throw ex;
        }
    }

    public List search(String s) throws Exception {
        String sql = "SELECT * FROM consultaSucursalA "
                + "WHERE "
                + "nombre like '%"
                + s+"%' OR domicilio like '%"
                + s+"%' OR latitud like '%"
                + s+"%' OR longitud like '%" 
                + s+"%' ;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursales =  new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Sucursal su = new Sucursal();
                    su.setIdSucursal(rs.getInt(1));
                    su.setNombre(rs.getString(2));
                    su.setDomicilio(rs.getString(3));
                    su.setLatitud(rs.getDouble(4));
                    su.setLongitud(rs.getDouble(5));
                    su.setFoto(rs.getString(6));
                    su.setEstatus(rs.getInt(7));
                    System.out.println(""+su.toString());
                    sucursales.add(su);
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
        return sucursales;
    }
    
        public List searchN(String s) throws Exception {
        String sql = "SELECT * FROM consultaSucursalI "
                + "WHERE "
                + "nombre like '%"
                + s+"%' OR domicilio like '%"
                + s+"%' OR latitud like '%"
                + s+"%' OR longitud like '%" 
                + s+"%' ;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursales =  new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Sucursal su = new Sucursal();
                    su.setIdSucursal(rs.getInt(1));
                    su.setNombre(rs.getString(2));
                    su.setDomicilio(rs.getString(3));
                    su.setLatitud(rs.getDouble(4));
                    su.setLongitud(rs.getDouble(5));
                    su.setFoto(rs.getString(6));
                    su.setEstatus(rs.getInt(7));
                    System.out.println(""+su.toString());
                    sucursales.add(su);
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
        return sucursales;
    }

    public List selectAll(int idE) throws Exception {
        String sql = "SELECT * FROM consultaSucursalA where idEmpresa="+idE+";";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursal = new ArrayList<Sucursal>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Sucursal objS = new Sucursal(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7)
                    );
                    sucursal.add(objS);
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
        return sucursal;
    }

    public List selectAllNoDisponible() throws Exception {
        String sql = "SELECT * FROM consultaSucursalI order by idSucursal ;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursal = new ArrayList<Sucursal>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Sucursal objE = new Sucursal(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7)
                    );
                    sucursal.add(objE);
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
        return sucursal;
    }
    public List selectAllEmpresa() throws Exception {
        String sql = "SELECT idEmpresa, nombre FROM empresa order by idEmpresa;";
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
                            rs.getString(2)
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
    
    // -----------------Por promocion nada mas -----------------------
    public List selectAllForEmpresa(int id) throws Exception {
        String sql = "select * from promosucursal where idEmpresa="+id+";";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursal = new ArrayList<Sucursal>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Sucursal objS = new Sucursal(rs.getInt(1),rs.getString(2),rs.getInt(3));
                    sucursal.add(objS);
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
        return sucursal;
    }
        
    public List selectAllCliente() throws Exception {
        String sql = "SELECT * FROM consultaSucursalCliente;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Sucursal> sucursal = new ArrayList<Sucursal>();
        Empresa e = new Empresa();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Sucursal objS = new Sucursal(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7)
                    );
                    sucursal.add(objS);
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
        return sucursal;
    }

    public static void main(String[] args) throws Exception {
        Gson json = new Gson();
        ControllerSucursal c = new ControllerSucursal();
        List<Sucursal> sers = new ArrayList<>();
        sers = c.selectAllCliente();
        System.out.println(json.toJson(sers));
    }

}
