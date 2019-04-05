package lk.janiru.greenlocator.services.firebase;

/*
 *
 * Project Name : ${PROJECT}
 * Created by Janiru on 3/27/2019 1:45 PM.
 *
 */


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.core.app.NotificationCompat;
import lk.janiru.greenlocator.R;
import lk.janiru.greenlocator.main.ui.MainActivity;
import lk.janiru.greenlocator.services.signin.GoogleSignInActivity;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMessagingService";

    public FirebaseMessagingService() {
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        /**
         * Request JSON Mock :
         * {
         *  "to":
         *      "/topics/BUS_IS_NEAR_BY",
         *   "data" : {
         *      "location" : "Requred Informations"
         *   },
         *   "notification" : {
         *      title : "",
         *      "text" : "",
         "click_action" : "Some Activity"
         *   }
         *
         * }
         **/

        //Check the data contains playload
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "onMessageReceived: Called" + remoteMessage.getData());

            JSONObject jsonObject = new JSONObject(remoteMessage.getData());

            try {
                String location = jsonObject.getString("location");
                Log.d(TAG, "JSON Object " + location);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // Check if message contains a notification payload
        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String clickAction = remoteMessage.getNotification().getClickAction();

            Log.d(TAG, "Log the details title : " + title);
            Log.d(TAG, "Log the details body: " + body);
            Log.d(TAG, "Log the details clickAction : " + clickAction);


            sendNotifications(title, body, clickAction);
        }

    }

    public void sendNotifications(String title, String messageBody, String click_action) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0/*Request Code*/, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);



    }

}
