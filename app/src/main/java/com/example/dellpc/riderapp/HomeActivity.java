package com.example.dellpc.riderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;
    ClassRecOrder classRecOrder;
    private AdapterReceiveOrder mAdapterOrder;
    public ListView mListView;
    List<ClassRecOrder> classOrders;
    //generic type indicator for item
    ArrayList<ClassRecOrder> yourStringArray;
    GenericTypeIndicator<ArrayList<ClassRecOrder>> t ;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Riders").child("orders");

        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    t = new GenericTypeIndicator<ArrayList<ClassRecOrder>>() {};

                    yourStringArray = dataSnapshot.getValue(t);

                    mAdapterOrder.add(yourStringArray.get(0));


                    for (i = 0; i < yourStringArray.size(); i++) {
                        mAdapterOrder.add(yourStringArray.get(i));

                    }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
        mListView = (ListView) findViewById(R.id.list);

        classOrders = new ArrayList<>();
        Log.d("showdata", classOrders.toString());
        mAdapterOrder = new AdapterReceiveOrder(HomeActivity.this, R.layout.custom_rec_order, classOrders);
        mListView.setAdapter(mAdapterOrder);

    }
}
