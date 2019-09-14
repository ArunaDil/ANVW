package com.example.anvw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends Activity {
    Button btn_continue,btn_newgame,btn_selectlevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        btn_continue = findViewById(R.id.btn_continue);
        btn_newgame = findViewById(R.id.btn_newgame);
        btn_selectlevel = findViewById(R.id.btn_selectlevel);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PlayActivity.this,
                        PlayActivity.class);
                startActivity(myIntent);
            }
        });

        btn_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PlayActivity.this,
                        NewGameActivity.class);
                startActivity(myIntent);
            }
        });

        btn_selectlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PlayActivity.this,
                        SelectLevelActivity.class);
                startActivity(myIntent);
            }
        });

    }

}
