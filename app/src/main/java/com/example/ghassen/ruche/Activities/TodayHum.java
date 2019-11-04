package com.example.ghassen.ruche.Activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ghassen.ruche.Activities.MainActivity;
import com.example.ghassen.ruche.Entities.Connexion;
import com.example.ghassen.ruche.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class TodayHum extends AppCompatActivity {

    TextView valeur,advice;
    String url,val;
    RequestQueue queue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_hum);
        valeur= (TextView) findViewById(R.id.value);
        advice = (TextView)findViewById(R.id.advice);
        queue = Volley.newRequestQueue(this);



        url = Connexion.url+"humNow/";
        StringRequest getData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getInt("success") == 1) {
                        JSONArray row = new JSONArray(obj.getString("result"));
                        JSONObject use = row.getJSONObject(0);
                        String separator = "\\";
                        String[] str_arr=use.getString("content").replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
                        val = str_arr[0];
                        valeur.setText(val+"%");
                        int result = Integer.parseInt(val);
                        if(result<60)
                        {
                            advice.setText("  Humidity is bad , consult your hive");
                            addNotification();
                        }
                        else if(result>65)
                        {
                            advice.setText(" Humidity is bad , consult your hive");
                        }
                        else if(result>=60 && result<=65)
                        {
                            advice.setText("Humidity is very good");
                        }
                    } else
                        Toast.makeText(getApplicationContext(), "Pas de valeurs", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Log.e("JSONExeption", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", "aaaaa"+error.getMessage());
            }
        });

        queue.add(getData);

    }

    private void addNotification() {

        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.humidee)
                .setContentTitle("Ruche")
                .setContentText("It's raining, consult your hive");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
