package com.example.zacke.greed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Zacke on 2016-02-10.
 *
 * Activity is shown after the player has reached the score to win the game
 * and is used to display a victory screen for the player
 */
public class WinActivity extends AppCompatActivity {

    /**
     * This method is run together with the activity which should be when the
     * game is won and is used to update the action bar, text and buttons to
     * display a victory screen for the player
     *
     * @param savedInstanceState The last saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("  Greed");
            actionBar.setLogo(R.drawable.coin);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        setWinText();
        setButtons();
    }

    /**
     * Sets the player victory text which it gets as data from the
     * MainActivity class when starting this intent
     */
    public void setWinText() {
        TextView winText = (TextView) findViewById(R.id
                .winText);
        winText.setText("You got "+String.valueOf(getIntent()
                .getStringExtra
                ("score"))
                +" " +
                "points after "+String.valueOf(getIntent()
                .getStringExtra
                ("rounds"))+" rounds.");
        ImageView winImage = (ImageView) findViewById(R.id.winImage);
        winImage.setImageResource(R.drawable.good);
    }

    /**
     * Sets the buttons to corresponding button from the view
     */
    public void setButtons() {
        Button tryButton = (Button) findViewById(R.id.tryButton);
        tryButton.setOnClickListener(new TryListener());
    }

    /**
     * Listener for the "Try again" button which resets the current
     * application and starts a new MainActivity
     */
    public class TryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(WinActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            System.exit(0);
            startActivity(intent);
        }
    }
}
