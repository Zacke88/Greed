package com.example.zacke.greed;

import java.util.ArrayList;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Class that represents the current player handling the  name and scores.
 * Should be static so it won't reset stats with phone rotation
 */
public class Player {

    private int roundScore = 0;
    private int totalScore = 0;
    private int rounds = 1;
    private ArrayList<Dice> diceList = new ArrayList<>();

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
}
