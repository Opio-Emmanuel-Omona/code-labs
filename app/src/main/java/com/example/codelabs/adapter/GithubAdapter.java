package com.example.codelabs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codelabs.view.OnListListener;
import com.example.codelabs.R;
import com.example.codelabs.model.GithubUsers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.MyViewHolder> {
    private ArrayList<GithubUsers> githubUsersArray;
    private OnListListener myOnListListener;


    public GithubAdapter(ArrayList<GithubUsers> githubUsers, OnListListener onListListener) {
        this.githubUsersArray = githubUsers;
        this.myOnListListener = onListListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v, myOnListListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GithubUsers githubUsers;
        githubUsers = githubUsersArray.get(position);
        holder.username.setText(githubUsers.getUsername());
        Picasso.get().load(githubUsers.getProfileImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return githubUsersArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username;
        ImageView image;
        OnListListener onListListener;

        public MyViewHolder(View view, OnListListener onListListener) {
            super(view);
            username = view.findViewById(R.id.list_item_username);
            image = view.findViewById(R.id.profile_image);

            this.onListListener = onListListener;
            username.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListListener.onItemClick(getAdapterPosition());
        }
    }
}
