package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealActivity extends AppCompatActivity {

    private EditText etMealName;
    private EditText etCalories;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        etMealName = findViewById(R.id.etMealName);
        etCalories = findViewById(R.id.etCalories);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });
    }

    private void saveMeal() {
        String mealName = etMealName.getText().toString();
        String caloriesText = etCalories.getText().toString();
        String caloriesString = "";

        if (!caloriesText.isEmpty()) {
            int calories = Integer.parseInt(caloriesText);
            caloriesString = calories + " kcal";
        }

        Intent intent = new Intent();
        intent.putExtra("mealName", mealName);
        intent.putExtra("calories", caloriesString);
        setResult(RESULT_OK, intent);
        finish();
    }
}

