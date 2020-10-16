package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class alcoholhealth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcoholhealth);
    }

    public void startbtn(View view) {
        Intent i=new Intent(this,alcoholquit.class);
        startActivity(i);
    }

    public void cravingbtn(View view) {

        Intent i=new Intent(this,alcoholcarving.class);
        startActivity(i);
    }

    public void healthbtn(View view) {

        Intent i=new Intent(this,alchealthstat.class);
        startActivity(i);
    }
}
