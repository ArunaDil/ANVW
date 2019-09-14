package com.example.anvw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevelActivity extends Activity {
    Button btn_lvl1,btn_lvl2,btn_lvl3,btn_lvl4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectlevel);

        btn_lvl1 = findViewById(R.id.btn_level1);
        btn_lvl2 = findViewById(R.id.btn_level2);
        btn_lvl3 = findViewById(R.id.btn_level3);
        btn_lvl4 = findViewById(R.id.btn_level4);

        btn_lvl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_play));

                // Start NewGameActivity.class
                Intent myIntent = new Intent(SelectLevelActivity.this,
                        Level1Activity.class);
                startActivity(myIntent);
            }
        });

        btn_lvl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_play));

                // Start NewGameActivity.class
                Intent myIntent = new Intent(SelectLevelActivity.this,
                        Level2Activity.class);
                startActivity(myIntent);
            }
        });

        btn_lvl3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_play));

                // Start NewGameActivity.class
                Intent myIntent = new Intent(SelectLevelActivity.this,
                        Level3Activity.class);
                startActivity(myIntent);
            }
        });

        btn_lvl4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_play));

                // Start NewGameActivity.class
                Intent myIntent = new Intent(SelectLevelActivity.this,
                        Level4Activity.class);
                startActivity(myIntent);
            }
        });
    }

    /*
    // loads level1
    public void loadSelectLevel1(View view) {
        // System.out.println(getResources().getString(R.string.clicked_btn_Scoreboard));

        // Get the view from activity_scoreboard.xml
        setContentView(R.layout.level1);

        // Get the correct button
        button = findViewById(R.id.btn_level1);

        // Start ScoreBoardActivity.class
        Intent myIntent = new Intent(SelectLevelActivity.this, Level1Activity.class);
        startActivity(myIntent);
    }

    // loads level2
    public void loadSelectLevel2(View view) {
        // System.out.println(getResources().getString(R.string.clicked_btn_Scoreboard));

        // Get the view from activity_scoreboard.xml
        setContentView(R.layout.level2);

        // Get the correct button
        button = findViewById(R.id.btn_level2);

        // Start ScoreBoardActivity.class
        Intent myIntent = new Intent(SelectLevelActivity.this, Level2Activity.class);
        startActivity(myIntent);
    }

    // loads level3
    public void loadSelectLevel3(View view) {
        // System.out.println(getResources().getString(R.string.clicked_btn_Scoreboard));

        // Get the view from activity_scoreboard.xml
        setContentView(R.layout.level3);

        // Get the correct button
        button = findViewById(R.id.btn_level3);

        // Start ScoreBoardActivity.class
        Intent myIntent = new Intent(SelectLevelActivity.this, Level3Activity.class);
        startActivity(myIntent);

    }

    // loads level4



  /*  public void loadSelectLevel4(View view) {
        // System.out.println(getResources().getString(R.string.clicked_btn_Scoreboard));

        // Get the view from activity_scoreboard.xml
        setContentView(R.layout.level4);

        // Get the correct button
        button = findViewById(R.id.btn_level4);

        // Start ScoreBoardActivity.class
        Intent myIntent = new Intent(SelectLevelActivity.this, Level4Activity.class);
        startActivity(myIntent);
    }
*/
}
