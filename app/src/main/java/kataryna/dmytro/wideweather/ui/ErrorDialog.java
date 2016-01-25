package kataryna.dmytro.wideweather.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import kataryna.dmytro.wideweather.R;

/**
 * Created by dmytroKataryna on 25.01.16.
 */
public class ErrorDialog extends AlertDialog implements View.OnClickListener {

    private Activity mActivity;

    public ErrorDialog(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    public AlertDialog createErrorDialog(String title) {
        View view = getLayoutInflater().inflate(R.layout.dialog_error_view, null);
        view.findViewById(R.id.errorDialogOkButton).setOnClickListener(this);

        TextView msg = (TextView) view.findViewById(R.id.msgTextView);
        msg.setTypeface(Typeface.createFromAsset(mActivity.getAssets(), "Roboto-Regular.ttf"));
        msg.setText(title);

        setCancelable(true);
        setView(view);
        return this;
    }

    @Override
    public void onClick(View v) {
        ErrorDialog.this.cancel();
    }
}