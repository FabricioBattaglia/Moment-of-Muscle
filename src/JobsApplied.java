package com.example.momentofmuscle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.List;

public class JobsApplied extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    public static final String TAG = "TAG";
    public static String documentID;
    public static String hostID;
    public static int pos;
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionRef; //notebookRef
    List<Job> jobListing;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView3;
    CollectionReference ref = db.collection("job_board");
    FirestoreAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                goToHosted();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_applied);
        recyclerView3 = findViewById(R.id.recyclerView);


        fAuth = FirebaseAuth.getInstance();
        String currentUser = fAuth.getCurrentUser().getUid();

        Query query = db.collection("job_board").whereEqualTo("accepted_by_id", currentUser);

        PagedList.Config config = new PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(3).build();


        //RecyclerOptions
        FirestorePagingOptions<Job> options = new FirestorePagingOptions.Builder<Job>().setLifecycleOwner(this).setQuery(query, config, Job.class).build();

        adapter = new FirestoreAdapter(options, this);

        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(adapter);

    }

    @Override
    public void onItemClick(DocumentSnapshot snapshot, int position) {
        documentID = snapshot.getId();//the id of the job
        hostID = snapshot.get("user_id").toString();//the id of the creator of the job
        pos = position;
        goToJob();
    }

    public void goToJob(){
        Intent intent = new Intent(this, JobsAppliedDetails.class);
        startActivity(intent);
    }

    public void goToHosted(){
        Intent intent = new Intent(this, CurrentJobsHosted.class);
        startActivity(intent);
    }

}