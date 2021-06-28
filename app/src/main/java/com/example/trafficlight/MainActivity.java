package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout bulb1;
    private LinearLayout bulb2;
    private LinearLayout bulb3;
    private Button button;
    private Boolean start = false;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulb1 = findViewById(R.id.bulb_1);
        bulb2 = findViewById(R.id.bulb_2);
        bulb3 = findViewById(R.id.bulb_3);
        button = findViewById(R.id.button_1);
    }

    public void onClickStart(View view) {
        if (!start) {
            start = true;
            button.setText(R.string.ButtonTextStop);
            new Thread(() -> {

                while (start) {
                    counter++;
                    switch (counter) {
                        case 1:
                            bulb1.setBackgroundColor(getResources().getColor(R.color.green));
                            bulb2.setBackgroundColor(getResources().getColor(R.color.grey));
                            bulb3.setBackgroundColor(getResources().getColor(R.color.grey));
                            break;
                        case 2:
                            bulb1.setBackgroundColor(getResources().getColor(R.color.grey));
                            bulb2.setBackgroundColor(getResources().getColor(R.color.yellow));
                            bulb3.setBackgroundColor(getResources().getColor(R.color.grey));
                            break;
                        case 3:
                            bulb1.setBackgroundColor(getResources().getColor(R.color.grey));
                            bulb2.setBackgroundColor(getResources().getColor(R.color.grey));
                            bulb3.setBackgroundColor(getResources().getColor(R.color.red));
                            counter = 0;
                            break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            start = false;
            button.setText(R.string.ButtonTextStart);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start = false;
    }
}