package com.example.mom;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = (Button) findViewById(R.id.loginBtn);
        Button createAccountButton = (Button) findViewById(R.id.createAccountButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHomeScreen();
            }

        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnToActivityMain();
            }

        });

    }

    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void returnToActivityMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}




