package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main4Activity extends navigationdrawer{
    DatabaseReference db;
    FirebaseDatabase database;
    String userKey;

    FirebaseUser user;
    String cig,cost,packno,startsmoke,stopsmoke,cdate;
    TextView tlife,tcalories,tmoney,tcigsave,tday1,hour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        db = database.getReference("myinfo");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main4, null, false);
        d1.addView(contentView, 0);


     tday1=findViewById(R.id.tday);
        tmoney = findViewById(R.id.tmoney);;
        tlife = findViewById(R.id.tlife);
        tcalories = findViewById(R.id.tcalories);
        tcigsave=findViewById(R.id.tcigsave);
        hour=findViewById(R.id.txthours);
    }



    @Override
    protected void onStart() {
        super.onStart();
        db.child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cig = String.valueOf(dataSnapshot.child("cigday").getValue());
                packno = String.valueOf(dataSnapshot.child("packno").getValue());
                cost = String.valueOf(dataSnapshot.child("cost").getValue());
               startsmoke = String.valueOf(dataSnapshot.child("startsmoke").getValue());
                stopsmoke=String.valueOf(dataSnapshot.child("stopsmoke").getValue());
                cdate=String.valueOf(dataSnapshot.child("date").getValue());
                setinfo();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setinfo()  {

        if(packno != "null" & cost != "null" & startsmoke != "null" & stopsmoke != "null" & cig != "null" & cdate !="null"){


            ParsePosition pp1 = new ParsePosition(0);


            SimpleDateFormat  format = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss aa", Locale.getDefault());

            Date date1;
            date1 = format.parse(cdate,pp1);

           Date date=new Date();
           String dd=format.format(new Date());
           Date date2=format.parse(dd,pp1);

            long difference = Math.abs(date.getTime() - date1.getTime());
            long diff = difference / (24 * 60 * 60 * 1000);
            long h=difference/3600000;
            hour.setText(String.valueOf(h));

            String g=Long.toString(diff);
            tday1.setText(g);

            Integer pn,c;
                    c=Integer.parseInt(cost);
                    pn=Integer.parseInt(packno);

                    Double cd=Double.parseDouble(cig);
                    Double i=(Double) (((c/pn)*cd)*diff);

                    Double j=(Double) ((c/pn)*cd)*diff;
                    String sm =j.toString().trim();
                    tmoney.setText(sm+" Rs");

                    Integer s1day =Integer.valueOf(startsmoke);
                    Integer s2day=Integer.valueOf(stopsmoke);
                    Integer day1=((s2day-s1day)*525600);
                    Integer day=day1/1440;
                    tlife.setText(day.toString().trim()+" minutes");
                    Integer cal=((s2day-s1day)*200);
                    tcalories.setText(cal.toString().trim());
                    Integer a=Integer.valueOf(cig);

                    Long p=a*diff;
                    String pt=p.toString();
                    tcigsave.setText(pt);




    }}
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shealthmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.health){
            Intent intent=new Intent(this,Main5health.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}
