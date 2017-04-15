package com.halanx.tript.userapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.halanx.tript.userapp.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void onPaymentOptionClick(View view) {
        startActivity(new Intent(PaymentActivity.this, MapsActivity.class));
        finish();
        }
}
