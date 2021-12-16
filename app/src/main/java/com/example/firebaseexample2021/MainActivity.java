package com.example.firebaseexample2021;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_log, btn_reg, btn_add, btn_all;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElements();


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            btn_log.setText("Logout");
            btn_reg.setVisibility(View.INVISIBLE);
        }else{
            btn_log.setText("Login");
            btn_reg.setVisibility(View.VISIBLE);
        }

    }

    private void initElements() {
        btn_log = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.btn_register);
        btn_add = findViewById(R.id.btn_add_post);
        btn_all = findViewById(R.id.btn_all_post);

        btn_log.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //todo -on click to buttons

            case R.id.btn_login:
                break;

            case R.id.btn_register:
                break;

            case R.id.btn_add_post:
                startActivity(new Intent(
                        MainActivity.this, AddPost_Screen.class));
                break;

            case R.id.btn_all_post:
                break;

        }
    }
}