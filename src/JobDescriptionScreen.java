package com.example.mom;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
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
        Spinner yardworkSpinner = (Spinner) findViewById(R.id.JobTypeSpinner);
        Spinner jobRadiusSpinner = (Spinner) findViewById(R.id.JobRadiusSpinner);

        ArrayAdapter<String> jobTypeAdapter = new ArrayAdapter<String>(JobDescriptionScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.JobType));

        jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yardworkSpinner.setAdapter(jobTypeAdapter);

        ArrayAdapter<String> jobRadiusAdapter = new ArrayAdapter<String>(JobDescriptionScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.JobRadius));

        jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobRadiusSpinner.setAdapter(jobRadiusAdapter);

        addJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code to send information to database after button click
                String jobTitleString = userEntryJobTitle.getText().toString();
                String yardworkString = yardworkSpinner.getSelectedItem().toString();
                String jobRadiusString = jobRadiusSpinner.getSelectedItem().toString();
                //Send both strings to database(job board) -> Can be printed if needed for Sprint 5
            }
        });
    }
}