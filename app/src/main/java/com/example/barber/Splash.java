package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private TextView tvWelcome;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        Animation mytransition = AnimationUtils.loadAnimation(this,R.anim.mytransition);
    }
}
