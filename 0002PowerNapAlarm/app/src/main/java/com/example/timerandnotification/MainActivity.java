package com.example.timerandnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

import static com.example.timerandnotification.NotificationActivity.CHANNEL_1_ID;
import static com.example.timerandnotification.NotificationActivity.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {
    protected boolean UserSelectAlertType=false;
    private Button button;
    private Button logButton;
    private ToggleButton toggleButton;
    private NotificationManagerCompat notificationManager;

    private int seconds = 0;


    // Is the stopwatch running?
    private boolean running;

    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);
        button=(Button)findViewById(R.id.button);
        logButton=(Button)findViewById(R.id.button5);
        toggleButton=(ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

                UserSelectAlertType=false;
            } else {
                UserSelectAlertType=true;
                // The toggle is disabled
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendAlertOption();
            }
        });
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent( getApplicationContext(), LogActivity.class);  // kinda of like a reference to child activity right?
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(intent);
            }
        });
        if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }


    public void sendAlertOption(){
        if(!UserSelectAlertType){
            sendAlert1();
        }
        else
            sendAlert2();


    }

    public void sendAlert1(){

        Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification2)
                .setContentTitle("15Minute is up!")
                .setContentText("Wake up!")
              //  .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(getResources().getColor(R.color.purple_700))
                .build();

        notificationManager.notify(1, notification);
    }



    /*
    Sends Alert to user even if application is in background without vibrate
     */
    public void sendAlert2(){

        Notification notification = new NotificationCompat.Builder(MainActivity.this,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notification1)
                .setContentTitle("30 minutes is up!")
                .setContentText("You're oversleeping!")
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(getResources().getColor(R.color.teal_700))
                .build();

        notificationManager.notify(2, notification);
    }




    // Save the state of the stopwatch
    // if it's about to be destroyed.
    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    // If the activity is paused,
    // stop the stopwatch.
    @Override
    protected void onPause()
    {
        super.onPause();
        if(running ==true && wasRunning==true)

            running = true;
    }

    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    // Start the stopwatch running
    // when the Start button is clicked.
    // Below method gets called
    // when the Start button is clicked.
    public void onClickStart(View view)
    {
        running = true;
    }

    // Stop the stopwatch running
    // when the Stop button is clicked.
    // Below method gets called
    // when the Stop button is clicked.
    public void onClickStop(View view)
    {
        wasRunning=false;
        running = false;
    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }

    // Sets the NUmber of seconds on the timer.
    // The runTimer() method uses a Handler
    // to increment the seconds and
    // update the text view.
    private void runTimer()
    {

        // Get the text view.
        final TextView timeView
                = (TextView)findViewById(
                R.id.time_view);

        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }




}