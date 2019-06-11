package br.unicamp.alunoservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //List Views
    private ListView lvAlunos;

    //Buttons
    private Button btnAdd;
    private Button btnDelete;
    private Button btnEdit;
    private Button btnSearch;

    //Labels (TextView)
    private TextView lbSearchResult;

    //Other
    private ArrayList<Aluno> listaAluno;
    private int itemSelected = -1;

    private String urlConsulta      = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/consulta";
    private String urlConsultaRa    = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/consultaRa/";
    private String urlConsultaNome  = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/consultaNome/";
    private String urlIncluirAluno  = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/incluiAluno/";
    private String urlAlterarAluno  = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/alteraAluno/";
    private String urlExcluirAluno  = "http://10.0.2.2:8080/WSRestServidor/webresources/generic/deleteAluno/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbSearchResult = findViewById(R.id.lbSearchResult);

        //------------------------ Inicialização da listView -----------------------------//

        lvAlunos = (ListView)findViewById(R.id.lvAlunos);
        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnDelete.setEnabled(true);
                btnEdit.setEnabled(true);
                itemSelected = position;
            }
        });

        buscarDados(this.urlConsulta, null, "0");

        //------------------------ Inicialização dos botões -----------------------------//

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnSearch = (Button)findViewById(R.id.btnSearch);

        //Eses começam disabled pq n tem item selecionado ainda
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    Intent intent = new Intent(MainActivity.this, IncludeAlunoActivity.class);
                    startActivityForResult(intent, 1);
                }
                else
                    Toast.makeText(getApplicationContext(), "Sem conexao", Toast.LENGTH_SHORT).show();

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    Intent intent = new Intent(MainActivity.this, SearchAlunoActivity.class);
                    startActivityForResult(intent, 2);
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    int raAluno = listaAluno.get(itemSelected).getRa();
                    buscarDados(urlExcluirAluno + new Integer(raAluno).toString(), null, "0");
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditAlunoActivity.class);
                intent.putExtra("aluno", listaAluno.get(itemSelected));
                startActivityForResult(intent, 3);
            }
        });

    }


    public boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;

        }
        else
            return false;
    }

    private void buscarDados(String uri, String parametro, String codOperacao) {
        MinhaAsyncTask minhaAsyncTask = new MinhaAsyncTask();
        minhaAsyncTask.execute(uri, parametro, codOperacao);
    }

    public void atualizarView(ArrayList<Aluno> listaAlunos){
        itemSelected = -1;
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);

        this.listaAluno = listaAlunos;
        ListaAlunoAdapter listaAlunoAdapter = new ListaAlunoAdapter(this, listaAlunos);
        lvAlunos.setAdapter(listaAlunoAdapter);
    }

    public void atualizarLabel(String tipoConsulta){
        lbSearchResult.setText("Result for: " + tipoConsulta);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            // Codigo 1 quer dizer que voltou da Adicao
            case 1:
                try{
                    String alunoJson = AlunoJSONParser.alunoToJSON((Aluno)data.getSerializableExtra("aluno"));
                    buscarDados(this.urlIncluirAluno, alunoJson, "1");
                    atualizarLabel("ALL");
                }catch(Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), "Erro ao Incluir Aluno", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            //Codigo 2 quer dizer que voltou da consulta
            case 2:
                try{
                    String parametroConsulta;
                    int qualConsulta = data.getIntExtra("qualConsulta", 0);
                    switch (qualConsulta){
                        //Consulta todos
                        case 1:
                            buscarDados(this.urlConsulta, null, "0");
                            atualizarLabel("ALL");
                            break;
                        //Consulta RA
                        case 2:
                            parametroConsulta = data.getStringExtra("parametroConsulta");
                            buscarDados(this.urlConsultaRa + parametroConsulta, null, "0");
                            atualizarLabel("RA");
                            break;
                        //Consulta Nome
                        case 3:
                            parametroConsulta = data.getStringExtra("parametroConsulta");
                            buscarDados(this.urlConsultaNome + parametroConsulta, null, "0");
                            atualizarLabel("NAME");
                            break;
                        default:
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), "Erro ao Pesquisar", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;
            case 3:
                try{
                    String alunoJson = AlunoJSONParser.alunoToJSON((Aluno)data.getSerializableExtra("aluno"));
                    buscarDados(this.urlAlterarAluno, alunoJson, "2");
                    atualizarLabel("ALL");
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erro ao Alterar Aluno", Toast.LENGTH_SHORT).show();
                }
                break;
            default: Toast toast = Toast.makeText(getApplicationContext(), "Erro Ocorrido", Toast.LENGTH_SHORT);
                     toast.show();
        }
    }

    private  class MinhaAsyncTask extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... parametros) {
            String conteudo = null;

            //parametros[0] eh a url que sera usada
            //parametros[1] eh um conteudo json q sera utilizado
            //parametros[2] eh o codigo da operacao que deve ser feita

            //qual operacao deve ser feita?
            switch (parametros[2]){
                case "0": //pesquisar ou deletar
                    conteudo = HttpManager.getDados(parametros[0]);
                    break;

                case "1": //adicionar aluno
                    conteudo = HttpManager.addAluno(parametros[0], parametros[1]);
                    break;

                case "2": //alterar aluno
                    conteudo = HttpManager.editAluno(parametros[0], parametros[1]);
                    break;

                default: //n faz nada
            }

            return conteudo;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String conteudoJson) {
            super.onPostExecute(conteudoJson);

            try {
                listaAluno = AlunoJSONParser.jsonToListAluno(conteudoJson);
                atualizarView(listaAluno);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
