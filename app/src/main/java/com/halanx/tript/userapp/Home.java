package com.halanx.tript.userapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.halanx.tript.userapp.Activities.BecomeShopperActivity;
import com.halanx.tript.userapp.Activities.HelpActivity;
import com.halanx.tript.userapp.Activities.OrderActivity;
import com.halanx.tript.userapp.Activities.PaymentActivity;
import com.halanx.tript.userapp.Activities.ReferEarnActivity;
import com.halanx.tript.userapp.Activities.SigninActivity;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    ImageView cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cart = (ImageView) findViewById(R.id.imageButton);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,OrderActivity.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();

        // set the fragment initially
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(Home.this, SigninActivity.class));
                    finish();
                }
            }
        };
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // set the fragment initially

            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_order) {
            startActivity(new Intent(Home.this,OrderActivity.class));
        } else if (id == R.id.nav_payment) {
            startActivity(new Intent(Home.this,PaymentActivity.class));
        } else if (id == R.id.nav_favourites) {

        } else if (id == R.id.nav_list) {

        } else if (id == R.id.nav_pts) {

        }// more buttons to be initialised i.e those below points
        else if (id == R.id.nav_ref) {
            startActivity(new Intent(Home.this,ReferEarnActivity.class));
        }
        else if (id == R.id.nav_shopper) {
            startActivity(new Intent(Home.this,BecomeShopperActivity.class));
        }
        else if (id == R.id.nav_help) {
            startActivity(new Intent(Home.this,HelpActivity.class));
        }

        else if (id == R.id.nav_sign_out) {
            mAuth.signOut();
            mAuth.addAuthStateListener(mAuthStateListener);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
