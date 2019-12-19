package com.example.barber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Signin;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvinfo);
        Signin = (Button) findViewById(R.id.btnlogin);
        TextView userRegistration = (TextView) findViewById(R.id.tvRegister);

//        Info.setText("No of Attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user= firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(Login.this,Bookings.class));
        }

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }

    public void validate(String userName, String userPassword) {

        progressDialog.setMessage("You can Check a quick tutorial on Login Demo");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
//                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else {
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter --;

                    Info.setText("no of Attempts Remaining: " + counter);
                    if(counter == 0){
                        progressDialog.dismiss();
                        Signin.setEnabled(false);
                    }
                }

            }
        });

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent( Login.this,MainActivity.class));
            Toast.makeText(this,"Verify your email account",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
