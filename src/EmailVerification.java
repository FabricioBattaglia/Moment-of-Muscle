package com.example.momentofmuscle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        Button resendCode = findViewById(R.id.verificationBtn);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser fireUser = fAuth.getCurrentUser();

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fireUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EmailVerification.this, "Check your e-mail for the verification e-mail", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmailVerification.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button goToHome = (Button) findViewById(R.id.alreadyVerified);
        goToHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //STILL HAS TO BE WORKED ON (CHECK IF USER IS VERIFIED)
                //FirebaseAuth checkAuth = FirebaseAuth.getInstance();
                //FirebaseUser checkUser = checkAuth.getCurrentUser();
                //if(checkUser.isEmailVerified()) {
                //   goToHomeVerified();
                //}
                //else{
                //    Toast.makeText(EmailVerification.this, "Your profile is not verified, please verify your e-mail", Toast.LENGTH_SHORT).show();
                //}
                //tried sending back to register screen since it checks if the user
                //is verified or not, but it worked the same as the above code
                goToHomeVerified();
            }
        });

        Button logoutButton = (Button) findViewById(R.id.eLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout1();
            }
        });
    }

    public void goToHomeVerified(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void logout1() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(this, SecondActivity.class));
        finish();
    }
}