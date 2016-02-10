package com.example.zacke.greed;

import android.graphics.Bitmap;

/**
 * Created by Zacke on 2016-02-10.
 */
public class Dice {

    private int diceValue;
    private boolean diceActive;
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
}
