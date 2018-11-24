package com.example.domenicomanna.changingbackgroundcolor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String CURRENT_COLOR_KEY = "Current Color";
    private static final int REQUEST_CODE_CHANGE_COLOR = 10;
    private int DEFAULT_COLOR = R.color.colorGreen;

    private LinearLayoutCompat linearLayout;
    private Button btnChangeColor;
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
        btnChangeColor = findViewById(R.id.btn_main_change_color);
        btnChangeColor.setOnClickListener((View v)-> switchToChangeColorActivity());
        changeBackGroundColor(currentBackgroundColor);

    }

    /**
     * Switches to the change color activity
     */
    private void switchToChangeColorActivity() {
        Intent intent = ChangeColor.getIntentToSwitchActivity(this, currentBackgroundColor);
        startActivityForResult(intent, REQUEST_CODE_CHANGE_COLOR);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d(TAG, "onActivityResult: Called");
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CODE_CHANGE_COLOR){
            if (data == null) return;
            currentBackgroundColor = ChangeColor.getCurrentColor(data);
            changeBackGroundColor(currentBackgroundColor);
        }

    }
}
