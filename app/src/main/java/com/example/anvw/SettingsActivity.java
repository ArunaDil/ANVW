package com.example.anvw;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends Activity {

    private Switch switchSfx,switchMusic;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        switchSfx = findViewById(R.id.switchSfx);
        switchMusic = findViewById(R.id.switchMusic);

        mp = MusicActivity.getMediaPlayer();

        if(mp.isPlaying())
            switchMusic.setChecked(true);

        switchSfx.setChecked(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!switchMusic.isChecked()) {
                    mp.stop();
                    mp = null;
                } else {
                    mp = MusicActivity.setMediaPlayer(SettingsActivity.this.getApplicationContext());
                    mp.start();
                }
            }
        });
    }

}
