package com.example.fitness;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MealItemView extends LinearLayout {

    private TextView tvMealName;
    private TextView tvCalories;

    public MealItemView(Context context) {
        super(context);
        init(context);
    }

    public MealItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MealItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_meal_item_view, this, true);

        tvMealName = findViewById(R.id.tvMealName);
        tvCalories = findViewById(R.id.tvCalories);
    }

    public void setMealName(String mealName) {
        tvMealName.setText(mealName);
    }

    public void setCalories(int calories) {
        tvCalories.setText(String.valueOf(calories));
    }

    public void setCaloriesText() {
        String caloriesText = tvCalories.getText().toString();
        if (!caloriesText.isEmpty()) {
            caloriesText += " kcal";
        }
        tvCalories.setText(caloriesText);
    }


    public int getCalories() {
        String caloriesText = tvCalories.getText().toString();
        if (!caloriesText.isEmpty()) {
            try {
                return Integer.parseInt(caloriesText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
