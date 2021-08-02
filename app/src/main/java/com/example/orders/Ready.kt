package com.example.orders

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ready: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val done = findViewById<Button>(R.id.Order_button)


        done.setOnClickListener {  }
    }



}