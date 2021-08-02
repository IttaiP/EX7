package com.example.orders

import android.app.Application
import android.content.SharedPreferences
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
        private lateinit var sp: SharedPreferences;

        fun getInstance(): OrderApp? {
            return instance
        }
        fun getCurrentId(): String? {
            if (sp != null) {
                return sp.getString("id", null)
            }
            return null
//    this.context = context;
        }
    }


    @Transient
    lateinit var db: FirebaseFirestore;


    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        instance = this
        sp = instance?.getSharedPreferences("orderID", MODE_PRIVATE)!!
        db = Firebase.firestore
    }



    fun add(id: String, tahini: CheckBox, pickles: EditText, humus: CheckBox, comments: EditText, name: EditText){
        var pickels_adjust = pickles.text.toString().toInt();
        if (pickels_adjust>10){
            pickels_adjust = 10;
            pickles.setText("10");
        }
        val order = hashMapOf(
                "id" to id,
                "tahini" to tahini.isChecked,
                "pickels" to pickels_adjust,
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
        val editor = sp!!.edit()
        editor.putString("id", id)
        editor.apply()
    }

    fun delete(id: String){
        db.collection("Orders")
                .document(id).delete()
                .addOnSuccessListener { documentReference ->
                }
                .addOnFailureListener { e ->
                    Log.w("failure", "Error deleting document", e)
                }
    }



    fun edit(id: String, tahini: CheckBox, pickles: EditText, humus: CheckBox, comments: EditText, name: String?){
        val order = hashMapOf(
                "id" to id,
                "tahini" to tahini.isChecked,
                "pickels" to pickles.text.toString().toInt(),
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

    fun getOrderComments(id: String): String? {
        var ret_val:String = "";
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.getString("comments").toString()
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrderHumus(id: String): Boolean? {
        var ret_val:Boolean = false;
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.getBoolean("humus") == true
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrderName(id: String): String {
        var ret_val:String = "";
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.getString("name").toString()
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrderPickles(id: String): Int {
        var ret_val:Int = 0;
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.get("pickles") as Int
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrderStatus(id: String): String {
        var ret_val:String = "";
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.getString("status").toString()
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrdertahini(id: String): Boolean {
        var ret_val:Boolean = false;
        db.collection("Orders").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                ret_val = document.getBoolean("tahini") == true
//                Log.d("rrrrrr", "DocumentSnapshot data: ${document.data}")
//                Log.d("rrrrrr", document.getString("status").toString())

            } else {
                Log.d("rrrr", "No such document")
            }
        }
                .addOnFailureListener { exception ->
                    Log.d("rrrr", "get failed with ", exception)
                }
        return ret_val
    }

    fun getOrder(): Any? {
        val docref = getCurrentId()?.let {
            db.collection("Orders").document(it).get().addOnSuccessListener { document ->
                if (document != null) {
                } else {
                }
            }
                    .addOnFailureListener { exception ->
                    }
        }
        if (docref != null) {
//            val intent = Intent(this, EditActivity::class.java).apply {
//                putExtra("tahini", docref.result?.get("tahini"))
//                putExtra("pickles", pickles.text.toString())
//                putExtra("humus", humus.isChecked)
//                putExtra("comments", comments.text.toString())
//                putExtra("name", comments.text.toString())
//                putExtra("id", id)
//            }
        }
        return null
    }
}