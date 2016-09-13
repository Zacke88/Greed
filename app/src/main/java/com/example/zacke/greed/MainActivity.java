package com.example.zacke.greed;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MainActivity for the game Greed, it handles the game rules and logic
 * <p/>
 * Greed is played by one player where the goal is to reach a set score
 * (10000 points) within as few rounds as possible
 * <p/>
 * Each round the player throws dices and the chooses which of the dices to
 * keep, making it counts towards the round score. the player can then choose
 * to continue the round by throwing unselected dices again or save score
 * starting a new round
 * <p/>
 * The first throw each round the player has to meet a criteria of at least
 * 300 points whereas the following throws the player has to get score
 * greater than 0. If theese critera is not met the round score will be set
 * to 0 and a new round will start.
 * <p/>
 * To score the player either has to get a straight (getting dices 1, 2, 3,
 * 4, 5, 6) giving a score of 1000 or by getting three of a kind which gives
 * score based on the value of the dice times 100. Also the 1's and 5's which
 * was not used for either a stright or three of a kind will give score, 100
 * for each 1 and 50 for each 5.
 *
 * @author Joakim Zakrisson
 * @version 2016-02-09
 */
public class MainActivity extends AppCompatActivity {

    private ImageView die1Image;
    private ImageView die2Image;
    private ImageView die3Image;
    private ImageView die4Image;
    private ImageView die5Image;
    private ImageView die6Image;

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

    private Random rand = new Random();

    static final Player PLAYER = new Player();
    static final Die DIE_1 = new Die();
    static final Die DIE_2 = new Die();
    static final Die DIE_3 = new Die();
    static final Die DIE_4 = new Die();
    static final Die DIE_5 = new Die();
    static final Die DIE_6 = new Die();

    private List<ImageView> diceImages = new ArrayList<>();
    private List<Die> dice = new ArrayList<>();

    /**
     * This method is run when this class is created which is when the
     * application is starting. Updates the action bar and sets the dice,
     * buttons, text and images for the applocation.
     *
     * @param savedInstanceState The state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("  Greed");
            actionBar.setLogo(R.drawable.coin);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        initiateLists();
        setDices();
        setButtons();
        setScoreText();
        updateDiceImages();
    }

    /**
     * Creates lists which hold all dice and corresponding image views
     */
    public void initiateLists() {
        if (diceImages.isEmpty()) {
            die1Image = (ImageView) findViewById(R.id.diceTopLeft);
            die2Image = (ImageView) findViewById(R.id.diceTopCenter);
            die3Image = (ImageView) findViewById(R.id.diceTopRight);
            die4Image = (ImageView) findViewById(R.id.diceBottomLeft);
            die5Image = (ImageView) findViewById(R.id.diceBottomCenter);
            die6Image = (ImageView) findViewById(R.id.diceBottomRight);
            diceImages.add(die1Image);
            diceImages.add(die2Image);
            diceImages.add(die3Image);
            diceImages.add(die4Image);
            diceImages.add(die5Image);
            diceImages.add(die6Image);
        }
        if (dice.isEmpty()) {
            dice.add(DIE_1);
            dice.add(DIE_2);
            dice.add(DIE_3);
            dice.add(DIE_4);
            dice.add(DIE_5);
            dice.add(DIE_6);
        }
    }

    /**
     * Sets the dices as ImageViews of the application and scales them
     * accordingly
     */
    public void setDices() {
        if (PLAYER.getDieList().isEmpty()) {
            PLAYER.getDieList().addAll(dice);
        }
        TextView text = (TextView) findViewById(R.id.dicePos);
        text.setText("");

        //Scale all dice images
        for (ImageView imageView : diceImages) {
            imageView.setScaleX((float) 0.7);
            imageView.setScaleY((float) 0.7);
        }
    }

    /**
     * Makes all dices grey to show the player that the round is over and the
     * player has to throw again
     */
    public void makeDiceInactive() {
        for (Die die : PLAYER.getDieList()) {
            setDieGrey(die.getValue(), die);
        }
        updateDiceImages();
    }

    /**
     * Changes the image of a die to grey
     *
     * @param dieValue Value of the die
     * @param die Die to give a new image
     */
    public void setDieGrey(int dieValue, Die die) {
        if (dieValue == 1) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey1));
        }
        if (dieValue == 2) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey2));
        }
        if (dieValue == 3) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey3));
        }
        if (dieValue == 4) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey4));
        }
        if (dieValue == 5) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey5));
        }
        if (dieValue == 6) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.grey6));
        }
    }

    /**
     * Changes the image of a die to white
     *
     * @param dieValue Value of the die
     * @param die Die to give a new image
     */
    public void setDieWhite(int dieValue, Die die) {
        if (dieValue == 1) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white1));
        }
        if (dieValue == 2) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white2));
        }
        if (dieValue == 3) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white3));
        }
        if (dieValue == 4) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white4));
        }
        if (dieValue == 5) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white5));
        }
        if (dieValue == 6) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.white6));
        }
    }

    /**
     * Changes the image of a die to red
     *
     * @param dieValue Value of the die
     * @param die Die to give a new image
     */
    public void setDieRed(int dieValue, Die die) {
        if (dieValue == 1) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red1));
        }
        if (dieValue == 2) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red2));
        }
        if (dieValue == 3) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red3));
        }
        if (dieValue == 4) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red4));
        }
        if (dieValue == 5) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red5));
        }
        if (dieValue == 6) {
            die.setImage(BitmapFactory.decodeResource(this
                    .getResources(), R.drawable.red6));
        }
    }

    /**
     * Sets the buttons to corresponding button and dice from the view and adds
     * listeners to them
     */
    public void setButtons() {
        Button scoreButton = (Button) findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(new ScoreListener());
        Button throwButton = (Button) findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new ThrowListener());
        die1Image.setOnClickListener(new Dice1Listener());
        die2Image.setOnClickListener(new Dice2Listener());
        die3Image.setOnClickListener(new Dice3Listener());
        die4Image.setOnClickListener(new Dice4Listener());
        die5Image.setOnClickListener(new Dice5Listener());
        die6Image.setOnClickListener(new Dice6Listener());
    }

    /**
     * Sets the player score text of the application based on how the screen is
     * oriented
     */
    public void setScoreText() {
        TextView roundScorePortrait = (TextView) findViewById(R.id
                .roundScorePortrait);
        roundScorePortrait.setText("");
        TextView totalScorePortrait = (TextView) findViewById(R.id
                .totalScorePortrait);
        totalScorePortrait.setText("");
        TextView roundScoreLandscape = (TextView) findViewById(R.id
                .roundScoreLandscape);
        roundScoreLandscape.setText("");
        TextView totalScoreLandscape = (TextView) findViewById(R.id
                .totalScoreLandscape);
        totalScoreLandscape.setText("");

        if (getResources().getConfiguration().orientation == 1) {
            roundScorePortrait.setText(String.valueOf(PLAYER.getRoundScore())
                    + " (+" + String.valueOf(PLAYER.getThrowScore()) + "/" + String
                    .valueOf(PLAYER.getMaxRoundScore() + ")"));
            totalScorePortrait.setText(String.valueOf(PLAYER.getTotalScore()));
        } else {
            roundScoreLandscape.setText(String.valueOf(PLAYER.getRoundScore())
                    + " (+" + String.valueOf(PLAYER.getThrowScore()) + "/" + String
                    .valueOf(PLAYER.getMaxRoundScore() + ")"));
            totalScoreLandscape.setText(String.valueOf(PLAYER.getTotalScore()));
        }
    }

    /**
     * Method for "throwing" 6 dices and randomizes the number of each dice
     * with a value ranging from 1-6. Then sets the value for each dice and
     * adds and shows a corresponding image for that value
     * Dices being "thrown" is only the active dices
     */
    public void throwDices() {
        for (Die die : PLAYER.getDieList()) {
            if (die.isActive()) {
                die.setValue(rand.nextInt(7 - 1) + 1);
                setDieGrey(die.getValue(), die);
            }
        }
        updateDiceImages();
        PLAYER.setRoundScore(PLAYER.getRoundScore() + PLAYER.getThrowScore());
        calculateScore(true);
    }

    /**
     * Calculates the score a user gets on one throw for the selected dices
     * The inital score is calculated only by the active dices whereas the
     * round score is then calculated byt the dices selected by the player
     * <p/>
     * Inactive dices which is dices already used to calculate score during
     * this round will not be used to further calculate the score since they
     * already gave points for total round scoer
     *
     * @param checkCriteria boolean which is set to true to check if a
     *                      criteria for a round is met, ie to get at least
     *                      300 score first throw or to get any score throws
     *                      after
     */
    public void calculateScore(boolean checkCriteria) {
        resetDiceState();

        //Calculate score for the thrown dice
        if (checkCriteria) {
            for (Die die : PLAYER.getDieList()) {
                if (die.getValue() == 1 && die.isActive()) {
                    ones++;
                }
                if (die.getValue() == 2 && die.isActive()) {
                    twos++;
                }
                if (die.getValue() == 3 && die.isActive()) {
                    threes++;
                }
                if (die.getValue() == 4 && die.isActive()) {
                    fours++;
                }
                if (die.getValue() == 5 && die.isActive()) {
                    fives++;
                }
                if (die.getValue() == 6 && die.isActive()) {
                    sixes++;
                }
            }
        }
        // Calculate score of dices selected by the player
        else {
            for (Die die : PLAYER.getDieList()) {
                if (die.getValue() == 1 && die.isSelected() && die
                        .isActive()) {
                    ones++;
                }
                if (die.getValue() == 2 && die.isSelected() && die
                        .isActive()) {
                    twos++;
                }
                if (die.getValue() == 3 && die.isSelected() && die
                        .isActive()) {
                    threes++;
                }
                if (die.getValue() == 4 && die.isSelected() && die
                        .isActive()) {
                    fours++;
                }
                if (die.getValue() == 5 && die.isSelected() && die
                        .isActive()) {
                    fives++;
                }
                if (die.getValue() == 6 && die.isSelected() && die
                        .isActive()) {
                    sixes++;
                }
            }
        }

        // Straight
        if (ones == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1
                && sixes == 1) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 1000);
            straight = true;
        }
        // Threee of a kind
        if (ones == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (1000 * 2));
            sixOfAKind = true;
        } else if (ones >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 1000);
            threeOfOnes = true;
        }
        if (twos == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (200 * 2));
            sixOfAKind = true;
        } else if (twos >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 200);
        }
        if (threes == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (300 * 2));
            sixOfAKind = true;
        } else if (threes >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 300);
        }
        if (fours == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (400 * 2));
            sixOfAKind = true;
        } else if (fours >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 400);
        }
        if (fives == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (500 * 2));
            sixOfAKind = true;
        } else if (fives >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 500);
            threeOfFives = true;
        }
        if (sixes == 6) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + (600 * 2));
            sixOfAKind = true;
        } else if (sixes >= 3) {
            PLAYER.setThrowScore(PLAYER.getThrowScore() + 600);
        }

        // Ones and fives
        if (!sixOfAKind && !straight) {
            if (threeOfOnes) {
                PLAYER.setThrowScore(PLAYER.getThrowScore() + (100 * (ones -
                        3)));
            } else {
                PLAYER.setThrowScore(PLAYER.getThrowScore() + (100 * ones));
            }
            if (threeOfFives) {
                PLAYER.setThrowScore(PLAYER.getThrowScore() + (50 * (fives -
                        3)));
            } else {
                PLAYER.setThrowScore(PLAYER.getThrowScore() + (50 * fives));
            }
        }

        if (checkCriteria) {
            setActiveDices();
            PLAYER.setMaxRoundScore(PLAYER.getThrowScore());
            //Starts a new round if criteria for round score not met
            boolean failedRound = false;
            if (PLAYER.getThrowScore() < 300 && PLAYER.isFirstRound()) {
                PLAYER.setRoundScore(0);
                failedRound = true;
                showToast("failFirstRound");
            } else if (PLAYER.getThrowScore() == 0) {
                PLAYER.setRoundScore(0);
                failedRound = true;
                showToast("failRound");
            }
            PLAYER.setFirstRound(false);
            if (failedRound) {
                newRound();
            }
            PLAYER.setThrowScore(0);
            setScoreText();
        } else {
            setScoreText();
        }

    }

    /**
     * Resets the dices and scores to 0, should be called before making a new
     * score calculation
     */
    public void resetDiceState() {
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
        PLAYER.setThrowScore(0);
    }

    /**
     * Sets all dices that is able to give score to inactive and updates their
     * images to make them appear white
     */
    public void setActiveDices() {
        //Straight or six of a kind
        if (straight || sixOfAKind) {
            for (Die die : PLAYER.getDieList()) {
                die.setSelectable(true);
                setDieWhite(die.getValue(), die);
            }
        } else {
            int diceCount;

            //Three of ones
            if (ones >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 1 && diceCount < 3) {
                        die.setSelectable(true);
                        setDieWhite(die.getValue(), die);
                        diceCount++;
                    }
                }
            }
            //Three of twos
            if (twos >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 2 && diceCount < 3) {
                        setDieWhite(die.getValue(), die);
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
            //Three of threes
            if (threes >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 3 && diceCount < 3) {
                        setDieWhite(die.getValue(), die);
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
            //Three of fours
            if (fours >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 4 && diceCount < 3) {
                        setDieWhite(die.getValue(), die);
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
            //Three of fives
            if (fives >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 5 && diceCount < 3) {
                        setDieWhite(die.getValue(), die);
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
            //Three of sixes
            if (sixes >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 6 && diceCount < 3) {
                        setDieWhite(die.getValue(), die);
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
        }

        // Loose one or fives
        for (Die die : PLAYER.getDieList()) {
            if (die.getValue() == 1 && die.isActive()) {
                setDieWhite(die.getValue(), die);
                die.setSelectable(true);
            }
            if (die.getValue() == 5 && die.isActive()) {
                setDieWhite(die.getValue(), die);
                die.setSelectable(true);
            }
        }
        updateDiceImages();
    }

    /**
     * Updates the images for all dices which shows if they are active or
     * selected
     */
    public void updateDiceImages() {
        die1Image.setImageBitmap(DIE_1.getImage());
        die2Image.setImageBitmap(DIE_2.getImage());
        die3Image.setImageBitmap(DIE_3.getImage());
        die4Image.setImageBitmap(DIE_4.getImage());
        die5Image.setImageBitmap(DIE_5.getImage());
        die6Image.setImageBitmap(DIE_6.getImage());
    }

    /**
     * Should be called each time a new round starts, sets all dices to
     * active and adds points to a players total score aswell as incrementing
     * total rounds played
     */
    public void newRound() {
        PLAYER.setRounds(PLAYER.getRounds() + 1);
        PLAYER.setTotalScore(PLAYER.getRoundScore() + PLAYER.getTotalScore());
        PLAYER.setRoundScore(0);
        setScoreText();
        PLAYER.setFirstRound(true);
        for (Die die : PLAYER.getDieList()) {
            die.activate();
            die.deselect();
            die.setSelectable(false);
        }
        makeDiceInactive();
    }

    /**
     * Gets the value of a dice and checks if it's active or not then selects
     * it making it red and is then used to calculate how much score a user gets
     *
     * @param die which dice to select
     */
    public void selectDice(Die die) {
        if (die.isSelectable()) {
            die.select();
            setDieRed(die.getValue(), die);
        }

        updateDiceImages();
    }

    /**
     * Shows a toast for the player which is placed depending on how the
     * screen is oriented
     *
     * @param toastString which toast text to show
     */
    public void showToast(String toastString) {
        if (toastString.equals("score")) {
            Toast toast = Toast.makeText(MainActivity.this, "Round score " +
                    "added " +
                    "to total " +
                    "score, Throw again to start new round)", Toast
                    .LENGTH_SHORT);
            if (getResources().getConfiguration().orientation == 1) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity
                                .CENTER_HORIZONTAL, 0,
                        0);
            } else {
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
            }
            toast.show();
        }
        if (toastString.equals("failFirstRound")) {
            Toast toast = Toast.makeText(this, "Failed to reach 300 on first " +
                    "throw, " +
                    "starting new round)", Toast.LENGTH_SHORT);
            if (getResources().getConfiguration().orientation == 1) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity
                                .CENTER_HORIZONTAL, 0,
                        0);
            } else {
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
            }
            toast.show();
        }
        if (toastString.equals("failRound")) {
            Toast toast = Toast.makeText(this, "Failed to score on this " +
                    "throw, " +
                    "starting new round)", Toast.LENGTH_SHORT);
            if (getResources().getConfiguration().orientation == 1) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity
                                .CENTER_HORIZONTAL, 0,
                        0);
            } else {
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
            }
            toast.show();
        }
    }

    /**
     * Listener for the throw Button which calls a method to throw all
     * active dices. Also makes all unselected dices active, meaning they
     * will be thrown again
     */
    private class ThrowListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int diceScored = 0;
            for (Die die : PLAYER.getDieList()) {
                if (!die.isSelected()) {
                    die.activate();
                }
                if (die.isSelected()) {
                    diceScored++;
                    die.deactivate();
                }
                die.setSelectable(false);
            }
            //Resets the dices if all dices are selected to give points
            if (diceScored >= 6) {
                for (Die die : PLAYER.getDieList()) {
                    die.activate();
                    die.deselect();
                    die.setSelectable(false);
                }
            }
            throwDices();
        }
    }

    /**
     * Listener for the Score button which calls methods to save the players
     * current round score then start a new round
     */
    private class ScoreListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            PLAYER.setRoundScore(PLAYER.getRoundScore() + PLAYER
                    .getThrowScore());
            if (PLAYER.getThrowScore() > 0) {
                showToast("score");
                newRound();
                PLAYER.setThrowScore(0);
            }
            if (PLAYER.getTotalScore() >= PLAYER.getWinScore()) {
                Intent intent = new Intent(MainActivity.this, WinActivity
                        .class);
                intent.putExtra("score", String.valueOf(PLAYER.getTotalScore
                        ()));
                intent.putExtra("rounds", String.valueOf(PLAYER.getRounds()));
                startActivity(intent);
            }
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice1Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(0));
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice2Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(1));
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice3Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(2));
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice4Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(3));
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice5Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(4));
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    private class Dice6Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(PLAYER.getDieList().get(5));
            calculateScore(false);
        }
    }
}

