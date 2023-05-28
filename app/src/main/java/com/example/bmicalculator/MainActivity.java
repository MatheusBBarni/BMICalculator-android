package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private TextView resultText;
    private Button calculateButton;
    private DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViews();
        this.setupButtonClickListener();
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double bmi = calculateBmi();

                String ageText = ageEditText.getText().toString();
                Integer age = Integer.parseInt(ageText);

                if (age >= 18) {
                    showResult(bmi);
                } else {
                    showGuidance(bmi);
                }

            }
        });
    }

    private Double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        Integer feet = Integer.parseInt(feetText);
        Integer inches = Integer.parseInt(inchesText);
        Integer weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        Double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void showResult(Double bmi) {
        String text = this.decimalFormatter.format(bmi);

        if (bmi < 18.5) {
            text += " - Underweight";
        } else if (bmi > 25) {
            text += " - Overweight";
        } else {
            text += " - Healthy";
        }

        resultText.setText(text);
    }

    private void showGuidance(Double bmi) {
        String text = this.decimalFormatter.format(bmi);

        if (maleButton.isChecked()) {
            text += " - please consult your boy doctor";
        } else if (femaleButton.isChecked()) {
            text += " - please consult your female doctor";
        } else {
            text += " - please consult your doctor";
        }

        resultText.setText(text);
    }

    private void findViews() {
        this.maleButton = findViewById(R.id.radio_button_male);
        this.femaleButton = findViewById(R.id.radio_button_female);
        this.ageEditText = findViewById(R.id.edit_text_age);
        this.feetEditText = findViewById(R.id.edit_text_feet);
        this.inchesEditText = findViewById(R.id.edit_text_inches);
        this.weightEditText = findViewById(R.id.edit_text_weight);
        this.resultText = findViewById(R.id.text_view_result);
        this.calculateButton = findViewById(R.id.button_calculate);
    }
}