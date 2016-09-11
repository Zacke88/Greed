package com.example.zacke.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the current player handling scores and amount of
 * rounds. Also holds a list with all dices for the game
 * Should be static final so it won't reset stats with activity being destroyed
 *
 * @author Joakim Zakrisson
 * @version 2016-02-10
 */
public class Player {

    private int roundScore = 0;
    private int throwScore = 0;
    private int maxRoundScore = 0;
    private int totalScore = 0;
    private int rounds = 1;
    private int winScore = 10000;
    private List<Die> dieList = new ArrayList<>();
    private boolean firstRound = true;

    /**
     *
     * @return The roundS sore as an integer
     */
    public int getRoundScore() {
        return roundScore;
    }

    /**
     *
     * @param roundScore Integer which sets the round score
     */
    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    /**
     *
     * @return The total round score
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     *
     * @param totalScore Integer which is used to set the total score
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    /**
     *
     * @return The number of rounds played
     */
    public int getRounds() {
        return rounds;
    }

    /**
     *
     * @param rounds Sets the number of rounds played
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     *
     * @return A list with all the dice which is currently used with this player
     */
    public List<Die> getDieList() {
        return dieList;
    }

    /**
     *
     * @return Boolean which is true if it's the first round, else false
     */
    public boolean isFirstRound() {
        return firstRound;
    }

    /**
     *
     * @param firstRound Sets the current round to the first round using a
     *                   boolean
     */
    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    /**
     *
     * @return Gets the maximum round score obtained
     */
    public int getMaxRoundScore() {
        return maxRoundScore;
    }

    /**
     *
     * @param maxRoundScore Sets the maximum round score that can be obtained
     */
    public void setMaxRoundScore(int maxRoundScore) {
        this.maxRoundScore = maxRoundScore;
    }

    /**
     *
     * @return Gets the score that the current throw generated
     */
    public int getThrowScore() {
        return throwScore;
    }

    /**
     *
     * @param throwScore Sets the score that the current throw can generate
     */
    public void setThrowScore(int throwScore) {
        this.throwScore = throwScore;
    }

    /**
     *
     * @return The score needed to win the game
     */
    public int getWinScore() {
        return winScore;
    }
}
