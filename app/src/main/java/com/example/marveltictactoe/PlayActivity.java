package com.example.marveltictactoe;

import static android.graphics.Color.LTGRAY;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    //board 1 is for checking the size of the piece that occupies a spot on the board
    private int[][] board1 = {{0,0,0}, {0,0,0}, {0,0,0}};
    //board 2 is for checking which player owns the spot, n stands for none
    private String[][] board2 = {{"n", "n", "n",}, {"n", "n", "n",}, {"n", "n", "n",}};

    //remainingPieces keeps track of how many pieces each player has left
    private int[][] remainingPieces = {{2,2,2}, {2,2,2,}};

    //these two strings represent the character types selected from settings
    private String player1Character, player2Character;

    //string indicating which player's turn it is
    private String currentPlayer = "player1";

    //integer indicating the size of the piece selected (1 is small, 2 is medium, and 3 is large)?
    private int currentPlayerSize = 0;

    //imageview of the current character selected
    private ImageView currentSelected;

    //button to go back to main screen and button to reset the game
    private Button back, reset;

    // Tictactoe grid section UI
    private Button square1, square2, square3, square4, square5, square6, square7, square8, square9;

    // Remaining pieces section UI
    private ImageView player1Big, player2Big, player1Medium,
                        player2Medium, player1Small, player2Small;
    private ImageView numPlayer1Big, numPlayer2Big, numPlayer1Medium,
                        numPlayer2Medium, numPlayer1Small, numPlayer2Small;

    //imageview showing the icon in the top right corner that shows whose turn it is
    private ImageView playerTurn;

    //resource id for image representing each player size
    private int player1BigImage, player1MediumImage, player1SmallImage, player2BigImage,
            player2MediumImage, player2SmallImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get player character information from MainActivity
        Intent intent = getIntent();
        player1Character = intent.getStringExtra("player1");
        player2Character = intent.getStringExtra("player2");

        /* ------- UI Objects ------- */
        // Back button
        back = (Button) findViewById(R.id.backButton);
        // Reset Button
        reset = (Button) findViewById(R.id.resetButton);
        // Tic tac tow grid square buttons
        square1 = (Button) findViewById(R.id.square1Button);
        square2 = (Button) findViewById(R.id.square2Button);
        square3 = (Button) findViewById(R.id.square3Button);
        square4 = (Button) findViewById(R.id.square4Button);
        square5 = (Button) findViewById(R.id.square5Button);
        square6 = (Button) findViewById(R.id.square6Button);
        square7 = (Button) findViewById(R.id.square7Button);
        square8 = (Button) findViewById(R.id.square8Button);
        square9 = (Button) findViewById(R.id.square9Button);
        // Remaining pieces pictures
        player1Big = (ImageView) findViewById(R.id.player1Big);
        player2Big = (ImageView) findViewById(R.id.player2Big);
        player1Medium = (ImageView) findViewById(R.id.player1Medium);
        player2Medium = (ImageView) findViewById(R.id.player2Medium);
        player1Small = (ImageView) findViewById(R.id.player1Small);
        player2Small = (ImageView) findViewById(R.id.player2Small);
        // Number of remaining pieces
        numPlayer1Big = (ImageView) findViewById(R.id.numPlayer1Big);
        numPlayer2Big = (ImageView) findViewById(R.id.numPlayer2Big);
        numPlayer1Medium = (ImageView) findViewById(R.id.numPlayer1Medium);
        numPlayer2Medium = (ImageView) findViewById(R.id.numPlayer2Medium);
        numPlayer1Small = (ImageView) findViewById(R.id.numPlayer1Small);
        numPlayer2Small = (ImageView) findViewById(R.id.numPlayer2Small);

        playerTurn = (ImageView) findViewById(R.id.playerTurn);

        /* ------- UI Setup ------- */
        // Set player character images based on selected character
        setImageViewCharacter(player1Big, player1Character);
        setImageViewCharacter(player1Medium, player1Character);
        setImageViewCharacter(player1Small, player1Character);
        setImageViewCharacter(player2Big, player2Character);
        setImageViewCharacter(player2Medium, player2Character);
        setImageViewCharacter(player2Small, player2Character);

        numPlayer1Big.setImageResource(R.drawable.two);
        numPlayer1Medium.setImageResource(R.drawable.two);
        numPlayer1Small.setImageResource(R.drawable.two);
        numPlayer2Big.setImageResource(R.drawable.two);
        numPlayer2Medium.setImageResource(R.drawable.two);
        numPlayer2Small.setImageResource(R.drawable.two);

        // Default select big player for player 1
        currentSelected = player1Big;
        selectCharacter(currentSelected);
        currentPlayer = "player1";
        setImageViewCharacter(playerTurn, player1Character);
        currentPlayerSize = 3;

        /* ------- On Click Listeners ------- */
        // Set on click listener for button and image view
        back.setOnClickListener(this);
        reset.setOnClickListener(this);
        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        square3.setOnClickListener(this);
        square4.setOnClickListener(this);
        square5.setOnClickListener(this);
        square6.setOnClickListener(this);
        square7.setOnClickListener(this);
        square8.setOnClickListener(this);
        square9.setOnClickListener(this);
        player1Big.setOnClickListener(this);
        player1Medium.setOnClickListener(this);
        player1Small.setOnClickListener(this);
        player2Big.setOnClickListener(this);
        player2Medium.setOnClickListener(this);
        player2Small.setOnClickListener(this);

        //setting characters for the first player
        if (player1Character.equals("cap")) {
            player1BigImage = R.drawable.cap_big;
            player1MediumImage = R.drawable.cap_medium;
            player1SmallImage = R.drawable.cap_small;

        } else if (player1Character.equals("ironman")) {
            player1BigImage = R.drawable.ironman_big;
            player1MediumImage = R.drawable.ironman_medium;
            player1SmallImage = R.drawable.ironman_small;
        }
        else if (player1Character.equals("hulk")) {
            player1BigImage = R.drawable.hulk_big;
            player1MediumImage = R.drawable.hulk_medium;
            player1SmallImage = R.drawable.hulk_small;
        }
        else if (player1Character.equals("thor")) {
            player1BigImage = R.drawable.thor_big;
            player1MediumImage = R.drawable.thor_medium;
            player1SmallImage = R.drawable.thor_small;
        }
        else if (player1Character.equals("widow")) {
            player1BigImage = R.drawable.widow_big;
            player1MediumImage = R.drawable.widow_medium;
            player1SmallImage = R.drawable.widow_small;
        }
        //setting characters for the second player
        if (player2Character.equals("cap")) {
            player2BigImage = R.drawable.cap_big;
            player2MediumImage = R.drawable.cap_medium;
            player2SmallImage = R.drawable.cap_small;

        } else if (player2Character.equals("ironman")) {
            player2BigImage = R.drawable.ironman_big;
            player2MediumImage = R.drawable.ironman_medium;
            player2SmallImage = R.drawable.ironman_small;
        }
        else if (player2Character.equals("hulk")) {
            player2BigImage = R.drawable.hulk_big;
            player2MediumImage = R.drawable.hulk_medium;
            player2SmallImage = R.drawable.hulk_small;
        }
        else if (player2Character.equals("thor")) {
            player2BigImage = R.drawable.thor_big;
            player2MediumImage = R.drawable.thor_medium;
            player2SmallImage = R.drawable.thor_small;
        }
        else if (player2Character.equals("widow")) {
            player2BigImage = R.drawable.widow_big;
            player2MediumImage = R.drawable.widow_medium;
            player2SmallImage = R.drawable.widow_small;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.backButton) {
            backToMainActivity();
        }

        if (id == R.id.square1Button) {
            updateSquare(0,0, square1);
        } else if (id == R.id.square2Button) {
            updateSquare(0, 1, square2);
        }  else if (id == R.id.square3Button) {
            updateSquare(0, 2, square3);
        }  else if (id == R.id.square4Button) {
            updateSquare(1, 0, square4);
        }  else if (id == R.id.square5Button) {
            updateSquare(1, 1, square5);
        }  else if (id == R.id.square6Button) {
            updateSquare(1, 2, square6);
        }  else if (id == R.id.square7Button) {
            updateSquare(2, 0, square7);
        }  else if (id == R.id.square8Button) {
            updateSquare(2, 1, square8);
        }  else if (id == R.id.square9Button) {
            updateSquare(2, 2, square9);
        }

        if (id == R.id.resetButton) {
            reset();
        }

        else {
            /* --- Determine if new character was selected --- */
            ImageView newSelected = currentSelected;

            // If player 1's turn and selected player 1 piece clicked
            // then change currentSelected
            if (currentPlayer == "player1") {
                if (id == R.id.player1Big) {
                    newSelected = player1Big;
                    currentPlayerSize = 3;
                } else if (id == R.id.player1Medium) {
                    newSelected = player1Medium;
                    currentPlayerSize = 2;
                } else if (id == R.id.player1Small) {
                    newSelected = player1Small;
                    currentPlayerSize = 1;
                }
            }
            // If player 2's turn and selected player 2 piece clicked
            // then change currentSelected
            else if (currentPlayer == "player2") {
                if (id == R.id.player2Big) {
                    newSelected = player2Big;
                    currentPlayerSize = 3;
                } else if (id == R.id.player2Medium) {
                    newSelected = player2Medium;
                    currentPlayerSize = 2;
                } else if (id == R.id.player2Small) {
                    newSelected = player2Small;
                    currentPlayerSize = 1;
                }
            }
            // If valid new character clicked
            if (!(newSelected.equals(currentSelected))) {
                Log.i("changing", "new");
                deselectCharacter(currentSelected);
                currentSelected = newSelected;
                selectCharacter(currentSelected);
            }
        }
    }

    public void backToMainActivity() {
        finish();
    }

    // Set ImageView picture to character given
    public void setImageViewCharacter(ImageView view, String character) {
        if (character.equals("ironman")) {
            view.setImageResource(R.drawable.ironman);
        } else if (character.equals("cap")) {
            view.setImageResource(R.drawable.cap);
        } else if (character.equals("thor")) {
            view.setImageResource(R.drawable.thor);
        } else if (character.equals("widow")) {
            view.setImageResource(R.drawable.widow);
        } else if (character.equals("hulk")) {
            view.setImageResource(R.drawable.hulk);
        }
    }

    //updates button to the correct picture depending on which character was selected
    public void setButtonCharacter(Button myButton, ImageView character, int x, int y) {
        //checking what size the character is and updating the count for pieces remaining
        if (character.equals(player1Big) && remainingPieces[0][0] > 0) {
            myButton.setBackgroundResource(player1BigImage);
            board1[x][y] = 3;
            updateCount(numPlayer1Big);
            flipTurn();
        } else if (character.equals(player1Medium) && remainingPieces[0][1] > 0) {
            myButton.setBackgroundResource(player1MediumImage);
            board1[x][y] = 2;
            updateCount(numPlayer1Medium);
            flipTurn();
        } else if (character.equals(player1Small) && remainingPieces[0][2] > 0) {
            myButton.setBackgroundResource(player1SmallImage);
            board1[x][y] = 1;
            updateCount(numPlayer1Small);
            flipTurn();
        } else if (character.equals(player2Big) && remainingPieces[1][0] > 0) {
            myButton.setBackgroundResource(player2BigImage);
            board1[x][y] = 3;
            updateCount(numPlayer2Big);
            flipTurn();
        } else if (character.equals(player2Medium) && remainingPieces[1][1] > 0) {
            myButton.setBackgroundResource(player2MediumImage);
            board1[x][y] = 2;
            updateCount(numPlayer2Medium);
            flipTurn();
        } else if (character.equals(player2Small) && remainingPieces[1][2] > 0) {
            myButton.setBackgroundResource(player2SmallImage);
            board1[x][y] = 1;
            updateCount(numPlayer2Small);
            flipTurn();
        }
    }

        //method that selects character and shows which character is selected by adding border
        public void selectCharacter(ImageView view) {
        view.setBackgroundResource(R.drawable.characterborder);
    }

    //method that deselects character
    public void deselectCharacter(ImageView view) {
        view.setBackgroundResource(0);
    }

    //method that flips the player's turn and default selects the biggest charcter
    public void flipTurn (){
        deselectCharacter(currentSelected);
        if (currentPlayer.equals("player1")){
            currentPlayer = "player2";
            currentSelected = player2Big;
            setImageViewCharacter(playerTurn, player2Character);
        }
        else {
            currentPlayer = "player1";
            currentSelected = player1Big;
            setImageViewCharacter(playerTurn, player1Character);
        }
        selectCharacter(currentSelected);
        currentPlayerSize = 3;
    }

    //this method updates the board piece with the character
    public void updateSquare (int x, int y, Button square){
        //checking if the size of the character is larger than any size of character on the board
        if (board1[x][y] < currentPlayerSize){
            setButtonCharacter(square, currentSelected, x, y);
            //updating the array to indicate who owns the piece
            if (currentPlayer.equals("player1")) {
                board2[x][y] = "a";
            }
            else {
                board2[x][y] = "b";
            }
            win();
        }


   }

   //method that updates the count of the number of pieces remaining foe each type of character
   public void updateCount (ImageView view){
        //checking how many pieces are left of each type and lowering it by one
        if (view.equals(numPlayer1Big)){
            if (remainingPieces[0][0] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[0][0]--;
            } else if (remainingPieces[0][0] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[0][0]--;
            }
        } else if (view.equals(numPlayer1Medium)) {
            if (remainingPieces[0][1] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[0][1]--;
            } else if (remainingPieces[0][1] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[0][1]--;
            }
        } else if (view.equals(numPlayer1Small)) {
            if (remainingPieces[0][2] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[0][2]--;
            } else if (remainingPieces[0][2] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[0][2]--;
            }
        } else if (view.equals(numPlayer2Big)) {
            if (remainingPieces[1][0] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[1][0]--;
            } else if (remainingPieces[1][0] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[1][0]--;
            }
        } else if (view.equals(numPlayer2Medium)) {
            if (remainingPieces[1][1] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[1][1]--;
            } else if (remainingPieces[1][1] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[1][1]--;
            }
        } else if (view.equals(numPlayer2Small)) {
            if (remainingPieces[1][2] == 2){
                view.setImageResource(R.drawable.one);
                remainingPieces[1][2]--;
            } else if (remainingPieces[1][2] == 1){
                view.setImageResource(R.drawable.zero);
                remainingPieces[1][2]--;
            }
        }
   }

   //method that checks if anyone has won yet or if no more pieces can be played
   public void win () {
        //checking if there are any three pieces in a row yet
       if ((board2[0][0] == board2[0][1]) && (board2[0][0] == board2[0][2]) && (board2[0][0] != "n")) {
           winDialog(board2[0][0]);
       } else if (board2[1][0] == board2[1][1] && board2[1][0] == board2[1][2] && board2[1][0] != "n") {
           winDialog(board2[1][0]);
       } else if (board2[2][0] == board2[2][1] && board2[2][0] == board2[2][2] && board2[2][0] != "n") {
           winDialog(board2[2][0]);
       } else if (board2[0][0] == board2[1][0] && board2[0][0] == board2[2][0] && board2[0][0] != "n") {
           winDialog(board2[0][0]);
       } else if (board2[0][1] == board2[1][1] && board2[0][1] == board2[2][1] && board2[0][1] != "n") {
           winDialog(board2[0][1]);
       } else if (board2[0][2] == board2[1][2] && board2[0][2] == board2[2][2] && board2[0][2] != "n") {
           winDialog(board2[0][2]);
       } else if (board2[0][0] == board2[1][1] && board2[0][0] == board2[2][2] && board2[0][0] != "n") {
           winDialog(board2[0][0]);
       } else if (board2[0][2] == board2[1][1] && board2[0][2] == board2[2][0] && board2[0][2] != "n") {
           winDialog(board2[0][2]);
       //checking if the player whose turn it is has run out of pieces to play
       } else if (currentPlayer.equals("player1") && remainingPieces[0][0] == 0 && remainingPieces[0][1] ==0 && remainingPieces[0][2] ==0) {
           tieDialog();
       } else if (currentPlayer.equals("player2") && remainingPieces[1][0] == 0 && remainingPieces[1][1] ==0 && remainingPieces[1][2] ==0) {
           tieDialog();
       }

   }

   //pop up message indicating someone has won, and who has won
   public void winDialog (String whoWon){
       String winner = " ";
        if (whoWon.equals("a")){
            winner = "Player 2";
        } else if (whoWon.equals("b")) {
            winner = "Player 1";
        }
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Game Over");
       builder.setMessage(winner + " won the game!");
       builder.setCancelable(false);
       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               // Handle button click (optional)
               // You can perform any desired action here
           }
       });
       AlertDialog alertDialog = builder.create();
       alertDialog.show();
   }

   //pop up message for a draw game
    public void tieDialog (){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage("It was a tie, nobody won the game!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle button click (optional)
                // You can perform any desired action here
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //method for reseting the game that sets all the initial conditions again
    public void reset (){
       deselectCharacter(currentSelected);
       currentSelected = player1Big;
       selectCharacter(currentSelected);
       currentPlayer = "player1";
       setImageViewCharacter(playerTurn, player1Character);
       currentPlayerSize = 3;
       //resetting the arrays
       for (int i = 0; i < board1.length; i++){
           for (int j = 0; j < board1[i].length; j++){
             board1 [i][j] = 0;
          }
       }
       for (int i = 0; i < board2.length; i++){
          for (int j = 0; j < board2[i].length; j++){
               board2 [i][j] = "n";
          }
       }
       for (int i = 0; i < remainingPieces.length; i++){
           for (int j = 0; j < remainingPieces[i].length; j++){
              remainingPieces [i][j] = 2;
           }
       }

       numPlayer1Big.setImageResource(R.drawable.two);
       numPlayer1Medium.setImageResource(R.drawable.two);
       numPlayer1Small.setImageResource(R.drawable.two);
       numPlayer2Big.setImageResource(R.drawable.two);
       numPlayer2Medium.setImageResource(R.drawable.two);
       numPlayer2Small.setImageResource(R.drawable.two);

       square1.setBackgroundResource(R.drawable.logo);
       square2.setBackgroundResource(R.drawable.logo);
       square3.setBackgroundResource(R.drawable.logo);
       square4.setBackgroundResource(R.drawable.logo);
       square5.setBackgroundResource(R.drawable.logo);
       square6.setBackgroundResource(R.drawable.logo);
       square7.setBackgroundResource(R.drawable.logo);
       square8.setBackgroundResource(R.drawable.logo);
       square9.setBackgroundResource(R.drawable.logo);



   }

}