package com.example.momentofmuscle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {

    public static final String TAG = "TAG";
    public static boolean checkDelete;
    public static String toDelID;
    FirebaseFirestore fStore;
    boolean toStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toStop = JobDetails.stop;

        if(toStop){

            fStore = FirebaseFirestore.getInstance();
            toDelID = JobDetails.documentID;

            DocumentReference documentReference = fStore.collection("job_board").document(toDelID);
            Map<String, Object> job_board = new HashMap<>();
            job_board.put("bothAccepted", true);
            job_board.put("category", "none");
            job_board.put("job_title", "none");
            job_board.put("user_id", "none");
            job_board.put("price", "none");
            job_board.put("job_description", "none");
            job_board.put("accepted_by_id", "none");
            job_board.put("accepted_by_name", "none");
            job_board.put("accepted_by_email", "none");
            job_board.put("accepted_by_phone", "none");
            job_board.put("hostFinished", true);
            job_board.put("workerFinished", true);
            job_board.put("isAccepted", true);
            documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: Job finished " + toDelID);
                }
            });
        }

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

        Button viewHost = (Button) findViewById(R.id.toHostButton);
        viewHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHost();
            }
        });

        /*

        Button logoutButton = (Button) findViewById(R.id.toLogoutButton);
        viewHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout2();
            }
        });

        */

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

    public void goToHost(){
        Intent intent = new Intent(this, CurrentJobsHosted.class);
        startActivity(intent);
    }

/*
    public void logout2() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(this, SecondActivity.class));
        finish();
    }
*/

}