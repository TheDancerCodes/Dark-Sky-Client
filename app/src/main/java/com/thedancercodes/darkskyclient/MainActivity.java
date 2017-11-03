package com.thedancercodes.darkskyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.thedancercodes.darkskyclient.services.WeatherService;
import com.thedancercodes.darkskyclient.services.WeatherServiceProvider;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tempTextView)
    TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCurrentWeather(1.2921, 36.8219);

        // Referencing textView
        //tempTextView = (TextView) findViewById(R.id.tempTextView);
        ButterKnife.bind(this);
    }

    private void requestCurrentWeather(double lat, double lng) {
        // Implement Weather Service Provider
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();

        Callback callback = new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Currently currently = response.body().getCurrently();
                Log.d(TAG, "Temperature = " + currently.getTemperature());

                // Access TextView to display temperature
                tempTextView.setText(String.valueOf(Math.round(currently.getTemperature())));

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
//                Toast.makeText(WeatherServiceProvider.this,
//                        "Unable to fetch weather data.",
//                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Unable to fetch weather data.");

            }
        };

        weatherServiceProvider.getWeather(lat, lng, callback);
    }
}
