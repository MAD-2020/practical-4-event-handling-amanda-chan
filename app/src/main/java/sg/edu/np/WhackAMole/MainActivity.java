package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonLeft;
    private Button buttonMiddle;
    private Button buttonRight;
    private TextView Score;
    int scoreNum = 0;
    final String TAG = "Whack-A-Mole";

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = (Button) findViewById(R.id.Button1);
        buttonMiddle = (Button) findViewById(R.id.Button2);
        buttonRight = (Button) findViewById(R.id.Button3);

        Score = (TextView) findViewById(R.id.Score);
        Score.setText("0");


        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Score.setText("0");
        Log.v(TAG, "Starting GUI!");
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Left Clicked");
                doCheck(buttonLeft);
            }
        });
        buttonMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Middle Clicked");
                doCheck(buttonMiddle);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Right Clicked");
                doCheck(buttonRight);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        if (scoreNum == 10){
            nextLevelQuery();
        }
        if (checkButton.getText().toString().equals("*")){
            Log.v(TAG, "Hit, score added!");
            scoreNum += 1;
        }
        else{
            Log.v(TAG, "Missed, score deducted!");
            scoreNum -= 1;
        }
        Score.setText(String.valueOf(scoreNum));
        setNewMole();
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setMessage("Would you like to advance to the next level?").setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Log.v(TAG, "User Accepts!");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Log.v(TAG, "User Declines!");
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Whack-A-Mole 2.0!");
        alert.show();
        Log.v(TAG, "Advance option given to user!");

    }

    private void nextLevel(){
        Intent advancedPage = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(advancedPage);
        /* Launch advanced page */
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if (randomLocation == 0){
            buttonLeft.setText("*");
            buttonRight.setText("o");
            buttonMiddle.setText("o");
        }
        else if(randomLocation == 1){
            buttonMiddle.setText("*");
            buttonRight.setText("o");
            buttonLeft.setText("o");
        }
        else if(randomLocation == 2){
            buttonRight.setText("*");
            buttonMiddle.setText("o");
            buttonLeft.setText("o");
        }
    }
}