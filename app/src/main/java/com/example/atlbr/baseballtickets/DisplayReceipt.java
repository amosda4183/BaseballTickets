package com.example.atlbr.baseballtickets;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_receipt);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ticket Purchase Receipt");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.baseball);


        //Setup textview
        final TextView receipt = findViewById(R.id.receipt);

        //Receive intent data and set it to output
        Intent intent = getIntent();

        String output = intent.getStringExtra("receipt");

        receipt.setText(output);

    }
}
