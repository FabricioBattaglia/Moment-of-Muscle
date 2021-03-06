//this is the profile page

package com.example.mom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ProfileScreen extends AppCompatActivity {

    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(fAuth.getCurrentUser() != null) {
                    if (documentSnapshot.exists()) {
                        phone.setText(documentSnapshot.getString("phone"));
                        fullName.setText(documentSnapshot.getString("fName"));
                        email.setText(documentSnapshot.getString("email"));

                    } else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            }
        });

        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openEditProfile();
            }
        });

        Button resetPasswordButton = (Button) findViewById(R.id.resetPasswordButton);
        resetPasswordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openResetPassword();
            }
        });

        ImageButton backToMain = (ImageButton) findViewById(R.id.imageButton3);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout1();
            }
        });

    }

    public void openEditProfile(){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void openResetPassword(){
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }

    public void returnToMain(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void logout1() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(this, SecondActivity.class));
        finish();
    }

}