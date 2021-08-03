package com.example.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;

import java.util.UUID;

public class EditActivity extends AppCompatActivity {

    // add buttons and views
    Button update_order;
    Button cancel_order;
    EditText pickles;
    CheckBox tahini;
    CheckBox humus;
    EditText comments;
    EditText name;

    DB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);


        db = OrderApp.getInstance().getDB();

        Order order = (Order) getIntent().getSerializableExtra("order");

        update_order = findViewById(R.id.Order_button);
        cancel_order = findViewById(R.id.Delete_button);
        tahini = findViewById(R.id.tahini);
        pickles = findViewById(R.id.pickels);
        humus = findViewById(R.id.humus);
        comments = findViewById(R.id.comments);
        name = findViewById(R.id.name);

        updateUI(order);

        // add listeners to buttons and text changes
        update_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order = new Order();
                order.setName(name.getText().toString());
                order.setComments(comments.getText().toString());
                order.setHumus(humus.isChecked());
                order.setTahini(tahini.isChecked());
                order.setId(db.getCurrentId());
                order.setPickels(pickles.getText().toString());
                pickles.setText(order.pickels);
                order.setStatus(Order.Status.waiting);
                db.addNewOrder(order);

//                Intent editIntent = new Intent(EditActivity.this, EditActivity.class);
//
//                startActivity(editIntent);
//                finish();
            }


        });
        db.listenForStatusChange(this);

        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeOrder(order);
//                db.removeSP(db.getCurrentId());
                Intent startIntent = new Intent(EditActivity.this, NewOrder.class);
                startActivity(startIntent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        db.destroyListener();
        super.onDestroy();
    }

    private void updateUI(Order order){
        tahini.setChecked(order.tahini);
        pickles.setText(order.pickels);
        humus.setChecked(order.humus);
        comments.setText(order.comments);
        name.setText(order.name);

    }


}
