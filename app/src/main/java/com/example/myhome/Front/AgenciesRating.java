package com.example.myhome.Front;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

public class AgenciesRating extends AppCompatActivity {

    private ImageView userImageView;
    private TextView userNameTextView, commentTextView, ratingNumberTextView;
    private RatingBar ratingBar;
    private CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_rating);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        // Inicializamos las vistas
        userImageView = findViewById(R.id.userImageView);
        userNameTextView = findViewById(R.id.userNameTextView);
        commentTextView = findViewById(R.id.commentTextView);

        ratingBar = findViewById(R.id.ratingBar);
        //cardView = findViewById(R.id.cardView);

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

}
