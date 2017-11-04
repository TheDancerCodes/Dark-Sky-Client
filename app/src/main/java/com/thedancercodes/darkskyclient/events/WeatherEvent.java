package com.thedancercodes.darkskyclient.events;

import models.Weather;

/**
 * Created by @thedancercodes on 03/11/2017.
 */

public class WeatherEvent {

    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
