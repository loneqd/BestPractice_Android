package com.lqd.androidpractice.view

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View

/**
 * @author a564
 * @version 1.0
 * @Date 4/6/21
 */
internal class CustomView(context: Context?) : View(context) {
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "CustomView"
    }
}
