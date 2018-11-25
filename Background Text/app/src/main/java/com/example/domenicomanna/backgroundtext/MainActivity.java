package com.example.domenicomanna.backgroundtext;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static String KEY_BACKGROUND_TEXT = "Background text";
    private static String VALUE_DEFAULT_STRING = "Monkey";

    private int REQUEST_TEXT_CHANGER_CODE = 2;

    private Button btnChangeBackgroundText;
    private TextView textViewbackgroundText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChangeBackgroundText = findViewById(R.id.btn_change_text_activity_main);
        textViewbackgroundText = findViewById(R.id.background_text);

        if (savedInstanceState!=null){
            Log.d(TAG, "onCreate: Restoring data");
            String backgroundText = savedInstanceState.getString(KEY_BACKGROUND_TEXT,VALUE_DEFAULT_STRING);
            setBackgroundText(backgroundText);
        }

        btnChangeBackgroundText.setOnClickListener((View v) -> switchToTextChangerActivity());
    }

    /**
     * Changes to the TextChanger activity
     */
    private void switchToTextChangerActivity() {
        Intent intent = TextChanger.changeToTextChangeActivity(this);
        startActivityForResult(intent, 2);
    }

    /**
     *
     * @param backgroundText the text to display in the background
     */
    private void setBackgroundText(String backgroundText) {
        textViewbackgroundText.setText(backgroundText);
    }

    /**
     *
     * Saves the current text in the background to a bundle
     */
    protected void onSaveInstanceState(Bundle bundle){
        Log.d(TAG, "onSaveInstanceState: Saving background text");
        super.onSaveInstanceState(bundle);
        String backGroundText = textViewbackgroundText.getText().toString();
        bundle.putString(KEY_BACKGROUND_TEXT, backGroundText);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode!=Activity.RESULT_OK){
            return;
        }
        Log.d(TAG, "onActivityResult: Getting data from child activity");
        if (requestCode == REQUEST_TEXT_CHANGER_CODE){
            if (data == null) return;
            String updatedText = TextChanger.getUpdatedText(data);
            setBackgroundText(updatedText);
        }
    }
}
