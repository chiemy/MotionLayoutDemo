package com.segway.demo

import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeTransform
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import androidx.core.animation.addListener
class AnimatorCompareActivity : AppCompatActivity() {
    lateinit var root: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)
        root = findViewById(R.id.root)
    }

    fun onClick(view: View) {
        val scaleAnimation = ScaleAnimation(1f, 2f, 1f, 2f)
                .apply {
                    duration = 1000
                    // fillAfter = true
                }
        view.startAnimation(scaleAnimation)
    }

    // Kotlin 标准函数
    // 好处：简洁、代码清晰
    // 返回自身 -> 从 apply 和 also 中选
    //   作用域中使用 this 作为参数 ----> 选择 apply
    //   作用域中使用 it 作为参数 ----> 选择 also
    // 不需要返回自身 -> 从 run 和 let 中选择
    //   作用域中使用 this 作为参数 ----> 选择 run
    //   作用域中使用 it 作为参数 ----> 选择 let
    // apply 适合对一个对象做附加操作的时候
    // let 适合配合空判断的时候 (最好是成员变量，而不是局部变量，局部变量更适合用 if )
    // with 适合对同一个对象进行多次操作的时候

    fun onClick1(view: View) {
        val width = view.width
        val height = view.height
        val animator = ObjectAnimator.ofFloat(1f, 2f).apply {
            duration = 1000
            addUpdateListener {
                view.layoutParams.width = (width * (animatedValue as Float)).toInt()
                view.layoutParams.height = (height * (animatedValue as Float)).toInt()
                view.requestLayout()
            }
        }
        animator.start()
    }

    fun onClick2(view: View) {
        // 怎么改动画时长？
        val changeBounds = ChangeBounds()
        changeBounds.duration = 1000
        TransitionManager.beginDelayedTransition(root, changeBounds)

        view.layoutParams.apply {
            width *= 2
            height *= 2
        }
//        with(view.layoutParams) {
//            width *= 2
//            height *= 2
//        }
        view.requestLayout()
    }
}