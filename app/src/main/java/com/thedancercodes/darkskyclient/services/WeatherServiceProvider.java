package com.thedancercodes.darkskyclient.services;

import android.util.Log;
import android.widget.Toast;

import com.thedancercodes.darkskyclient.MainActivity;

import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @thedancercodes on 02/11/2017.
 */

public class WeatherServiceProvider {
    private static final String BASE_URL = "https://api.darksky.net/forecast/<YOUR-API_KEY>/";
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private Retrofit retrofit;

    private Retrofit getRetrofit() {

        // Checking if builder exists
        if (this.retrofit == null) {

            // Initial creation object of Retrofit
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getWeather(double lat, double lng) {
        // Implementation of WeatherService Interface
        WeatherService weatherService = getRetrofit().create(WeatherService.class);

        // Each Call from the created WeatherService can make a synchronous or asynchronous HTTP
        // request to the remote webserver.
        Call<Weather> weatherData = weatherService.getWeather(lat, lng);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Currently currently = response.body().getCurrently();
                Log.d(TAG, "Temperature = " + currently.getTemperature());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
//                Toast.makeText(WeatherServiceProvider.this,
//                        "Unable to fetch weather data.",
//                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Unable to fetch weather data.");

            }
        });
    }
}
