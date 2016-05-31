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
    final String EXTRAT_IDP = "idP";
    static String idP;
    String values[] = new String[2];
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
        String url_newMdp = "http://virtual-kine.ddns.net/mobile/newMdp.php";

        //En fonction du nom de la méthode passée en paramètre -->Action
         switch (method) {
             case "login":

                 //Récupération paramètres
                 String login_ident = params[1];
                 String login_pass = params[2];

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_connect);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("login_ident", "UTF-8") + "=" + URLEncoder.encode(login_ident, "UTF-8") + "&" + URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                     bufferedWriter.write(data);
                     bufferedWriter.flush();
                     bufferedWriter.close();
                     outputStream.close();
                     InputStream inputStream = httpURLConnection.getInputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                     String response = "";
                     String line = "";

                     //Lecture du resultat retourné
                     while ((line = bufferedReader.readLine()) != null) {
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
                 break;
             case "mdpo":

                 //Récupération paramètres
                 String email = params[1];
                 String datenais = params[2];

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_mdpo);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("datenais", "UTF-8") + "=" + URLEncoder.encode(datenais, "UTF-8");
                     bufferedWriter.write(data);
                     bufferedWriter.flush();
                     bufferedWriter.close();
                     outputStream.close();
                     InputStream inputStream = httpURLConnection.getInputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                     String response = "";
                     String line = "";

                     //Lecture du resultat retourné
                     while ((line = bufferedReader.readLine()) != null) {
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

             case "newMdp":

                 //Récupération paramètres
                 String old_mdp = params[1];
                 String new_mdp = params[2];
                 String conf_new_mdp = params[3];

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_newMdp);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("old_mdp", "UTF-8") + "=" + URLEncoder.encode(old_mdp, "UTF-8") + "&" +
                                   URLEncoder.encode("new_mdp", "UTF-8") + "=" + URLEncoder.encode(new_mdp, "UTF-8") + "&" +
                                   URLEncoder.encode("conf_new_mdp", "UTF-8") + "=" + URLEncoder.encode(conf_new_mdp, "UTF-8") + "&" +
                             URLEncoder.encode("idP", "UTF-8") + "=" + URLEncoder.encode(idP, "UTF-8");
                     bufferedWriter.write(data);
                     bufferedWriter.flush();
                     bufferedWriter.close();
                     outputStream.close();
                     InputStream inputStream = httpURLConnection.getInputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                     String response = "";
                     String line = "";

                     //Lecture du resultat retourné
                     while ((line = bufferedReader.readLine()) != null) {
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
                 break;
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
        values = result.split("-");
        switch (values[0]) {
            case "connexionOK": //Connexion application réussie
                idP = values[1];
                Intent i = new Intent(ctx, Menu.class);
                i.putExtra("id",idP);
                ctx.startActivity(i);
                break;
            case "connexionKO": //Connexion application echec
                alertDialog.setMessage("Identifiant et/ou mot de passe incorrect. Veuillez recommencer");
                alertDialog.show();
                break;
            case "mailOK": //nouveau mdp par mail envoyé
                alertDialog.setTitle("Mail envoyé");
                alertDialog.setMessage("Un mail vient de vous être envoyé avec votre nouveau mot de passe. Penser à consulter les mails indésirables.");
                alertDialog.show();
                break;
            case "mailKO": // nouveau mot de passe echec
                alertDialog.setMessage("Informations incorrectes... Veuillez recommence");
                alertDialog.show();
                break;
            case "newMdpOK": //première connexion avec un nouveau mot de passe
                idP = values[1];
                Intent j = new Intent(ctx, ChangerMDP.class);
                j.putExtra(EXTRAT_IDP,idP);
                ctx.startActivity(j);
                break;
            case "newMdpKO": // nouveau mot de passe echec
                alertDialog.setMessage("Une erreur est survenue. Le mot de passe n'a pas été modifié");
                alertDialog.show();
                ctx.startActivity(new Intent(ctx,Menu.class));
                break;
            case "newMdpModifOK": //modification du mot de passe OK
                ctx.startActivity(new Intent(ctx,Menu.class));
                break;
            case "newMdpModifKO": //modification du mot de passe KO
                alertDialog.setMessage("Une erreur est survenue. Le mot de passe n'a pas été modifié");
                alertDialog.show();
                ctx.startActivity(new Intent(ctx,Menu.class));
                break;

            default :
                alertDialog.setMessage(result);
                alertDialog.show();
                break;
        }
    }
}
