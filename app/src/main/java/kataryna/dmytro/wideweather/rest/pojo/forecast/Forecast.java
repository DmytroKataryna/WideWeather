package kataryna.dmytro.wideweather.rest.pojo.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kataryna.dmytro.wideweather.helpers.Constants;
import kataryna.dmytro.wideweather.rest.pojo.weather.Weather;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class Forecast {

    @SerializedName("dt")
    private long time;

    @SerializedName("temp")
    private Temp temperature;

    @SerializedName("weather")
    private List<Weather> weather;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Temp getTemperature() {
        return temperature;
    }

    public void setTemperature(Temp temperature) {
        this.temperature = temperature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getIcon() {
        if (getWeather() != null && !getWeather().isEmpty())
            return getWeather().get(Constants.ZERO).getIcon() + ".png";
        return Constants.EMPTY;
    }
}
