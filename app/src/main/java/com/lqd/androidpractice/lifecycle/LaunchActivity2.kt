package com.lqd.androidpractice.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.lqd.androidpractice.R

class LaunchActivity2 : BaseLaunchActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取extra参数
        val param1 = intent.getStringExtra("param1") ?: "default"

        setContentView(R.layout.activity_lauch2)
        Log.d(TAG, Thread.currentThread().stackTrace[2].methodName)

        findViewById<Button>(R.id.btn2).setText(param1)

        findViewById<Button>(R.id.btn2).setOnClickListener {
            startActivity(Intent(this@LaunchActivity2, LaunchActivity3::class.java))
        }


        ProcessLifecycleOwner.get().lifecycle.addObserver(object: DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
            }
        })

    }

}

