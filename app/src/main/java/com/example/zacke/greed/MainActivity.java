package com.example.zacke.greed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Zacke on 2016-02-09.
 *
 * MainActivity for the game Greed, it handles the game rules and logic
 *
 * Greed is played by one player where the goal is to reach a set score
 * (10000 points) within as few rounds as possible
 *
 * Each round the player throws dices and the chooses which of the dices to
 * keep, making it counts towards the round score. the player can then choose
 * to continue the round by throwing unselected dices again or save score
 * starting a new round
 *
 * The first throw each round the player has to meet a criteria of at least
 * 300 points whereas the following throws the player has to get score
 * greater than 0. If theese critera is not met the round score will be set
 * to 0 and a new round will start.
 *
 * To score the player either has to get a straight (getting dices 1, 2, 3,
 * 4, 5, 6) giving a score of 1000 or by getting three of a kind which gives
 * score based on the value of the dice times 100. Also the 1's and 5's which
 * was not used for either a stright or three of a kind will give score, 100
 * for each 1 and 50 for each 5.
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

        setDices();
        setButtons();
        setScoreText();
        updateDiceImages();
    }

    /**
     * Sets the dices as ImageViews of the application and scales them
     * accordingly
     */
    public void setDices() {
        if (PLAYER.getDieList().isEmpty()) {
            PLAYER.getDieList().add(DIE_1);
            PLAYER.getDieList().add(DIE_2);
            PLAYER.getDieList().add(DIE_3);
            PLAYER.getDieList().add(DIE_4);
            PLAYER.getDieList().add(DIE_5);
            PLAYER.getDieList().add(DIE_6);
        }

        TextView text = (TextView) findViewById(R.id.dicePos);
        text.setText("");

        die1Image = (ImageView) findViewById(R.id.diceTopLeft);
        die1Image.setScaleX((float) 0.7);
        die1Image.setScaleY((float) 0.7);
        die2Image = (ImageView) findViewById(R.id.diceTopCenter);
        die2Image.setScaleX((float) 0.7);
        die2Image.setScaleY((float) 0.7);
        die3Image = (ImageView) findViewById(R.id.diceTopRight);
        die3Image.setScaleX((float) 0.7);
        die3Image.setScaleY((float) 0.7);
        die4Image = (ImageView) findViewById(R.id.diceBottomLeft);
        die4Image.setScaleX((float) 0.7);
        die4Image.setScaleY((float) 0.7);
        die5Image = (ImageView) findViewById(R.id.diceBottomCenter);
        die5Image.setScaleX((float) 0.7);
        die5Image.setScaleY((float) 0.7);
        die6Image = (ImageView) findViewById(R.id.diceBottomRight);

        die6Image.setScaleX((float) 0.7);
        die6Image.setScaleY((float) 0.7);
    }

    /**
     * Makes all dices grey to show the player that the round is over and the
     * player has to throw again
     */
    public void makeDicesInactive() {
        for (Die die : PLAYER.getDieList()) {
            if (die.getValue() == 1) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey1));
            }
            if (die.getValue() == 2) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey2));
            }
            if (die.getValue() == 3) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey3));
            }
            if (die.getValue() == 4) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey4));
            }
            if (die.getValue() == 5) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey5));
            }
            if (die.getValue() == 6) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey6));
            }
        }
        updateDiceImages();
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
                    +" (+"+ String.valueOf(PLAYER.getThrowScore())+"/"+String
                    .valueOf(PLAYER.getMaxRoundScore()+")"));
            totalScorePortrait.setText(String.valueOf(PLAYER.getTotalScore()));
        } else {
            roundScoreLandscape.setText(String.valueOf(PLAYER.getRoundScore())
                    +" (+"+ String.valueOf(PLAYER.getThrowScore())+"/"+String
                    .valueOf(PLAYER.getMaxRoundScore()+")"));
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
            }
            if (die.getValue() == 1 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey1));
            }
            if (die.getValue() == 2 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey2));
            }
            if (die.getValue() == 3 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey3));
            }
            if (die.getValue() == 4 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey4));
            }
            if (die.getValue() == 5 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey5));
            }
            if (die.getValue() == 6 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.grey6));
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
     *
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
                if (die.getValue() == 1) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white1));
                } else if (die.getValue() == 2) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white2));
                } else if (die.getValue() == 3) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white3));
                } else if (die.getValue() == 4) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white4));
                } else if (die.getValue() == 5) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white5));
                } else if (die.getValue() == 6) {
                    die.setImage(BitmapFactory.decodeResource(this
                            .getResources(), R.drawable.white6));
                }
            }
        } else {
            //Three of ones
            int diceCount;
            if (ones >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 1 && diceCount < 3) {
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white1));
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
            //Three of twos
            if (twos >= 3) {
                diceCount = 0;
                for (Die die : PLAYER.getDieList()) {
                    if (die.getValue() == 2 && diceCount < 3) {
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white2));
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
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white3));
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
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white4));
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
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white5));
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
                        die.setImage(BitmapFactory.decodeResource(this
                                .getResources(), R.drawable.white6));
                        die.setSelectable(true);
                        diceCount++;
                    }
                }
            }
        }

        // Loose one or fives
        for (Die die : PLAYER.getDieList()) {
            if (die.getValue() == 1 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.white1));
                die.setSelectable(true);
            }
            if (die.getValue() == 5 && die.isActive()) {
                die.setImage(BitmapFactory.decodeResource(this
                        .getResources(), R.drawable.white5));
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
        makeDicesInactive();
    }

    /**
     * Gets the value of a dice and checks if it's active or not then selects
     * it making it red and is then used to calculate how much score a user gets
     *
     * @param dice which dice to select
     */
    public void selectDice(int dice) {
        if (dice == 1 && PLAYER.getDieList().get(dice - 1).isSelectable
                ()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
        } else if (dice == 2 && PLAYER.getDieList().get(dice - 1)
                .isSelectable()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
        } else if (dice == 3 && PLAYER.getDieList().get(dice - 1)
                .isSelectable()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
        } else if (dice == 4 && PLAYER.getDieList().get(dice - 1)
                .isSelectable()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
        } else if (dice == 5 && PLAYER.getDieList().get(dice - 1)
                .isSelectable()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
        } else if (dice == 6 && PLAYER.getDieList().get(dice - 1)
                .isSelectable()) {
            PLAYER.getDieList().get(dice - 1).select();
            if (PLAYER.getDieList().get(dice - 1).getValue() == 1) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red1));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 2) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red2));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 3) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red3));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 4) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red4));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 5) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red5));
            } else if (PLAYER.getDieList().get(dice - 1).getValue() == 6) {
                PLAYER.getDieList().get(dice - 1).setImage(BitmapFactory
                        .decodeResource(this.getResources(), R.drawable.red6));
            }
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
        if(toastString.equals("score")) {
            Toast toast = Toast.makeText(MainActivity.this, "Round score " +
                    "added " +
                    "to total " +
                    "score, Throw again to start new round)", Toast
                    .LENGTH_SHORT);
            if(getResources().getConfiguration().orientation == 1) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity
                                .CENTER_HORIZONTAL, 0,
                        0);
            } else {
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
            }
            toast.show();
        }
        if(toastString.equals("failFirstRound")) {
            Toast toast = Toast.makeText(this, "Failed to reach 300 on first " +
                    "throw, " +
                    "starting new round)", Toast.LENGTH_SHORT);
            if(getResources().getConfiguration().orientation == 1) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity
                                .CENTER_HORIZONTAL, 0,
                        0);
            } else {
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
            }
            toast.show();
        }
        if(toastString.equals("failRound")) {
            Toast toast = Toast.makeText(this, "Failed to score on this " +
                    "throw, " +
                    "starting new round)", Toast.LENGTH_SHORT);
            if(getResources().getConfiguration().orientation == 1) {
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
    public class ThrowListener implements View.OnClickListener {

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
            if(diceScored >= 6) {
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
    public class ScoreListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            PLAYER.setRoundScore(PLAYER.getRoundScore() + PLAYER
                    .getThrowScore());
            if(PLAYER.getThrowScore() > 0) {
                showToast("score");
                newRound();
                PLAYER.setThrowScore(0);
            }
            if(PLAYER.getTotalScore() >= PLAYER.getWinScore()) {
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
    public class Dice1Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(1);
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class Dice2Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(2);
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class Dice3Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(3);
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class Dice4Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(4);
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class Dice5Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(5);
            calculateScore(false);
        }
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class Dice6Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            selectDice(6);
            calculateScore(false);
        }
    }
}

