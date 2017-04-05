package com.halanx.tript.userapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.halanx.tript.userapp.Interfaces.DataInterface;
import com.halanx.tript.userapp.POJO.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by samarthgupta on 13/02/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFname, inputLname, inputMobile , inputAddress,inputIcode;
    private Button btnRegister;
   // private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference ref ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        //Get Firebase auth instance
            auth = FirebaseAuth.getInstance();
            ref = firebaseDatabase.getReference();
            user=auth.getCurrentUser();

            btnRegister = (Button)findViewById(R.id.btn1);
            inputEmail = (EditText) findViewById(R.id.tv_email);
            inputPassword = (EditText) findViewById(R.id.tv_password);
          //  progressBar = (ProgressBar) findViewById(R.id.progressBar);
            inputAddress = (EditText)findViewById(R.id.tv_address);
            inputFname=(EditText)findViewById(R.id.tv_firstName);
            inputLname= (EditText)findViewById(R.id.tv_lastName);
            inputMobile = (EditText)findViewById(R.id.tv_mobile);
            inputIcode = (EditText)findViewById(R.id.tv_inviteCode);


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String email = inputEmail.getText().toString().trim();
                    final String password = inputPassword.getText().toString().trim();
                    final String firstName = inputFname.getText().toString().trim();
                    final String lastName = inputLname.getText().toString().trim();
                    final String mobileNumber = inputMobile.getText().toString().trim();
                    final String address = inputAddress.getText().toString().trim();
                    final String icode = inputIcode.getText().toString().trim();



                    //  CHECKING ALL EDIT TEXT FIELDS
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (TextUtils.isEmpty(firstName)||TextUtils.isEmpty(lastName)) {
                        Toast.makeText(getApplicationContext(), "Enter First and Last name", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (TextUtils.isEmpty(mobileNumber)) {
                        Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (mobileNumber.length()!=10) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else if (TextUtils.isEmpty(address)) {
                        Toast.makeText(getApplicationContext(), "Enter your city", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   // progressBar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                  //  progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                    } else {

                                        UserInfo userInfo = new UserInfo(firstName,lastName,address,mobileNumber,email);
                                        //SET VALUES TO THE OBJECT
                                        ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userInfo);
                                        sendRegistrationDataToServer(userInfo);
                                        startActivity(new Intent(RegisterActivity.this, SigninActivity.class));
                                        finish();
                                    }
                                }
                            });

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }

    void sendRegistrationDataToServer(UserInfo info) {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://ec2-34-208-181-152.us-west-2.compute.amazonaws.com/").
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        DataInterface client = retrofit.create(DataInterface.class);

        Call<UserInfo> call = client.putDataOnServer(info);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                // String s = response.body().getEmailId();
                Log.i("TAG1","DONE PUT");
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.i("TAG1","FAIL");
            }
        });

    }
}


