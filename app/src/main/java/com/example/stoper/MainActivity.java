package com.example.stoper;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView tv_time;
    ImageButton btn_play, btn_pause, btn_stop, btn_play_again;
    Button btn_play_two, btn_pause_two, btn_stop_two, btn_play_again_two;

    int timeMill=0;
    int timeSec=0;
    int timeMin=0;
    int timeHour=0;

    Handler handler;
    Runnable r,ran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variableDefinitions();

        btn_play();
        btn_pause();
        btn_stop();
        btn_play_again();

    }

    private void variableDefinitions() {

        tv_time = (TextView) findViewById(R.id.tv_time);

        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_pause = (ImageButton) findViewById(R.id.btn_pause);
        btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        btn_play_again = (ImageButton) findViewById(R.id.btn_play_again);

        btn_play_two = (Button) findViewById(R.id.btn_play_two);
        btn_pause_two = (Button) findViewById(R.id.btn_pause_two);
        btn_stop_two = (Button) findViewById(R.id.btn_stop_two);
        btn_play_again_two = (Button) findViewById(R.id.btn_play_again_two);

    }

    public void btn_play(){

        btn_play_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_play.setVisibility(View.INVISIBLE);
                btn_stop.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.VISIBLE);
                btn_play_again.setVisibility(View.INVISIBLE);

                btn_play_two.setVisibility(View.INVISIBLE);
                btn_stop_two.setVisibility(View.VISIBLE);
                btn_pause_two.setVisibility(View.VISIBLE);
                btn_play_again_two.setVisibility(View.INVISIBLE);

                stoper();

            }
        });

    }

    public void btn_pause(){
        btn_pause_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_play.setVisibility(View.INVISIBLE);
                btn_stop.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.INVISIBLE);
                btn_play_again.setVisibility(View.VISIBLE);

                btn_play_two.setVisibility(View.INVISIBLE);
                btn_stop_two.setVisibility(View.VISIBLE);
                btn_pause_two.setVisibility(View.INVISIBLE);
                btn_play_again_two.setVisibility(View.VISIBLE);

                handler.removeCallbacksAndMessages(r);
                handler.removeCallbacksAndMessages(ran);


            }
        });

    }

    public void btn_stop(){
        btn_stop_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_time.setText("00:00:00.00");
                btn_play.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.INVISIBLE);
                btn_pause.setVisibility(View.INVISIBLE);
                btn_play_again.setVisibility(View.INVISIBLE);

                btn_play_two.setVisibility(View.VISIBLE);
                btn_stop_two.setVisibility(View.INVISIBLE);
                btn_pause_two.setVisibility(View.INVISIBLE);
                btn_play_again_two.setVisibility(View.INVISIBLE);

                handler.removeCallbacksAndMessages(r);

                 timeMill=0;
                 timeSec=0;
                 timeMin=0;
                 timeHour=0;

                Intent serviceIntent = new Intent(MainActivity.this, ExampleService.class);
                stopService(serviceIntent);

            }
        });

    }

    public void btn_play_again(){
        btn_play_again_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_play.setVisibility(View.INVISIBLE);
                btn_stop.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.VISIBLE);
                btn_play_again.setVisibility(View.INVISIBLE);

                btn_play_two.setVisibility(View.INVISIBLE);
                btn_stop_two.setVisibility(View.VISIBLE);
                btn_pause_two.setVisibility(View.VISIBLE);
                btn_play_again_two.setVisibility(View.INVISIBLE);

                stoper_again();

            }
        });

    }

    public void stoper(){

        handler = new Handler();

        Runnable r = new Runnable() {
            public void run() {
                timeMill = timeMill + 2;
                tv_time.setText(String.format("%02d:%02d:%02d.%02d",timeHour,timeMin, timeSec, timeMill));
                handler.postDelayed(this, 17);

                    if (timeMill >= 98){
                        timeMill=0;
                        timeSec++;
                    }
                    if (timeSec >59){
                        timeSec=0;
                        timeMin++;
                    }
                    if (timeMin >59){
                        timeMin=0;
                        timeHour++;
                    }

                Intent serviceIntnet= new Intent(MainActivity.this, ExampleService.class);
                serviceIntnet.putExtra("timeMillExtra", timeMill);
                serviceIntnet.putExtra("timeSecExtra", timeSec);
                serviceIntnet.putExtra("timeMinExtra", timeMin);
                serviceIntnet.putExtra("timeHourExtra", timeHour);
                ContextCompat.startForegroundService(MainActivity.this, serviceIntnet);


            }
        };

        handler.post(r);
    }

    public void stoper_again(){

        ran = new Runnable() {
            public void run() {
                timeMill = timeMill + 2;
                tv_time.setText(String.format("%02d:%02d:%02d.%02d",timeHour,timeMin, timeSec, timeMill));
                handler.postDelayed(this, 16);

                    if (timeMill >= 98){
                        timeMill=0;
                        timeSec++;
                    }
                    if (timeSec >59){
                        timeSec=0;
                        timeMin++;
                    }
                    if (timeMin >59){
                        timeMin=0;
                        timeHour++;
                    }

                Intent serviceIntnet= new Intent(MainActivity.this, ExampleService.class);
                serviceIntnet.putExtra("timeMillExtra", timeMill);
                serviceIntnet.putExtra("timeSecExtra", timeSec);
                serviceIntnet.putExtra("timeMinExtra", timeMin);
                serviceIntnet.putExtra("timeHourExtra", timeHour);
                ContextCompat.startForegroundService(MainActivity.this, serviceIntnet);

            }
        };

        handler.post(ran);
    }

    // Powracanie do poprzedniego Activity za pomocą przycisku na telefonie
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {

            finish();

            tv_time.setText("00:00:00.00");
            btn_play.setVisibility(View.VISIBLE);
            btn_stop.setVisibility(View.INVISIBLE);
            btn_pause.setVisibility(View.INVISIBLE);
            btn_play_again.setVisibility(View.INVISIBLE);

            btn_play_two.setVisibility(View.VISIBLE);
            btn_stop_two.setVisibility(View.INVISIBLE);
            btn_pause_two.setVisibility(View.INVISIBLE);
            btn_play_again_two.setVisibility(View.INVISIBLE);

            handler.removeCallbacksAndMessages(r);

            timeMill=0;
            timeSec=0;
            timeMin=0;
            timeHour=0;

            Intent serviceIntent = new Intent(MainActivity.this, ExampleService.class);
            stopService(serviceIntent);

        }
        return super.onKeyDown(keyCode, event);
    }


}
