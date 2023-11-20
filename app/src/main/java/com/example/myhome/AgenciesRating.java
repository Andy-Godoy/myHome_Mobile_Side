package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AgenciesRating extends AppCompatActivity {

    private ImageView userImageView;
    private TextView userNameTextView, commentTextView, ratingNumberTextView;
    private RatingBar ratingBar;
    private CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_rating);

        // Inicializamos las vistas
        userImageView = findViewById(R.id.userImageView);
        userNameTextView = findViewById(R.id.userNameTextView);
        commentTextView = findViewById(R.id.commentTextView);

        ratingBar = findViewById(R.id.ratingBar);
        cardView = findViewById(R.id.cardView);

        // Aca podemos configurar el RatingBarChangeListener para actualizar el número de valoración
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int ratingValue = (int) rating;
                ratingNumberTextView.setText(String.valueOf(ratingValue));
            }
        });

        // Configurar los datos de prueba
        userImageView.setImageResource(R.drawable.profile_picture);
        userNameTextView.setText("Nombre del Usuario");
        commentTextView.setText("Comentario de prueba");



    }
    public void volver(View view) {
        Intent miIntent=new Intent(AgenciesRating.this, AgenciesProfile.class);
        startActivity(miIntent);
    }
}
