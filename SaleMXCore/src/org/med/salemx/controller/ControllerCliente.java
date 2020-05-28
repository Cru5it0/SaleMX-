package org.med.salemx.controller;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import org.med.salemx.model.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.med.salemx.datos.Conexion;
import org.med.salemx.model.Sucursal;
import sun.security.rsa.RSACore;

/**
 *
 * @author angel
 */
public class ControllerCliente {

    public static final int STATUS_ACTIVO = 1;
    public static final int STATUS_INACTIVO = 0;
    public static final String Rol_Admin = "Empresa";
    public static final String Rol_Cliente = "Cliente";

    public int insertCliente(Cliente c) throws SQLException {
        int result = 0;
        int id = 0;
        String query = "{call insertCliente(?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?,"
                + "?, ?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(query);
            cstmt.setString(1, c.getNombre());
            cstmt.setString(2, c.getApellidoPaterno());
            cstmt.setString(3, c.getApellidoMaterno());
            cstmt.setString(4, c.getGenero());
            cstmt.setString(5, c.getDomicilio());
            cstmt.setString(6, c.getTelefono());
            cstmt.setString(7, c.getNombreUsuario());
            cstmt.setString(8, c.getCorreo());
            cstmt.setString(9, c.getContrasenia());
            cstmt.setString(10, Rol_Cliente);
            cstmt.setInt(11, STATUS_ACTIVO);
            cstmt.registerOutParameter(12, Types.INTEGER);
            cstmt.executeUpdate();
            conn.commit();
            result = 1;
            cstmt.close();
            objConn.cerrar();
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
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

    public int updateCliente(Cliente c) throws SQLException {
        int result = 0;
        String query = "{call updateCliente(?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?,"
                + "?, ?)}";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1, c.getIdCliente());
            cstmt.setString(2, c.getNombre());
            cstmt.setString(3, c.getApellidoPaterno());
            cstmt.setString(4, c.getApellidoMaterno());
            cstmt.setString(5, c.getGenero());
            cstmt.setString(6, c.getDomicilio());
            cstmt.setString(7, c.getTelefono());
            cstmt.setString(8, c.getNombreUsuario());
            cstmt.setString(9, c.getCorreo());
            cstmt.setString(10, c.getContrasenia());
            cstmt.setString(11, Rol_Cliente);
            cstmt.setInt(12, STATUS_ACTIVO);
            cstmt.executeUpdate();
            conn.commit();
            result = 1;
            cstmt.close();
            objConn.cerrar();
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
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

    public int deleteCliente(int id) throws SQLException {
        int result = 0;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            String sql = null;
            Statement stl = null;
            sql = "update cliente set estatus=" + STATUS_INACTIVO + " where idCliente=" + id + ";";
            stl = conn.createStatement();
            stl.executeUpdate(sql);
            conn.commit();
            result = 1;
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
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

    public int activarCliente(int id) throws SQLException {
        int result = 0;
        Conexion objConn = new Conexion();
        Connection conn = null;
        try {
            conn = objConn.abrir();
            conn.setAutoCommit(false);
            String sql = null;
            Statement stl = null;
            sql = "update cliente set estatus=" + STATUS_ACTIVO + " where idCliente=" + id + ";";
            stl = conn.createStatement();
            stl.executeUpdate(sql);
            conn.commit();
            result = 1;
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
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

    public List selectAct() throws Exception {
        String sql = "select * from consulta_Cli_ac;";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken(rs.getString(13));

                    clientes.add(c);
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
        return clientes;
    }

    public List selectInac() throws Exception {
        String sql = "select * from consulta_Cli_in;";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken(rs.getString(13));

                    clientes.add(c);
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
        return clientes;
    }

    public List searchAct(String s) throws Exception {
        String sql = "select * from consulta_Cli_ac where nombre like '%" + s + "%' or "
                + "apellidoPaterno like '%" + s + "%' or "
                + "apellidoMaterno like '%" + s + "%' or "
                + "genero like '%" + s + "%' or "
                + "domicilio like '%" + s + "%' or "
                + "telefono like '%" + s + "%' or "
                + "nombreUsuario like '%" + s + "%' or "
                + "correo like '%" + s + "%' or "
                + "contrasenia like '%" + s + "%';";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken(rs.getString(13));

                    clientes.add(c);
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
        return clientes;
    }

    public List searchInac(String s) throws Exception {
        String sql = "select * from consulta_Cli_in where nombre like '%" + s + "%' or "
                + "apellidoPaterno like '%" + s + "%' or "
                + "apellidoMaterno like '%" + s + "%' or "
                + "genero like '%" + s + "%' or "
                + "domicilio like '%" + s + "%' or "
                + "telefono like '%" + s + "%' or "
                + "nombreUsuario like '%" + s + "%' or "
                + "correo like '%" + s + "%' or "
                + "contrasenia like '%" + s + "%';";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken(rs.getString(13));

                    clientes.add(c);
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
        return clientes;
    }

    public List validarToken(String t) throws Exception {
        String sql = "SELECT token FROM consulta_Cli_ac "
                + "WHERE "
                + "token = '"
                + t + "';";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken(rs.getString(13));

                    clientes.add(c);
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
        return clientes;
    }

    public List generarTokenAlmacenar(String u, String p) throws Exception {

        String sql = "select * from consulta_Cli_ac where "
                + "nombreUsuario like '%" + u + "%' and "
                + "contrasenia like '%" + p + "%';";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellidoPaterno(rs.getString(3));
                    c.setApellidoMaterno(rs.getString(4));
                    c.setGenero(rs.getString(5));
                    c.setDomicilio(rs.getString(6));
                    c.setTelefono(rs.getString(7));
                    c.setNombreUsuario(rs.getString(8));
                    c.setCorreo(rs.getString(9));
                    c.setContrasenia(rs.getString(10));
                    c.setRol(rs.getString(11));
                    c.setEstatus(rs.getInt(12));
                    c.setToken();

                    clientes.add(c);
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
        return clientes;
    }

    public void eliminarToken(int user) throws Exception {
        String sql = "update cliente set token=\"\" where idCliente=" + user + ";";
        CallableStatement cstmt = null;
        Conexion objConn = new Conexion();
        Connection conn = null;

        try {
            conn = objConn.abrir();
            cstmt = conn.prepareCall(sql);
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

    public List buscarToken(String u, String c) throws Exception {
        String sql = "select * from consulta_Cli_ac where"
                + "nombreUsuario like '%" + u + "%' and"
                + "contrasenia like '%" + c + "%';";
        Conexion objConn = new Conexion();
        java.sql.Connection conn = null;
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = objConn.abrir();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente cli = new Cliente();
                    cli.setIdCliente(rs.getInt(1));
                    cli.setNombre(rs.getString(2));
                    cli.setApellidoPaterno(rs.getString(3));
                    cli.setApellidoMaterno(rs.getString(4));
                    cli.setGenero(rs.getString(5));
                    cli.setDomicilio(rs.getString(6));
                    cli.setTelefono(rs.getString(7));
                    cli.setNombreUsuario(rs.getString(8));
                    cli.setCorreo(rs.getString(9));
                    cli.setContrasenia(rs.getString(10));
                    cli.setRol(rs.getString(11));
                    cli.setEstatus(rs.getInt(12));
                    cli.setToken(rs.getString(13));

                    clientes.add(cli);
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
        return clientes;
    }

    public static void main(String[] args) throws Exception {
        Gson json = new Gson();
        ControllerCliente c = new ControllerCliente();
        List<Cliente> sers = new ArrayList<>();
        sers = c.selectAct();
        System.out.println(json.toJson(c.selectAct()));
    }
}
