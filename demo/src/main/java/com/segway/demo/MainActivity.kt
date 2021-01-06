package com.segway.demo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayout = findViewById<ViewGroup>(R.id.root)

        packageManager.getPackageInfo(
                packageName, PackageManager.GET_ACTIVITIES).activities.forEach { activity ->
            if (activity.name == this::class.java.name) {
                return@forEach
            }

            val clazz = Class.forName(activity.name)

            val button = Button(this).apply {
                this.isAllCaps = false
                text = if (activity.labelRes != 0) {
                    resources.getString(activity.labelRes)
                } else {
                    clazz.simpleName
                }
                setOnClickListener {
                    startActivity(Intent(this@MainActivity, clazz))
                }
            }
            linearLayout.addView(button)
        }
    }


}