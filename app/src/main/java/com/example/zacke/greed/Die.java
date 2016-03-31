package com.example.zacke.greed;

import android.graphics.Bitmap;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Class that represents a dice and holds values for if a dice is selected or
 * active. Also holds a bitmap for the image and a value for the dice
 * Should be static final so it won't reset stats with activity being destroyed
 */
public class Die {

    private int value;
    private boolean active = true;
    private boolean selected = false;
    private boolean selectable = false;
    private Bitmap image;

    /**
     *
     * @return the current value of the die
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param diceValue value to set the die to
     */
    public void setValue(int diceValue) {
        this.value = diceValue;
    }

    /**
     *
     * @return Boolean which is true if the die is active and false if it's
     * inactive
     */
    public boolean isActive() {
        return active;
    }

    /**
     * sets the die to active
     */
    public void activate() {
        active = true;
    }

    /**
     * sets the die to inactive
     */
    public void deactivate() {
        active = false;
    }

    /**
     *
     * @return the image for the die as a Bitmap
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     *
     * @param diceImage the image for the die as Bitmap
     */
    public void setImage(Bitmap diceImage) {
        this.image = diceImage;
    }

    /**
     *
     * @return Boolean which is true if the die is selected, else false
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the die to being selected
     */
    public void select() {
        selected = true;
    }

    /**
     * Sets the die to not being selected
     */
    public void deselect() {
        selected = false;
    }

    /**
     *
     * @return Boolean which is true if the die is selectable, else false.
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     *
     * @param diceSelectable Boolean for if the die is selectable or not
     */
    public void setSelectable(boolean diceSelectable) {
        this.selectable = diceSelectable;
    }
}
