package com.example.orders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewOrder extends AppCompatActivity {

    // add buttons and text views stuff
    Button make_order;
    Button tahini;
    EditText pickles;
    CheckBox humus;
    EditText comments;
    EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make_order = findViewById(R.id.Order_button);
        tahini = findViewById(R.id.tahini);
        pickles = findViewById(R.id.pickels);
        humus = findViewById(R.id.humus);
        comments = findViewById(R.id.comments);
        name = findViewById(R.id.name);

        // add listeners to buttons and text changes

    }
}
