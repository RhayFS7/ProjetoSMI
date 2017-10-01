package com.example.rhayf.projetosmi.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rhayf.projetosmi.Adaptador.ManutencaoAdaptador;
import com.example.rhayf.projetosmi.DAO.ConfiguracaoFireBase;
import com.example.rhayf.projetosmi.Entidades.Manutencao;
import com.example.rhayf.projetosmi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManutencaoActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<Manutencao> adapter;
    private ArrayList<Manutencao> manutencao;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerManutencao;
    private Button btnVoltarTelaInicial;
    private android.support.v7.app.AlertDialog alerta;
    private Manutencao manutencaoExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencao);

        manutencao = new ArrayList<>();

        listView = (ListView)findViewById(R.id.listViewManutencao);
        adapter = new ManutencaoAdaptador(this, manutencao);
        listView.setAdapter(adapter);

        firebase = ConfiguracaoFireBase.getFirebase().child("addmanutencao");

        valueEventListenerManutencao = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                manutencao.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Manutencao manutencaoNovo = dados.getValue(Manutencao.class);

                    manutencao.add(manutencaoNovo);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        btnVoltarTelaInicial = (Button) findViewById(R.id.btnVoltarTelaInicial2);
        btnVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTelaInicial();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                manutencaoExcluir = adapter.getItem(i);

                //cria o gerador do alertDialog
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ManutencaoActivity.this);

                //Define o título
                builder.setTitle("Confirma exclusão?");

                //define uma mensagem
                builder.setMessage("Você deseja excluir - " + manutencaoExcluir.getNomeProblema().toString() + " - ?");

                //define botão sim
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        firebase = ConfiguracaoFireBase.getFirebase().child("addmanutencao");

                        firebase.child(manutencaoExcluir.getNomeProblema().toString()).removeValue();

                        Toast.makeText(ManutencaoActivity.this, "Exclusão efetuada!", Toast.LENGTH_LONG).show();
                    }
                });

                //define o botão não

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ManutencaoActivity.this, "Exclusão Cancelada efetuada!", Toast.LENGTH_LONG).show();
                    }
                });

                //criar o alertdialog
                alerta = builder.create();

                //exibe alertdialog
                alerta.show();
            }
        });

    }

    private void voltarTelaInicial() {
        Intent intent = new Intent(ManutencaoActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerManutencao);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerManutencao);
    }
}
