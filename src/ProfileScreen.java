package com.example.mom;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        Button button = (Button) findViewById(R.id.returnHomeScreen);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnHomeScreen();
            }

        });
    }

    public void returnHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}

