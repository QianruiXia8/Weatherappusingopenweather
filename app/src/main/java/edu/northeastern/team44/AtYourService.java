package edu.northeastern.team44;

import android.graphics.Color;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


public class AtYourService extends AppCompatActivity {
    ImageView imageView;
    ProgressBar progressBar;
    EditText location;
    TextView Result;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "5c8a_16480a0_a2_5_e27_cafb_bc_";
    DecimalFormat df = new DecimalFormat("#");
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atyourservice);
        location = findViewById(R.id.input_2);
        Result = findViewById(R.id.TV_4);
        b = findViewById(R.id.b_1);
        progressBar = findViewById(R.id.progressbar);
        imageView = findViewById(R.id.imageView);
        progressBar.setVisibility(View.GONE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Thread newThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String url1 = "";
                        String city = location.getText().toString();
                        if(city.equals("")){
                            Result.setText("Please enter a valid location");
                        }else{
                            url1 = url + "?q=" + city + "&appid=" + appid;
                        }
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                                    String description = jsonObjectWeather.getString("description");
                                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                                    int humidity = jsonObjectMain.getInt("humidity");
                                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                                    String wind = jsonObjectWind.getString("speed");
                                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                                    String clouds = jsonObjectClouds.getString("all");
                                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                                    String cityName = jsonResponse.getString("name");
                                    Result.setTextColor(Color.rgb(128,128,128));
                                    String output = "                        Current weather of " + cityName
                                            + "\n"
                                            + "\n Temp: " + df.format(temp) + " ??C"
                                            + "\n"
                                            + "\n Feels Like: " + df.format(feelsLike) + " ??C"
                                            + "\n"
                                            + "\n Humidity: " + humidity + "%"
                                            + "\n"
                                            + "\n Description: " + description
                                            + "\n"
                                            + "\n Wind Speed: " + wind + "m/s"
                                            + "\n"
                                            + "\n Cloudiness: " + clouds + "%";
                                    Result.setText(output);
                                    if (description.equals("clear sky")){
                                        String iconUrl = "http://openweathermap.org/img/w/01d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("few clouds")){
                                        String iconUrl = "http://openweathermap.org/img/w/02d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("scattered clouds")){
                                        String iconUrl = "http://openweathermap.org/img/w/03d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("broken clouds")){
                                        String iconUrl = "http://openweathermap.org/img/w/04d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("shower rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("thunderstorm")){
                                        String iconUrl = "http://openweathermap.org/img/w/11d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("mist")){
                                        String iconUrl = "http://openweathermap.org/img/w/50d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("overcast clouds")){
                                        String iconUrl = "http://openweathermap.org/img/w/04d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("moderate rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy intensity rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("very heavy rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("extreme rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/10d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("freezing rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light intensity shower rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy intensity shower rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("ragged shower rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("sleet")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("shower sleet")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light rain and snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("rain and snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light shower snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("shower snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy shower snow")){
                                        String iconUrl = "http://openweathermap.org/img/w/13d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light intensity drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy intensity drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("light intensity drizzle rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("drizzle rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy intensity drizzle rain")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("shower rain and drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("heavy shower rain and drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }
                                    if(description.equals("shower drizzle")){
                                        String iconUrl = "http://openweathermap.org/img/w/09d.png";
                                        Picasso.get().load(iconUrl).into(imageView);
                                    }

                                    progressBar.setVisibility(View.GONE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }} , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);}

                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newThread.start();
                    }
                });

            }});
    }

}
