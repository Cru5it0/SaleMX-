package org.med.salemx;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Login extends AppCompatActivity {

    EditText txtPassword;
    EditText txtEmail;
    Button btnLogin;
    Dialog dlgValidar;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        //Declaramos nuestros componentes
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        //Inicializamos un dialogo que bloqueara la activity
        // mientras se este consultando el precio del dolar
        Drawable d = new ColorDrawable(Color.BLACK);
        dlgValidar = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        d.setAlpha(130);
        dlgValidar.getWindow().setBackgroundDrawable(d);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(txtEmail.getText()).equalsIgnoreCase("") && String.valueOf(txtPassword.getText()).equalsIgnoreCase("")) {
                    Toast.makeText(getBaseContext(), "Llena todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    validarLogeo(v);

                }
            }
        });

    }

    public void validarLogeo(final View v) {
        //Instanciamos una nueva petición HTTP a través de Volley:
        RequestQueue rq = Volley.newRequestQueue(this);

        //La URL del servicio de divisas tomando como base el dólar:
        String url = "http://192.168.0.13:8084/Sale_MX/api/loginC/validarAndroid?u=" + String.valueOf(txtEmail.getText()) + "&c=" + String.valueOf(txtPassword.getText());

        //Generamos un nuevo objeto Response.Listener<String> para indicar que haremos cuando
        //tengamos una respuesta correcta:
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            //Aquí indicamos que haremos con la respuesta de la petición HTTP.
            @Override
            public void onResponse(String response) {
                //Generamos un objeto JSON Genérico:
                JsonParser jp = new JsonParser();
                JsonObject jso = (JsonObject) jp.parse(response);
                token = jso.get("token").getAsString();
                if (token.equalsIgnoreCase("null")) {
                    Toast.makeText(getBaseContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                } else {
                    enviarViewToken(v);
                }
                dlgValidar.hide();
            }
        };

        //Generamos un nuevo objeto Response.ErrorListener para indicar que haremos
        //cuando ocurra un error con nuestra petición:
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dlgValidar.hide();
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        //Generamos una nueva petición Volley:
        StringRequest sr = new StringRequest(Request.Method.GET,
                url,
                responseListener, errorListener);

        dlgValidar.show();
        //Agregamos la petición a la cola de peticiones de Volley
        //para que se ejecute:
        rq.add(sr);
    }

    public void enviarViewToken(View v) {
        Intent viewToken = new Intent(this, NavigationActivity.class);
        viewToken.putExtra("token", token);
        startActivity(viewToken);
    }

}

