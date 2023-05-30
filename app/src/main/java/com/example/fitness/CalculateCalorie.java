package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.fitness.Profile;


import androidx.appcompat.app.AppCompatActivity;

public class CalculateCalorie extends AppCompatActivity {

    private EditText editTextAge, editTextWeight, editTextHeight;
    private RadioGroup radioGroupGender, radioGroupActivityLevel;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_calorie);

        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupActivityLevel = findViewById(R.id.radioGroupActivityLevel);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalorieIntake();
            }
        });
    }

    private void calculateCalorieIntake() {
        int age = Integer.parseInt(editTextAge.getText().toString());
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());

        int genderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(genderId);
        String gender = selectedGender.getText().toString();

        int activityLevelId = radioGroupActivityLevel.getCheckedRadioButtonId();
        RadioButton selectedActivityLevel = findViewById(activityLevelId);
        String activityLevel = selectedActivityLevel.getText().toString();

        double bmr;

        if (gender.equals("Erkek")) {
            bmr = (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age));
        } else {
            bmr = (447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age));
        }

        double calorieIntake;

        switch (activityLevel) {
            case "Hareketsiz":
                calorieIntake = bmr * 1.2;
                break;
            case "Az hareketli":
                calorieIntake = bmr * 1.375;
                break;
            case "Orta hareketli":
                calorieIntake = bmr * 1.55;
                break;
            case "Çok hareketli":
                calorieIntake = bmr * 1.725;
                break;
            case "Son derece hareketli":
                calorieIntake = bmr * 1.9;
                break;
            default:
                calorieIntake = 0;
        }
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalorieIntake();
                Intent intent = new Intent(CalculateCalorie.this, Profile.class);
                intent.putExtra("calorieIntake", calorieIntake);
                startActivity(intent);
            }
        });
    }
}
