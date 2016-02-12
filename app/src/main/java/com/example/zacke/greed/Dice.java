package com.example.zacke.greed;

import android.graphics.Bitmap;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Class that represents a dice and holds values for if a dice is selected or
 * active. Also holds a bitmap for the image and a value for the dice
 * Should be static final so it won't reset stats with activity being destroyed
 */
public class Dice {

    private int diceValue;
    private boolean diceActive = true;
    private boolean diceSelected = false;
    private Bitmap diceImage;

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public boolean isDiceActive() {
        return diceActive;
    }

    public void setDiceActive(boolean diceActive) {
        this.diceActive = diceActive;
    }

    public Bitmap getDiceImage() {
        return diceImage;
    }

    public void setDiceImage(Bitmap diceImage) {
        this.diceImage = diceImage;
    }

    public boolean isDiceSelected() {
        return diceSelected;
    }

    public void setDiceSelected(boolean diceSelected) {
        this.diceSelected = diceSelected;
    }
}
