package com.example.ghassen.ruche.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ghassen.ruche.Entities.Connexion;
import com.example.ghassen.ruche.Entities.User;
import com.example.ghassen.ruche.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    ImageView panel_IMG_back;
    TextView signUp;
    String url;
    RequestQueue queue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        panel_IMG_back = (ImageView) findViewById(R.id.imageView);




        Glide
                .with(this)
                .load(R.drawable.bee);

        signUp = findViewById(R.id.signup_text);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Login.this, SignUp.class);
                startActivity(signUp);
            }
        });
        final EditText login= findViewById(R.id.email);
        final EditText psw= findViewById(R.id.password);
        Button Login= findViewById(R.id.login);
        queue = Volley.newRequestQueue(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = Connexion.url+"login/";
                url += login.getText() + "/" + psw.getText();
                StringRequest getData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("success") == 1) {
                                JSONArray row = new JSONArray(obj.getString("result"));
                                Intent accueil = new Intent(Login.this, ChoixRuche.class);
                                JSONObject use = row.getJSONObject(0);
                                User.id = use.getInt("id");
                                startActivity(accueil);
                                User.login = use.getString("login");
                                User.mdp = use.getString("password");
                                User.mail = use.getString("email");
                            } else
                                Toast.makeText(getApplicationContext(), "Invalid password or login", Toast.LENGTH_LONG).show();
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
        });



    }

}
