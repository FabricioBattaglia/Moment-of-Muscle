package com.example.mom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mName, mPhone;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mName = (EditText) findViewById(R.id.newName);

        mPhone = (EditText) findViewById(R.id.newPhone);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Button saveChangesButton = (Button) findViewById(R.id.saveChangesButton);

        saveChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //final String email = mEmail.getText().toString().trim();
                String fullName = mName.getText().toString();
                final String phone = mPhone.getText().toString();
                userID = fAuth.getCurrentUser().getUid();

                //it needs this email otherwise it will set to blank
                final String email = fAuth.getCurrentUser().getEmail();
                //

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
                saveChangesProfile();
            }
        });

        Button cancelChangesButton = (Button) findViewById(R.id.cancelChangesButton);
        cancelChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelChangesProfile();
            }
        });
    }

    public void saveChangesProfile(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void cancelChangesProfile(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }
}