package com.example.anvw;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.DialogFragment;

public class GameOver extends DialogFragment {

    public GameOver() {
        // Required empty public constructor
        //
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = layoutInflater.inflate(R.layout.gameover, container, false);

        Button btnNextLevel = v.findViewById(R.id.btn_retry2);
        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextLevelIntent = new Intent(getActivity(),SelectLevelActivity.class);
                startActivity(nextLevelIntent);

            }

        });

        // Inflate the layout for this fragment
        return v;
    }

}
