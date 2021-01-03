package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////
    private PieChart piechart;
    private TextView cases;
    private TextView recovered;
    private TextView critical;
    private TextView active;
    private TextView total_cases;
    private TextView total_deaths;
    private TextView today_deaths;
    private TextView affected_country;
    private ProgressBar pogresbar;
    private Button button;

    ////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();//init all
        fetchData();

        ///////////////////////////////////////////////////
        ///click listner on button

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CoronaCaseWise.class));
            }
        });
    }

    private void fetchData() {

        String url="https://disease.sh/v3/covid-19/all/";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    ////////////////////////////////////////////////////////////////////
                    // set global data value
                    cases.setText(jsonObject.getString("cases"));
                    recovered.setText(jsonObject.getString("recovered"));
                    critical.setText(jsonObject.getString("critical"));
                    active.setText(jsonObject.getString("active"));
                    total_cases.setText(jsonObject.getString("updated"));
                    total_deaths.setText(jsonObject.getString("deaths"));
                    today_deaths.setText(jsonObject.getString("todayDeaths"));
                    affected_country.setText(jsonObject.getString("affectedCountries"));

                    pogresbar.setVisibility(View.GONE);

                    ////////////////////////////////////////////////////////////////////
                    // set pie chart value

                    piechart.addPieSlice(new PieModel("Cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#FFB300")));
                    piechart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#44FF00")));
                    piechart.addPieSlice(new PieModel("Deaths",Integer.parseInt(total_deaths.getText().toString()), Color.parseColor("#D81B60")));
                    piechart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#039BE5")));
                    piechart.startAnimation();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pogresbar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }

    public void init()
    {
        piechart=findViewById(R.id.piechart);
        cases=findViewById(R.id.cases_count);
        recovered=findViewById(R.id.cases_recorvered);
        critical=findViewById(R.id.cases_critical);
        active=findViewById(R.id.cases_active);
        total_cases=findViewById(R.id.cases_total_cases);
        total_deaths=findViewById(R.id.cases_total_deaths);
        today_deaths=findViewById(R.id.cases_today_deaths);
        affected_country=findViewById(R.id.cases_affectedcountries);
        pogresbar=findViewById(R.id.progress_circular);
        button=findViewById(R.id.track);
    }
}