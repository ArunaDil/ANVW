package com.example.anvw;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicActivity extends MediaPlayer {

    private static MediaPlayer mediaPlayer;

    public static MediaPlayer setMediaPlayer(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.hust);
        return mediaPlayer;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }



}
