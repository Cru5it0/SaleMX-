package org.med.salemx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        txtToken = findViewById(R.id.txtToken);

        String dato = "";
        dato = getIntent().getStringExtra("token");
        txtToken.setText(dato);

    }

}
