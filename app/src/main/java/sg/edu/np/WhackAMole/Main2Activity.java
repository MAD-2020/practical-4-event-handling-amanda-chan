package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */
    private TextView Score;
    int advancedScore = 10;
    CountDownTimer myCountdown;
    CountDownTimer moleTimer;
    Button setButton;
    Button moleButton;
    Button buttonListener;
    Button buttonClicked;
    final String TAG = "Whack-A-Mole 2.0";



    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        myCountdown = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready Countdown! " + millisUntilFinished/1000);
                Toast.makeText(getApplicationContext(), "Get Ready In " + millisUntilFinished / 1000 + " seconds", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.v(TAG, "Ready CountDown Complete!");
                Toast.makeText(getApplicationContext(), "GO!", Toast.LENGTH_LONG).show();
                placeMoleTimer();
                myCountdown.cancel();
            }
        };
        myCountdown.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        moleTimer = new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                Log.v(TAG, "New Mole Location!");
                setNewMole();
                moleTimer.start();
            }
        };
        moleTimer.start();

    }
    private static final int[] BUTTON_IDS = {
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
        R.id.Button1,
        R.id.Button2,
        R.id.Button3,
        R.id.Button4,
        R.id.Button5,
        R.id.Button6,
        R.id.Button7,
        R.id.Button8,
        R.id.Button9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Score = (TextView) findViewById(R.id.Score);
        Score.setText(String.valueOf(advancedScore));

        Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));

        for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            buttonListener = (Button) findViewById(id);
            buttonListener.setOnClickListener(this);
        }

    }
    @Override
    protected void onStart(){
        super.onStart();
        Score.setText(String.valueOf(advancedScore));
        readyTimer();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.Button1 :
                buttonClicked = (Button) findViewById(R.id.Button1);
                doCheck(buttonClicked);
                break;
            case R.id.Button2 :
                buttonClicked = (Button) findViewById(R.id.Button2);
                doCheck(buttonClicked);
                break;
            case R.id.Button3 :
                buttonClicked = (Button) findViewById(R.id.Button3);
                doCheck(buttonClicked);
                break;
            case R.id.Button4 :
                buttonClicked = (Button) findViewById(R.id.Button4);
                doCheck(buttonClicked);
                break;
            case R.id.Button5 :
                buttonClicked = (Button) findViewById(R.id.Button5);
                doCheck(buttonClicked);
                break;
            case R.id.Button6 :
                buttonClicked = (Button) findViewById(R.id.Button6);
                doCheck(buttonClicked);
                break;
            case R.id.Button7 :
                buttonClicked = (Button) findViewById(R.id.Button7);
                doCheck(buttonClicked);
                break;
            case R.id.Button8 :
                buttonClicked = (Button) findViewById(R.id.Button8);
                doCheck(buttonClicked);
                break;
            case R.id.Button9 :
                buttonClicked = (Button) findViewById(R.id.Button9);
                doCheck(buttonClicked);
                break;
        }
    }

    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText().toString().equals("*")){
            Log.v(TAG, "Hit, score added!");
            advancedScore += 1;
        }
        else{
            Log.v(TAG, "Missed, score deducted!");
            advancedScore -= 1;
        }
        Score.setText(String.valueOf(advancedScore));
        placeMoleTimer();
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for(final int id : BUTTON_IDS){
            setButton = (Button) findViewById(id);
            setButton.setText("o");
        }
        moleButton = (Button) findViewById(BUTTON_IDS[randomLocation]);
        moleButton.setText("*");
    }
}

