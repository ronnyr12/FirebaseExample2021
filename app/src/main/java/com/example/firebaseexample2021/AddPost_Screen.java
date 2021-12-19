package com.example.firebaseexample2021;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddPost_Screen extends AppCompatActivity implements View.OnClickListener {
    EditText et_title, et_body;
    Button btn_save;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference postRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost_screen);

        initElements();

        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void initElements() {
        et_body = findViewById(R.id.et_body_add_post);
        et_title = findViewById(R.id.et_title_add_post);
        btn_save = findViewById(R.id.btn_save_add_post_screen);

        btn_save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btn_save){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            Post p = new Post(et_title.getText().toString(),
                    et_body.getText().toString(), uid, "" );

            postRef = firebaseDatabase.getReference("Posts").push();
            p.key = postRef.getKey();
            postRef.setValue(p);

        }
    }
}