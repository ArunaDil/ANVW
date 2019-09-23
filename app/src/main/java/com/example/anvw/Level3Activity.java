package com.example.anvw;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Level3Activity extends Activity {

    Button button;
    TextView textView;

    // FragmentTransaction declaration
    FragmentTransaction ft;

    final static int LEVEL_ID = 1;
    final static String LEVEL_NAME = Strings.level3;
    // toast declaration
    Toast levelToast;

    static boolean gameCompleteFlag = false;

    // next level button
    static Button btn_nextLevel3;

    // current Player
    static Player currentPlayer;

    // player score
    static int playerScore = 30000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level3);

        if(Player.getIsLevelComplete()==1){
            Player.setIsLevelComplete();
        }

        // set next level button and hide it
        btn_nextLevel3 = findViewById(R.id.btn_nextLevel3);
        btn_nextLevel3.setVisibility(View.VISIBLE);

        //Set the Time
        textView = (TextView) findViewById(R.id.textView);

        CountDownTimer countDownTimer = new CountDownTimer(40 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText("TIME : " + millisUntilFinished / 1000);

                if(Player.getIsLevelComplete()==1){
                    this.cancel();

                    ft = getFragmentManager().beginTransaction();
                    String tag_A = getResources().getString(R.string.fragment_dialog);
                    Fragment prev = getFragmentManager().findFragmentByTag(tag_A);
                    if (prev != null) {
                        ft.remove(prev);
                    }

                    ft.addToBackStack(null);

                    DialogFragment dialogFragment = new Complete();
                    dialogFragment.show(ft, tag_A);

                }
            }
            @Override
            public void onFinish() {

                if (Player.getIsLevelComplete() == 0) {

                    ft = getFragmentManager().beginTransaction();
                    String tag_A = getResources().getString(R.string.fragment_dialog);
                    Fragment prev = getFragmentManager().findFragmentByTag(tag_A);
                    if (prev != null) {
                        ft.remove(prev);
                    }

                    ft.addToBackStack(null);

                    DialogFragment dialogFragment = new GameOver();
                    dialogFragment.show(ft, tag_A);
                }
            }

        }.start();


    // set next level onclick button
        btn_nextLevel3.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view) {

        Intent nextLevelIntent = new Intent(Level3Activity.this, Level4Activity.class);
        nextLevelIntent.putExtra(Strings.extra_anvw_levelID, Level3Activity.LEVEL_ID);
        nextLevelIntent.putExtra(Strings.extra_anvw_levelName, Level3Activity.LEVEL_NAME);
        nextLevelIntent.putExtra(Strings.extra_anvw_levelScore, Level3Activity.getPlayerScore());
        startActivity(nextLevelIntent);
    }
    });
}

    public static void checkWin(){
        if(gameCompleteFlag == true){
            btn_nextLevel3.setVisibility(View.INVISIBLE);
        }

    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore){
        Level3Activity.playerScore = playerScore;
    }

    @Override
    public void onBackPressed()
    {
        ft = getFragmentManager().beginTransaction();
        String tag_A = getResources().getString(R.string.fragment_dialog);
        Fragment prev = getFragmentManager().findFragmentByTag(tag_A);
        if(prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);

        DialogFragment dialogFragment = new PauseActivity();
        dialogFragment.show(ft, tag_A);
    }


}
