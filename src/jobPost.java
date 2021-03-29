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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JobPost extends AppCompatActivity {


    String documentID;
    int pos;
    String currentName;
    String currentEmail;
    String currentPhone;

    public static final String TAG = "TAG";
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionRef; //notebookRef
    //List<Job> jobListing;
    //List<Job> list;
    public static Job list;

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

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("users").document(currentUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                currentName = documentSnapshot.getString("fName");
                currentEmail = documentSnapshot.getString("email");
                currentPhone = documentSnapshot.getString("phone");
            }
        });


        documentID = JobBoard.documentID;
        pos = JobBoard.pos;

        //jobListing = new ArrayList<>();

        fStore = FirebaseFirestore.getInstance();
        final int[] count = {0};
        list = new Job();

        DocumentReference docRef = fStore.collection("job_board").document(documentID);
        docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                list.category = documentSnapshot.getString("category");
                list.job_title = documentSnapshot.getString("job_title");
                //list.radius = documentSnapshot.getString("category");
                list.user_id = documentSnapshot.getString("user_id");
                //list.job_id = documentSnapshot.getString("category");
                list.price = documentSnapshot.getString("price");
                list.job_description = documentSnapshot.getString("job_description");
                list.accepted_by_id = documentSnapshot.getString("accepted_by_id");
                list.accepted_by_email = documentSnapshot.getString("accepted_by_email");
                list.accepted_by_phone = documentSnapshot.getString("accepted_by_phone");
                list.accepted_by_name = documentSnapshot.getString("accepted_by_name");
                list.isAccepted = documentSnapshot.getBoolean("isAccepted");
                list.bothAccepted = documentSnapshot.getBoolean("bothAccepted");
                list.hostFinished = documentSnapshot.getBoolean("hostFinished");
                list.workerFinished = documentSnapshot.getBoolean("workerFinished");
                //list.isRejected = documentSnapshot.getBoolean("isRejected");
                jobTitle.setText(list.job_title);
                jobCategory.setText(list.category);
                //     jobCityState.setText(jobListing.get(0).city_state);
                jobPrice.setText(list.price);
                jobDescription.setText(list.job_description);
            }

        });

        acceptJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference documentReference = fStore.collection("job_board").document(documentID);
                if(list.user_id.equals(currentUser)) {
                    Toast.makeText(JobPost.this, "You cannot accept your own job", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String,Object> job_board = new HashMap<>();

                    job_board.put("category", list.category);
                    job_board.put("job_title", list.job_title);
                    job_board.put("user_id", list.user_id);
                    job_board.put("price", list.price);
                    job_board.put("job_description", list.job_description);
                    job_board.put("accepted_by_id", currentUser);
                    job_board.put("accepted_by_name", currentName);
                    job_board.put("accepted_by_email", currentEmail);
                    job_board.put("accepted_by_phone", currentPhone);
                    job_board.put("hostFinished", false);
                    job_board.put("workerFinished", false);
                    job_board.put("isAccepted", true);
                    //job_board.put("isRejected", false);
                    job_board.put("bothAccepted", false);

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