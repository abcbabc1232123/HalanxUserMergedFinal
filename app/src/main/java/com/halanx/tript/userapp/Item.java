package com.halanx.tript.userapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Item extends AppCompatActivity {
    EditText ed1;
    Button plus, minus,cart;  int i; String val;
    ImageView imageView, imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ed1 = (EditText) findViewById(R.id.quantity);
        plus = (Button) findViewById(R.id.increment);
        minus = (Button) findViewById(R.id.decrement);
        cart = (Button) findViewById(R.id.payment);
        imageView = (ImageView) findViewById(R.id.imgFav);
        imageView2 = (ImageView) findViewById(R.id.imgFav2);

        val = ed1.getText().toString();
        try{i = Integer.parseInt(val);}
        catch (NumberFormatException a) { a.printStackTrace();}

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                val = Integer.toString(i);
                ed1.setText(val);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i == 0){ }
                i--;
                val = Integer.toString(i);
                ed1.setText(val);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Item.this, MapsActivity.class);
//                startActivity(intent);
                Toast.makeText(Item.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                Toast.makeText(Item.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(Item.this, "Removed From Favourites", Toast.LENGTH_SHORT).show();
            }
        });
    }
}