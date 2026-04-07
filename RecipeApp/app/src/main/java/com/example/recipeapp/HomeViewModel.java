package com.example.recipeapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> ingredients = new MutableLiveData<>();
    private final MutableLiveData<Integer> imageResource = new MutableLiveData<>();
    private final MutableLiveData<String> selectedRecipeName = new MutableLiveData<>();

    public LiveData<String> getIngredients() {
        return ingredients;
    }

    public LiveData<Integer> getImageResource() {
        return imageResource;
    }

    public LiveData<String> getSelectedRecipeName() {
        return selectedRecipeName;
    }

    public void selectRecipe(String recipeName, String[] ingredientsArray) {
        if (ingredientsArray == null || ingredientsArray.length < 3 || recipeName == null) {
            return;
        }

        String normalizedName = recipeName.trim();
        selectedRecipeName.setValue(normalizedName);

        if (normalizedName.equalsIgnoreCase("Crab Cake Sandwiches")) {
            ingredients.setValue(ingredientsArray[0]);
            imageResource.setValue(R.drawable.sandwinch);
        } else if (normalizedName.equalsIgnoreCase("Chewy Chocolate Chip Cookies")) {
            ingredients.setValue(ingredientsArray[1]);
            imageResource.setValue(R.drawable.cookies);
        } else if (normalizedName.equalsIgnoreCase("Tater Tot Chips w/ Loaded Green Onion Dip")) {
            ingredients.setValue(ingredientsArray[2]);
            imageResource.setValue(R.drawable.tatertotchips);
        } else {
            // Default selection
            ingredients.setValue(ingredientsArray[0]);
            imageResource.setValue(R.drawable.sandwinch);
            selectedRecipeName.setValue("Crab Cake Sandwiches");
        }
    }
}