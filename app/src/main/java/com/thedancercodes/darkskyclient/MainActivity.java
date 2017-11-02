package com.thedancercodes.darkskyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.thedancercodes.darkskyclient.services.WeatherService;
import com.thedancercodes.darkskyclient.services.WeatherServiceProvider;

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
        weatherServiceProvider.getWeather(lat, lng);
    }
}
