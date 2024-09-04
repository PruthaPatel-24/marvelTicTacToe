package com.example.marveltictactoe;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Main screen buttons
    private Button playButton, settingsButton, instructionsButton;
    private String player1 = "ironman";
    private String player2 = "cap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to UI
        playButton = (Button) findViewById(R.id.playButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        instructionsButton = (Button) findViewById(R.id.instructionsButton);

        // Set same on click listener for buttons
        playButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        instructionsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.playButton) {
            openPlayActivity();
        } else if (id == R.id.settingsButton) {
            openSettingsActivity();
        } else if (id == R.id.instructionsButton) {
            openInstructionsActivity();
        }
    }

    public void openPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        startActivity(intent);
    }

    public void openInstructionsActivity (){
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    ActivityResultLauncher<Intent> settingsActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent resultData = result.getData();
                        player1 = resultData.getStringExtra("player1");
                        player2 = resultData.getStringExtra("player2");
                        Log.i("player2", player2);
                        Log.i("player1", player1);
                    }
                }
            }
    );

    public void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        settingsActivityResultLauncher.launch(intent);
    }

}