package com.example.dellpc.riderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {

    private static final String MYTAG = "SigninActivity";
    EditText EditUserText;
    EditText EditPassText;
    Button BtnLogin;
    TextView TextSignup;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = mFirebaseDatabase.getInstance();
        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();




        EditUserText = (EditText) findViewById(R.id.Edit_userName);
        EditPassText = (EditText) findViewById(R.id.Edit_userPassword);
        BtnLogin = (Button) findViewById(R.id.Btn_login);


//        //attaching a authentication listener
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };
//    }
    }
    //sign in with existing user
    public void Signin(View view){
        mFirebaseAuth.signInWithEmailAndPassword(EditUserText.getText().toString(),EditPassText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(MYTAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        Log.d("useruid",mFirebaseAuth.getCurrentUser().getUid());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Log.w(MYTAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(SigninActivity.this,"User not exists",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(SigninActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
//                            intent.putExtra("username",mFirebaseAuth.getCurrentUser().getUid());
//                            Log.d("idtest",mFirebaseAuth.getCurrentUser().getUid());
                            startActivity(intent);
                        }
                        Log.d("usertest","notworking");
                        //   }

                        // ...
                    }
                });
    }

}
