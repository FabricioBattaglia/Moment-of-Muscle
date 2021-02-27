package com.example.mom;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class JobDescriptionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description_screen);

        EditText userEntryJobTitle = (EditText) findViewById(R.id.JobPostingEntry);
        Button addJobButton = (Button) findViewById(R.id.AddJobButton);
        TextView jobBoard = (TextView) findViewById(R.id.JobBoard);

        addJobButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String jobTitle = userEntryJobTitle.getText().toString();
                jobBoard.setText(jobTitle);
            }
        });
    }
}