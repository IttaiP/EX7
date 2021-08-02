package com.example.orders

import android.content.Intent
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
        val delete_order = findViewById<Button>(R.id.Delete_button)
        val tahini = findViewById<CheckBox>(R.id.tahini)
        val pickles = findViewById<EditText>(R.id.pickels)
        val humus = findViewById<CheckBox>(R.id.humus)
        val comments: EditText = findViewById(R.id.comments)

        tahini.isChecked = intent.getBooleanExtra("tahini", false)
        pickles.setText(intent.getStringExtra("pickles"))
        humus.isChecked = intent.getBooleanExtra("humus", false)
        comments.setText(intent.getStringExtra("comments"))
        val id = intent.getStringExtra("id")


        delete_order.setOnClickListener {
            if (id != null) {
                OrderApp.getInstance()?.delete(id)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        make_order.setOnClickListener {
            if (id != null) {
                OrderApp.getInstance()?.edit(id, tahini, pickles, humus, comments, intent.getStringExtra("name"))
            }


            val order = hashMapOf(
                    "id" to id,
                    "tahini" to tahini.isChecked,
                    "pickels" to pickles.text.toString().toInt(),
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