package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main5alcohol extends navigationalcohol {
    DatabaseReference db;
    FirebaseDatabase database;
    String userKey;
    FirebaseUser user;

    TextView tmoney,tcalories,tday,tlife,hour,avoid;
    String money,cal,pday,life,date,startday,stopday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        db = database.getReference("myinfoalcohol");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();



        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main5alcohol, null, false);
        d1.addView(contentView, 0);

        tmoney=findViewById(R.id.tmoney);
        tcalories=findViewById(R.id.tcalories);
        tday=findViewById(R.id.tday);
        tlife=findViewById(R.id.tlife);
        hour=findViewById(R.id.txthours);
        avoid=findViewById(R.id.tav);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db.child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              date=String.valueOf(dataSnapshot.child("date").getValue());
              money=String.valueOf(dataSnapshot.child("cost").getValue());
              pday=String.valueOf(dataSnapshot.child("perday").getValue());
              startday=String.valueOf(dataSnapshot.child("startday").getValue());
              stopday=String.valueOf(dataSnapshot.child("stopday").getValue());
              infoupdate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public  void infoupdate(){

        if(date!=null & money!=null & pday!= null & startday!=null & stopday!=null){
            ParsePosition pp1 = new ParsePosition(0);


            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss aa", Locale.getDefault());

            Date date1;
            date1 = format.parse(date,pp1);

            Date date=new Date();
            String dd=format.format(new Date());
            Date date2=format.parse(dd,pp1);

            long difference = Math.abs(date.getTime() - date1.getTime());
            long diff = difference / (24 * 60 * 60 * 1000);
            long h=difference/3600000;
            hour.setText(String.valueOf(h));

            String g=Long.toString(diff);
            tday.setText(g);

            Integer c,p;
            c=Integer.parseInt(money);
            p=Integer.parseInt(pday);
            long o=c*p*diff;
            tmoney.setText(String.valueOf(o)+" Rs");

            Integer s1day =Integer.valueOf(startday);
            Integer s2day=Integer.valueOf(stopday);
            Integer day1=((s2day-s1day)*525600);
            Integer day=day1/1440;
            tlife.setText(day.toString().trim()+" minutes");
            Integer cal=((s2day-s1day)*200);
            tcalories.setText(cal.toString().trim());

            long out=diff*p;
            avoid.setText(String.valueOf(out));



        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shealthmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.health){
            Intent intent=new Intent(this,alcoholhealth.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
