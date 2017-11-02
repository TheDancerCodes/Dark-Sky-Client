package com.thedancercodes.darkskyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.thedancercodes.darkskyclient.services.WeatherService;

import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial creation object of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/<YOUR-API_KEY>/1.2921,36.8219/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Implementation of WeatherService Interface
        WeatherService weatherService = retrofit.create(WeatherService.class);

        // Each Call from the created WeatherService can make a synchronous or asynchronous HTTP
        // request to the remote webserver.
        Call<Weather> weatherData = weatherService.getWeather();
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Currently currently = response.body().getCurrently();
                Log.d(TAG, "Temperature = " + currently.getTemperature());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Unable to fetch weather data.",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Unable to fetch weather data.");

            }
        });
    }
}
