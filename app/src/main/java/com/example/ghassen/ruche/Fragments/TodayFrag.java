package com.example.ghassen.ruche.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.example.ghassen.ruche.Activities.TodayDistance;
import com.example.ghassen.ruche.Activities.TodayHum;
import com.example.ghassen.ruche.Activities.TodayLight;
import com.example.ghassen.ruche.Activities.TodayTemp;
import com.example.ghassen.ruche.R;


public class TodayFrag extends Fragment {
    Button temp,hum,dis,light,map;
    String url,val,longitude,latitude;
    RequestQueue queue ;
    public TodayFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_today, container, false);



        temp = (Button) view.findViewById(R.id.temp);
        hum = (Button) view.findViewById(R.id.hum);
        dis = (Button) view.findViewById(R.id.dis);
        light = (Button) view.findViewById(R.id.light);
        map= view.findViewById(R.id.mappp);


        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getActivity(), TodayTemp.class);
                startActivity(signUp);
                // Create a Uri from an intent string. Use the result to create an Intent.


            }
        });

        hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getActivity(), TodayHum.class);
                startActivity(signUp);
            }
        });

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getActivity(), TodayDistance.class);
                startActivity(signUp);
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getActivity(), TodayLight.class);
                startActivity(signUp);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=36.899557,10.188969(Location)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        return view;
    }




}
