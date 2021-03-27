package com.example.profilescreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobPost extends AppCompatActivity {

    String documentID;
    int pos;

    public static final String TAG = "TAG";
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionRef; //notebookRef
    List<Job> jobListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        TextView jobTitle = findViewById(R.id.jobtitleviewtext);
        TextView jobCategory = findViewById(R.id.jobcategoryviewtext);
        TextView jobPrice = findViewById(R.id.jobpriceviewtext);
        TextView jobDescription = findViewById(R.id.jobdescriptionviewtext);
        Button acceptJobButton = (Button) findViewById(R.id.acceptButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);


        fAuth = FirebaseAuth.getInstance();
        String currentUser = fAuth.getCurrentUser().getUid();

        documentID = JobBoard.documentID;
        pos = JobBoard.pos;

        jobListing = new ArrayList<>();

        fStore = FirebaseFirestore.getInstance();
        final int[] count = {0};

        fStore.collection("job_board").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    //we have documents inside
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    //traverse the list
                    for(DocumentSnapshot d : list){
                        Job j = d.toObject(Job.class);
                        if(d.getId().equals(documentID)) {

                            jobListing.add(0, j);
                            count[0]++;

                        }
                    }
                    //adapter.notifyDataSetChanged();
                }
                //as of now this displays only the element 0 of the list
                //if(jobListing.get(0) != null) {
                if(count[0] != 0){
                    jobTitle.setText(jobListing.get(0).job_title);
                    jobCategory.setText(jobListing.get(0).category);
               //     jobCityState.setText(jobListing.get(0).city_state);
                    jobPrice.setText(jobListing.get(0).price);
                    jobDescription.setText(jobListing.get(0).job_description);
                }
                else{
                    jobBoard.setText("No jobs? Try posting a new job!");
                }
            }
        });

        acceptJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("job_board").document(documentID);
                if(jobListing.get(0).user_id.equals(currentUser)) {
                    Toast.makeText(JobPost.this, "You cannot accept your own job", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String,Object> job_board = new HashMap<>();

                    job_board.put("category", jobListing.get(0).category);
                    job_board.put("job_title", jobListing.get(0).job_title);
                    job_board.put("user_id", jobListing.get(0).user_id);
                    job_board.put("price", jobListing.get(0).price);
                    job_board.put("job_description", jobListing.get(0).job_description);
                    job_board.put("isAccepted", true);

                    documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: Job accepted, wait for creator to approve "+ documentID);

                        }
                    });
                    //go back to another page
                    returnToMain();
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelChangesProfile();
            }
        });

    }

    public void cancelChangesProfile(){
        Intent intent = new Intent(this, JobBoard.class);
        startActivity(intent);
    }

    public void returnToMain(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

}