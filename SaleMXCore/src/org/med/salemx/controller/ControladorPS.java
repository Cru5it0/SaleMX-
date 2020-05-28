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
import org.med.salemx.datos.Conexion;
import org.med.salemx.model.Empresa;
import org.med.salemx.model.ProductoServicio;
import org.med.salemx.model.Sucursal;

/**
 *
 * @author Carlos
 */
public class ControladorPS {

    int Status_activo = 1;
    int Status_inactivo = 0;

    public List<ProductoServicio> getAll(int idE) {
        String sql = "select * from getAllActivos where idEmpresa = " + idE + ";";
        List<ProductoServicio> productosservicios = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                ProductoServicio ps = new ProductoServicio();
                Empresa em = new Empresa();

                ps.setIdProductoServicio(rs.getInt("idProductoServicio"));
                ps.setNombre(rs.getString("nombre"));
                ps.setFoto(rs.getString("foto"));
                ps.setPrecio(rs.getDouble("precio"));
                ps.setDescripcion(rs.getString("descripcion"));
                ps.setEstatus(rs.getInt("estatus"));
                em.setIdEmpresa(rs.getInt("idEmpresa"));
                em.setNombre(rs.getString("Empresa"));
                ps.setEmpresa(em);

                productosservicios.add(ps);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productosservicios;
    }

    public List<ProductoServicio> getAll02(int id) {
        String sql = "select * from getAllActivos where idEmpresa=" + id + ";";
        List<ProductoServicio> productosservicios = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                ProductoServicio ps = new ProductoServicio();
                Empresa em = new Empresa();

                ps.setIdProductoServicio(rs.getInt("idProductoServicio"));
                ps.setNombre(rs.getString("nombre"));
                ps.setFoto(rs.getString("foto"));
                ps.setPrecio(rs.getDouble("precio"));
                ps.setDescripcion(rs.getString("descripcion"));
                ps.setEstatus(rs.getInt("estatus"));
                em.setIdEmpresa(rs.getInt("idEmpresa"));
                em.setNombre(rs.getString("Empresa"));
                ps.setEmpresa(em);

                productosservicios.add(ps);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productosservicios;
    }

    public List<ProductoServicio> getAllInactivos(int idE) {
        String sql = "select * from getAllIna where idEmpresa = "+idE+";";
        List<ProductoServicio> productosservicios = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                ProductoServicio ps = new ProductoServicio();
                Empresa em = new Empresa();

                ps.setIdProductoServicio(rs.getInt("idProductoServicio"));
                ps.setNombre(rs.getString("nombre"));
                ps.setFoto(rs.getString("foto"));
                ps.setPrecio(rs.getDouble("precio"));
                ps.setDescripcion(rs.getString("descripcion"));
                ps.setEstatus(rs.getInt("estatus"));
                em.setIdEmpresa(rs.getInt("idEmpresa"));
                em.setNombre(rs.getString("Empresa"));
                ps.setEmpresa(em);

                productosservicios.add(ps);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productosservicios;
    }

    public int insertProSer(ProductoServicio ps) throws Exception{
        
       int idPs=0;
        Conexion conexion =new Conexion();
        Connection con=null;
            String sql= "call insertProSer(?,?,?,?,?,?);";
            CallableStatement cstmt=null;
             try {
                con=conexion.abrir();
                con.setAutoCommit(false);
                cstmt=con.prepareCall(sql);
                cstmt.setString(1, ps.getNombre());
                cstmt.setString(2, ps.getFoto());
                cstmt.setDouble(3, ps.getPrecio());
                cstmt.setString(4,ps.getDescripcion() );
                cstmt.setInt(5,ps.getEmpresa().getIdEmpresa());
                cstmt.registerOutParameter(6, Types.INTEGER);
                cstmt.executeQuery();
                ps.setIdProductoServicio(cstmt.getInt(6));
                idPs=ps.getIdProductoServicio();
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }finally{
                 con.setAutoCommit(true);
                 conexion.cerrar();
             }
             return idPs;
    }
    
    public void updateProSer(ProductoServicio ps) throws SQLException{
        Conexion conexion =new Conexion();
       // Empresa em=new Empresa();
        Connection con=null;
            String sql= "{call updateProSer(?,?,?,?,?,?)}";
            CallableStatement cstmt=null;
              try {
                con=conexion.abrir();
                con.setAutoCommit(false);
                cstmt=con.prepareCall(sql);
                cstmt.setInt(1, ps.getIdProductoServicio());
                cstmt.setString(2, ps.getNombre());
                cstmt.setString(3, ps.getFoto());
                cstmt.setDouble(4, ps.getPrecio());
                cstmt.setString(5,ps.getDescripcion() );
                cstmt.setInt(6,ps.getEmpresa().getIdEmpresa());
                cstmt.executeQuery();
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }finally{
                 con.setAutoCommit(true);
                 conexion.cerrar();
             }
    }
    
    public void deleteProSer(int id) throws SQLException{
        String sql = "call deleteProSer(?);";
        
        CallableStatement cstmt = null;
        Conexion conexion = new Conexion();
        Connection conn = null;
        
        try
        {
            conn = conexion.abrir();
            conn.setAutoCommit(false);
            
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, id);
            
            cstmt.executeUpdate();
            conn.commit();
            cstmt.close();
            conn.close();
            conexion.cerrar();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            if(cstmt != null)
                conn.rollback();
            cstmt.close();
            
            conexion.cerrar();
        }
        conn.close();
    }

    public void activatProSer(int id) throws SQLException {
        String sql = "call activateProSer(?);";

        CallableStatement cstmt = null;
        Conexion conexion = new Conexion();
        Connection conn = null;

        try {
            conn = conexion.abrir();
            conn.setAutoCommit(false);

            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, id);

            cstmt.executeUpdate();
            conn.commit();
            cstmt.close();
            conn.close();
            conexion.cerrar();
        } catch (Exception e) {
            System.out.println(e.toString());
            if (cstmt != null) {
                conn.rollback();
            }
            cstmt.close();

            conexion.cerrar();
        }
        conn.close();
    }

    /////Agregado reciente para promocion-----------------------------
    public List<ProductoServicio> getAllForEmpresa(int id) {
        String sql = "select * from promo2 where idEmpresa=" + id + ";";
        List<ProductoServicio> productosservicios = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                ProductoServicio ps = new ProductoServicio();
                Empresa em = new Empresa();

                ps.setIdProductoServicio(rs.getInt("idProductoServicio"));
                ps.setNombre(rs.getString("nombre"));
                em.setIdEmpresa(rs.getInt("idEmpresa"));
                ps.setEmpresa(em);

                productosservicios.add(ps);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productosservicios;
    }
       
    public List<ProductoServicio> getAllCliente() {
        String sql = "select * from vclientePS;";
        List<ProductoServicio> productosservicios = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                ProductoServicio ps = new ProductoServicio();
                Empresa em = new Empresa();

                ps.setIdProductoServicio(rs.getInt("idProductoServicio"));
                ps.setNombre(rs.getString("nombre"));
                ps.setFoto(rs.getString("foto"));
                ps.setPrecio(rs.getDouble("precio"));
                ps.setDescripcion(rs.getString("descripcion"));
                ps.setEstatus(rs.getInt("estatus"));
                em.setIdEmpresa(rs.getInt("idEmpresa"));
                em.setNombre(rs.getString("Empresa"));
                ps.setEmpresa(em);

                productosservicios.add(ps);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return productosservicios;
    }


    /////Agregado reciente para promocion-----------------------------
    public static void main(String[] args) throws Exception {
        Gson json = new Gson();
        ControladorPS c = new ControladorPS();
        List<ProductoServicio> sers = new ArrayList<>();
        sers = c.getAllCliente();
        System.out.println(json.toJson(sers));
    }
}
