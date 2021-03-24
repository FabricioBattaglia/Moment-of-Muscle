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
    //   FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    //   RecyclerView recyclerView;

    //  CollectionReference ref=db.collection("job_board");
    FirestoreRecyclerOptions<Job>options;
    //FirestoreRecyclerAdapter<Job,MyJobHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description_screen);

        EditText userEntryJobTitle = (EditText) findViewById(R.id.JobPostingEntry);
        Button addJobButton = (Button) findViewById(R.id.AddJobButton);
        Spinner yardworkSpinner = (Spinner) findViewById(R.id.JobTypeSpinner);
        Spinner jobRadiusSpinner = (Spinner) findViewById(R.id.JobRadiusSpinner);
        //    recyclerView = findViewById(R.id.recyclerView);
        //     recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //    recyclerView.setHasFixedSize(true);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

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
                String userID = fAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("job_board").document();
                Map<String, Object> job_board = new HashMap<>();
                job_board.put("category", yardworkString);
                job_board.put("job_title", jobTitleString);
                job_board.put("radius", jobRadiusString);
                job_board.put("user_id", userID);

                documentReference.set(job_board).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(JobDescriptionScreen.this, "Job Posted", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });

        //here is where we read
        //ReadData();

    }

    /*
    void ReadData() {
        options=new FirestoreRecyclerOptions.Builder<Job>().setQuery(ref,Job.class).build();
        adapter=new FirestoreRecyclerAdapter<Job, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Job model) {
                holder.NAME.setText(model.getJob_title());
            }
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
}
     */


}