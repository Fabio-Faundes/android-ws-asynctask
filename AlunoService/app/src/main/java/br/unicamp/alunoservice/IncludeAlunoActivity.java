package br.unicamp.alunoservice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IncludeAlunoActivity extends AppCompatActivity {

    private Button btnSalvarAluno;
    private EditText edRa;
    private EditText edNome;
    private EditText edEmailALuno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusao_aluno);

        edRa = (EditText)findViewById(R.id.edRA);
        edNome = (EditText)findViewById(R.id.edNome);
        edEmailALuno = (EditText)findViewById(R.id.edEmailAluno);

        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);
        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                String ra = edRa.getText().toString();
                String nome = edNome.getText().toString();
                String emailAluno = edEmailALuno.getText().toString();

                if (ra == null || nome == null || emailAluno == null || ra.trim().equals("") || nome.trim().equals("") || emailAluno.trim().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int intRa = 0;
                try{
                    intRa = Integer.parseInt(ra);
                }catch(Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), "O ra deve ser um numero", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Aluno aux = null;
                try{
                    aux = new Aluno(intRa, nome, emailAluno);
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("aluno", aux);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
