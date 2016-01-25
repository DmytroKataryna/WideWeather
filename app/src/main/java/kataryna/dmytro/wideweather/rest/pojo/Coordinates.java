package kataryna.dmytro.wideweather.rest.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class Coordinates {

    @SerializedName("lon")
    private double lon;

    @SerializedName("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
