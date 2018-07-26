package com.proteam.bai_12_handler_message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private Button mBtnThreads;
    private Button mBtnMainThread;
    private Button mBtnMainThreadToThreads;

    private TextView mTvTime;
    private Handler mHandler;
    private int dem;
    LooperThread looperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_main);
        mBtnThreads = (Button) findViewById(R.id.btnThreads);
        mBtnMainThread = (Button) findViewById(R.id.btnMainThread);
        mBtnMainThreadToThreads = (Button) findViewById(R.id.btnMainThreadToThreads);
        mTvTime = (TextView) findViewById(R.id.tvTime);

        looperThread = new LooperThread();
        looperThread.start();

        mBtnThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToLooperThread();
            }
        });

        mBtnMainThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageUIThread();
            }
        });

        mBtnMainThreadToThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageFromUIToLooperThread();
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

    private void sendMessageUIThread() {

        // Thread run
        Thread mythread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = mHandler.obtainMessage();
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                }
            }
        });
        mythread.start();
    }

    private void sendMessageToLooperThread() {

        // Thread run
        Thread mythread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.obj = "Send message From WorkerThread " + i;
                    looperThread.handler.sendMessage(msg);
                }
            }
        });
        mythread.start();
    }


    private void sendMessageFromUIToLooperThread() {
        Message msg = Message.obtain();
        for (int i = 0; i <= 100; i++) {
            msg.obj = "Send message From Me " + i;
            looperThread.handler.sendMessage(msg);
        }
    }
}
