package com.example.countercompoundview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val counter = findViewById<Counter>(R.id.counter_compound_view)

        lifecycle.addObserver(counter)
    }
}