package com.example.marveltictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView player1Character;
    private ImageView player2Character;
    private String player1StringName;
    private String player2StringName;

    // Buttons
    private Button back;

    // ImageViews
    private ImageView ironman1, ironman2, cap1, cap2,
            thor1, thor2, widow1, widow2, hulk1, hulk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* ------- UI Objects ------- */
        // Connect buttons to UI
        ironman1 = (ImageView) findViewById(R.id.ironman1Button);
        ironman2 = (ImageView) findViewById(R.id.ironman2Button);
        cap1 = (ImageView) findViewById(R.id.cap1Button);
        cap2 = (ImageView) findViewById(R.id.cap2Button);
        thor1 = (ImageView) findViewById(R.id.thor1Button);
        thor2 = (ImageView) findViewById(R.id.thor2Button);
        widow1 = (ImageView) findViewById(R.id.widow1Button);
        widow2 = (ImageView) findViewById(R.id.widow2Button);
        hulk1 = (ImageView) findViewById(R.id.hulk1Button);
        hulk2 = (ImageView) findViewById(R.id.hulk2Button);
        back = (Button) findViewById(R.id.backButton);

        /* ------- Setup UI ------- */
        // Initially select characters sent by main
        Intent intent = getIntent();
        player1StringName = intent.getStringExtra("player1");
        player2StringName = intent.getStringExtra("player2");
        player1Character = getCharacterImageView(player1StringName, "player1");
        player2Character = getCharacterImageView(player2StringName, "player2");
        selectCharacter(player1Character);
        selectCharacter(player2Character);

        /* ------- On Click Listeners ------- */
        // Set same on click for each button
        ironman1.setOnClickListener(this);
        ironman2.setOnClickListener(this);
        cap1.setOnClickListener(this);
        cap2.setOnClickListener(this);
        thor1.setOnClickListener(this);
        thor2.setOnClickListener(this);
        widow1.setOnClickListener(this);
        widow2.setOnClickListener(this);
        hulk1.setOnClickListener(this);
        hulk2.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    // common onClick() for all buttons
    @Override
    public void onClick(View v) {
        int id = v.getId();
        String newPlayer1StringName = player1StringName;
        String newPlayer2StringName = player2StringName;

        // If back button, set character chosen results, end activity and go to main screen
        if (id == R.id.backButton) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("player1", player1StringName);
            resultIntent.putExtra("player2", player2StringName);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
        // Determine which character was clicked
        else if (id == R.id.ironman1Button) {
            newPlayer1StringName = "ironman";
        }
        else if (id == R.id.cap1Button) {
            newPlayer1StringName = "cap";
        }
        else if (id == R.id.thor1Button) {
            newPlayer1StringName = "thor";
        }
        else if (id == R.id.widow1Button) {
            newPlayer1StringName = "widow";
        }
        else if (id == R.id.hulk1Button) {
            newPlayer1StringName = "hulk";
        }
        else if (id == R.id.ironman2Button) {
            newPlayer2StringName = "ironman";
        }
        else if (id == R.id.cap2Button) {
            newPlayer2StringName = "cap";
        }
        else if (id == R.id.thor2Button) {
            newPlayer2StringName = "thor";
        }
        else if (id == R.id.widow2Button) {
            newPlayer2StringName = "widow";
        }
        else if (id == R.id.hulk2Button) {
            newPlayer2StringName = "hulk";
        }

        // If the new player character is not equal to the old or the other players character
        // then change character selection
        if (newPlayer1StringName != player1StringName && newPlayer1StringName != player2StringName) {
            deselectCharacter(player1Character);
            player1StringName = newPlayer1StringName;
            player1Character = getCharacterImageView(player1StringName, "player1");
            selectCharacter(player1Character);
        }
        if (newPlayer2StringName != player2StringName && newPlayer2StringName != player1StringName) {
            deselectCharacter(player2Character);
            player2StringName = newPlayer2StringName;
            player2Character = getCharacterImageView(player2StringName, "player2");
            selectCharacter(player2Character);
        }
    }

    // To select character add border background
    public void selectCharacter(ImageView view) {
        view.setBackgroundResource(R.drawable.characterborder);
    }

    // To deselect character remove any background
    public void deselectCharacter(ImageView view) {
        view.setBackgroundResource(0);
    }

    // Using string of character name and player number, get the associated image view
    public ImageView getCharacterImageView(String playerStringName, String player) {
        if (player.equals("player1")) {
            if (playerStringName.equals("ironman")) {
                return ironman1;
            } else if (playerStringName.equals("cap")) {
                return cap1;
            } else if (playerStringName.equals("thor")) {
                return thor1;
            } else if (playerStringName.equals("widow")) {
                return widow1;
            } else if (playerStringName.equals("hulk")) {
                return hulk1;
            }
        } else if (player.equals("player2")) {
            if (playerStringName.equals("ironman")) {
                return ironman2;
            } else if (playerStringName.equals("cap")) {
                return cap2;
            } else if (playerStringName.equals("thor")) {
                return thor2;
            } else if (playerStringName.equals("widow")) {
                return widow2;
            } else if (playerStringName.equals("hulk")) {
                return hulk2;
            }
        }
        return null;
    }
}