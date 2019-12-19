package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barber.R.id.findRestaurantsButton;


public class Location extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(findRestaurantsButton) Button mFindRestaurantsButton;
    @BindView (R.id.locationEditText) EditText mLocationEditText;
    @BindView (R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        setTitle("Find your Location");

//        mFindRestaurantButton = (Button)findViewById(R.id.findRestaurantsButton);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);

        mFindRestaurantsButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(Location.this, MainActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

    }

}