package com.lqd.androidpractice.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lqd.androidpractice.R;
import com.lqd.utils.ThreadHelper;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

/**
 * handler 使用实例
 * <p>
 * 在异步线程发送消息到主线程刷新UI
 *
 * @author: a564
 * @Date 2021/6/7
 */
public class HandlerActivity extends Activity {

    private static String TAG = "HandlerActivity";

    private TextView textView;

    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

        textView = findViewById(R.id.ah_textview);
        findViewById(R.id.ah_btn1).setOnClickListener(v -> {

            ThreadHelper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, Thread.currentThread().getName());
                    Log.d(TAG, "isDestroyed: " + HandlerActivity.this.isDestroyed());
                    HandlerActivity.this.textView.setText("haha");
                }
            }, 4000);

            ThreadHelper.runOnAsyncThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, Thread.currentThread().getName());
                    Log.d(TAG, "isDestroyed: " + HandlerActivity.this.isDestroyed());
                    HandlerActivity.this.textView.setText("hehe");
                }
            }, 3500);

            new Thread() {
                @Override
                public void run() {
                    super.run();

                    Message msg = Message.obtain();
                    msg.what = 1; // 消息标识
                    msg.obj = "A"; // 消息内存存放
                    myHandler.sendMessage(msg); // 异步线程发送消息

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg2 = Message.obtain();
                    msg2.what = 2; // 消息标识
                    msg2.obj = "BB"; // 消息内存存放
                    myHandler.sendMessageDelayed(msg2, 5000);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, ">>>" + Thread.currentThread().getStackTrace()[2].getMethodName());

                    //使用post
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //回到UI线程
                            Log.d(TAG, ">>>" + Thread.currentThread().getName());
                            textView.setText("reset");
                        }
                    });

//                    HandlerActivity.this.finish();
                }
            }.start();

        });

        myHandler = new MyHandler(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        myHandler = null;
        Log.d(TAG, "onDestroy");
    }

    //静态内部类
    private static class MyHandler extends Handler {
        //弱引用
        WeakReference<HandlerActivity> activityWeakReference;

        public MyHandler(HandlerActivity activity) {
            activityWeakReference = new WeakReference<HandlerActivity>(activity);
        }


        @Override
        public void handleMessage(@NonNull @NotNull Message msg) {
            super.handleMessage(msg);

            Log.d(TAG, ">>>" + Thread.currentThread().getStackTrace()[2].getMethodName());
            Log.d(TAG, ">>>" + msg.toString());
            HandlerActivity activity = activityWeakReference.get();
            Log.d(TAG, "handleMessage" + activity);
            if (activity == null)
                return;

            switch (msg.what) {
                case 1:
                    activity.textView.setText("执行了线程1的UI操作");
                    break;
                case 2:
                    activity.textView.setText("执行了线程2的UI操作");
                    break;
            }
        }
    }

    ;


}
