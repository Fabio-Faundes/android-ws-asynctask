package br.unicamp.alunoservice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditAlunoActivity extends AppCompatActivity {

    private Aluno aluno;

    private EditText edRaEdit;
    private EditText edNomeEdit;
    private EditText edEmailEdit;

    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_aluno);

        edRaEdit = findViewById(R.id.edRaEdit);
        edEmailEdit = findViewById(R.id.edEmailEdit);
        edNomeEdit = findViewById(R.id.edNomeEdit);

        this.aluno = (Aluno)getIntent().getSerializableExtra("aluno");

        edRaEdit.setText(new Integer (aluno.getRa()).toString());
        edEmailEdit.setText(aluno.getEmailAluno());
        edNomeEdit.setText(aluno.getNome());

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                try{
                    aluno.setEmailAluno(edEmailEdit.getText().toString());
                    aluno.setNome(edNomeEdit.getText().toString());
                }catch(Exception e){

                    e.printStackTrace();

                }finally {
                    returnIntent.putExtra("aluno", aluno);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

    }
}
