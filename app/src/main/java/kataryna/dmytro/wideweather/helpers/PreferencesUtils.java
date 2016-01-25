package kataryna.dmytro.wideweather.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class PreferencesUtils {

    private static PreferencesUtils sUtils;
    private static SharedPreferences sharedPref;

    // *****************  preferences data *****************
    private static final String KEY_SHARED_PREF = "ANDROID_WIDE_WEATHER";
    private static final int KEY_MODE_PRIVATE = Constants.ZERO;


    public static final String CURRENT_LOCATION = "locationName";


    //****************************  PREFERENCES ************************************

    public PreferencesUtils(Context context) {
        sharedPref = context.getSharedPreferences(KEY_SHARED_PREF, KEY_MODE_PRIVATE);
    }

    public static PreferencesUtils get(Context c) {
        if (sUtils == null) {
            sUtils = new PreferencesUtils(c.getApplicationContext());
        }
        return sUtils;
    }

    public void setLocationName(String locationName) {
        sharedPref.edit().putString(CURRENT_LOCATION, locationName).apply();
    }

    public String getLocationName() {
        return sharedPref.getString(CURRENT_LOCATION, Constants.EMPTY);
    }


}
