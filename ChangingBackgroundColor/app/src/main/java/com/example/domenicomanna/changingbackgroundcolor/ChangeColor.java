package com.example.domenicomanna.changingbackgroundcolor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ChangeColor extends AppCompatActivity {
    private static final String TAG = "ChangeColor";
    private static final String CURRENT_COLOR_KEY = "Current Color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
    }

    /**
     *
     * @param packageContext the context being switched from
     * @param currentBackgroundColor the current background color of the calling activity
     * @return an intent that can be used to switch activities
     */
    public static Intent getIntentToSwitchActivity(Context packageContext, int currentBackgroundColor){
        Log.d(TAG, "getIntentToSwitchActivity: Creating intent to switch activities");
        Intent intent = new Intent(packageContext, ChangeColor.class);
        intent.putExtra(CURRENT_COLOR_KEY, currentBackgroundColor);
        return intent;
    }
}
