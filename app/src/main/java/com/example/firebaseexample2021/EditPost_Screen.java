package com.example.firebaseexample2021;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EditPost_Screen extends AppCompatActivity implements View.OnClickListener {
    EditText et_title, et_body;
    Button btn_save;

    String key;
    Post post;

    DatabaseReference postRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post_screen);

        et_body = findViewById(R.id.etBody_edit);
        et_title = findViewById(R.id.etTitle_edit);
        btn_save = findViewById(R.id.btnSave_edit);
        btn_save.setOnClickListener(this);

        Intent intent = getIntent();
        key = intent.getExtras().getString("key");
        retrieveData();
    }

    private void retrieveData() {
        postRef = FirebaseDatabase.getInstance().getReference("Posts/" + key);
        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                post = snapshot.getValue(Post.class);
                et_body.setText(post.body);
                et_title.setText(post.title);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == btn_save){
            postRef = FirebaseDatabase.getInstance().getReference("Posts/" + key);
            post.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            post.title  = et_title.getText().toString();
            post.body = et_body.getText().toString();
            postRef.setValue(post);
            finish();
        }
    }
}