package kataryna.dmytro.wideweather.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kataryna.dmytro.wideweather.R;
import kataryna.dmytro.wideweather.helpers.Constants;
import kataryna.dmytro.wideweather.helpers.Utils;
import kataryna.dmytro.wideweather.rest.pojo.forecast.Forecast;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context context;
    private List<Forecast> mData;

    public ForecastAdapter(List<Forecast> mData, Context context) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row, parent, false);
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecast forecast = mData.get(position);
        holder.dateTextView.setText(Utils.getForecastTime(forecast.getTime()));
        holder.weatherTextView.setText(String.format("%d℃ / %d℃", forecast.getTemperature().getDay(), forecast.getTemperature().getNight()));
        Picasso.with(context).load(Constants.ICON_BASE_URL + forecast.getIcon()).into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<Forecast> forecasts) {
        mData.clear();
        mData.addAll(forecasts);
        notifyDataSetChanged();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView, weatherTextView;
        public ImageView iconImageView;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            weatherTextView = (TextView) itemView.findViewById(R.id.weatherTextView);
            iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);

            dateTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf"));
            weatherTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf"));
        }
    }

}
