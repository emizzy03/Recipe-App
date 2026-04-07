package com.example.recipeapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

/**
 * HomeFragment displays the main screen of the application.
 * It provides a Spinner to select a recipe and displays the corresponding image
 * and ingredients.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private TextView txt;
    private ImageView img;
    private AutoCompleteTextView userspinner;
    private View root;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn = (Button) root.findViewById(R.id.button);
        txt = (TextView) root.findViewById(R.id.textView);
        img = (ImageView) root.findViewById(R.id.imageView);
        userspinner = (AutoCompleteTextView) root.findViewById(R.id.spinner3);

        // Update ViewModel immediately on selection
        userspinner.setOnItemClickListener((parent, view, position, id) -> {
            String userSelected = userspinner.getText().toString().trim();
            String[] stringArray = getResources().getStringArray(R.array.Ingredients);
            mViewModel.selectRecipe(userSelected, stringArray);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userSelected = userspinner.getText().toString().trim();
                String[] stringArray = getResources().getStringArray(R.array.Ingredients);
                mViewModel.selectRecipe(userSelected, stringArray);
            }
        });

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Restore the selected recipe name to the AutoCompleteTextView
        mViewModel.getSelectedRecipeName().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.equals(userspinner.getText().toString())) {
                userspinner.setText(name, false); // Using false to avoid filtering during restoration
            }
        });

        // Observe the ViewModel's LiveData for display
        mViewModel.getIngredients().observe(getViewLifecycleOwner(), ingredients -> {
            txt.setText(ingredients);
        });

        mViewModel.getImageResource().observe(getViewLifecycleOwner(), resourceId -> {
            img.setImageResource(resourceId);
        });
    }

}