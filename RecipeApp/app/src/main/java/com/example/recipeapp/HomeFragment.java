package com.example.recipeapp;

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
import android.widget.Spinner;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    View root;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       root=inflater.inflate(R.layout.fragment_home, container, false);

        Button btn= (Button)root.findViewById(R.id.button);
        TextView txt = (TextView) root.findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner userspinner = (Spinner) root.findViewById(R.id.spinner3);
                String userSelected = userspinner.getSelectedItem().toString();
                ImageView img = (ImageView)root.findViewById(R.id.imageView);

                String item = "";

                String[]stringArray = getResources().getStringArray(R.array.Ingredients);
                switch (userSelected) {
                    case "Crab Cake Sandwiches":
                        item=stringArray[1];
                        img.setImageResource(R.drawable.sandwinch);
                        break;
                    case "Chewy Chocolate Chip Cookies":
                        item = stringArray[2];
                        img.setImageResource(R.drawable.cookies);
                        break;
                    case "Tater Tot Chips w/ Loaded Green Onion Dip":
                        item= stringArray[3];
                        img.setImageResource(R.drawable.tatertotchips);
                        break;
                    default:
                        item=stringArray[1];
                        img.setImageResource(R.drawable.sandwinch);
                        break;
            }
            txt.setText(item);
            }
        });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}