package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.RatingApi;
import com.example.myhome.Interfaces.RatingCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Resenas;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListAgencieReviews extends AppCompatActivity implements RatingCallback {

    private Button btnPropiedades;
    private Button btnNuevaPropiedad;
    private Button btnPerfil;
    private LinearLayout cardConteiner;
    private Long agencyId;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_rating);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Obtenemos el ID del ítem de menú correspondiente a esta actividad
        int menuItemId = R.id.action_home;

        // Marcar el ítem del menú como seleccionado
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configurar el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandler.handleMenuItemClick(this, item, this.getClass());
            return true;
        });

        cardConteiner = findViewById(R.id.cardContainer);

        FiltersDTO filters = new FiltersDTO();

        //Recupero la agencia en contexto para tener los datos de sus reviews
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            agencyId = ((MyHome) this.getApplication()).getUsuario().getAgencyId();
            filters.setAgencyId(agencyId);
        }

        RatingApi ratingApi = new RatingApi();
        ratingApi.verResenas(agencyId, this);



    }
    public void volver(View view) {
        Intent miIntent=new Intent(ListAgencieReviews.this, AgenciesProfile.class);
        startActivity(miIntent);
    }

    @Override
    public void onResenasSuccess(List<Resenas> resenas) {

        if (resenas != null){
            for (Resenas r: resenas){

                View raitingCard = LayoutInflater.from(this).inflate(R.layout.card_rating, cardConteiner, false);

                ImageView userImageView = raitingCard.findViewById(R.id.userImageView);
                Glide.with(this)
                        .load(r.getPhoto())
                        .into(userImageView);

                ((TextView) raitingCard.findViewById(R.id.userNameTextView)).setText(r.getUserName());
                ((TextView) raitingCard.findViewById(R.id.commentTextView)).setText(r.getReviewComment());
                ((RatingBar) raitingCard.findViewById(R.id.ratingBar)).setRating(r.getReviewScore());

                cardConteiner.addView(raitingCard);
            }
        }

    }

    @Override
    public void onFailure(String errorMessage) {
        Toast.makeText(this, "Tu agencia no tiene reseñas", Toast.LENGTH_SHORT).show();
    }
}
