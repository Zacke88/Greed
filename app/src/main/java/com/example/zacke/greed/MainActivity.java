package com.example.zacke.greed;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView dice1;
    ImageView dice2;
    ImageView dice3;
    ImageView dice4;
    ImageView dice5;
    ImageView dice6;

    TextView roundScorePortrait;
    TextView totalScorePortrait;
    TextView roundScoreLandscape;
    TextView totalScoreLandscape;

    int roundScore = 100;
    int totalScore = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDices();
        setScoreText();

        throwDices();
    }

    public void setDices() {
        TextView text = (TextView) findViewById(R.id.dicePos);
        text.setText("");

        dice1 = (ImageView) findViewById(R.id.diceTopLeft);
        dice1.setScaleX((float) 0.7);
        dice1.setScaleY((float) 0.7);
        dice2 = (ImageView) findViewById(R.id.diceTopCenter);
        dice2.setScaleX((float) 0.7);
        dice2.setScaleY((float) 0.7);
        dice3 = (ImageView) findViewById(R.id.diceTopRight);
        dice3.setScaleX((float) 0.7);
        dice3.setScaleY((float) 0.7);
        dice4 = (ImageView) findViewById(R.id.diceBottomLeft);
        dice4.setScaleX((float) 0.7);
        dice4.setScaleY((float) 0.7);
        dice5 = (ImageView) findViewById(R.id.diceBottomCenter);
        dice5.setScaleX((float) 0.7);
        dice5.setScaleY((float) 0.7);
        dice6 = (ImageView) findViewById(R.id.diceBottomRight);
        dice6.setScaleX((float) 0.7);
        dice6.setScaleY((float) 0.7);
    }

    public void setScoreText() {
        roundScorePortrait = (TextView) findViewById(R.id.roundScorePortrait);
        roundScorePortrait.setText("");
        totalScorePortrait = (TextView) findViewById(R.id.totalScorePortrait);
        totalScorePortrait.setText("");
        roundScoreLandscape = (TextView) findViewById(R.id.roundScoreLandscape);
        roundScoreLandscape.setText("");
        totalScoreLandscape = (TextView) findViewById(R.id.totalScoreLandscape);
        totalScoreLandscape.setText("");

        if(getResources().getConfiguration().orientation == 1) {
            roundScorePortrait.setText(String.valueOf(roundScore));
            totalScorePortrait.setText(String.valueOf(totalScore));
        } else {
            roundScoreLandscape.setText(String.valueOf(roundScore));
            totalScoreLandscape.setText(String.valueOf(totalScore));
        }
    }

    public void throwDices() {
        dice1.setImageResource(R.drawable.white1);
        dice2.setImageResource(R.drawable.white2);
        dice3.setImageResource(R.drawable.white3);
        dice4.setImageResource(R.drawable.white4);
        dice5.setImageResource(R.drawable.white5);
        dice6.setImageResource(R.drawable.white6);
    }
}
