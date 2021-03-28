package com.example.momentofmuscle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

public class JobDetails extends AppCompatActivity {

    public static int count;
    public static String acceptedID;
    public static boolean accepted;
    String documentID;
    int pos;
    public static final String TAG = "TAG";
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseFirestore getAcc;
    CollectionReference collectionRef; //notebookRef
    public static Job jobListing;
    public static Button acceptJobButton;
    public static Button rejectButton;
    public static Button finishJobButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        TextView jobTitle = findViewById(R.id.jobtitleviewtext);
        TextView jobCategory = findViewById(R.id.jobcategoryviewtext);
        TextView jobAccepted = findViewById(R.id.jobacceptedtextview);
        TextView jobPrice = findViewById(R.id.jobpriceviewtext);
        TextView jobDescription = findViewById(R.id.jobdescriptionviewtext);
        TextView acceptedAccount = findViewById(R.id.acceptedAccount);
        acceptJobButton = (Button) findViewById(R.id.acceptButton);
        rejectButton = (Button) findViewById(R.id.rejectButton);
        finishJobButton = (Button) findViewById(R.id.finishJobButton);
        TextView acceptby = findViewById(R.id.acceptBy);
        TextView acceptname = findViewById(R.id.acceptedAccount);


        documentID = CurrentJobsHosted.documentID;
        pos = JobBoard.pos;

        jobListing = new Job();

        fStore = FirebaseFirestore.getInstance();
        getAcc = FirebaseFirestore.getInstance();
        count = 0;
        accepted = false;

        if(fStore.collection("job_board").document(documentID) != null) {
            DocumentReference docRef = fStore.collection("job_board").document(documentID);
            docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    //PASS ALL INFO INTO JOBLISTING
                    jobListing.category = documentSnapshot.getString("category");
                    jobListing.job_title = documentSnapshot.getString("job_title");
                    //list.radius = documentSnapshot.getString("category");
                    jobListing.user_id = documentSnapshot.getString("user_id");
                    //list.job_id = documentSnapshot.getString("category");
                    jobListing.price = documentSnapshot.getString("price");
                    jobListing.job_description = documentSnapshot.getString("job_description");
                    jobListing.accepted_by_id = documentSnapshot.getString("accepted_by_id");
                    jobListing.accepted_by_email = documentSnapshot.getString("accepted_by_email");
                    jobListing.accepted_by_phone = documentSnapshot.getString("accepted_by_phone");
                    jobListing.accepted_by_name = documentSnapshot.getString("accepted_by_name");
                    jobListing.isAccepted = documentSnapshot.getBoolean("isAccepted");
                    //jobListing.isRejected = documentSnapshot.getBoolean("isRejected");
                    jobListing.bothAccepted = documentSnapshot.getBoolean("bothAccepted");

                    //PASS ALL INFO TO TEXT FIELDS
                    jobTitle.setText(jobListing.job_title);
                    jobCategory.setText(jobListing.category);
                    //     jobCityState.setText(jobListing.get(0).city_state);
                    jobPrice.setText(jobListing.price);
                    jobDescription.setText(jobListing.job_description);

                    //Waiting for host to accept the job
                    if (jobListing.isAccepted == true && jobListing.bothAccepted == false) {
                        jobAccepted.setText("Accepted");
                        accepted = true;
                        acceptedAccount.setText(jobListing.accepted_by_name);
                        acceptJobButton.setText("Accept " + jobListing.accepted_by_name);
                        rejectButton.setText("Reject " + jobListing.accepted_by_name);
                        acceptJobButton.setVisibility(View.VISIBLE);
                        rejectButton.setVisibility(View.VISIBLE);
                        acceptby.setVisibility(View.VISIBLE);
                        acceptname.setVisibility(View.VISIBLE);
                        acceptedID = jobListing.accepted_by_id;
                        if (accepted == true) {
                            acceptname.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goToAccProfile();
                                }
                            });
                        }
                    }
                    //host has accepted the job
                    else if (jobListing.isAccepted == true && jobListing.bothAccepted == true) {
                        finishJobButton.setVisibility(View.VISIBLE);
                        acceptJobButton.setVisibility(View.GONE);
                        rejectButton.setVisibility(View.GONE);
                        jobAccepted.setText("Ongoing");
                        acceptby.setVisibility(View.VISIBLE);
                        acceptname.setVisibility(View.VISIBLE);
                        acceptname.setText(jobListing.accepted_by_name);
                        acceptedID = jobListing.accepted_by_id;
                        acceptname.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToAccProfile();
                            }
                        });
                    } else {//rejected
                        jobAccepted.setText("Not Yet Accepted");
                        acceptJobButton.setVisibility(View.GONE);
                        rejectButton.setVisibility(View.GONE);
                        acceptby.setVisibility(View.GONE);
                        acceptname.setVisibility(View.GONE);
                        finishJobButton.setVisibility(View.GONE);
                    }
                }

            });
        } else {
            goToMain();
        }

        acceptJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("job_board").document(documentID);
                Map<String,Object> job_board = new HashMap<>();
                job_board.put("bothAccepted", true);
                job_board.put("category", jobListing.category);
                job_board.put("job_title", jobListing.job_title);
                job_board.put("user_id", jobListing.user_id);
                job_board.put("price", jobListing.price);
                job_board.put("job_description", jobListing.job_description);
                job_board.put("accepted_by_id", jobListing.accepted_by_id);
                job_board.put("accepted_by_name", jobListing.accepted_by_name);
                job_board.put("accepted_by_email", jobListing.accepted_by_email);
                job_board.put("accepted_by_phone", jobListing.accepted_by_phone);
                job_board.put("isAccepted", true);
                //job_board.put("isRejected", false);
                documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: User accepted "+ documentID);
                    }
                });
                goToMain();
            }
        });

        finishJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
                fStore.collection("job_board").document(documentID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Job finished");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error, Cannot do that", e);
                    }
                });
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("job_board").document(documentID);
                Map<String,Object> job_board = new HashMap<>();
                job_board.put("bothAccepted", false);
                job_board.put("category", jobListing.category);
                job_board.put("job_title", jobListing.job_title);
                job_board.put("user_id", jobListing.user_id);
                job_board.put("price", jobListing.price);
                job_board.put("job_description", jobListing.job_description);
                job_board.put("accepted_by_id", "none");
                job_board.put("accepted_by_name", "none");
                job_board.put("accepted_by_email", "none");
                job_board.put("accepted_by_phone", "none");
                job_board.put("isAccepted", false);
                //job_board.put("isRejected", true);
                documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: User accepted "+ documentID);
                    }
                });
                goToMain();
            }
        });

    }
    public void goToAccProfile(){
        Intent intent = new Intent(this, WorkProfile.class);
        startActivity(intent);
    }

    public void goToMain(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

}