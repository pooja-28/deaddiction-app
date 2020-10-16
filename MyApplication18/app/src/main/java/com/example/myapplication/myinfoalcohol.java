package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class myinfoalcohol extends AppCompatActivity {
EditText edtalday,edtstartday,edtcost,edtyear,edtstopday;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference db;
    String id;
    String userKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        db = database.getReference("myinfoalcohol");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfoalcohol);

        edtalday=findViewById(R.id.edtalpday);
        edtcost=findViewById(R.id.edtcost);
        edtstartday=findViewById(R.id.edtstartday);
        edtstopday=findViewById(R.id.edtstopday);
        edtyear=findViewById(R.id.edtyear);
    }

    public void infoalc(View view) {
        String ald=edtalday.getText().toString();
        int perday= Integer.valueOf(ald);

        String c=edtcost.getText().toString();
        int cost=Integer.valueOf(c);

        String strtday=edtstartday.getText().toString();
        int startday=Integer.valueOf(strtday);

        String stpday=edtstopday.getText().toString();
        int stopday=Integer.valueOf(stpday);

        String y=edtyear.getText().toString();
        int year=Integer.valueOf(y);

        SimpleDateFormat mdformat = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss aa", Locale.getDefault());
        String date =  mdformat.format(new Date());
        if(ald.isEmpty() || c.isEmpty() || strtday.isEmpty() || y.isEmpty()|| stpday.isEmpty()){
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
        }
        else {
            alcadapter i=new alcadapter(userKey,date,perday,cost,year,startday,stopday);
            db.child(userKey).setValue(i);
            edtalday.setText("");
            edtyear.setText("");
            edtstartday.setText("");
            edtcost.setText("");
            edtstopday.setText("");
        }

    }
}
