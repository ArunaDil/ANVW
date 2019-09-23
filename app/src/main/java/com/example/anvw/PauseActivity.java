package com.example.anvw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PauseActivity extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pause);

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = layoutInflater.inflate(R.layout.pause, container, false);

        Button btnNextLevel = v.findViewById(R.id.btn_nextLevel);
        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentLevel = Player.getInstance().getCurrentLevel();
                Activity gm = null;

                if(currentLevel == 1) gm = new Level1Activity();
                else if (currentLevel == 2) gm = new Level2Activity();
                else if(currentLevel == 3) gm = new Level3Activity();
                else if(currentLevel == 4) gm = new Level4Activity();

                Intent nextLevelIntent = new Intent(getActivity(), gm.getClass());
                startActivity(nextLevelIntent);

            }

        });

        // Inflate the layout for this fragment
        return v;
    }

}
