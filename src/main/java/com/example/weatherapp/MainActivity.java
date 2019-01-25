package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView t_temp,t_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_temp = (TextView) findViewById(R.id.txt_temp);
        t_description = (TextView) findViewById(R.id.txt_description);

        find_weather();
    }

    public void find_weather()
    {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Hyderabad&mode=json&appid=212c66a25a472c08ed353270edf23703&units=metric";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_obj = response.getJSONObject("main");

                    JSONArray weather_arr = response.getJSONArray("weather");

                    JSONObject x = new JSONObject(weather_arr.getString(0));

                    String dec = x.getString("description");
                    String temp = String.valueOf(main_obj.getDouble("temp"));

                    t_temp.setText(temp);
                    t_description.setText(dec);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );


        RequestQueue queue =  Volley.newRequestQueue(MainActivity.this);
        queue.add(jor);


    }
}
