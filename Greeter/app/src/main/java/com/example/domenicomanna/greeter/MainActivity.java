package com.example.domenicomanna.greeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button greet;
    private Button clear;
    private EditText editText;
    private String NAME_KEY = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = findViewById(R.id.btn_clear);
        greet = findViewById(R.id.btn_greet);
        editText = findViewById(R.id.edit_text_name);
        configureClickListeners();

        if (savedInstanceState != null){
            String nameToDisplay = savedInstanceState.getString(NAME_KEY);
            setNameTo(nameToDisplay);
        }

    }

    /**
     * Sets the click listeners for the greet and clear
     * buttons
     */
    private void configureClickListeners() {
        clear.setOnClickListener((View v) ->
                setNameTo(""));
        greet.setOnClickListener((View v) -> {
            displayToast(getNameFromEditText());
        });
    }

    /**
     *
     * @param name the name to display in the edit text
     */
    private void setNameTo(String name){
        editText.setText(name);
    }

    /**
     *
     * @return the name written in the edit text
     */
    private String getNameFromEditText(){
        return editText.getText().toString();
    }

    /**
     *
     * @param nameFromEditText the name entered in the edit text
     */
    private void displayToast(String nameFromEditText) {
        String message = "";
        if (nameFromEditText.equals("")){
            message = "How can I greet you if I do not know your name!";
        }
        else {
            message = "Hello " + nameFromEditText + "!!!";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(NAME_KEY, getNameFromEditText());
    }
}
