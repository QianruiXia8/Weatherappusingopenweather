package edu.northeastern.team44;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

public class AtYourService extends AppCompatActivity {
    Button search;
   EditText location1;
    EditText location2;
    TextView result;
    private final String url = "http://openweathermap.org/api/data/2.5/weather";
    private final String appid ="5c8a8164830aa07a205ee27ccafbfbc6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atyourservice);
        search = findViewById(R.id.b_1);
        location1 = findViewById(R.id.input_1);
        location2 = findViewById(R.id.input_2);
        result = findViewById(R.id.TV_4);
    }

    public void getWeatherinput(View view) {
        String url1 = "";
        String location1 = this.location1.getText().toString();
        String location2 = this.location2.getText().toString();
        if (location2.equals("")){
            result.setText("Please enter a valid location");
        }else{
            if(!location1.equals("")){
                url1 = url + "?q=" + location2 + "," + location1 + "&appid=" + appid;
            }else{
                url1 = url + "?q=" + location2 + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String main = jsonObject1.getString("main");
                    String description = jsonObject1.getString("description");
                    JSONObject jsonObject2 = jsonObject.getJSONObject("main");
                    String temp = jsonObject2.getString("temp");
                    String pressure = jsonObject2.getString("pressure");
                    String humidity = jsonObject2.getString("humidity");
                    JSONObject jsonObject3 = jsonObject.getJSONObject("wind");
                    String speed = jsonObject3.getString("speed");
                    String deg = jsonObject3.getString("deg");
                    String all = jsonObject3.getString("all");
                    String output = "Main: " + main + "\n" + "Description: " + description + "\n" + "Temperature: " + temp + "\n" + "Pressure: " + pressure + "\n" + "Humidity: " + humidity + "\n" + "Speed: " + speed + "\n" + "Deg: " + deg + "\n" + "All: " + all;
                    result.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}
