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
import org.joda.time.DateTime;
import org.med.salemx.datos.Conexion;
import org.med.salemx.model.Empresa;
import org.med.salemx.model.ProductoServicio;
import org.med.salemx.model.Promocion;
import org.med.salemx.model.Promociones;
import org.med.salemx.model.Sucursal;

/**
 *
 * @author DELL
 */
public class ControllerPromocion {

    public int insert(Promocion p) throws Exception {
        int id;
        String sql = "CALL insertpromocion(?,?,?,?,?,?,?,?,?,?);";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        //System.out.println(p.toString());
        try {
            conn = objConn.abrir();

            cstmt = conn.prepareCall(sql);
            //11 datos
            cstmt.setString(1, p.getTitulo());
            
            cstmt.setString(2, p.getFechaInicioAsString()); //Fecha Inicio en String
            //System.out.println(p.getFechaInicioAsString());
            cstmt.setString(3, p.getFechaTerminoAsString()); //Fecha Fin en String
            cstmt.setString(4, p.getImagen());
            cstmt.setString(5, p.getDescripcion());
            

            cstmt.setDouble(6, p.getPrecio());
            cstmt.setDouble(7, p.getDescuento());

            
            cstmt.setInt(8, p.getProductoServicio().getIdProductoServicio());
            cstmt.setInt(9, p.getSucursal().getIdSucursal());

            //Datos de Salida:
            cstmt.registerOutParameter(10, Types.INTEGER);

            cstmt.executeUpdate();
            id = cstmt.getInt(10);
            p.setIdPromocion(id);

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

    public void update(Promocion p) throws Exception {
        String sql = "{call updatepromocion(?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();

            cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, p.getIdPromocion());
            cstmt.setString(2, p.getTitulo());
            cstmt.setString(3, p.getFechaInicioAsString());
            cstmt.setString(4, p.getFechaTerminoAsString());
            cstmt.setString(5, p.getDescripcion());
            cstmt.setString(6, p.getImagen());
            cstmt.setDouble(7, p.getPrecio());
            cstmt.setDouble(8, p.getDescuento());
            cstmt.setInt(9, p.getProductoServicio().getIdProductoServicio());
            cstmt.setInt(10, p.getSucursal().getIdSucursal());

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

    public void delete(int idP) throws Exception {
        String sql = "{call deletepromocion(?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, idP);
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
    
    public void activate(int idP) throws Exception {
        String sql = "{call activatepromocion(?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, idP);
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
    
    public List selectAll(int idE) throws Exception {
        String sql = "select * from consultaPromocionAc where idEmpresa="+idE+";;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Promociones> promocion = new ArrayList<Promociones>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Promociones p = new Promociones();
                    Sucursal s = new Sucursal();
                    Empresa e=new Empresa();
                    ProductoServicio ps = new ProductoServicio();
                    p.setIdPromocion(rs.getInt(1));
                    p.setTitulo(rs.getString(2));
                    p.setFechaInicio(rs.getString(3));
                    p.setFechaTermino(rs.getString(4));
                    p.setImagen(rs.getString(5));
                    p.setDescripcion(rs.getString(6));
                    p.setPrecio(rs.getDouble(7));
                    p.setDescuento(rs.getDouble(8));
                    
                    s.setIdSucursal(rs.getInt(9));
                    s.setNombre(rs.getString(10));
                    ps.setIdProductoServicio(rs.getInt(11));
                    ps.setNombre(rs.getString(12));
                    e.setIdEmpresa(rs.getInt(13));
                    ps.setEmpresa(e);
                    p.setSucursal(s);
                    p.setProductoservicio(ps);
                    
                    promocion.add(p);
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
        return promocion;
    }
    
    public List selectAll2(int idE) throws Exception {
        String sql = "select * from consultaPromocionIna where idEmpresa="+idE+";;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Promociones> promocion = new ArrayList<Promociones>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Promociones p = new Promociones();
                    Sucursal s = new Sucursal();
                    Empresa e=new Empresa();
                    ProductoServicio ps = new ProductoServicio();
                    p.setIdPromocion(rs.getInt(1));
                    p.setTitulo(rs.getString(2));
                    p.setFechaInicio(rs.getString(3));
                    p.setFechaTermino(rs.getString(4));
                    p.setImagen(rs.getString(5));
                    p.setDescripcion(rs.getString(6));
                    p.setPrecio(rs.getDouble(7));
                    p.setDescuento(rs.getDouble(8));
                    
                    s.setIdSucursal(rs.getInt(9));
                    s.setNombre(rs.getString(10));
                    ps.setIdProductoServicio(rs.getInt(11));
                    ps.setNombre(rs.getString(12));
                    e.setIdEmpresa(rs.getInt(13));
                    ps.setEmpresa(e);
                    p.setSucursal(s);
                    p.setProductoservicio(ps);
                    
                    promocion.add(p);
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
        return promocion;
    }
    
    public List selectAllCliente() throws Exception {
        String sql = "select * from vclienteP;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Promociones> promocion = new ArrayList<Promociones>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Promociones p = new Promociones();
                    Sucursal s = new Sucursal();
                    Empresa e=new Empresa();
                    ProductoServicio ps = new ProductoServicio();
                    p.setIdPromocion(rs.getInt(1));
                    p.setTitulo(rs.getString(2));
                    p.setFechaInicio(rs.getString(3));
                    p.setFechaTermino(rs.getString(4));
                    p.setImagen(rs.getString(5));
                    p.setDescripcion(rs.getString(6));
                    p.setPrecio(rs.getDouble(7));
                    p.setDescuento(rs.getDouble(8));
                    
                    s.setIdSucursal(rs.getInt(9));
                    s.setNombre(rs.getString(10));
                    ps.setIdProductoServicio(rs.getInt(11));
                    ps.setNombre(rs.getString(12));
                    e.setIdEmpresa(rs.getInt(13));
                    ps.setEmpresa(e);
                    p.setSucursal(s);
                    p.setProductoservicio(ps);
                    
                    promocion.add(p);
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
        return promocion;
    }

    public List selectAllArduino(int idS) throws Exception {
        String sql = "select * from consultaPromocionAc where idSucursal="+idS+";;";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Promociones> promocion = new ArrayList<Promociones>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Promociones p = new Promociones();
                    Sucursal s = new Sucursal();
                    Empresa e=new Empresa();
                    ProductoServicio ps = new ProductoServicio();
                    p.setIdPromocion(rs.getInt(1));
                    p.setTitulo(rs.getString(2));
                    p.setFechaInicio(rs.getString(3));
                    p.setFechaTermino(rs.getString(4));
                    p.setImagen(rs.getString(5));
                    p.setDescripcion(rs.getString(6));
                    p.setPrecio(rs.getDouble(7));
                    p.setDescuento(rs.getDouble(8));
                    
                    s.setIdSucursal(rs.getInt(9));
                    s.setNombre(rs.getString(10));
                    ps.setIdProductoServicio(rs.getInt(11));
                    ps.setNombre(rs.getString(12));
                    e.setIdEmpresa(rs.getInt(13));
                    ps.setEmpresa(e);
                    p.setSucursal(s);
                    p.setProductoservicio(ps);
                    
                    promocion.add(p);
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
        return promocion;
    }
    
    public List selectAllClienteA(int idP) throws Exception {
        String sql = "select * from vclienteP where idPromocion = "+ idP +";";
        Conexion objConn = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        List<Promociones> promocion = new ArrayList<Promociones>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                rs.last();
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    Promociones p = new Promociones();
                    Sucursal s = new Sucursal();
                    Empresa e=new Empresa();
                    ProductoServicio ps = new ProductoServicio();
                    p.setIdPromocion(rs.getInt(1));
                    p.setTitulo(rs.getString(2));
                    p.setFechaInicio(rs.getString(3));
                    p.setFechaTermino(rs.getString(4));
                    p.setImagen(rs.getString(5));
                    p.setDescripcion(rs.getString(6));
                    p.setPrecio(rs.getDouble(7));
                    p.setDescuento(rs.getDouble(8));
                    
                    s.setIdSucursal(rs.getInt(9));
                    s.setNombre(rs.getString(10));
                    ps.setIdProductoServicio(rs.getInt(11));
                    ps.setNombre(rs.getString(12));
                    e.setIdEmpresa(rs.getInt(13));
                    ps.setEmpresa(e);
                    p.setSucursal(s);
                    p.setProductoservicio(ps);
                    
                    promocion.add(p);
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
        return promocion;
    }
    
    public static void main(String[] args) throws Exception {
//        Gson json = new Gson();
//        ControllerPromocion c = new ControllerPromocion();
//        List<Promocion> promo = new ArrayList<>();
//        //promo = c.selectAll(4);
//        Promocion pro=new Promocion();
//        ProductoServicio ps=new ProductoServicio();
//        Sucursal s=new Sucursal();
//        pro.setTitulo("new bara");
//        DateTime fechaInicio=DateTime.now();
//        //pro.setFechaInicio(fechaInicio);
//        pro.setFechaInicioAsString("2020-03-05");
//        pro.setFechaTerminoAsString("2020-03-06");        
//        //pro.setFechaTermino(fechaInicio);
//       
//        pro.setImagen("nada");
//        pro.setDescripcion("prueba");
//        pro.setPrecio(2020.2);
//        pro.setDescuento(10);
//        ps.setIdProductoServicio(30);
//        s.setIdSucursal(10);
//        pro.setProductoServicio(ps);
//        pro.setSucursal(s);
//        int id=0;
//        try {
//                    //System.out.println(json.toJson(pro));
//                    promo=c.selectAll(2);
//                   // id=c.insert(pro);
//        } catch (Exception exx) {
//            System.out.println(exx.toString());
//        }
//
//        //System.out.println("idPromocione"+ id);
//        //System.out.println(pro.getFechaInicioAsString()+" 123");
//        
//        System.out.println(json.toJson(promo));
        Gson json = new Gson();
        ControllerPromocion c = new ControllerPromocion();
        List<Promocion> sers = new ArrayList<>();
        sers = c.selectAllClienteA(1);
        System.out.println(json.toJson(sers));
    }

}
