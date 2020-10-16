package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText edtemail, edtpassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        progressBar = findViewById(R.id.progressbar);
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
        }
    }

    public void userlogin() {
        final String email = edtemail.getText().toString();
        final String password = edtpassword.getText().toString();


        if (email.isEmpty()) {
            edtemail.setError("email is required");
            edtemail.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtemail.setError("email incorrect");

            edtemail.requestFocus();
            Toast.makeText(this, "please enter a proper email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            edtpassword.setError("password is required");
            edtpassword.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            edtpassword.setError("minimum length of password is 6");
            Toast.makeText(this, "Please enter with minimum 6 number", Toast.LENGTH_LONG).show();
            edtpassword.requestFocus();
            return;
        }

        // progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        } else {




                                Intent j = new Intent(MainActivity.this, Main3Activity.class);
                                startActivity(j);

                        }
                    }
                });
    }

    public void register(View view) {
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);
    }

    public void loginbtn(View view) {
        userlogin();
    }

    public void forgotpass(View view) {
        Intent i = new Intent(MainActivity.this, forgotpass.class);
        startActivity(i);
    }
}
