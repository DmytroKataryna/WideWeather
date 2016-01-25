package kataryna.dmytro.wideweather.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class Utils {

    public static String getCountryName(String isoCode) {
        return new Locale("", isoCode).getDisplayCountry(Locale.ENGLISH);
    }

    public static String getTime(long unixTime) {
        Date d = new Date(unixTime * 1000);
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy, HH:mm", Locale.ENGLISH);
        f.setTimeZone(TimeZone.getDefault());
        return f.format(d);
    }

    public static String getForecastTime(long unixTime) {
        Date d = new Date(unixTime * 1000);
        SimpleDateFormat f = new SimpleDateFormat("E dd", Locale.ENGLISH);
        f.setTimeZone(TimeZone.getDefault());
        return f.format(d);
    }
}
