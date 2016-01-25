package kataryna.dmytro.wideweather.rest.pojo.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kataryna.dmytro.wideweather.rest.pojo.City;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class ForecastResponse {

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private List<Forecast> forecast;

    @SerializedName("cnt")
    private int cnt;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
