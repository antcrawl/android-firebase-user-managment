package com.antcrawl.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.antcrawl.FirebaseUtill;
import com.antcrawl.R;
import com.antcrawl.dto.LoginDetails;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    Firebase firebase;
    Context ctx;
    LoginDetails loginDetails = new LoginDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        firebase = FirebaseUtill.getConnection(this);

    }

    public void signUp(View view) {
        Intent intent = new Intent(this,UserSignUp.class);
        startActivity(intent);
    }

    public void login(View view) {
        loginDetails.setEmail(((EditText) findViewById(R.id.user_email)).getText().toString());
        loginDetails.setPassword(((EditText) findViewById(R.id.user_password)).getText().toString());

        firebase.authWithPassword(loginDetails.getEmail(), loginDetails.getPassword(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {

                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Toast.makeText(getApplicationContext(), "Login Successfully " + authData.getUid() + "  " + authData.getProvider(), Toast.LENGTH_LONG).show();

                Log.d("success", authData.getUid());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("error ", firebaseError.toString());
                Toast.makeText(getApplicationContext(), String.valueOf(firebaseError), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
