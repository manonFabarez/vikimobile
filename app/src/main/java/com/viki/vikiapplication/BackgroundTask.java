package com.viki.vikiapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

/**
 * Created by Pauline on 25/05/2016.
 */
public class BackgroundTask extends AsyncTask <String, Void, String>{

    //Variables
    Context ctx;
    AlertDialog alertDialog;

    BackgroundTask(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        //Récupération du nom de la méthode
        String method = params[0];
        //url de la page php à exécuter
        String url_connect = "ftp://virtual-kine.ddns.net/eds-www/mobile/login.php";

        //En fonction du nom de la méthode passée en paramètre -->Action
        if(method.equals("login")){

            //Récupération paramètres
            String login_ident = params[1];
            String login_pass = params[2];

            //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
            try {
                URL url = new URL(url_connect);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode(login_ident,"UTF-8")+"="+URLEncoder.encode(login_ident,"UTF-8")+"&"+URLEncoder.encode(login_pass,"UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";

                //Lecture du resultat retourné
                while ((line = bufferedReader.readLine())!= null){

                    response += line;
                }

                //Fermeture des objets
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //retour de la réponse
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Patienter ...");
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();

    }
}
