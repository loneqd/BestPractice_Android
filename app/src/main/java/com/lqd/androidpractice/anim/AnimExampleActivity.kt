package com.lqd.androidpractice.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.lqd.androidpractice.R
import com.lqd.androidpractice.databinding.ActivityAnimExampleBinding
import com.lqd.androidpractice.databinding.ActivityKotlinTest1Binding

class AnimExampleActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimExampleBinding

    private var sceneRoot: ViewGroup? = null
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var scene3: Scene
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.enterTransition = Explode().let { explode ->
            explode.excludeTarget(android.R.id.statusBarBackground, true)
            explode.excludeTarget(android.R.id.navigationBarBackground, true)
            explode.duration = 2000L
            explode}

        sceneRoot = binding.sceneRoot

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.anim_layout_scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.anim_layout_scene2, this)
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.anim_layout_scene3, this)


        binding.scene1Button.setOnClickListener {
            TransitionManager.go(scene1)
        }

        binding.scene2Button.setOnClickListener {
            TransitionInflater.from(this).inflateTransitionManager(R.transition.scene2_transition_manager, sceneRoot)
                .transitionTo(scene2)
        }

        binding.scene3Button.setOnClickListener {
            val slide = Slide(Gravity.RIGHT).setDuration(1000)
            TransitionManager.go(scene3, slide)
        }

        binding.scene4Button.setOnClickListener {
            TransitionManager.beginDelayedTransition(sceneRoot)
            val square = sceneRoot!!.findViewById<View>(R.id.transition_oval_blue)
//            square.layoutParams = square.layoutParams.let { lp ->
//                lp.width = lp.width * 2
//                lp.height = lp.height * 2
//                lp
//            }


            //属性动画
            val animationSet = AnimationSet(true).also {
                it.addAnimation(AlphaAnimation(0.0f, 1.0f))
                it.addAnimation(TranslateAnimation(0f, 0f, 0f, 100f))
                it.duration = 1000
                it.addAnimation(ScaleAnimation(1f, 2f, 1f, 2f, 0.5f, 0.5f))
                it.addAnimation(RotateAnimation(0f, 360f, 0.5f, 0.5f))
            }
            square.startAnimation(animationSet)

        }

    }
}