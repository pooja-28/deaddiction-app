package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    EditText etname,etemail,etmsg;
    String sName,sMsg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        etname=(EditText)findViewById(R.id.fb1);
        etmsg=(EditText)findViewById(R.id.fb3);
    }
    public void sendFeedback(View view) {

        sName=etname.getText().toString().trim();
        sMsg=etmsg.getText().toString().trim();
        if(!(sName.equals("") && sMsg.equals(""))){
            String feedback="Name : "+sName+"\n\nFeedback : "+sMsg;

            String[] TO = {"nathomkar113@gmail.com"};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "BeyondTheDecor Feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT,feedback);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(feedback.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(getApplicationContext(),"Please enter all data",Toast.LENGTH_SHORT).show();
        }
    }
}
