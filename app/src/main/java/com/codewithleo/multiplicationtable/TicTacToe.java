package com.codewithleo.multiplicationtable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TicTacToe extends AppCompatActivity implements  View.OnClickListener {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8;
    TextView header_text;
    int Player_o = 0;
    int Player_x = 1;

    int ActivePlayer = Player_o;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameActive = true;                 //set default truee

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tic_tac_toe);
        header_text = findViewById(R.id.header_text);
        header_text.setText("O Turn");

        header_text = findViewById(R.id.header_text);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        //Logic for button press
        if (!isGameActive)
            return;

        Button clickedbutton = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickedTag] != -1) {
            return;
        }

        filledPos[clickedTag] = ActivePlayer;

        if (ActivePlayer == Player_o) {
            clickedbutton.setText("o");
            clickedbutton.setBackground(getDrawable(android.R.color.holo_blue_bright));
            ActivePlayer = Player_x;
            header_text.setText("X's Turn!");
        } else {
            clickedbutton.setText("x");
            clickedbutton.setBackground(getDrawable(android.R.color.holo_orange_light));
            ActivePlayer = Player_o;
            header_text.setText("O's Turn");
        }

        checkforWin();
    }

    private void checkforWin() {
        //we will check the winner and show
        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];
            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    isGameActive = false;
                    if (filledPos[val0] == Player_o)
                        showDialog("O is Winner!");
                    else
                        showDialog("X is Winner!!");
                }
                //winner declared

            }
        }
    }

    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("RESTART GAME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }

    private void restartGame() {
        ActivePlayer = Player_o;
        header_text.setText("O turn!");
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        isGameActive = true;
    }

    @Override
    public void onBackPressed() {
        //Dialog box for exit
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)  // dialog box tabtak nahi hatega jabtak yes or no pe click nahi hoga
                //if yes then finish
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}