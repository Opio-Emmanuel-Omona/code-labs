package com.example.codelabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Person> personArray;
    private OnListListener myOnListListener;


    public MyAdapter(ArrayList<Person> person, OnListListener onListListener) {
        this.personArray = person;
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
        Person person;
        person = personArray.get(position);
        holder.username.setText(person.getUsername());
        holder.image.setImageResource(person.getProfileImage());

    }

    @Override
    public int getItemCount() {
        return personArray.size();
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

