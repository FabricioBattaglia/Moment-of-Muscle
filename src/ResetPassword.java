package com.example.profilescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResetPassword extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Button submitNewPasswordButton = (Button) findViewById(R.id.submitNewPassword);
        submitNewPasswordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveNewPassword();
            }
        });

        Button cancelChangesPassword = (Button) findViewById(R.id.cancelNewPassword);
        cancelChangesPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelPassword();
            }
        });
    }

    public void saveNewPassword(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void cancelPassword(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

}