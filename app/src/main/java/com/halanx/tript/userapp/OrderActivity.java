package com.halanx.tript.userapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class OrderActivity extends AppCompatActivity {

    Button checkout,delivery;
    LinearLayout charges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, PaymentActivity.class));
            }
        });

        delivery = (Button) findViewById(R.id.delivery);
        charges = (LinearLayout) findViewById(R.id.charges);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, PaymentActivity.class));
                delivery.setVisibility(View.GONE);
                charges.setVisibility(View.VISIBLE);
            }
        });
    }
}
