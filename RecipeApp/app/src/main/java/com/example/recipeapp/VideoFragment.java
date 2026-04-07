package com.example.recipeapp;



import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.VideoView;

/**
 * VideoFragment handles the playback of recipe tutorials.
 * It uses a VideoView to play selected recipe videos from local resources.
 */
public class VideoFragment extends Fragment {

    private VideoViewModel mViewModel;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }
    View root;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root= inflater.inflate(R.layout.fragment_video, container, false);
        Button btn = (Button) root.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView userSelected = (AutoCompleteTextView) root.findViewById(R.id.spinner2);
                String user = userSelected.getText().toString().trim();
                VideoView videoView = (VideoView) root.findViewById(R.id.videoView2);
                if (getContext() == null) return;
                
                String pkgName = getContext().getPackageName();
                Uri videoUri1 = Uri.parse("android.resource://" + pkgName + "/" + R.raw.facebook_20240429_205118);
                Uri videoUri2 = Uri.parse("android.resource://" + pkgName + "/" + R.raw.facebook_20240429_204224);
                Uri videoUri3 = Uri.parse("android.resource://" + pkgName + "/" + R.raw.facebook_20240429_200928);
                
                switch (user) {
                    case "Crab Cake Sandwiches":
                        videoView.setVideoURI(videoUri1);
                        videoView.start();
                        break;
                    case "Chewy Chocolate Chip Cookies":
                        videoView.setVideoURI(videoUri2);
                        videoView.start();
                        break;
                    case "Tater Tot Chips w/ Loaded Green Onion Dip":
                        videoView.setVideoURI(videoUri3);
                        videoView.start();
                        break;
                    default:
                        break;
                }

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        // TODO: Use the ViewModel
    }

}