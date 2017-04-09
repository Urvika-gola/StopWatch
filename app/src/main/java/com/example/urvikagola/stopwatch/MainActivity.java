package com.example.urvikagola.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by urvikagola on 09/04/17.
 */
public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    TextView mm,ss,cs;
    int m=0,s=0,c=0;
    Timer T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        mm = (TextView) findViewById(R.id.mm);
        ss = (TextView) findViewById(R.id.ss);
        cs = (TextView) findViewById(R.id.cs);
        //1 Second = 1000 Millisecond
        //1 Centisecond = 10 Millisecond
        //1 Second = 100 Centisecond
    }
    public void start(View v)
    {
        btnStart.setEnabled(false);
        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cs.setText(""+  String.format("%02d", c));
                        ss.setText("" + String.format("%02d", s));
                        mm.setText(""+  String.format("%02d", m));

                        c++;
                        if(c > 99)  //1 Second = 100 Centisecond
                        {
                            c=0;
                            s++;
                            if(s > 59)
                            {
                                s=0;
                                m++;
                                if(m > 59)
                                {
                                    m=0;
                                }
                            }
                        }
                    }
                });
            }
        }, 10, 10);     // 10 millisecond = 1 Centisecond
    }
    public void stop(View v) {
        btnStart.setEnabled(true);
        T.cancel();
    }

    public void reset(View v) {
        btnStart.setEnabled(true);
        c=0;s=0;m=0;
        cs.setText(""+  String.format("%02d", c));
        ss.setText("" + String.format("%02d", s));
        mm.setText(""+  String.format("%02d", m));
        T.cancel();
    }
    }
