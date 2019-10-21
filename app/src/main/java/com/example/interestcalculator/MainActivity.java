package com.example.interestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //class variables for getting input from the user.
    Button btnCalculate;
    EditText etInvestment, etPayment, etRate;
    TextView years, compound_interest;
    Spinner spinnerFreq;
    SeekBar seekbar;
    Boolean calculateActive = true;

    //class variables for calculating compound interest.
    double compounding_frequency = 1.0;
    double investment = 0;
    double payment = 0;
    double interest = 0;
    int deposit_freq = 0;
    int period = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        btnCalculate = findViewById(R.id.btn_calculate);
        etInvestment = findViewById(R.id.input_investment);
        etRate = findViewById(R.id.input_rate);
        years = findViewById(R.id.text_time);
        etPayment = findViewById(R.id.input_payment);
        spinnerFreq = findViewById(R.id.spinner_freq);
        compound_interest = findViewById(R.id.compound_interest);
        seekbar = findViewById(R.id.seek_bar);

        //getting the default value of the seekbar.
        int progress = seekbar.getProgress();
        years.setText(getString(R.string.period, progress));

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //updating the textView for every update in the seekbar progress
                years.setText(getString(R.string.period, i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the input form the user.
                getValue();

                //check if the input from the user is valid
                if (calculateActive){
                    /* Amount = Principal * (1+r) + (Deposit * Deposit Freq) [((1+r) -1) / r]
                     ▸ Where r = (interest rate/100)*(1/Compounding freq)
                     ▸ p = period * compounding freq
                     */
                    double r = (interest / 100) * (1/compounding_frequency);
                    double p = period * compounding_frequency;
                    double temp = Math.pow((1 + r), p);

                    double amount = ((investment * temp) + ((payment * deposit_freq) * ((temp - 1) / r)));

                    //Display the compound interest to the user using textView.
                    compound_interest.setText(getString(R.string.result,  String.format(Locale.getDefault(),"%.2f", amount)));
                }
            }
        });
    }

    //this method gets the value of all the input fields from the user
    //and stores in class variables.
    //it also check and validates if the input field is not filled.
    private void getValue(){

        calculateActive = true;

        //Get investment field value
        String input;
        input = etInvestment.getText().toString();
        if (input.isEmpty()){
            calculateActive = false;
            Toast.makeText(getApplicationContext(), "Please input Initial Investment", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            investment = Double.valueOf(input);
        }

        //get regular payment field value
        input = etPayment.getText().toString();
        if (input.isEmpty()){
            calculateActive = false;
            Toast.makeText(getApplicationContext(), "Please input Regular Payment", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            payment = Double.valueOf(input);
        }

        //get rate of interest field value
        input = etRate.getText().toString();
        if (input.isEmpty()){
            calculateActive = false;
            Toast.makeText(getApplicationContext(), "Please input Annual Interest Rate", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            interest = Double.valueOf(input);
        }

        //gets the time peroid from the seekbar.
        period = seekbar.getProgress();

        //gets the deposit frequency from the spinner
        String freq = spinnerFreq.getSelectedItem().toString();

        //converts the deposit frequency string to integer value.
        switch (freq){
            case "Monthly":
                deposit_freq = 12;
                break;

            case "Quarterly":
                deposit_freq = 4;
                break;

            case "Semi-annually":
                deposit_freq = 2;
                break;

            case "Yearly":
                deposit_freq = 1;
                break;
        }

    }
}
