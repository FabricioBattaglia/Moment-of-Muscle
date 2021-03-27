package com.example.profilescreen;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class JobDescriptionScreen extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    FirestoreRecyclerOptions<Job>options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description_screen);

        EditText userEntryJobTitle = (EditText) findViewById(R.id.JobPostingEntry);
        Button addJobButton = (Button) findViewById(R.id.AddJobButton);
        Spinner jobTypeSpinner = (Spinner) findViewById(R.id.JobTypeSpinner);
        EditText jobPrice = (EditText) findViewById(R.id.jobPrice);
        EditText jobDescription = (EditText) findViewById(R.id.jobDescription);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        ArrayAdapter<String> jobTypeAdapter = new ArrayAdapter<String>(JobDescriptionScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.JobType));

        ArrayAdapter<String> jobRadiusAdapter = new ArrayAdapter<String>(JobDescriptionScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.JobRadius));


        jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobTypeSpinner.setAdapter(jobTypeAdapter);

        jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code to send information to database after button click
                String jobTitleString = userEntryJobTitle.getText().toString();
                String jobTypeString = jobTypeSpinner.getSelectedItem().toString();
                String userID = fAuth.getCurrentUser().getUid();
                String jobPriceString = jobPrice.getText().toString();
                String jobDescriptionString = jobDescription.getText().toString();

                DocumentReference documentReference = fStore.collection("job_board").document();
                Map<String, Object> job_board = new HashMap<>();
                job_board.put("category", jobTypeString);
                job_board.put("job_title", jobTitleString);
                job_board.put("user_id", userID);
                job_board.put("price", jobPriceString);
                job_board.put("job_description", jobDescriptionString);
                job_board.put("isAccepted", false);

                documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(JobDescriptionScreen.this, "Job Posted", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });

    }

}