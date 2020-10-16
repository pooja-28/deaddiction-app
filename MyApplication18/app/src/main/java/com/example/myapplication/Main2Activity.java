package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edt1,edt2,edt3,edt4;
    ProgressBar progressBar;

    public static final String TAG = "REGISTRATION:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        progressBar=findViewById(R.id.progressbar);

    }
    public void registeruser() {
        final String email = edt4.getText().toString();
        String password = edt2.getText().toString();
        String confirmpass = edt3.getText().toString();
        final String name = edt1.getText().toString();



        if (email.isEmpty()) {
            edt4.setError("email is required");
            edt4.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt4.setError("email incorrect");

            edt4.requestFocus();
            Toast.makeText(this, "please enter a proper email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            edt2.setError("password is required");
            edt2.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            edt2.setError("minimum length of password is 6");
            Toast.makeText(this, "Please enter with minimum 6 number", Toast.LENGTH_LONG).show();
            edt2.requestFocus();
            return;
        }
        if (email.equals("admin@gmail.com") && password.equals("9702046780")) {
            edt2.setError("Not available");
            edt2.requestFocus();
            Toast.makeText(this, "Not available", Toast.LENGTH_LONG).show();
            return;
        }
        if (!confirmpass.equals(password)) {
            edt3.setError("password is not matching");
            Toast.makeText(this, "password not matching", Toast.LENGTH_LONG).show();
            edt3.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getApplicationContext(), "successfully registered", Toast.LENGTH_LONG).show();
                            emailverifcation();
                        }

                    }

                });



    }

    public void emailverifcation(){
        final FirebaseUser user = mAuth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        // Re-enable button
                        findViewById(R.id.btnsubmit).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(Main2Activity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(Main2Activity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void submitbtn(View view) {
        registeruser();



    }

    public void reset(View view) {
        edt1.setText(" ");
        edt2.setText(" ");
        edt3.setText(" ");
        edt4.setText(" ");

    }

    public void login(View view) {

        Intent i=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);
    }
}


