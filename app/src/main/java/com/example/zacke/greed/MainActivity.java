package com.example.zacke.greed;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * MainActivity for the game Greed, it handles the game rules and logic
 */
public class MainActivity extends AppCompatActivity {

    private ImageView dice1Image;
    private ImageView dice2Image;
    private ImageView dice3Image;
    private ImageView dice4Image;
    private ImageView dice5Image;
    private ImageView dice6Image;

    private ArrayList<Dice> diceList = new ArrayList<>();

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
        setButtons();
        setScoreText();
        updateDiceImages();
    }

    /**
     * Sets the dices as ImageViews of the application and scales them
     * accordingly
     */
    public void setDices() {
        if(PLAYER.getDiceList().isEmpty()) {
            diceList.add(DICE1);
            diceList.add(DICE2);
            diceList.add(DICE3);
            diceList.add(DICE4);
            diceList.add(DICE5);
            diceList.add(DICE6);
            PLAYER.setDiceList(diceList);
        }

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
     * Sets the buttons to corresponding button and dice from the view and adds
     * listeners to them
     */
    public void setButtons() {
        Button scoreButton = (Button) findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(new ScoreListener());
        Button throwButton = (Button) findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new ThrowListener());
        dice1Image.setOnClickListener(new Dice1Listener());
        dice2Image.setOnClickListener(new Dice2Listener());
        dice3Image.setOnClickListener(new Dice3Listener());
        dice4Image.setOnClickListener(new Dice4Listener());
        dice5Image.setOnClickListener(new Dice5Listener());
        dice6Image.setOnClickListener(new Dice6Listener());
    }

    /**
     * Sets the player score text of the application based on how the screen is
     * oriented
     */
    public void setScoreText() {
        TextView roundScorePortrait = (TextView) findViewById(R.id.roundScorePortrait);
        roundScorePortrait.setText("");
        TextView totalScorePortrait = (TextView) findViewById(R.id.totalScorePortrait);
        totalScorePortrait.setText("");
        TextView roundScoreLandscape = (TextView) findViewById(R.id.roundScoreLandscape);
        roundScoreLandscape.setText("");
        TextView totalScoreLandscape = (TextView) findViewById(R.id.totalScoreLandscape);
        totalScoreLandscape.setText("");

        if (getResources().getConfiguration().orientation == 1) {
            roundScorePortrait.setText(String.valueOf(PLAYER.getRoundScore()
                    + " (+" + (PLAYER.getRoundScore() - PLAYER.getPreviousRoundScore()) + ")"));
            totalScorePortrait.setText(String.valueOf(PLAYER.getTotalScore()));
        } else {
            roundScoreLandscape.setText(String.valueOf(PLAYER.getRoundScore()
                    + " (+" + (PLAYER.getRoundScore() - PLAYER.getPreviousRoundScore()) + ")"));
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
        updateDiceImages();
        calculateScore(true);
    }

    /**
     * Calculates the score a user gets on one throw for the selected dices
     *
     * @param checkCriteria boolean which is set to true to check if a
     *                      criteria for a round is met, ie to get at least
     *                      300 score first throw or to get any score throws
     *                      after
     */
    public void calculateScore(boolean checkCriteria) {
        resetDiceState();
        PLAYER.setPreviousRoundScore(PLAYER.getRoundScore());

        // Calculate score for the thrown dices
        if(checkCriteria) {
            for (Dice dice : PLAYER.getDiceList()) {
                if (dice.getDiceValue() == 1 && dice.isDiceActive()) {
                    ones++;
                }
                if (dice.getDiceValue() == 2 && dice.isDiceActive()) {
                    twos++;
                }
                if (dice.getDiceValue() == 3 && dice.isDiceActive()) {
                    threes++;
                }
                if (dice.getDiceValue() == 4 && dice.isDiceActive()) {
                    fours++;
                }
                if (dice.getDiceValue() == 5 && dice.isDiceActive()) {
                    fives++;
                }
                if (dice.getDiceValue() == 6 && dice.isDiceActive()) {
                    sixes++;
                }
            }
        }
        // Calculate score of dices selected by the player
        else {
            for (Dice dice : PLAYER.getDiceList()) {
                if (dice.getDiceValue() == 1 && dice.isDiceSelected()) {
                    ones++;
                }
                if (dice.getDiceValue() == 2 && dice.isDiceSelected()) {
                    twos++;
                }
                if (dice.getDiceValue() == 3 && dice.isDiceSelected()) {
                    threes++;
                }
                if (dice.getDiceValue() == 4 && dice.isDiceSelected()) {
                    fours++;
                }
                if (dice.getDiceValue() == 5 && dice.isDiceSelected()) {
                    fives++;
                }
                if (dice.getDiceValue() == 6 && dice.isDiceSelected()) {
                    sixes++;
                }
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
            PLAYER.setRoundScore(PLAYER.getRoundScore() + (600 * 2));
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

        if(checkCriteria) {
            setActiveDices();
            setScoreText();
            //Starts a new round if criteria for round score not met
            boolean failedRound = false;
            if(PLAYER.getRoundScore() < 300 && PLAYER.isFirstRound()) {
                PLAYER.setRoundScore(0);
                failedRound = true;
                Toast.makeText(this, "Failed to reach 300 on first throw, " +
                        "starting new round)", Toast.LENGTH_SHORT).show();
            } else if(PLAYER.getRoundScore() == 0 || PLAYER.getRoundScore() ==
                    PLAYER.getPreviousRoundScore()) {
                PLAYER.setRoundScore(0);
                failedRound = true;
                Toast.makeText(this, "Failed to score on this throw, " +
                        "starting new round)", Toast.LENGTH_SHORT).show();
            }
            PLAYER.setFirstRound(false);
            if(failedRound) {
                newRound();
            }
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
    }

    /**
     * Sets all dices that was giving score to inactive and updates their
     * images to make them appear white
     */
    public void setActiveDices() {
        //Straight or six of a kind
        if (straight || sixOfAKind) {
            for(Dice dice: PLAYER.getDiceList()) {
                dice.setDiceActive(false);
                if (dice.getDiceValue() == 1) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white1));
                } else if (dice.getDiceValue() == 2) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white2));
                } else if (dice.getDiceValue() == 3) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white3));
                } else if (dice.getDiceValue() == 4) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white4));
                } else if (dice.getDiceValue() == 5) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white5));
                } else if (dice.getDiceValue() == 6) {
                    dice.setDiceImage(BitmapFactory.decodeResource(this
                                    .getResources(),
                            R.drawable.white6));
                }
            }
        } else {
            //Three of ones
            int diceCount;
            if (ones >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 1 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white1));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
            //Three of twos
            if (twos >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 2 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white2));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
            //Three of threes
            if (threes >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 3 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white3));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
            //Three of fours
            if (fours >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 4 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white4));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
            //Three of fives
            if (fives >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 5 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white5));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
            //Three of sixes
            if (sixes >= 3) {
                diceCount = 0;
                for (Dice dice : PLAYER.getDiceList()) {
                    if (dice.getDiceValue() == 6 && diceCount < 3) {
                        dice.setDiceImage(BitmapFactory.decodeResource(this
                                        .getResources(),
                                R.drawable.white6));
                        dice.setDiceActive(false);
                        diceCount++;
                    }
                }
            }
        }

        // Loose one or fives
        for(Dice dice: PLAYER.getDiceList()) {
            if(dice.getDiceValue() == 1 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.white1));
                dice.setDiceActive(false);
            }
            if(dice.getDiceValue() == 5 && dice.isDiceActive()) {
                dice.setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.white5));
                dice.setDiceActive(false);
            }
        }
        updateDiceImages();
    }

    /**
     * Updates the images for all dices which shows if they are active or
     * selected
     */
    public void updateDiceImages() {
        dice1Image.setImageBitmap(DICE1.getDiceImage());
        dice2Image.setImageBitmap(DICE2.getDiceImage());
        dice3Image.setImageBitmap(DICE3.getDiceImage());
        dice4Image.setImageBitmap(DICE4.getDiceImage());
        dice5Image.setImageBitmap(DICE5.getDiceImage());
        dice6Image.setImageBitmap(DICE6.getDiceImage());
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
        PLAYER.setPreviousRoundScore(0);
        setScoreText();
        PLAYER.setFirstRound(true);
        for(Dice dice: PLAYER.getDiceList()) {
            dice.setDiceActive(true);
            dice.setDiceSelected(false);
        }
    }

    /**
     * Gets the value of a dice and checks if it's active or not then selects
     * it making it red and is then used to calculate how much score a user gets
     *
     * @param dice which dice to select
     */
    public void selectDice(int dice) {
        if(dice == 1 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        } else if(dice == 2 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        } else if(dice == 3 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        } else if(dice == 4 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        } else if(dice == 5 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        } else if(dice == 6 && !diceList.get(dice-1).isDiceActive()) {
            diceList.get(dice-1).setDiceSelected(true);
            if (diceList.get(dice-1).getDiceValue() == 1) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red1));
            } else if (diceList.get(dice-1).getDiceValue() == 2) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red2));
            } else if (diceList.get(dice-1).getDiceValue() == 3) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red3));
            } else if (diceList.get(dice-1).getDiceValue() == 4) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red4));
            } else if (diceList.get(dice-1).getDiceValue() == 5) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red5));
            } else if (diceList.get(dice-1).getDiceValue() == 6) {
                diceList.get(dice-1).setDiceImage(BitmapFactory.decodeResource(this
                                .getResources(),
                        R.drawable.red6));
            }
        }
        updateDiceImages();
    }

    /**
     * Listener for the throw Button which calls a method to throw all
     * active dices. Also makes all unselected dices active, meaning they
     * will be thrown again
     */
    public class ThrowListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            for(Dice dice: PLAYER.getDiceList()) {
                if(!dice.isDiceSelected()) {
                    dice.setDiceActive(true);
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
            newRound();
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

