package com.example.domenicomanna.changingbuttoncolor;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textView;
    private int blue;
    private int green;
    private int orange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blue = getResources().getColor(R.color.blue);
        green = getResources().getColor(R.color.green);
        orange = getResources().getColor(R.color.orange);

        textView = findViewById(R.id.text_view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentColor = currentColor();
                if (currentColor == blue){
                    changeColorTo(green);
                    makeToast("green");
                }
                else if (currentColor == green) {
                    changeColorTo(orange);
                    makeToast("orange");
                }
                else if (currentColor == orange) {
                    changeColorTo(blue);
                    makeToast("blue");
                }
            }
        });
    }

    private int currentColor(){
        ColorDrawable colorDrawable = (ColorDrawable) textView.getBackground();
        return colorDrawable.getColor();
    }

    private void changeColorTo(int resColorId){
        textView.setBackgroundColor(resColorId);
    }

    /**
     *
     * @param color the color that was changed to
     */
    private void makeToast(String color){
        Toast toast = Toast.makeText(this, "I am a " + color + " button!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        toast.show();

    }
}
