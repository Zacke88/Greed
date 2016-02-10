package com.example.zacke.greed;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private ImageView dice1Image;
    private ImageView dice2Image;
    private ImageView dice3Image;
    private ImageView dice4Image;
    private ImageView dice5Image;
    private ImageView dice6Image;

    private TextView roundScorePortrait;
    private TextView totalScorePortrait;
    private TextView roundScoreLandscape;
    private TextView totalScoreLandscape;

    private Random rand = new Random();

    static final Player PLAYER = new Player();
    static final Dice DICE1 = new Dice();
    static final Dice DICE2 = new Dice();
    static final Dice DICE3 = new Dice();
    static final Dice DICE4 = new Dice();
    static final Dice DICE5 = new Dice();
    static final Dice DICE6 = new Dice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDices();
        setScoreText();
        newRound();
        throwDices();
    }

    /**
     * Sets the dices as ImageViews of the application and scales them
     * accordingly
     */
    public void setDices() {
        TextView text = (TextView) findViewById(R.id.dicePos);
        text.setText("");

        dice1Image = (ImageView) findViewById(R.id.diceTopLeft);
        dice1Image.setScaleX((float) 0.7);
        dice1Image.setScaleY((float) 0.7);
        dice2Image = (ImageView) findViewById(R.id.diceTopCenter);
        dice2Image.setScaleX((float) 0.7);
        dice2Image.setScaleY((float) 0.7);
        dice3Image = (ImageView) findViewById(R.id.diceTopRight);
        dice3Image.setScaleX((float) 0.7);
        dice3Image.setScaleY((float) 0.7);
        dice4Image = (ImageView) findViewById(R.id.diceBottomLeft);
        dice4Image.setScaleX((float) 0.7);
        dice4Image.setScaleY((float) 0.7);
        dice5Image = (ImageView) findViewById(R.id.diceBottomCenter);
        dice5Image.setScaleX((float) 0.7);
        dice5Image.setScaleY((float) 0.7);
        dice6Image = (ImageView) findViewById(R.id.diceBottomRight);
        dice6Image.setScaleX((float) 0.7);
        dice6Image.setScaleY((float) 0.7);
    }

    /**
     * Sets the player score text of the application based on how the screen is
     * oriented
     */
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
            roundScorePortrait.setText(String.valueOf(PLAYER.getRoundScore()));
            totalScorePortrait.setText(String.valueOf(PLAYER.getTotalScore()));
        } else {
            roundScoreLandscape.setText(String.valueOf(PLAYER.getRoundScore()));
            totalScoreLandscape.setText(String.valueOf(PLAYER.getTotalScore()));
        }
    }

    /**
     * Method for "throwing" 6 dices and randomizes the number of each dice
     * with a value ranging from 1-6. Then sets the value for each dice and
     * adds and shows a corresponding image for that value
     */
    public void throwDices() {

        for(Dice dice: PLAYER.getDiceList()) {
            dice.setDiceValue(rand.nextInt(7 - 1) + 1);
            if(dice.getDiceValue() == 1) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey1));
            }
            if(dice.getDiceValue() == 2) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey2));
            }
            if(dice.getDiceValue() == 3) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey3));
            }
            if(dice.getDiceValue() == 4) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey4));
            }
            if(dice.getDiceValue() == 5) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey5));
            }
            if(dice.getDiceValue() == 6) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey6));
            }
        }

        dice1Image.setImageBitmap(DICE1.getDiceImage());
        dice2Image.setImageBitmap(DICE2.getDiceImage());
        dice3Image.setImageBitmap(DICE3.getDiceImage());
        dice4Image.setImageBitmap(DICE4.getDiceImage());
        dice5Image.setImageBitmap(DICE5.getDiceImage());
        dice6Image.setImageBitmap(DICE6.getDiceImage());

        calculateScore();
    }

    /**
     * Calculates the score a user gets on one throw for the active dices
     */
    public void calculateScore() {

    }

    public void newRound() {
        PLAYER.getDiceList().clear();
        PLAYER.getDiceList().add(DICE1);
        PLAYER.getDiceList().add(DICE2);
        PLAYER.getDiceList().add(DICE3);
        PLAYER.getDiceList().add(DICE4);
        PLAYER.getDiceList().add(DICE5);
        PLAYER.getDiceList().add(DICE6);
    }

}
