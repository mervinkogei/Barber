package com.example.barber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @BindView (R.id.etName) EditText userName;
    @BindView(R.id.etEmail) EditText userEmail;
    @BindView (R.id.etLocation) EditText userLocation;
    @BindView(R.id.etPassword) EditText userPassword;
    @BindView(R.id.btnSignup) Button regButton;
    @BindView(R.id.tvLogin) TextView userLogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Sign Up to Barber");

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        regButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validate()){
                //upload files
                String user_email = userEmail.getText().toString().trim();
                String user_password = userPassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            sendEmailVerification();
                        }else{
                            Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }
    });

        userLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,Login.class));
        }
    });
}

    private void setupUIViews(){
        ButterKnife.bind(this);
    }

    private Boolean validate(){
        Boolean results = false;

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String location = userLocation.getText().toString();
        String password = userPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || location.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please Enter all the fields",Toast.LENGTH_SHORT).show();
        }
        else{
            results = true;
        }
        return results;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(MainActivity.this,"Successfully Registered, and Verification Email Sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                    }else {
                        Toast.makeText(MainActivity.this,"Verification hasn't been Sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.db){
            Toast.makeText(this,"Dashboard",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.prof){
            Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.cal){
            Toast.makeText(this,"Calendar",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.book){
            Toast.makeText(this,"Bookings",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.pay){
            Toast.makeText(this,"Payment",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.set){
            Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.logout){
            Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
        }
        return  false;
    }
}
