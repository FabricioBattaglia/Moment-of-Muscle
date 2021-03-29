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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.HashMap;
import java.util.Map;

public class JobsAppliedDetails extends AppCompatActivity {

    public static String hostID;
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

    public static Button cancelJobButton;
    public static Button finishJobButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_applied_details);

        TextView jobTitle = findViewById(R.id.jobtitleviewtext);
        TextView jobCategory = findViewById(R.id.jobcategoryviewtext);
        TextView jobAccepted = findViewById(R.id.jobacceptedtextview);
        TextView jobPrice = findViewById(R.id.jobpriceviewtext);
        TextView jobDescription = findViewById(R.id.jobdescriptionviewtext);
        TextView acceptedAccount = findViewById(R.id.acceptedAccount);
        cancelJobButton = (Button) findViewById(R.id.cancelButton);
        finishJobButton = (Button) findViewById(R.id.finishJobButton);
        TextView acceptby = findViewById(R.id.acceptBy);


        documentID = JobsApplied.documentID;
        hostID = JobsApplied.hostID;
        pos = JobsApplied.pos;

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
                    accepted = true;

                    //PASS ALL INFO TO TEXT FIELDS
                    jobTitle.setText(jobListing.job_title);
                    jobCategory.setText(jobListing.category);
                    //     jobCityState.setText(jobListing.get(0).city_state);
                    jobPrice.setText(jobListing.price);
                    jobDescription.setText(jobListing.job_description);

                    //Waiting for host to accept the job
                    if (jobListing.isAccepted == true && jobListing.bothAccepted == false) {
                        jobAccepted.setText("Pending Approval");
                    }
                    //host has accepted the job
                    else if (jobListing.isAccepted == true && jobListing.bothAccepted == true) {
                        jobAccepted.setText("Accepted: Ongoing");
                        accepted = true;
                        cancelJobButton.setVisibility(View.VISIBLE);
                        finishJobButton.setVisibility(View.VISIBLE);
                    } else {//rejected

                    }
                }

            });
        } else {
            goToMain();
        }

        if(hostID != null) {
            DocumentReference docRef = fStore.collection("users").document(hostID);
            docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    acceptedAccount.setText(value.getString("fName"));
                    acceptby.setVisibility(View.VISIBLE);
                    acceptedAccount.setVisibility(View.VISIBLE);
                    if (accepted == true) {
                        acceptedAccount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToAccProfile();
                            }
                        });
                    }
                }
            });
        }

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

        cancelJobButton.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(this, CreatorProfile.class);
        startActivity(intent);
    }

    public void goToMain(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

}