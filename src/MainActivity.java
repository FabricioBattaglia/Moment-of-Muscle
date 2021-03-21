//this is the register screen

package com.example.profilescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToLoginBtn = (Button) findViewById(R.id.logInButton);
        goToLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        mName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerButton);
        mLoginBtn = findViewById(R.id.logInButton);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //if some account is already logged in, there is no need to log in or register
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            finish();
        }

        if(fAuth.getCurrentUser() != null && !fAuth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(this, EmailVerification.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                String fullName = mName.getText().toString();
                final String phone = mPhone.getText().toString();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email cannot be empty");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password cannot be empty");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be at least 6 Characters long");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user to firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //verification email
                            FirebaseUser fireUser = fAuth.getCurrentUser();
                            fireUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this, "Check your e-mail for the verification e-mail", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            //
                            Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName", fullName);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            });

                            FirebaseUser checkVerified = fAuth.getCurrentUser();
                            if(checkVerified.isEmailVerified()) {
                                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                            }
                            else{
                                startActivity(new Intent(getApplicationContext(), EmailVerification.class));
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Try again later" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void goToLogin(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}