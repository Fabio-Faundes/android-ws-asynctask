package br.unicamp.alunoservice;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListaAlunoAdapter extends ArrayAdapter<Aluno> {
    private Context context;
    private List<Aluno> alunos = null;

    public ListaAlunoAdapter (@NonNull Context context, @NonNull List<Aluno> listaDeAlunos){
        super(context, 0, listaDeAlunos);
        this.context = context;
        this.alunos = listaDeAlunos;
    }

    @Override
    public View getView (int position, View view, ViewGroup parent){
        Aluno aluno = alunos.get(position);

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.lista_items, null);
        }

        TextView textViewNome = (TextView)view.findViewById(R.id.txtNome);
        TextView textViewEmail = (TextView)view.findViewById(R.id.txtEmail);
        TextView textViewRA = (TextView)view.findViewById(R.id.txtRA);

        textViewNome.setText(aluno.getNome());
        textViewEmail.setText(aluno.getEmailAluno());
        textViewRA.setText(new Integer(aluno.getRa()).toString());

        return view;
    }
}
