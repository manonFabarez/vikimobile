package com.viki.vikiapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import static android.support.v4.app.ActivityCompat.startActivity;

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
        String url_connect = "http://virtual-kine.ddns.net/mobile/login.php";
        String url_mdpo = "http://virtual-kine.ddns.net/mobile/mdpo.php";

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
                String data = URLEncoder.encode("login_ident","UTF-8")+"="+URLEncoder.encode(login_ident,"UTF-8")+"&"+URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
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
        else if(method.equals("mdpo")){

            //Récupération paramètres
            String email = params[1];
            String datenais = params[2];

            //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
            try {
                URL url = new URL(url_mdpo);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("datenais","UTF-8")+"="+URLEncoder.encode(datenais,"UTF-8");
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
        alertDialog.setTitle("Oups ...");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        switch (result) {
            case "OKconnexion":
                ctx.startActivity(new Intent(ctx, Menu.class));
                break;
            case "Identifiant et/ou mot de passe incorrect. Veuillez recommencer":
                alertDialog.setMessage(result);
                alertDialog.show();
                break;
            case "Un mail vient de vous être envoyé avec votre nouveau mot de passe. N'hésitez pas à consulter votre dossier Courrier Indésirables.":
                alertDialog.setTitle("Mail envoyé");
                alertDialog.setMessage(result);
                alertDialog.show();
                break;
            case "Informations incorrectes... Veuillez recommencer":

                break;
        }

        alertDialog.setMessage(result);
        alertDialog.show();
    }
}
