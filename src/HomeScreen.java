package com.example.mom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button viewProfile = (Button) findViewById(R.id.toProfileButton);
        viewProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goProfilePage();
            }
        });

        Button viewJob = (Button) findViewById(R.id.toJobButton);
        viewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToJob();
            }
        });

        Button viewPost = (Button) findViewById(R.id.toPostedButton);
        viewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPost();
            }
        });
    }

    public void goProfilePage(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void goToJob(){
        Intent intent = new Intent(this, JobDescriptionScreen.class);
        startActivity(intent);
    }

    public void goToPost(){
        Intent intent = new Intent(this, JobBoard.class);
        startActivity(intent);
    }
}