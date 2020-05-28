/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.messaging;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
import java.util.Arrays;
import javax.ws.rs.core.Response;

/**
 *
 * @author LiveGrios
 */
public class SaleMXMessaging
{
    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = { MESSAGING_SCOPE };
    
    //Aqui ira la ruta de mi archivo json descargado y guardado
    private static String rutaCredencial = "E:\\NPROY7\\Projects\\UTL\\ProyectoIntegrador\\myspa_utl\\soap\\myspa_utl\\Java\\myspa_web\\service_account_valid.json";
    private static FileInputStream archivoCredencial; 
    private static GoogleCredentials credencial;
    private static FirebaseOptions options;
    private static FirebaseApp firebaseApp;
    private static FirebaseAuth firebaseAuth;
    static
    {
        inicializarComunicacionConFirebase();
    }
    
    private static Exception inicializarComunicacionConFirebase()
    {
        try
        {
            archivoCredencial = new FileInputStream(rutaCredencial);
            options = new FirebaseOptions.Builder()
                                         .setCredentials(GoogleCredentials.fromStream(archivoCredencial))
                                         .setDatabaseUrl("https://myspa-850a3.firebaseio.com/")
                                         .build();
            firebaseApp = FirebaseApp.initializeApp(options);
            firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
            System.out.println("Comunicación con Firebase inicializada en el servidor.");
            return null;
        } 
        catch (Exception e)
        {
            archivoCredencial = null;
            e.printStackTrace();
            return e;
        }
    }
    
    public String enviarMensaje(String tokenDispositivo, String texto) throws Exception
    {
        Exception errorFirebase = null;
        
        if (archivoCredencial == null)
        {
            errorFirebase = inicializarComunicacionConFirebase();
            if (errorFirebase != null)
                return "Sin comunicación desde este servidor con Firebase: " +
                       errorFirebase.toString();
        }
        
        Message msg = Message.builder().putData("confirmacion", texto)
                                       .setToken(tokenDispositivo)
                                       .build();
        
        String response = FirebaseMessaging.getInstance().send(msg);
        
        return response;
    }
}
