package com.example.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Main5health extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5health);


    }



    public void startbtn(View view) {
        Intent i =new Intent(this,Main5start.class);
        startActivity(i);
    }

    public void cravingbtn(View view) {
        Intent i =new Intent(this,smokingcraving.class);
        startActivity(i);
    }

    public void healthbtn(View view) {
        Intent i =new Intent(this,smokinghealth.class);
        startActivity(i);
    }
}
