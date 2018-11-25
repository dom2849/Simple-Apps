package com.example.domenicomanna.changingbackgroundcolor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeColor extends AppCompatActivity {
    private static final String TAG = "ChangeColor";

    private static final String CURRENT_COLOR_KEY = "Current color";
    //private static final String UPDATED_COLOR_KEY = "Updated color";
    private static int DEFAULT_COLOR = R.color.colorRed;
    private int currentColor;
    private RadioGroup radioGroup;
    private RadioButton radioBtnBlue, radioBtnGreen, radioBtnRed, newlySelectedRadioBtn;
    private Button btnSubmitChanges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: creating change color activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
        currentColor = getIntent().getIntExtra(CURRENT_COLOR_KEY, DEFAULT_COLOR);
        radioGroup = findViewById(R.id.radio_group);
        radioBtnBlue = findViewById(R.id.radio_btn_blue);
        radioBtnGreen = findViewById(R.id.radio_btn_green);
        radioBtnRed = findViewById(R.id.radio_btn_red);
        enableCurrentBackgroundColorRadioBtn();
        btnSubmitChanges = findViewById(R.id.btn_change_color_submit_changes);
        btnSubmitChanges.setOnClickListener((View v) -> changeValueOfCurrentColor());
    }

    /**
     * Changes the value of the current color to the selected radio button
     */
    private void changeValueOfCurrentColor() {
        int selectedRadioBtnId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioBtnId == -1){
            return;
        }
        Log.d(TAG, "changeValueOfCurrentColor: Changing current color id");

        Intent colorInformation = new Intent();
        currentColor = getIdOfUpdatedColor(selectedRadioBtnId);
        colorInformation.putExtra(CURRENT_COLOR_KEY, currentColor);
        setResult(Activity.RESULT_OK, colorInformation);
    }

    /**
     *
     * @param selectedRadioBtnId the id of the selected radio button
     * @return the updated color id
     */
    private int getIdOfUpdatedColor(int selectedRadioBtnId) {
        if (selectedRadioBtnId == R.id.radio_btn_blue){
            return R.color.colorBlue;
        }
        else if (selectedRadioBtnId == R.id.radio_btn_green){
            return R.color.colorGreen;
        }

        return R.color.colorRed;

    }

    /**
     * Selects the option of the current background color
     */
    private void enableCurrentBackgroundColorRadioBtn() {
        if (currentColor == R.color.colorBlue){
            checkRadioButton(radioBtnBlue);
        }
        else if (currentColor == R.color.colorGreen){
            checkRadioButton(radioBtnGreen);
        }
        else if (currentColor == R.color.colorRed){
            checkRadioButton(radioBtnRed);
        }
    }

    private void checkRadioButton(RadioButton radioButton){
        radioButton.setChecked(true);
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

    /**
     *
     * @param colorInformation represents the intent that will be passed to in onActivityResult
     *                         called from the parent activity
     * @return the integer value of the color to change the background to
     */
    public static int getUpdatedColor(Intent colorInformation){
        return colorInformation.getIntExtra(CURRENT_COLOR_KEY, DEFAULT_COLOR);
    }
}
