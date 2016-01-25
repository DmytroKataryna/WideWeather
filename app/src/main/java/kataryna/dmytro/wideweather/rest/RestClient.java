package kataryna.dmytro.wideweather.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by dmytroKataryna on 24.01.16.
 */
public class RestClient {

    private static API REST_CLIENT;
    private static final String ROOT = "http://api.openweathermap.org/data/2.5";

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static API getInstance() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(API.class);
    }

}
