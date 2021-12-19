package com.example.firebaseexample2021;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AllPost_Screen extends AppCompatActivity {
    ListView lv_posts;
    ArrayList<Post> posts;
    AllPost_Adapter adapter;

    DatabaseReference post_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post_screen);

        lv_posts = findViewById(R.id.lv_post_all_post);

        post_ref = FirebaseDatabase.getInstance().
                getReference("Posts");

        retrieveData();

        lv_posts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                Post p = posts.get(i);
                Intent intent = new Intent(AllPost_Screen.this,
                        EditPost_Screen.class);
                intent.putExtra("key", p.key);
                startActivity(intent);
            }
        });
        lv_posts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post p = posts.get(i);
                DatabaseReference current =
                        FirebaseDatabase.getInstance().getReference("Posts/" + p.key);
                current.removeValue();
                return true;
            }
        });
    }

    private void retrieveData() {
        post_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                posts = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Post p = data.getValue(Post.class);
                    posts.add(p);
                }
                adapter = new AllPost_Adapter(
                        AllPost_Screen.this,
                        0, 0,
                        posts);
                lv_posts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //no interesting in our purpose in the lesson
            }
        });
    }
}