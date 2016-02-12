package com.example.zacke.greed;

import java.util.ArrayList;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Class that represents the current player handling scores and amount of
 * rounds. Also holds a list with all dices for the game
 * Should be static final so it won't reset stats with activity being destroyed
 */
public class Player {

    private int roundScore = 0;
    private int previousRoundScore = 0;
    private int totalScore = 0;
    private int rounds = 1;
    private ArrayList<Dice> diceList = new ArrayList<>();
    private boolean firstRound = true;

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(ArrayList<Dice> diceList) {
        this.diceList = diceList;
    }

    public int getPreviousRoundScore() {
        return previousRoundScore;
    }

    public void setPreviousRoundScore(int previousRoundScore) {
        this.previousRoundScore = previousRoundScore;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }
}
