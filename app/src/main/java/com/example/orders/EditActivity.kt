package com.example.orders

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class EditActivity : AppCompatActivity(){
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_order)
        val make_order = findViewById<Button>(R.id.Order_button)
        val tahini = findViewById<CheckBox>(R.id.tahini)
        val pickles = findViewById<CheckBox>(R.id.pickels)
        val humus = findViewById<CheckBox>(R.id.humus)
        val comments: EditText = findViewById(R.id.comments)

        tahini.isChecked = intent.getBooleanExtra("tahini", false)
        pickles.isChecked = intent.getBooleanExtra("pickles", false)
        humus.isChecked = intent.getBooleanExtra("humus", false)
        comments.setText(intent.getStringExtra("comments"))
        val id = intent.getStringExtra("id")




        make_order.setOnClickListener {
            if (id != null) {
                OrderApp.getInstance()?.edit(id, tahini, pickles, humus, comments, intent.getStringExtra("name"))
            }


            val order = hashMapOf(
                    "id" to id,
                    "tahini" to tahini.isChecked,
                    "pickels" to pickles.isChecked,
                    "humus" to humus.isChecked,
                    "comments" to comments.text.toString(),
                    "name" to intent.getStringExtra("name"),
                    "status" to "waiting"

            )
// Add a new document with a generated ID
            if (id != null) {
                db.collection("Orders")
                        .document(id).set(order)
                        .addOnSuccessListener { documentReference ->
                        }
                        .addOnFailureListener { e ->
                            Log.w("failure", "Error adding document", e)
                        }
            }
        }
    }
}