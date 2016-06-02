package com.viki.vikiapplication;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by Pauline on 02/06/2016.
 */
public class GCMRegistrationIntentService extends IntentService {

        public static final String REGISTRATION_SUCCESS = "RegistrationSuccesS";
        public static final String REGISTRATION_ECHEC = "RegistrationError";

    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        registerGCM();
    }

    private void registerGCM(){
        Intent registrationComplete = null;
        String token = null;

        try{
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.w("GCMRegIntentService","token:" + token);

            //notify to Ui that registrtion complete success
            registrationComplete = new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token", token);

        }catch (Exception e){
            Log.w("GCMRegIntentService","Regditration error");
            registrationComplete = new Intent(REGISTRATION_ECHEC);
        }
        //Send broadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
