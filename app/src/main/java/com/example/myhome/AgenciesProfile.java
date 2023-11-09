package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AgenciesProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_profile);

        Spinner spinnerCurrency = findViewById(R.id.spinnerCurrency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_options, // Definimos las opciones en strings.xml dentro del array
                android.R.layout.simple_spinner_item


        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);


        RatingBar ratingBar = findViewById(R.id.ratingBar);

        // aca obtenemos la calificación actual
        float currentRating = ratingBar.getRating();

        // Establecemos la calificacion
        ratingBar.setRating(4.0f); // aca podemos ajustar la calificación según queramos



    }
    public void volver(View view) {
        Intent miIntent=new Intent(AgenciesProfile.this, ListAgencieProperties.class);
        startActivity(miIntent);
    }
}
