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
import com.example.ghassen.ruche.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {
    ImageView panel_IMG_back;
    TextView loginn;
    String url;
    RequestQueue queue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        panel_IMG_back = (ImageView) findViewById(R.id.imageView);
        Glide
                .with(this)
                .load(R.drawable.bees);
        loginn = findViewById(R.id.login_text);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(SignUp.this, Login.class);
                startActivity(log);
            }
        });


        final EditText login = findViewById(R.id.password);
        //final EditText mail = findViewById(R.id.mail);
        final EditText email = findViewById(R.id.email);
        final EditText psw = findViewById(R.id.password2);


        Button SignUp = findViewById(R.id.login);

        queue = Volley.newRequestQueue(this);




        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = Connexion.url+"register/user/";
                //  + "/" + mail.getText()
                url += login.getText() + "/" + psw.getText()+ "/" + email.getText();
                StringRequest getData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("success") == 1) {
                                Intent signIn = new Intent(SignUp.this, Login.class);
                                startActivity(signIn);
                            } else
                                Toast.makeText(getApplicationContext(), "non", Toast.LENGTH_LONG).show();
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
