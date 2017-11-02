package com.thedancercodes.darkskyclient.services;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by @thedancercodes on 02/11/2017.
 */

public interface WeatherService {
    @GET("{lat},{lng}")
    Call<Weather> getWeather(@Path("lat") double lat, @Path("lng") double lng);
}
