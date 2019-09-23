package com.example.anvw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Player");

                myRef.setValue("Hello, World!");

                // Start NewGameActivity.class
                Intent myIntent = new Intent(SelectLevelActivity.this,
                        Level4Activity.class);
                startActivity(myIntent);
            }
        });
    }


}
