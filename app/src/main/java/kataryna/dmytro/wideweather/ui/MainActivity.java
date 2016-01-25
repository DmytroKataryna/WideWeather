package kataryna.dmytro.wideweather.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import kataryna.dmytro.wideweather.helpers.Constants;
import kataryna.dmytro.wideweather.helpers.PreferencesUtils;
import kataryna.dmytro.wideweather.R;
import kataryna.dmytro.wideweather.otto.BusProvider;
import kataryna.dmytro.wideweather.otto.WeatherReceivedEvent;
import kataryna.dmytro.wideweather.rest.RestClient;
import kataryna.dmytro.wideweather.rest.pojo.Coordinates;
import kataryna.dmytro.wideweather.rest.pojo.weather.WeatherResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, MaterialSearchView.OnQueryTextListener {

    private MaterialSearchView searchView;
    private TextView toolbarTitleVIew;
    private AlertDialog errorDialog;
    private GoogleMap mMap;
    private View weatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViews();
        initMap();
        restoreSession();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitleVIew = (TextView) findViewById(R.id.toolbarTitleView);
        toolbarTitleVIew.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initViews() {
        weatherFragment = findViewById(R.id.weatherFragment);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setOnQueryTextListener(this);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void restoreSession() {
        String storedLocationName = PreferencesUtils.get(getApplicationContext()).getLocationName();
        if (!storedLocationName.equals(Constants.EMPTY)) {
            changeWeatherFragmentVisibility(true);
            makeWeatherRequest(storedLocationName);
        } else
            changeWeatherFragmentVisibility(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    private void makeWeatherRequest(String cityQuery) {
        RestClient.getInstance().getWeather(cityQuery, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                if (weatherResponse.getName() == null) {
                    showAlertDialog("City not found");
                    return;
                }
                changeWeatherFragmentVisibility(true);
                setMarker(weatherResponse.getCoord());
                toolbarTitleVIew.setText(weatherResponse.getName());
                BusProvider.getInstance().post(new WeatherReceivedEvent(weatherResponse));
                PreferencesUtils.get(getApplicationContext()).setLocationName(weatherResponse.getName());
            }

            @Override
            public void failure(RetrofitError error) {
                showAlertDialog("City not found");
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void setMarker(Coordinates coord) {
        LatLng position = new LatLng(coord.getLat(), coord.getLon());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(position));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 6));
    }

    public void showAlertDialog(String title) {
        errorDialog = new ErrorDialog(this).createErrorDialog(title);
        errorDialog.show();
    }

    private void changeWeatherFragmentVisibility(boolean visibility) {
        changeViewVisibility(visibility, weatherFragment);
        changeErrorViewVisibility(!visibility);
    }

    private void changeErrorViewVisibility(boolean visibility) {
        findViewById(R.id.noDataText).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
        findViewById(R.id.noDataTextSecond).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void changeViewVisibility(boolean visibility, View view) {
        view.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        makeWeatherRequest(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
