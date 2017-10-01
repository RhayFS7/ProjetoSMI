package com.example.rhayf.projetosmi.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rhayf.projetosmi.Entidades.Manutencao;
import com.example.rhayf.projetosmi.R;

import java.util.ArrayList;

/**
 * Created by rhayf on 26/09/2017.
 */

public class ManutencaoAdaptador extends ArrayAdapter<Manutencao> {

    private ArrayList<Manutencao> manutencao;
    private Context context;



    public ManutencaoAdaptador(Context c, ArrayList<Manutencao> objects) {

        super(c, 0, objects);
        this.context = c;
        this.manutencao = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (manutencao != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_manutencoes, parent, false);

            TextView txtViewManutencao = (TextView) view.findViewById(R.id.txtViewManutencao);
            TextView txtViewSala = (TextView) view.findViewById(R.id.txtViewSala);

            Manutencao manutencao2 = manutencao.get(position);

            txtViewManutencao.setText(manutencao2.getNomeProblema());
            txtViewSala.setText(manutencao2.getSala().toString());

        }
        return view;
    }
}
