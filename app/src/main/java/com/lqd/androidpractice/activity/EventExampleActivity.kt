package com.lqd.androidpractice.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lqd.androidpractice.R

class EventExampleActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_example)

        GestureDetector(this, this).apply {
            setOnDoubleTapListener(this@EventExampleActivity)
        }

        val view = findViewById<Button>(R.id.eventBtn1)
        view.setOnLongClickListener {
            Log.d("EventExampleActivity", "setOnLongClickListener")
            true
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("EventExampleActivity", "onTouchEvent: ACTION_DOWN")
            }

            MotionEvent.ACTION_MOVE -> {
                Log.d("EventExampleActivity", "onTouchEvent: ACTION_MOVE")
            }

            MotionEvent.ACTION_UP -> {
                Log.d("EventExampleActivity", "onTouchEvent: ACTION_UP")
            }
        }
        return super.onTouchEvent(event)
    }
    private val DEBUG_TAG = "Gestures"

    override fun onDown(e: MotionEvent): Boolean {
        Log.d(DEBUG_TAG,"onDown: " + e.toString());
        return true;
    }

    override fun onShowPress(e: MotionEvent) {
        Log.d(DEBUG_TAG,"onShowPress: " + e.toString());
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Log.d(DEBUG_TAG,"onSingleTapUp: " + e.toString());
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: " + e.toString());
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + e.toString());
        return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: " + e.toString());
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + e.toString());
        return true
    }


}