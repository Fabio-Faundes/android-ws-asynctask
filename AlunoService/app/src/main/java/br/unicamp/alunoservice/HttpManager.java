package br.unicamp.alunoservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

    public static String getDados(String uri){

        BufferedReader bufferedReader = null;

        try{

            URL url = new URL(uri);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String linha;
            while((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha);
            }

            return stringBuilder.toString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

    public static String addAluno (String uri, String aluno) {
        BufferedReader bufferedReader = null;

        try{

            URL url = new URL(uri);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(aluno.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String linha;
            while((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha);
            }

            return stringBuilder.toString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String editAluno (String uri, String aluno){
        BufferedReader bufferedReader = null;

        try{

            URL url = new URL(uri);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            os.write(aluno.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String linha;
            while((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha);
            }

            return stringBuilder.toString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String deleteAluno (String uri){
        BufferedReader bufferedReader = null;

        try{

            URL url = new URL(uri);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String linha;
            while((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha);
            }

            return stringBuilder.toString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}

