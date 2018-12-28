package tk.alltrue.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCounter;
    private TextView mInfoTextView;

    public static final String APP_PREFERENCES="mysettings";
    public static final String APP_PREFERENCES_COUNTER="counter";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mInfoTextView = (TextView) findViewById(R.id.textViewInfo);
    }

    public void onClick(View v) {
        mInfoTextView.setText("I counted " + ++mCounter + " crows");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, mCounter);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
           mCounter = mSettings.getInt(APP_PREFERENCES_COUNTER,0);
           mInfoTextView.setText("I counted " + mCounter + " crows");
        }
    }
}
