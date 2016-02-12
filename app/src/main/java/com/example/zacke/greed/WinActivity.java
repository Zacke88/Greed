package com.example.zacke.greed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Activity is shown after the player has reached the score to win the game
 * and is used to display a victory screen for the player
 */
public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        setWinText();
        setButtons();
    }

    /**
     * Sets the player victory text
     */
    public void setWinText() {
        TextView winText = (TextView) findViewById(R.id
                .winText);
        winText.setText("You got "+String.valueOf(getIntent().getStringExtra
                ("score"))
                +" " +
                "points after " +
                ""+String.valueOf(getIntent().getStringExtra
                ("rounds"))+" rounds.");
    }

    /**
     * Sets the buttons to corresponding button from the view
     */
    public void setButtons() {
        Button tryButton = (Button) findViewById(R.id.tryButton);
        tryButton.setOnClickListener(new TryListener());
    }

    /**
     * Listener for the dice image to make it selected
     */
    public class TryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(WinActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
