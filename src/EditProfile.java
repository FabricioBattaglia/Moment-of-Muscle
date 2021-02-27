package com.example.profilescreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfile extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button saveChangesButton = (Button) findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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