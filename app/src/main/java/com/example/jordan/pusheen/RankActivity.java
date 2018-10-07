package com.example.jordan.pusheen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {
    ArrayList<Rank> rankArrayList;
    MyBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        rankArrayList = new ArrayList<>();
        adapter = new MyBaseAdapter(RankActivity.this,rankArrayList);
        ListView listView = (ListView) findViewById(R.id.lsview);
        listView.setAdapter(adapter);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("data");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //bad implement need to improve
                rankArrayList.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    String name = data.child("name").getValue().toString();
                    String times = data.child("times").getValue().toString();
                    rankArrayList.add(new Rank(name,times));
                }
                adapter.updateRankArrayList(rankArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
