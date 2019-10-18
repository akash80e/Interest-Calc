package com.example.interestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    EditText investment, rate;
    TextView years;
    Spinner spinner_freq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate = findViewById(R.id.btn_calculate);
        investment = findViewById(R.id.input_investment);
        rate = findViewById(R.id.input_rate);
        years = findViewById(R.id.text_time);

    }
}
