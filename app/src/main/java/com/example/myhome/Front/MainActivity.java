package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myhome.R;


public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000; // 3 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.splash_image);
        final TextView textViewMyHome = findViewById(R.id.textViewMyHome);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // inicio de la animación
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // cuando la animación termina, muestra el texto "MyHome"
                textViewMyHome.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // por si queremos repeteir la animación
            }
        });

        imageView.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después de SPLASH_TIME_OUT, inicia la actividad principal
                Intent intent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}