package com.example.domenicomanna.changingbackgroundcolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String CURRENT_COLOR_KEY = "Current Color";
    private int DEFAULT_COLOR = R.color.colorBlue;

    private LinearLayoutCompat linearLayout;
    private Button changeColor;
    private int currentBackgroundColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentBackgroundColor = DEFAULT_COLOR;

        if (savedInstanceState != null){
            Log.d(TAG, "onCreate: Restoring previous state");
            currentBackgroundColor = savedInstanceState.getInt(CURRENT_COLOR_KEY, DEFAULT_COLOR);
        }

        linearLayout = findViewById(R.id.linear_layout_compat_main);
        changeBackGroundColor(currentBackgroundColor);
    }

    /**
     *
     * @param color the color to change the background too. Note, the color
     *              passed as a parameter must be defined within the colors.xml
     *              resource
     */
    private void changeBackGroundColor(int color){
        color = getResources().getColor(color);
        linearLayout.setBackgroundColor(color);
    }

    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        Log.d(TAG, "onSaveInstanceState: Saving data");
        bundle.putInt(CURRENT_COLOR_KEY, currentBackgroundColor);
    }
}
