package org.med.salemx.datos;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class Prueba {

    public static void main(String[] args) throws Exception {
        Conexion objConn = new Conexion();
        Connection conn = objConn.abrir();

        if (conn != null) {
            JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "Error en la Conexión");
        }
        conn.close();
        objConn.cerrar();
    }
}
