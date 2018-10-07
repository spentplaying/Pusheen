package com.example.jordan.pusheen;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Rank {
    String name;
    String times;

    public Rank(String _name,String _times){
        name = _name;
        times = _times;
    }

    public String getName() {
        return name;
    }

    public String getTimes() {
        return times;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("name",name);
        result.put("times",times);
        return result;
    }

    public void addNewPost(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String key = database.child("data").push().getKey();
        Map<String,Object> entry = this.toMap();
        Map<String,Object> child = new HashMap<>();
        child.put("/data/"+key,entry);
        database.updateChildren(child);
    }
}
