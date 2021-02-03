package com.example.security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GuestsAdapter adapter;
    FirebaseDatabase database;
    ArrayList<Model> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.list);
        modelArrayList = new ArrayList<>();
        adapter =new GuestsAdapter(modelArrayList, this);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//        database = FirebaseDatabase.getInstance().getReference().child("Guests").push();

        fetch();
    }

    private void fetch() {
//        Query query = FirebaseDatabase.getInstance().getReference().child("Tasks");
//
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(query, new SnapshotParser<Model>() {
//            @NonNull
//            @Override
//            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
//                return new Model(snapshot.child("Full name").getValue().toString(),
//                        snapshot.child("Reason").getValue().toString(),
//                        snapshot.child("Time").getValue().toString(),
//                        snapshot.child("Who Add").getValue().toString());
//            }
//        }).build();
//        Query query = FirebaseDatabase.getInstance().getReference().child("Guests");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Guests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot guestData : snapshot.getChildren()){
                        Model model = guestData.getValue(Model.class);
                        modelArrayList.add(model);
                    }

                    adapter.notifyDataSetChanged();

                    Log.i("DB", "go");
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("DB", "error");
            }
        });

//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
//                .setQuery(query, Model.class)
//                .build();

//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapter = new GuestsAdapter(options);
//        recyclerView.setAdapter(adapter);
//
//        Log.i("DB", "go");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.nav_list:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_guest:
                intent = new Intent(MainActivity.this, AddGuestActivity.class);
                startActivity(intent);
                break;
        };
        return true;
    }
}