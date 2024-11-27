package com.example.recipeapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ItemPriceFragment extends Fragment {

    private ItemPriceViewModel mViewModel;

    public static ItemPriceFragment newInstance() {
        return new ItemPriceFragment();
    }
    View root;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root= inflater.inflate(R.layout.fragment_item_price, container, false);
        TextView txt = (TextView) root.findViewById(R.id.textView2);
        ImageButton imgBtn = (ImageButton) root.findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner userspinner = (Spinner) root.findViewById(R.id.spinner);
                String userSelected = userspinner.getSelectedItem().toString();
                String item = "";

                String[] stringArray = getResources().getStringArray(R.array.totalPriceWalmart);
                switch (userSelected) {
                    case "Crab Cake Sandwiches":
                        item = stringArray[0];
                        break;
                    case "Chewy Chocolate Chip Cookies":
                        item = stringArray[1];
                    case "Tater Tot Chips w/ Loaded Green Onion Dip":
                        item = stringArray[2];
                        break;
                    default:
                        item = stringArray[0];
                        break;
                }
                txt.setText(item);
            }
        });
        ImageButton imgBtn2 = (ImageButton) root.findViewById(R.id.imageButton3);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner userspinner = (Spinner) root.findViewById(R.id.spinner);
                String userSelected = userspinner.getSelectedItem().toString();
                String item = "";

                String[] stringArray = getResources().getStringArray(R.array.totalPriceTarget);
                switch (userSelected) {
                    case "Crab Cake Sandwiches":
                        item = stringArray[0];
                        break;
                    case "Chewy Chocolate Chip Cookies":
                        item = stringArray[1];
                    case "Tater Tot Chips w/ Loaded Green Onion Dip":
                        item = stringArray[2];
                        break;
                    default:
                        item = stringArray[0];
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
        mViewModel = new ViewModelProvider(this).get(ItemPriceViewModel.class);
        // TODO: Use the ViewModel
    }

}