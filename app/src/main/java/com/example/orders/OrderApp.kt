package com.example.orders

import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

public class OrderApp: Application() {
    companion object{
        private var instance: com.example.orders.OrderApp? = null
        public fun getInstance(): OrderApp? {
            return instance
        }
    }
    lateinit var db: FirebaseFirestore;



    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Firebase.firestore

    }





    fun add(id: String, tahini: CheckBox, pickles: CheckBox, humus: CheckBox, comments: EditText, name: EditText){
        val order = hashMapOf(
                "id" to id,
                "tahini" to tahini.isChecked,
                "pickels" to pickles.isChecked,
                "humus" to humus.isChecked,
                "comments" to comments.text.toString(),
                "name" to name.text.toString(),
                "status" to "waiting"
        )
        db.collection("Orders")
                .document(id).set(order)
                .addOnSuccessListener { documentReference ->
                }
                .addOnFailureListener { e ->
                    Log.w("failure", "Error adding document", e)
                }

    }

    fun edit(id: String, tahini: CheckBox, pickles: CheckBox, humus: CheckBox, comments: EditText, name: String?){
        val order = hashMapOf(
                "id" to id,
                "tahini" to tahini.isChecked,
                "pickels" to pickles.isChecked,
                "humus" to humus.isChecked,
                "comments" to comments.text.toString(),
                "name" to name,
                "status" to "waiting"

        )
// Add a new document with a generated ID
        db.collection("Orders")
                .document(id).set(order)
                .addOnSuccessListener { documentReference ->
                }
                .addOnFailureListener { e ->
                    Log.w("failure", "Error adding document", e)
                }
    }
}