package com.viki.vikiapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
    final String EXTRAT_RETOUR = "retourSeance";
    final String EXTRAT_RESULTAT = "resultatSeance";
    static String idP, resultat;
    String values[] = new String[2];
    Context ctx;
    AlertDialog.Builder builder;
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
        String url_seance = "http://virtual-kine.ddns.net/mobile/seance.php";
        String url_noterseance = "http://virtual-kine.ddns.net/mobile/noterseance.php";
        String url_programme = "http://virtual-kine.ddns.net/mobile/programme.php";
        String url_resultatseance = "http://virtual-kine.ddns.net/mobile/resultat.php";

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

             case "seance":

                 //Récupération paramètres
                 String login = idP;

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_seance);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login, "UTF-8");
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
             case "noterseance":

                 //Récupération paramètres
                 String idP = params[1];
                 String dateSeance = params[2];
                 String  noteSeance = params[3];
                 String commentaireSeance = params[4];

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_noterseance);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("idP", "UTF-8") + "=" + URLEncoder.encode(idP, "UTF-8") + "&" +
                             URLEncoder.encode("dateSeance", "UTF-8") + "=" + URLEncoder.encode(dateSeance, "UTF-8") + "&" +
                             URLEncoder.encode("noteSeance", "UTF-8") + "=" + URLEncoder.encode(noteSeance, "UTF-8") + "&" +
                             URLEncoder.encode("commentaireSeance", "UTF-8") + "=" + URLEncoder.encode(commentaireSeance, "UTF-8");
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
             case "resultat":

                 //Récupération paramètres
                 String loginRes = params[1];

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_resultatseance);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("loginRes", "UTF-8") + "=" + URLEncoder.encode(loginRes, "UTF-8");
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
             case "programme":

                 //Récupération paramètres
                 String loginP = idP;

                 //Création de l'url d'accès a la page php + paramétrage + stockage dans le buffer
                 try {
                     URL url = new URL(url_programme);
                     HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                     httpURLConnection.setRequestMethod("POST");
                     httpURLConnection.setDoOutput(true);
                     httpURLConnection.setDoInput(true);
                     OutputStream outputStream = httpURLConnection.getOutputStream();
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                     String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(loginP, "UTF-8");
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
                 break;         }


            return null;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Oups ...");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Intent retour;
        values = result.split("-");
        switch (values[0]) {
            case "connexionOK": //Connexion application réussie
                idP = values[1];
                Intent i = new Intent(ctx, Menu.class);
                i.putExtra("id",idP);
                ctx.startActivity(i);
                break;
            case "connexionKO": //Connexion application echec
                builder.setMessage("Identifiant et/ou mot de passe incorrect. Veuillez recommencer.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertConnexionKO = builder.create();
                alertConnexionKO.show();
                break;
            case "mailOK": //nouveau mdp par mail envoyé
                builder.setTitle("Mail envoyé")
                        .setMessage("Un mail vient de vous être envoyé avec votre nouveau mot de passe. Penser à consulter les mails indésirables.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ctx.startActivity(new Intent(ctx,Connexion.class));
                            }
                        });
                AlertDialog alertmailOK = builder.create();
                alertmailOK.show();
                break;
            case "mailKO": // nouveau mot de passe echec
                builder.setMessage("Informations incorrectes... Veuillez recommencer.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertmailKO = builder.create();
                alertmailKO.show();
                break;
            case "newMdpOK": //première connexion avec un nouveau mot de passe
                idP = values[1];
                Intent j = new Intent(ctx, ChangerMDP.class);
                j.putExtra(EXTRAT_IDP,idP);
                ctx.startActivity(j);
                break;
            case "newMdpKO": // nouveau mot de passe echec
                builder.setMessage("Une erreur est survenue. Le mot de passe n'a pas été modifié.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ctx.startActivity(new Intent(ctx,Menu.class));
                            }
                        });
                AlertDialog alertnewMdpKO = builder.create();
                alertnewMdpKO.show();
                break;
            case "newMdpModifOK": //modification du mot de passe OK
                ctx.startActivity(new Intent(ctx,Menu.class));
                break;
            case "newMdpModifKO": //modification du mot de passe KO
                builder.setMessage("Une erreur est survenue. Le mot de passe n'a pas été modifié.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ctx.startActivity(new Intent(ctx,Menu.class));
                            }
                        });
                AlertDialog alertnewMdpModifKO = builder.create();
                alertnewMdpModifKO.show();
                break;
            case "retourSeance": //retour de la séance
                retour = new Intent(ctx, Seance.class);
                retour.putExtra(EXTRAT_IDP,idP);
                retour.putExtra(EXTRAT_RETOUR,result);
                ctx.startActivity(retour);
                break;
            case "noterseanceOK": //Notation de la séance OK
                Toast.makeText(ctx,
                        "Merci d'avoir noter cette séance !",
                        Toast.LENGTH_SHORT).show();
                resultat = "finishSEANCE";
                break;
            case "noterseanceKO": //Notation de la séance échec
                builder.setMessage("Une erreur est survenue. La notation n'a pas été enregistrée.")
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertnoterSeanceKO = builder.create();
                alertnoterSeanceKO.show();
                break;
            case "resultatSeance": //retour de la séance
                Intent resultat = new Intent(ctx,Resultat.class);
                resultat.putExtra(EXTRAT_RESULTAT,result);
                ctx.startActivity(resultat);
                break;
            case "retourProgramme": //retour de la séance
                retour = new Intent(ctx, Programme.class);
                retour.putExtra(EXTRAT_IDP,idP);
                retour.putExtra(EXTRAT_RETOUR,result);
                ctx.startActivity(retour);
                break;
            default :
                builder.setTitle("Erreur système")
                        .setMessage("Une erreur est survenue : "+result)
                        .setCancelable(false)// ne tient pas compte de BACK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertError = builder.create();
                alertError.show();
                break;
        }
    }
}
