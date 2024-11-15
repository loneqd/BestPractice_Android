package com.lqd.androidpractice.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.lqd.androidpractice.R

class ConstraintLayoutXmlAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_xml_normal)

        val constraintSet1 = ConstraintSet()
        val constraintSet2 = ConstraintSet()
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)

        constraintSet1.clone(constraintLayout)
        constraintSet2.clone(this, R.layout.activity_constraint_layout_xml_anim)

        findViewById<Button>(R.id.start).setOnClickListener {
            val transition = AutoTransition()
            TransitionManager.beginDelayedTransition(constraintLayout, transition)
            constraintSet2.applyTo(constraintLayout)
        }

        findViewById<Button>(R.id.recover).setOnClickListener {
            val transition = AutoTransition().also {
                it.duration = 300
            }
            TransitionManager.beginDelayedTransition(constraintLayout, transition)
            constraintSet1.applyTo(constraintLayout)
        }
    }
}