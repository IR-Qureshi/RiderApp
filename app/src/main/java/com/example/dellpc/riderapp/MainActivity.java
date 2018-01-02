package com.example.dellpc.riderapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = mFirebaseDatabase.getInstance();


        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

//                    Intent intent = new Intent(MainActivity.this, SigninActivity.class);
//                    startActivity(intent);

                    // MainActivity.this.finish();
                }
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // Log.d(TAG,"mAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                    Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                    startActivity(intent);
                }

            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!= null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
}
