package org.med.salemx.datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private Connection conn;
    private String userName;
    private String password;
    private String url;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/salemx";
            userName = "root";
            password = "root";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public Connection abrir() throws Exception {
        conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    public void cerrar() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
