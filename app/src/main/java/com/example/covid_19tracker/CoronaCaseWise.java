package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoronaCaseWise extends AppCompatActivity {

    private EditText editText;
    private RecyclerView listView;
    private ProgressBar progressBar;
    public static ArrayList<CountryModel> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_case_wise);

        init();

        //getSupportActionBar().setTitle("Affected Countries");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        ////////////////////////////////////////////
        // data for search view
        fetchData();
        

    }

    private void fetchData() {

        String url="https://disease.sh/v3/covid-19/countries/";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String country=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todayCases= jsonObject.getString("todayCases");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String recovered=jsonObject.getString("recovered");
                        String totalDeaths=jsonObject.getString("deaths");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");

                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flag=object.getString("flag");

                        ///// store data
                        data.add(new CountryModel(flag,country,cases,todayCases,todayDeaths,recovered,totalDeaths,active,critical,true));

                    }
                    progressBar.setVisibility(View.GONE);
                    listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    listView.setHasFixedSize(true);
                    listView.setAdapter(new MyAdapter(data,getApplicationContext()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    private void init() {
        editText=findViewById(R.id.search_bar_edittext);
        listView=findViewById(R.id.list_item);
        progressBar=findViewById(R.id.progress_horizontal);
    }
}