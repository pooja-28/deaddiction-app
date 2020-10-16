package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

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

public class alchealthstat extends AppCompatActivity {

    DatabaseReference db;
    FirebaseDatabase database;
    String userKey;
    FirebaseUser user;
    String date;

    smokehealthadapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        db = database.getReference("myinfoalcohol");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userKey = user.getUid();

        setContentView(R.layout.activity_alchealthstat);

        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hwZPb1uQqmY\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
        WebView displayYoutubeVideo = (WebView) findViewById(R.id.video);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");




        db.child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                date = String.valueOf(dataSnapshot.child("date").getValue());
                if (date != null) {
                    ParsePosition pp1 = new ParsePosition(0);


                    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss aa", Locale.getDefault());

                    Date date1;
                    date1 = format.parse(date, pp1);

                    Date date = new Date();
                    String dd = format.format(new Date());
                    Date date2 = format.parse(dd, pp1);

                    long difference = Math.abs(date.getTime() - date1.getTime());
                    long diff = difference / (24 * 60 * 60 * 1000);
                   String s = " "+String.valueOf(diff) + " %";
                    listView = findViewById(R.id.list1);
                    String[] status = new String[]{"Sudden death risk lowered" +s, "Improves critical thinking" + s, "Reduced risk of liver cancer" + s,
                            "Anxiety and depression relief" + s, "Reduced heart disease risk" + s};

                    adapter = new smokehealthadapter(getApplicationContext(), status);
                    listView.setAdapter(adapter);

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}