package com.abhishekb.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    //0 = "x"
    //1 = "o"
    int activePlayer = 0;
    int[] gameState = {2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 };
    // State meanings->
    // 0 -> "X"
    // 1 -> "O"
    // 2 -> "NULL"

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
       // Toast.makeText(this,"You clicked "+tappedImage,Toast.LENGTH_SHORT).show();
        if(!gameActive)
        {
            gameReset();
        }
        if(gameState[tappedImage] ==2 && gameActive)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Player 2's TURN");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Player 1's TURN");
            }
            img.animate().translationYBy(1000f).setDuration(200);
        }
        //Check if any player won
        for(int[] winPosition : winPositions){
            String winnerStr;
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]]!=2)
            {
                if(gameState[winPosition[0]]==0)
                {
                    gameActive = false;
                    winnerStr = "Player 1 has won";
                }
                else{
                    gameActive = false;
                    winnerStr = "Player 2 has won";
                }
                //Update status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }

    private void gameReset() {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }
}