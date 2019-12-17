package com.example.barber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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
