package com.example.rhayf.projetosmi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhayf.projetosmi.DAO.ConfiguracaoFireBase;
import com.example.rhayf.projetosmi.Entidades.Manutencao;
import com.example.rhayf.projetosmi.R;
import com.google.firebase.database.DatabaseReference;

public class CadastroManutencaoActivity extends Activity {

    private Button btnAdicionarManutencao, btnVoltarTelaInicial;
    private EditText edtNomeManutencao, edtSalaManutencao;
    private Manutencao manutencao;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_manutencao);

        edtNomeManutencao = (EditText)findViewById(R.id.edtNomeManutencao);
        edtSalaManutencao = (EditText)findViewById(R.id.edtSalaManutencao);
        btnAdicionarManutencao = (Button) findViewById(R.id.btnAdicionarManutencao);
        btnVoltarTelaInicial = (Button) findViewById(R.id.btnVoltarTelaInicial);

        btnAdicionarManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manutencao = new Manutencao();
                manutencao.setNomeProblema(edtNomeManutencao.getText().toString());
                manutencao.setSala(Double.valueOf(edtSalaManutencao.getText().toString()));

                salvarManutencao(manutencao);
            }
        });


        btnVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTelaInicial();

            }
        });


    }

    private boolean salvarManutencao (Manutencao manutencao){
        try{
            firebase = ConfiguracaoFireBase.getFirebase().child("addmanutencao");
            firebase.child(manutencao.getNomeProblema()).setValue(manutencao);
            Toast.makeText(CadastroManutencaoActivity.this, "Produto inserido com sucesso.",Toast.LENGTH_LONG ).show();

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }

    }

    private void voltarTelaInicial(){
        Intent intent = new Intent(CadastroManutencaoActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

}
