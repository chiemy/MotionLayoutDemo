package com.segway.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.transition.Scene
import androidx.transition.TransitionManager

class TransitionGo : AppCompatActivity(), View.OnClickListener {
    private var start = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_go)
        bindData()
    }

    private fun bindData() {
        findViewById<View>(R.id.image_film_cover).setOnClickListener(this)
        findViewById<TextView>(R.id.text_film_title).text = "黑客帝国"
        findViewById<TextView>(R.id.text_film_description).text = "balabalalba"
    }

    override fun onClick(v: View) {
        val root = findViewById<ViewGroup>(R.id.root)
        TransitionManager.go(
                Scene.getSceneForLayout(
                        root,
                        if (start) R.layout.go_end else R.layout.go_start,
                        this
                )
        )
        start = !start
        bindData()
    }
}