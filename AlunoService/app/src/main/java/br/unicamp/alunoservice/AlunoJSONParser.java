package br.unicamp.alunoservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlunoJSONParser {

    public static ArrayList<Aluno> jsonToListAluno (String alunosJson) throws Exception {

        if(alunosJson == null)
            throw new Exception("parametro Estava nulo");

        JSONArray jsonArrayAluno;
        try{
            jsonArrayAluno = new JSONArray(alunosJson);
        }
        catch (Exception e){
            JSONObject jsonObjAluno = new JSONObject(alunosJson);
            ArrayList<Aluno> aux = new ArrayList<>();
            aux.add(new Aluno(jsonObjAluno.getInt("ra"),
                              jsonObjAluno.getString("nome"),
                              jsonObjAluno.getString("emailAluno")));

            return  aux;
        }
        ArrayList<Aluno> listaAlunos = new ArrayList<>();

        Aluno aux = null;

        for(int i = 0; i < jsonArrayAluno.length(); i++){
            JSONObject jsonObject = jsonArrayAluno.getJSONObject(i);

            try {
                aux = new Aluno (
                jsonObject.getInt("ra"),
                jsonObject.getString("nome"),
                jsonObject.getString("emailAluno")
                );
            }catch(Exception e){
                break;
                //nao faz nada pq sabemos que n vai dar errado
            }

            listaAlunos.add(aux);

        }

        return listaAlunos;
    }

    public static String alunoToJSON (Aluno a) {
        if(a == null)
            return null;

        JSONObject aux = new JSONObject();
        try{

            aux.put("ra", a.getRa());
            aux.put("nome", a.getNome());
            aux.put("emailAluno", a.getEmailAluno());

        }catch(JSONException ex){
            ex.printStackTrace();
            return null;
        }

        return aux.toString();
    }

}
