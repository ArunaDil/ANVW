package com.example.anvw;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class Level4Activity extends Activity {

    private Button button;
    TextView textView;

    // FragmentTransaction declaration
    FragmentTransaction ft;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level4);

        textView = (TextView) findViewById(R.id.textView);

        //button.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        CountDownTimer countDownTimer = new CountDownTimer(10 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText("TIME : " + millisUntilFinished / 1000);


            }

            @Override
            public void onFinish() {
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

        }.start();
    }
}
//        });
//
//    }
//    }








