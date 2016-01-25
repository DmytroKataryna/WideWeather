package kataryna.dmytro.wideweather.rest.pojo.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kataryna.dmytro.wideweather.helpers.Constants;
import kataryna.dmytro.wideweather.rest.pojo.Coordinates;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class WeatherResponse {

    @SerializedName("coord")
    private Coordinates coord;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("name")
    private String name;

    @SerializedName("dt")
    private long time;

    @SerializedName("main")
    private Main main;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
