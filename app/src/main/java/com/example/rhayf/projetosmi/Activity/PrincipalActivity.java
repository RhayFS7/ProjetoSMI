package com.example.rhayf.projetosmi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rhayf.projetosmi.DAO.ConfiguracaoFireBase;
import com.example.rhayf.projetosmi.R;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends Activity {

    private FirebaseAuth usuarioFirebase;
    private Button btnAddManutencao, btnVerManutencao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuarioFirebase = ConfiguracaoFireBase.getFirebaseAutenticacao();

        btnAddManutencao = (Button) findViewById(R.id.btnAddManutencao);
        btnVerManutencao = (Button) findViewById(R.id.btnVerManutencao);

        btnAddManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarManutencao();
            }
        });

        btnVerManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verManutencao();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.principal_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {
            deslogarUsuario();
        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarManutencao() {
        Intent intent = new Intent(PrincipalActivity.this, CadastroManutencaoActivity.class);
        startActivity(intent);
        finish();

    }

    private void verManutencao() {
        Intent intent = new Intent(PrincipalActivity.this, ManutencaoActivity.class);
        startActivity(intent);
        finish();

    }

    private void deslogarUsuario() {
        usuarioFirebase.signOut();
        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
