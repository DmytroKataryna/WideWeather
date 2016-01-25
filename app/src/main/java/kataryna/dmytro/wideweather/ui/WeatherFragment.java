package kataryna.dmytro.wideweather.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kataryna.dmytro.wideweather.R;
import kataryna.dmytro.wideweather.adapter.ForecastAdapter;
import kataryna.dmytro.wideweather.helpers.Constants;
import kataryna.dmytro.wideweather.helpers.Utils;
import kataryna.dmytro.wideweather.otto.BusProvider;
import kataryna.dmytro.wideweather.otto.WeatherReceivedEvent;
import kataryna.dmytro.wideweather.rest.RestClient;
import kataryna.dmytro.wideweather.rest.pojo.forecast.Forecast;
import kataryna.dmytro.wideweather.rest.pojo.forecast.ForecastResponse;
import kataryna.dmytro.wideweather.rest.pojo.weather.WeatherResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class WeatherFragment extends Fragment {

    private ForecastAdapter mAdapter;
    private ImageView weatherIcon;
    private TextView cityName, countryName, temperature, temperatureIcon, umbrellaIcon, humidity, pressureIcon, pressure, time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        mAdapter = new ForecastAdapter(new ArrayList<Forecast>(), getActivity().getApplicationContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);

        weatherIcon = (ImageView) view.findViewById(R.id.iconImageView);
        cityName = (TextView) view.findViewById(R.id.cityTextView);
        countryName = (TextView) view.findViewById(R.id.countryTextView);
        temperature = (TextView) view.findViewById(R.id.temperatureTextView);
        temperatureIcon = (TextView) view.findViewById(R.id.celsiusTextView);
        umbrellaIcon = (TextView) view.findViewById(R.id.umbrellaTextView);
        humidity = (TextView) view.findViewById(R.id.humidityTextView);
        pressureIcon = (TextView) view.findViewById(R.id.pressureIconTextView);
        pressure = (TextView) view.findViewById(R.id.pressureTextView);
        time = (TextView) view.findViewById(R.id.timeTextView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void weatherReceived(WeatherReceivedEvent event) {
        getForecast(event.weather.getId());
        inflateWeatherViews(event.weather);
    }

    private void inflateWeatherViews(WeatherResponse weather) {
        Picasso.with(getContext()).load(Constants.ICON_BASE_URL + weather.getIcon()).into(weatherIcon);
        cityName.setText(weather.getName());
        countryName.setText(Utils.getCountryName(weather.getSys().getCountry()));
        temperature.setText(weather.getMain().getTemp());
        humidity.setText(String.format("%d%%", weather.getMain().getHumidity()));
        pressure.setText(String.format("%d%s", weather.getMain().getPressure(), Constants.hPa));
        time.setText(Utils.getTime(weather.getTime()));

        cityName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf"));
        countryName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf"));
        pressure.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf"));
        humidity.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf"));
        time.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf"));

        temperatureIcon.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fontello.ttf"));
        umbrellaIcon.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fontello.ttf"));
        pressureIcon.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fontello.ttf"));
    }

    private void getForecast(int cityID) {
        RestClient.getInstance().getForecast(cityID, new Callback<ForecastResponse>() {
            @Override
            public void success(ForecastResponse forecastResponse, Response response) {
                mAdapter.addData(forecastResponse.getForecast());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(WeatherFragment.class.getName(), error.getMessage());
            }
        });
    }
}