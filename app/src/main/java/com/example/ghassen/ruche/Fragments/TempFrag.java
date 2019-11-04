package com.example.ghassen.ruche.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ghassen.ruche.Entities.Connexion;
import com.example.ghassen.ruche.Entities.Valeur;
import com.example.ghassen.ruche.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;


public class TempFrag extends Fragment {

    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;
    String url;
    RequestQueue queue ;
    String url1;
    RequestQueue queue1 ;
    ArrayList xVals = new ArrayList();
    ArrayList yVals1 = new ArrayList<>();
    ArrayList yVals2 = new ArrayList();
    int groupCount =6;

    public TempFrag(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temp, container, false);











        queue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        url = Connexion.url+"temp/";
        StringRequest getData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getInt("success") == 1) {
                        JSONArray row = new JSONArray(obj.getString("result"));
                        for ( int j=0 ; j < row.length() ; j++) {
                            JSONObject use = row.getJSONObject(j);
                            String separator = "\\";
                            String[] str_arr=use.getString("content").replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
                            Valeur valeur = new Valeur(str_arr[0]);
                            yVals1.add(new BarEntry(j+1, (float) Integer.parseInt(valeur.toString())));
                            Log.e("test list valeurs", yVals1.get(j).toString());



                            BarDataSet set1;
                            set1 = new BarDataSet(yVals1, "A");
                            set1.setColor(Color.RED);
                            BarData data = new BarData(set1);
                            data.setValueFormatter(new LargeValueFormatter());
                            chart.setData(data);
                            chart.getBarData().setBarWidth(barWidth);
                            chart.getXAxis().setAxisMinimum(0);
                            chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
                            chart.getData().setHighlightEnabled(false);
                            chart.invalidate();
                        }
                    }
                } catch (JSONException e) {
                    Log.e("JSONExeption", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", error.getMessage());
            }
        });

        queue.add(getData);









        url1 = Connexion.url+"date/";
        StringRequest getData1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getInt("success") == 1) {
                        JSONArray row = new JSONArray(obj.getString("result"));
                        for ( int i=0 ; i < row.length() ; i++) {
                            JSONObject use = row.getJSONObject(i);

                            String date =use.getString("dateCreation");
                            String[] parts = date.split("T");

                            Valeur valeur = new Valeur(use.getInt("id"),parts[0]);
                            xVals.add(valeur.toString1());
                            XAxis xAxis = chart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            xAxis.setCenterAxisLabels(true);
                            xAxis.setDrawGridLines(false);
                            xAxis.setAxisMaximum(6);
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
                            Log.e("test list dates", xVals.get(i).toString());
                        }
                    }
                } catch (JSONException e) {
                    Log.e("JSONExeption", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", error.getMessage());
            }
        });

        queue.add(getData1);








        barWidth = 0.6f;
        barSpace = 0f;
        groupSpace = 0.1f;


        chart = (BarChart)view.findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);







        /*xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");*/



        //yVals1.add(new BarEntry(1, (float) 1));
        //yVals2.add(new BarEntry(1, (float) 2));
        //yVals1.add(new BarEntry(2, (float) 3));
        //yVals2.add(new BarEntry(2, (float) 4));
        //yVals1.add(new BarEntry(3, (float) 5));
        //yVals2.add(new BarEntry(3, (float) 6));
        //yVals1.add(new BarEntry(4, (float) 7));
        //yVals2.add(new BarEntry(4, (float) 8));
        //yVals1.add(new BarEntry(5, (float) 9));
        //yVals2.add(new BarEntry(5, (float) 10));
        //yVals1.add(new BarEntry(6, (float) 11));
        //yVals2.add(new BarEntry(6, (float) 12));



/*if(yVals1.size()>0)
{
    BarDataSet set1, set2;
    set1 = new BarDataSet(yVals1, "A");
    set1.setColor(Color.RED);
    set2 = new BarDataSet(yVals2, "B");
    set2.setColor(Color.BLUE);
    BarData data = new BarData(set1, set2);
    data.setValueFormatter(new LargeValueFormatter());
    chart.setData(data);
    chart.getBarData().setBarWidth(barWidth);
    chart.getXAxis().setAxisMinimum(0);
    chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
    chart.groupBars(0, groupSpace, barSpace);
    chart.getData().setHighlightEnabled(false);
    chart.invalidate();
}*/













        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);






        //X-axis
        /*XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));*/
        //Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
        Log.e("test list valeurs", String.valueOf(yVals2));





        return view;
    }


}
