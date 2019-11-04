package com.example.ghassen.ruche.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ghassen.ruche.R;

public class ChoixRuche extends AppCompatActivity {
    Button ruche1,ruche2;
    public static final int REQUEST_CODE = 10 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ruche);
        ruche1 = findViewById(R.id.R1);
        ruche2 = findViewById(R.id.R2);



        ruche2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ruche1=new Intent(ChoixRuche.this, MainActivity.class);
                ruche1.putExtra("ruche",1);
                startActivityForResult(ruche1,REQUEST_CODE);
            }
        });
        ruche1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Invalid password or login", Toast.LENGTH_LONG).show();
            }
        });
    }
}
