package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private LinearLayout llBreakfast;
    private LinearLayout llLunch;
    private LinearLayout llDinner;
    private TextView textViewResult;
    private Button btnAddBreakfast;
    private Button btnAddLunch;
    private Button btnAddDinner;
    private TextView tvTotalCalories;
    private int totalCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        llBreakfast = findViewById(R.id.llBreakfast);
        llLunch = findViewById(R.id.llLunch);
        llDinner = findViewById(R.id.llDinner);
        btnAddBreakfast = findViewById(R.id.btnAddBreakfast);
        btnAddLunch = findViewById(R.id.btnAddLunch);
        btnAddDinner = findViewById(R.id.btnAddDinner);
        tvTotalCalories = findViewById(R.id.tvTotalCalories);
        textViewResult = findViewById(R.id.textViewResult);

        // Intent'ten gelen veriyi al
        double calorieIntake = getIntent().getDoubleExtra("calorieIntake", 0.0);

        String resultText = String.format("Günlük kalori ihtiyacın: %.2f", calorieIntake);
        textViewResult.setText(resultText);
        calculateTotalCalories();
    }

    public void addBreakfastItem(View view) {
        Intent intent = new Intent(Profile.this, AddMealActivity.class);
        startActivityForResult(intent, 1);
    }

    public void addLunchItem(View view) {
        Intent intent = new Intent(Profile.this, AddMealActivity.class);
        startActivityForResult(intent, 2);
    }

    public void addDinnerItem(View view) {
        Intent intent = new Intent(Profile.this, AddMealActivity.class);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String mealName = data.getStringExtra("mealName");
            String caloriesString = data.getStringExtra("calories");
            int calories = 0;
            if (!caloriesString.isEmpty()) {
                calories = Integer.parseInt(caloriesString.split(" ")[0]);
            }

            switch (requestCode) {
                case 1: // Kahvaltı
                    // Yeni bir kahvaltı yemek öğesi oluşturulur ve llBreakfast'e eklenir
                    MealItemView breakfastItemView = new MealItemView(this);
                    breakfastItemView.setMealName(mealName);
                    breakfastItemView.setCalories(calories);
                    llBreakfast.addView(breakfastItemView);
                    break;
                case 2: // Öğle yemeği
                    // Yeni bir öğle yemeği yemek öğesi oluşturulur ve llLunch'a eklenir
                    MealItemView lunchItemView = new MealItemView(this);
                    lunchItemView.setMealName(mealName);
                    lunchItemView.setCalories(calories);
                    llLunch.addView(lunchItemView);
                    break;
                case 3: // Akşam yemeğiz
                    // Yeni bir akşam yemeği yemek öğesi oluşturulur ve llDinner'a eklenir
                    MealItemView dinnerItemView = new MealItemView(this);
                    dinnerItemView.setMealName(mealName);
                    dinnerItemView.setCalories(calories);
                    llDinner.addView(dinnerItemView);
                    break;
            }
        }

        calculateTotalCalories();
    }


    private void calculateTotalCalories() {
        // Toplam kalorileri sıfırla
        totalCalories = 0;

        // Kahvaltı yemekleri üzerinde dolaşarak kalorileri topla
        for (int i = 0; i < llBreakfast.getChildCount(); i++) {
            View childView = llBreakfast.getChildAt(i);
            if (childView instanceof MealItemView) {
                MealItemView mealItemView = (MealItemView) childView;
                totalCalories += mealItemView.getCalories();
            }
        }

        // Öğle yemeği yemekleri üzerinde dolaşarak kalorileri topla
        for (int i = 0; i < llLunch.getChildCount(); i++) {
            View childView = llLunch.getChildAt(i);
            if (childView instanceof MealItemView) {
                MealItemView mealItemView = (MealItemView) childView;
                totalCalories += mealItemView.getCalories();
            }
        }

        // Akşam yemeği yemekleri üzerinde dolaşarak kalorileri topla
        for (int i = 0; i < llDinner.getChildCount(); i++) {
            View childView = llDinner.getChildAt(i);
            if (childView instanceof MealItemView) {
                MealItemView mealItemView = (MealItemView) childView;
                totalCalories += mealItemView.getCalories();
            }
        }

        // Toplam kalorileri göster
        tvTotalCalories.setText(totalCalories + " kcal");
    }}
