package com.thedancercodes.darkskyclient.services;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by @thedancercodes on 02/11/2017.
 */

public interface WeatherService {
    @GET(".")
    Call<Weather> getWeather();
}
