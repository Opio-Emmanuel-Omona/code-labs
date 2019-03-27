package com.example.codelabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnListListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Person> personArray;
    private Person person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Integer [] images = {R.drawable.emmanuel, R.drawable.moses};

        personArray = new ArrayList<Person>();
        person = new Person();
        person.setUsername("Emmanuel");
        person.setOrganisation("Andela");
        person.setProfileLink("http://github.com/opio-emmanuel-omona");
        person.setProfileImage(images[0]);
        personArray.add(person);

        person = new Person();
        person.setUsername("Moses");
        person.setOrganisation("Andela");
        person.setProfileLink("http://github.com/mosesk");
        person.setProfileImage(images[1]);
        personArray.add(person);

        mAdapter = new MyAdapter(personArray, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        personArray.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Username", personArray.get(position).getUsername());
        intent.putExtra("ProfileImage", personArray.get(position).getProfileImage());
        startActivity(intent);
    }
}
