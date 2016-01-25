package kataryna.dmytro.wideweather.otto;

import kataryna.dmytro.wideweather.rest.pojo.weather.WeatherResponse;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class WeatherReceivedEvent {

    public final WeatherResponse weather;

    public WeatherReceivedEvent(WeatherResponse weather) {
        this.weather = weather;
    }
}
