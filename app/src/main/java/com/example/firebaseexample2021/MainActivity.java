package com.example.firebaseexample2021;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_log, btn_reg, btn_add, btn_all;
    FirebaseAuth firebaseAuth;
    Dialog dialog;
    ProgressDialog progressDialog;
    //dialogs layout elements
    EditText et_pass_reg_dialog, et_email_reg_dialog;
    Button btn_register_reg_dialog;


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
    public void createRegisterDialog()
    {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.register_layout);
        dialog.setTitle("Register");
        dialog.setCancelable(true);
        et_pass_reg_dialog = dialog.findViewById(R.id.et_pass_reg_dialog);
        et_email_reg_dialog = dialog.findViewById(R.id.et_email_reg_dialog);
        btn_register_reg_dialog = dialog.findViewById(R.id.btn_register_reg_dialog);
        btn_register_reg_dialog.setOnClickListener(this);
        dialog.show();

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

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //todo -on click to buttons

            case R.id.btn_login:
                break;

            case R.id.btn_register:
                createRegisterDialog();
                break;

            case R.id.btn_add_post:
                startActivity(new Intent(
                        MainActivity.this,
                        AddPost_Screen.class));
                break;

            case R.id.btn_all_post:
                break;

            case R.id.btn_register_reg_dialog:
                registerToFirebase();

        }
    }

    private void registerToFirebase() {
        progressDialog.setMessage("Registering, please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(
                et_email_reg_dialog.getText().toString(),
                et_pass_reg_dialog.getText().toString()).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,
                                    "Succesfully registered", Toast.LENGTH_SHORT).show();
                            btn_log.setText("Logout");
                            btn_reg.setVisibility(View.INVISIBLE);
                        }
                        else{
                            Toast.makeText(MainActivity.this,
                                    "Registration Error!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        progressDialog.dismiss();
                }
        });
    }
}