package com.example.momentofmuscle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobBoard extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionRef; //notebookRef
    List<Job> jobListing;
    //JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_board);

        jobBoard = findViewById(R.id.JobBoard);

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
                        jobListing.add(j);
                        count[0]++;
                    }
                    //adapter.notifyDataSetChanged();
                }
                //as of now this displays only the element 0 of the list
                //if(jobListing.get(0) != null) {
                if(count[0] != 0){
                    jobBoard.setText(jobListing.get(0).job_title);
                }
                else{
                    jobBoard.setText("No jobs? Try posting a new job!");
                }
            }
        });
    }

}