package com.thedeveloperworldisyours.animationupdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DigitalTextView mDigitTextView, mDigitTextView2, mDigitTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDigitTextView= (DigitalTextView) findViewById(R.id.activity_main_digit_text_view);
        mDigitTextView2= (DigitalTextView) findViewById(R.id.activity_main_digit_text_view2);
        mDigitTextView3 = (DigitalTextView) findViewById(R.id.activity_main_digit_text_view3);
    }

    public void refresh(View view) {
        mDigitTextView.setValue(15);
        mDigitTextView2.setValue(2);
        mDigitTextView3.setValue(9);

    }
}
