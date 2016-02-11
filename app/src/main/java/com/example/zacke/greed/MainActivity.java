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

    private int ones = 0;
    private int twos = 0;
    private int threes = 0;
    private int fours = 0;
    private int fives = 0;
    private int sixes = 0;

    private boolean straight = false;
    private boolean threeOfOnes = false;
    private boolean threeOfFives = false;
    private boolean sixOfAKind = false;

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

        if(PLAYER.getDiceList().isEmpty()) {
            PLAYER.getDiceList().add(DICE1);
            PLAYER.getDiceList().add(DICE2);
            PLAYER.getDiceList().add(DICE3);
            PLAYER.getDiceList().add(DICE4);
            PLAYER.getDiceList().add(DICE5);
            PLAYER.getDiceList().add(DICE6);
        }

        setDices();
        setScoreText();
        //newRound();
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
            if(dice.isDiceActive()) {
                dice.setDiceValue(rand.nextInt(7 - 1) + 1);
            }
            if(dice.getDiceValue() == 1 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey1));
            }
            if(dice.getDiceValue() == 2 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey2));
            }
            if(dice.getDiceValue() == 3 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey3));
            }
            if(dice.getDiceValue() == 4 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey4));
            }
            if(dice.getDiceValue() == 5 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.grey5));
            }
            if(dice.getDiceValue() == 6 && dice.isDiceActive()) {
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
        PLAYER.setRoundScore(0);
        ones = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        straight = false;
        threeOfOnes = false;
        threeOfFives = false;
        sixOfAKind = false;

        for(Dice dice: PLAYER.getDiceList()) {
            if(dice.getDiceValue() == 1 && dice.isDiceActive()) {
                ones++;
            }
            if(dice.getDiceValue() == 2 && dice.isDiceActive()) {
                twos++;
            }
            if(dice.getDiceValue() == 3 && dice.isDiceActive()) {
                threes++;
            }
            if(dice.getDiceValue() == 4 && dice.isDiceActive()) {
                fours++;
            }
            if(dice.getDiceValue() == 5 && dice.isDiceActive()) {
                fives++;
            }
            if(dice.getDiceValue() == 6 && dice.isDiceActive()) {
                sixes++;
            }
        }

        // Straight
        if(ones == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1
                && sixes == 1) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+1000);
            straight = true;
        }
        // Threee of a kind
        if(ones == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(1000*2));
            sixOfAKind = true;
        } else if(ones >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+1000);
            threeOfOnes = true;
        }
        if(twos == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(200*2));
            sixOfAKind = true;
        } else if(twos >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+200);
        }
        if(threes == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(300*2));
            sixOfAKind = true;
        } else if(threes >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+300);
        }
        if(fours == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(400*2));
            sixOfAKind = true;
        } else if(fours >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+400);
        }
        if(fives == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(500*2));
            sixOfAKind = true;
        } else if(fives >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+500);
            threeOfFives = true;
        }
        if(sixes == 6) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+(600*2));
            sixOfAKind = true;
        } else if(sixes >= 3) {
            PLAYER.setRoundScore(PLAYER.getRoundScore()+600);
        }

        // Ones and fives
        if(!sixOfAKind && !straight) {
            if(threeOfOnes) {
                PLAYER.setRoundScore(PLAYER.getRoundScore()+(100*(ones-3)));
            } else {
                PLAYER.setRoundScore(PLAYER.getRoundScore()+(100*ones));
            }
            if(threeOfFives) {
                PLAYER.setRoundScore(PLAYER.getRoundScore()+(50*(fives-3)));
            } else {
                PLAYER.setRoundScore(PLAYER.getRoundScore()+(50*fives));
            }
        }

        PLAYER.setTotalScore(PLAYER.getRoundScore()+PLAYER.getTotalScore());
        setScoreText();
        setActiveDices();

        if(PLAYER.getRoundScore() == 0) {
            newRound();
        }





    }

    public void setActiveDices() {
        for(Dice dice: PLAYER.getDiceList()) {
            if(dice.getDiceValue() == 1) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.white1));
                dice.setDiceActive(false);
            }
            if(dice.getDiceValue() == 5) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.white5));
                dice.setDiceActive(false);
            }
        }
        dice1Image.setImageBitmap(DICE1.getDiceImage());
        dice2Image.setImageBitmap(DICE2.getDiceImage());
        dice3Image.setImageBitmap(DICE3.getDiceImage());
        dice4Image.setImageBitmap(DICE4.getDiceImage());
        dice5Image.setImageBitmap(DICE5.getDiceImage());
        dice6Image.setImageBitmap(DICE6.getDiceImage());

    }

    public void newRound() {
        PLAYER.setRounds(PLAYER.getRounds()+1);
        for(Dice dice: PLAYER.getDiceList()) {
            dice.setDiceActive(true);
        }
    }

}
