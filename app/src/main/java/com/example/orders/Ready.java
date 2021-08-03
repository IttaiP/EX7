package com.example.orders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ready extends AppCompatActivity {


    private Button doneButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready);



        doneButton.setOnClickListener(val -> {
            // dataBase updates
            Intent newOrderIntent = new Intent(this, NewOrder.class);
            startActivity(newOrderIntent);
            finish();
        });
    }
}
