package com.example.orders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ready extends AppCompatActivity {


    private Button doneButton;

    DB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready);
        doneButton = findViewById(R.id.done);

        db = OrderApp.getInstance().getDB();

        // add status change listener
        db.listenForStatusChange(this);


        doneButton.setOnClickListener(val -> {
            // dataBase updates
            db.removeSP(db.getCurrentId());
            Intent newOrderIntent = new Intent(this, NewOrder.class);
            startActivity(newOrderIntent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        db.destroyListener();
        super.onDestroy();
    }
}
