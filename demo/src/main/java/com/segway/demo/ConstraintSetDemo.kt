package com.segway.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager

class ConstraintSetDemo : AppCompatActivity(), View.OnClickListener {
    private var start = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_start)

        findViewById<View>(R.id.image_film_cover).setOnClickListener(this)
        findViewById<TextView>(R.id.text_film_title).text = "黑客帝国"
        findViewById<TextView>(R.id.text_film_description).text = "balabalalba"
    }

    override fun onClick(v: View) {
        val root = findViewById<ConstraintLayout>(R.id.root)
        TransitionManager.beginDelayedTransition(root)
        val constraintSet = ConstraintSet()
        if (start) {
            constraintSet.clone(this, R.layout.constraint_end)
        } else {
            constraintSet.clone(this, R.layout.constraint_start)
        }
        constraintSet.applyTo(root)
        start = !start
    }
}