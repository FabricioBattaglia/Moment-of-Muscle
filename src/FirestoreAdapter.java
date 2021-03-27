package com.example.profilescreen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirestoreAdapter extends FirestorePagingAdapter<Job, FirestoreAdapter.JobViewHolder> {

OnListItemClick onListItemClick;

    public FirestoreAdapter(@NonNull FirestorePagingOptions<Job> options, OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position, @NonNull Job model) {
        holder.list_name.setText(model.getJob_title());
        holder.list_category.setText(model.getCategory());
        holder.list_price.setText(model.getPrice());
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onLoadingStateChanged(@NonNull LoadingState state) {
        super.onLoadingStateChanged(state);
        switch(state){
            case LOADING_INITIAL:
                Log.d("PAGING_LOG", "Loading initial data");
                break;
            case LOADING_MORE:
                Log.d("PAGING_LOG", "Loading next page");
                break;
            case FINISHED:
                Log.d("PAGING_LOG", "All data loaded");
                break;
            case ERROR:
                Log.d("PAGING_LOG", "Error loading data");
                break;
            case LOADED:
                Log.d("PAGING_LOG", "Total Items Loaded : " + getItemCount());
                break;
        }
    }

    public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView list_name;
        TextView list_category;
        TextView list_price;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.NAME);
            list_category= itemView.findViewById(R.id.CATEGORY);
            list_price = itemView.findViewById(R.id.PRICE);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnListItemClick {
        void onItemClick(DocumentSnapshot snapshot, int position);
    }


}
