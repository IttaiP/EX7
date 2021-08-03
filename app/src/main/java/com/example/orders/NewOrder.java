package com.example.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class NewOrder extends AppCompatActivity {

    // add buttons and text views stuff
    Button make_order;
    EditText pickles;
    CheckBox tahini;
    CheckBox humus;
    EditText comments;
    EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB db = OrderApp.getInstance().getDB();

        make_order = findViewById(R.id.Order_button);
        tahini = findViewById(R.id.tahini);
        pickles = findViewById(R.id.pickels);
        humus = findViewById(R.id.humus);
        comments = findViewById(R.id.comments);
        name = findViewById(R.id.name);

        // add listeners to buttons and text changes
        make_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newId = UUID.randomUUID().toString();
                db.updateSP(newId);

                Order order = new Order();
                order.setName(name.getText().toString());
                order.setComments(comments.getText().toString());
                order.setHumus(humus.isChecked());
                order.setTahini(tahini.isChecked());
                order.setId(newId);
                order.setPickels(pickles.getText().toString());
                order.setStatus(Order.Status.waiting);
                db.addNewOrder(order);

                Intent editIntent = new Intent(NewOrder.this, EditActivity.class);
                editIntent.putExtra("order", order);
                startActivity(editIntent);
                finish();
            }


            });

    }

}
