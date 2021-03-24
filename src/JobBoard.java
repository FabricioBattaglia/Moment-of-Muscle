package com.example.profilescreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
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
import com.firebase.ui.firestore.SnapshotParser;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
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

public class JobBoard extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    public static final String TAG = "TAG";
    public static String documentID;
    public static int pos;
    TextView jobBoard;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionRef; //notebookRef
    List<Job> jobListing;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    CollectionReference ref=db.collection("job_board");
    FirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_board);
        recyclerView = findViewById(R.id.recyclerView);

        //Query
        Query query = db.collection("job_board");

        PagedList.Config config = new PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(3).build();


        //RecyclerOptions
        FirestorePagingOptions<Job> options = new FirestorePagingOptions.Builder<Job>().setLifecycleOwner(this).setQuery(query, config, Job.class).build();

        adapter = new FirestoreAdapter(options, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(DocumentSnapshot snapshot, int position) {
        documentID = snapshot.getId();
        pos = position;
        goToJob();
    }

    public void goToJob(){
        Intent intent = new Intent(this, jobPost.class);
        startActivity(intent);
    }

}