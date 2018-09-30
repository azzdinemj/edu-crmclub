package com.wuxue.view.utils;

import com.wuxue.model.StudentSignupDetails;
import com.wuxue.model.junhwa.Material;
import com.wuxue.model.junhwa.MealMaterial;

import java.util.List;

public class SetMealList {

    private List<MealMaterial> details;

    public SetMealList(List<MealMaterial> details) {
        this.details = details;
    }

    public SetMealList() {
    }

    public List<MealMaterial> getDetails() {
        return details;
    }

    public void setDetails(List<MealMaterial> details) {
        this.details = details;
    }
}
