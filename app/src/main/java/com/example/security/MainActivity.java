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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GuestsAdapter adapter;
//    FirebaseDatabase database;
    ArrayList<Model> modelArrayList;
    Button btn1, btn2, btn3;
    DatabaseReference databaseReference;
    ArrayList<String> lastDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.list);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Guests");

        getLastThreeDays();

        getData("Guests");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastDays.size() >= 3){
                    getData(lastDays.get(2));
                }else{
                    Toast.makeText(MainActivity.this, "Бұл бет бос", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastDays.size() >= 2){
                    getData(lastDays.get(1));
                }else{
                    Toast.makeText(MainActivity.this, "Бұл бет бос", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastDays.size() >= 1){
                    getData(lastDays.get(0));
                }else{
                    Toast.makeText(MainActivity.this, "Бұл бет бос", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetch();
    }
    private void getLastThreeDays(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String btnText = "no";
                int count = 0;
                lastDays.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
//                    count = (int) snapshot1.child("Guests").getChildrenCount();
//                    Log.i("infodb", ""+count);
                    lastDays.add(snapshot1.getKey());
                }
                Collections.reverse(lastDays);
                if (lastDays.size() == 1){
                    btn1.setText("Бос");
                    btn2.setText("Бос");
                    btn3.setText(lastDays.get(0));
                }else if (lastDays.size() == 2){
                    btn1.setText("Бос");
                    btn2.setText(lastDays.get(1));
                    btn3.setText(lastDays.get(0));
                }else if(lastDays.size() >= 3){
                    btn1.setText(lastDays.get(2));
                    btn2.setText(lastDays.get(1));
                    btn3.setText(lastDays.get(0));
                }else if(lastDays.isEmpty()){
                    btn1.setText("Бос");
                    btn2.setText("Бос");
                    btn3.setText("Бос");
                }

                Log.i("Tag", "info" + lastDays.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getData(String date){
        databaseReference.child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelArrayList.clear();
                for (DataSnapshot btn1 : snapshot.getChildren()){
                    Model model = btn1.getValue(Model.class);
                    modelArrayList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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
        /*
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
        */

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

    @Override
    public void onClick(View v) {



        switch (v.getId()){
            case R.id.btn1:


            case R.id.btn2:

        }
    };
}