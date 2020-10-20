package com.example.easytrail.ui.help;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.easytrail.AboutActivity;
import com.example.easytrail.R;
import com.example.easytrail.SplashActivity;
import com.google.android.material.button.MaterialButton;

public class HelpFragment extends Fragment {
    MaterialButton about_about_btn;
    MaterialButton about_instruction_btn;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);

        about_about_btn = root.findViewById(R.id.about_about_btn);
        about_instruction_btn = root.findViewById(R.id.about_instruction_btn);
        about_about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });
        about_instruction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.putExtra("result",true);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });



        return root;
    }



}