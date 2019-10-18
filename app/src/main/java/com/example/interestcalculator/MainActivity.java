package com.example.interestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    EditText investment, payment, rate;
    TextView years, time, compound_interest;
    Spinner spinner_freq;
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate = findViewById(R.id.btn_calculate);
        investment = findViewById(R.id.input_investment);
        rate = findViewById(R.id.input_rate);
        years = findViewById(R.id.text_time);
        payment = findViewById(R.id.input_payment);
        spinner_freq = findViewById(R.id.spinner_freq);
        compound_interest = findViewById(R.id.compound_interest);



        seekbar = findViewById(R.id.seek_bar);
        int progress = seekbar.getProgress();
        years.setText(progress + " yrs");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                years.setText(i + " yrs");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int compounding_frequency = 1;
                int invest = Integer.valueOf(investment.getText().toString());
                int pay = Integer.valueOf(payment.getText().toString());
                double interest = Double.valueOf(rate.getText().toString());
                int progress = seekbar.getProgress();
                String freq = spinner_freq.getSelectedItem().toString();
                int deposit_freq = 0;

                switch (freq){
                    case "monthly":
                        deposit_freq = 12;
                        break;

                    case "quarterly":
                        deposit_freq = 4;
                        break;

                    case "semi-annually":
                        deposit_freq = 2;
                        break;
                    case "annually":
                        deposit_freq = 1;
                        break;
                }
                double r = (interest / 100) * (1/compounding_frequency);
                int p = progress;
                double temp = Math.pow((1 + r), p);
                System.out.println("Values : " + invest + " " + pay + " " +  interest);
                System.out.println("Values : " + progress + " " + freq + " " +  deposit_freq);

                double result = ((invest * temp) + ((pay * deposit_freq) * ((temp - 1) / r)));
                System.out.println("Values : " + r + " " + temp + " " +  result);
                compound_interest.setText("Compound Interest: " + String.format("%.2f",result));

            }
        });





    }
}
