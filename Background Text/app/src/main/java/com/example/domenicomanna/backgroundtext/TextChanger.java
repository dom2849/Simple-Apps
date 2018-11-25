package com.example.domenicomanna.backgroundtext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextChanger extends AppCompatActivity {

    private static String KEY_UPDATED_TEXT = "Updated text";
    private EditText editText;
    private Button btnReplaceText;
    private TextView textViewDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_changer);

        editText = findViewById(R.id.edit_text_updated_text);
        textViewDirections = findViewById(R.id.text_view_directions);
        btnReplaceText = findViewById(R.id.btn_replace_text_activity_text_changer);
        btnReplaceText.setOnClickListener((View v) -> updateText());
    }

    /**
     * Saves the user entered text in an intent and passes that to onResult
     */
    private void updateText() {
        Intent textInformation = new Intent();
        String updatedText = editText.getText().toString();
        textInformation.putExtra(KEY_UPDATED_TEXT, updatedText);
        setResult(Activity.RESULT_OK, textInformation);
        textViewDirections.setVisibility(View.VISIBLE);
    }

    /**
     *
     * @param context the context of the activity to switch from
     * @return an intent allowing the current activity to be changed
     * to this activity
     */
    public static Intent changeToTextChangeActivity(Context context){
        return new Intent(context, TextChanger.class);
    }

    /**
     *
     * @param textInformation the intent passed in onActivityResult
     * @return the text to display in the background
     */
    public static String getUpdatedText(Intent textInformation){
        return textInformation.getStringExtra(KEY_UPDATED_TEXT);
    }
}
