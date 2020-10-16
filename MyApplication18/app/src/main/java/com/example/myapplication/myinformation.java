package com.example.myapplication;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class myinformation extends AppCompatActivity {
    DatabaseReference db;
    String id;
    FirebaseDatabase database;
    FirebaseUser user;
    String userKey;
    EditText  ecigday, ecost, epackno,edtsday,edtday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        db = database.getReference("myinfo");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinformation);


        ecigday = findViewById(R.id.edtcigday);
        ecost = findViewById(R.id.edtcost);
        edtsday=findViewById(R.id.edtsday);
        epackno = findViewById(R.id.edtpackno);
         edtday=findViewById(R.id.edtday);

    }


    public void infosubmit(View view) {
        String cigday = ecigday.getText().toString();
        int cigperday = Integer.valueOf(cigday);
        String cos = ecost.getText().toString();
        int cost = Integer.valueOf(cos);
        String ey = epackno.getText().toString();
        int packno = Integer.valueOf(ey);
        String esday=edtsday.getText().toString();
        int startsmoke=Integer.valueOf(esday);
        String estop=edtday.getText().toString();
        int stopsmoke=Integer.valueOf(estop);
        SimpleDateFormat mdformat = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss aa", Locale.getDefault());
        String date =  mdformat.format(new Date());
        if (esday.isEmpty() || cigday.isEmpty() || cos.isEmpty() || estop.isEmpty()) {
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_LONG).show();
        } else {

            infoadapter i = new infoadapter(userKey,cigperday,startsmoke,stopsmoke, cost, packno,date);
            db.child(userKey).setValue(i);
            epackno.setText(" ");
            ecost.setText(" ");
            ecigday.setText(" ");
edtsday.setText(" ");
edtday.setText(" ");

        }
    }


}
