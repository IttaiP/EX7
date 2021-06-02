package com.example.orders

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val make_order = findViewById<Button>(R.id.Order_button)
        val tahini = findViewById<CheckBox>(R.id.tahini)
        val pickles = findViewById<CheckBox>(R.id.pickels)
        val humus = findViewById<CheckBox>(R.id.humus)
        val comments:EditText = findViewById(R.id.comments)
        val name:EditText = findViewById(R.id.name)
        val id = UUID.randomUUID().toString()






        make_order.setOnClickListener {
            OrderApp.getInstance()?.add(id, tahini, pickles, humus, comments, name)
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("tahini", tahini.isChecked)
                putExtra("pickles", pickles.isChecked)
                putExtra("humus", humus.isChecked)
                putExtra("comments", comments.text.toString())
                putExtra("name", comments.text.toString())
                putExtra("id", id)
            }
            startActivity(intent)
        }
    }
}