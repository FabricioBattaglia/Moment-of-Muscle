package com.example.profilescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class jobPost extends AppCompatActivity {

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

        TextView jobTitle = findViewById(R.id.jobnameviewtext);
        TextView jobCategory = findViewById(R.id.jobcategoryviewtext);
        TextView jobRadius = findViewById(R.id.jobradiusviewtext);

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
                    jobRadius.setText(jobListing.get(0).radius);
                    jobCategory.setText(jobListing.get(0).category);
                }
                else{
                    jobBoard.setText("No jobs? Try posting a new job!");
                }
            }
        });
    }



    }
