package br.unicamp.alunoservice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchAlunoActivity extends AppCompatActivity {

    private Button btnConsultarTodos;
    private Button btnSearchNome;
    private Button btnSearchRA;

    private EditText edSearchRA;
    private EditText edSearchNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_aluno);

        edSearchNome = findViewById(R.id.edSearchNome);
        edSearchRA = findViewById(R.id.edSearchRA);

        btnSearchNome = findViewById(R.id.btnSearchNome);
        btnConsultarTodos = findViewById(R.id.btnConsultarTodos);
        btnSearchRA = findViewById(R.id.btnSearchRA);

        btnSearchNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edSearchNome.getText().toString();
                if(nome == null || nome.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "O campo nome deve ser preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("parametroConsulta", nome);
                returnIntent.putExtra("qualConsulta", 3);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });

        btnSearchRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = edSearchRA.getText().toString();
                if(aux == null || aux.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "O campo RA deve ser preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }
                int ra = 0;

                try{
                    ra = Integer.parseInt(aux);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "O ra deve ser um numero", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("parametroConsulta", aux);
                returnIntent.putExtra("qualConsulta", 2);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        btnConsultarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = null;

                Intent returnIntent = new Intent();
                returnIntent.putExtra("qualConsulta", 1);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
    }
}
