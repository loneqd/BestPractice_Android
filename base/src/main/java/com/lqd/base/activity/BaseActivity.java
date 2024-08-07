package com.lqd.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * 基础Activity
 */
public class BaseActivity extends Activity {

    //    private static Handler mHandler = null;
    private static WeakReference<Handler> mHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initHandler() {
        Handler _handler = new Handler();
        mHandler = new WeakReference<>(_handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源，当OOM时降低内存消耗
        traverse((ViewGroup) findViewById(android.R.id.content));

        //回收handler
        if (mHandler != null && mHandler.get() != null) {
            mHandler.get().removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }


    private void traverse(ViewGroup root) {
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                child.setBackground(null);
                traverse((ViewGroup) child);
            } else {
                if (child != null) {
                    child.setBackground(null);
                }
                if (child instanceof ImageView) {
                    ((ImageView) child).setImageDrawable(null);
                }
//                else if (child instanceof EditText) {
//                    ((EditText) child).removeTextChangedListener();
//                }
            }
        }
    }
}
