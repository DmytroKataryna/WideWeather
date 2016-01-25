package kataryna.dmytro.wideweather.rest;

import kataryna.dmytro.wideweather.rest.pojo.forecast.ForecastResponse;
import kataryna.dmytro.wideweather.rest.pojo.weather.WeatherResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by dmytroKataryna on 24.01.16.
 */
public interface API {

    String API_KEY = "aff9a86b86de000d74db7c4ffc203425";

    @GET("/weather?units=metric&APPID=" + API_KEY)
    void getWeather(@Query("q") String cityName, Callback<WeatherResponse> callback);


    @GET("/forecast/daily?units=metric&cnt=7&APPID=" + API_KEY)
    void getForecast(@Query("id") Integer cityID, Callback<ForecastResponse> callback);

}
