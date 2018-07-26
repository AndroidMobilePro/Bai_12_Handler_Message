package com.proteam.bai_12_handler_message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSchedule;
    private Button mBtnMessage;
    private TextView mTvTime;
    public Handler mHandler;
    private int dem;
    LooperThread looperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnMessage = (Button) findViewById(R.id.btnMessage);
        mBtnSchedule = (Button) findViewById(R.id.btnSchedule);
        mTvTime = (TextView) findViewById(R.id.tvTime);

        looperThread = new LooperThread();
        looperThread.start();
        mBtnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleExample();
            }
        });

        mBtnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTvTime.setText(msg.arg1 + "");
            }
        };

    }

    private void scheduleExample() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(this, 100);
                dem++;
                mTvTime.setText(dem + "");
                if (dem == 100) {
                    mHandler.removeCallbacks(this);
                }
            }
        }, 100);
    }
}
