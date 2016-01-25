package kataryna.dmytro.wideweather.rest.pojo.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class Main {

    @SerializedName("temp")
    private double temp;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private double humidity;

    public String getTemp() {
        return String.format("%.1f", temp);
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return (int) pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return (int) humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
