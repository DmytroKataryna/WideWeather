package kataryna.dmytro.wideweather.rest.pojo.forecast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class Temp {

    @SerializedName("day")
    private double day;

    @SerializedName("night")
    private double night;

    public int getDay() {
        return (int) day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public int getNight() {
        return (int) night;
    }

    public void setNight(double night) {
        this.night = night;
    }
}
