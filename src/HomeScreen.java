package com.example.mom;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button button1 = (Button) findViewById(R.id.toProfileButton);
        Button button2 = (Button) findViewById(R.id.toJobBoardButton);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openProfileScreen();
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openJobBoard();
            }

        });

    }

    public void openProfileScreen() {
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void openJobBoard() {
        Intent intent = new Intent(this, JobDescriptionScreen.class);
        startActivity(intent);
    }

}



