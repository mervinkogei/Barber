package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
      final Intent intent = new Intent(this,MainActivity.class);

        Animation mytransition = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tvWelcome.startAnimation(mytransition);
        ivLogo.startAnimation(mytransition);
        final Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
