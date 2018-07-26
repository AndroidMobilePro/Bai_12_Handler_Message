package com.proteam.bai_12_handler_message;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
    public Handler handler;

    public void run() {
        // Creating Looper and MessageQueue for this Thread
        Looper.prepare();

        handler = new Handler() {
            public void handleMessage(final Message msg) {
                Log.d("TAGGGG", msg.obj.toString());
                // process incoming messages here
                // this will run in non-ui/background thread
            }
        };
        // Associate the Thread with its Looper and MessageQueue
        Looper.loop();
    }
}