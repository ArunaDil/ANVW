package com.example.anvw;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.level4);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer x = MusicActivity.getMediaPlayer();
        x = MusicActivity.setMediaPlayer(getApplicationContext());
        x.start();

        Button buttonPlay,buttonScoreBoard,buttonSettings,buttonHelp,buttonQuit;

        // Locate the buttons in activity_main.xml
        buttonPlay = findViewById(R.id.btn_play);
        buttonScoreBoard = findViewById(R.id.btn_scoreboard);
        buttonSettings = findViewById(R.id.btn_Settings);
        buttonHelp = findViewById(R.id.btn_help);
        buttonQuit = findViewById(R.id.btn_quit);

        // loads Play
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_play));

                // Start NewGameActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        PlayActivity.class);
                startActivity(myIntent);
            }
        });

        // loads ScoreBoard
        buttonScoreBoard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_scoreboard));

                // Start ScoreBoardActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        ScoreBoardActivity.class);
                startActivity(myIntent);
            }
        });

        // loads Settings
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_Settings));

                // Start SettingsActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SettingsActivity.class);
                startActivity(myIntent);
            }
        });

        // loads Help
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_Help));

                // Start HelpActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        HelpActivity.class);
                startActivity(myIntent);
            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                System.out.println(getResources().getString(R.string.clicked_btn_Quit));

                System.exit(0);
            }
        });
    }

}

