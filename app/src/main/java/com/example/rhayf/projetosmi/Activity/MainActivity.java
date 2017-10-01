package com.example.rhayf.projetosmi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rhayf.projetosmi.R;

public class MainActivity extends Activity {

    private Button btnAbrirActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Ir da tela inicial para tela de Login
        btnAbrirActivityLogin = (Button) findViewById(R.id.btnFazerLogin);

        btnAbrirActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentAbrirTelaLogin);

            }
        });
    }
}
