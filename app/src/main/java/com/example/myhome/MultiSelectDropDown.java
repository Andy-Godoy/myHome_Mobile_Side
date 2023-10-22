package com.example.myhome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MultiSelectDropDown extends AppCompatActivity {
    //inicializo las variables
    TextView tvAmenities;
    boolean[] selectedAmenities;
    ArrayList<Integer> AmenitiesList = new ArrayList<>();
    String[] AmenitiesArray = {"Gimnasio", "Quincho", "Pileta", "Jacuzzi", "Sauna", "SUM", "Sala de Juegos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_select_drop_down);

        //asigno las variables
        tvAmenities = findViewById(R.id.tv_amenities);

        //inicializo la seleccion en el array
        selectedAmenities = new boolean[AmenitiesArray.length];
        tvAmenities.setOnClickListener(v -> {
            //inicializo el Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MultiSelectDropDown.this);

            //seteo el titulo
            builder.setTitle("Seleccionar Amenities");

            //seteo el dialog no cancelable
            builder.setCancelable(false);

            builder.setMultiChoiceItems(AmenitiesArray, selectedAmenities, (dialog, which, isChecked) -> {
                //compruebo si el checkbox esta seleccionado
                if (isChecked) {
                    //si esta seleccionado, agrego el indice a la lista
                    AmenitiesList.add(which);
                    //ordeno la lista
                    AmenitiesList.sort(Integer::compareTo);
                } else {
                    //si no esta seleccionado, remuevo el indice de la lista
                    AmenitiesList.remove(which);
                }
            });

            builder.setPositiveButton("Aceptar", (dialog, which) -> {
                //inicializo la variable string
                StringBuilder item = new StringBuilder();
                //recorro la lista
                for (int i = 0; i < AmenitiesList.size(); i++) {
                    //concateno el valor del array con la variable string
                    item.append(AmenitiesArray[AmenitiesList.get(i)]);
                    //compruebo si el indice es menor que el tamaño de la lista
                    if (i < AmenitiesList.size() - 1) {
                        //si es menor, concateno una coma
                        item.append(", ");
                    }
                }
                //seteo el texto del textview
                tvAmenities.setText(item.toString());
            });
            builder.setNegativeButton("Limpiar Todo", (dialog, which) -> {
                //recorro la lista
                for (int i = 0; i < selectedAmenities.length; i++) {
                    //seteo el valor del array en falso
                    selectedAmenities[i] = false; //selecciono todos los items
                    AmenitiesList.clear();
                    AmenitiesList.size();
                }
                //seteo el texto del textview
                tvAmenities.setText("");
            });


            builder.setNeutralButton("seleccionar Todo", (dialog, which) -> {
                        StringBuilder item = new StringBuilder();
                //recorro la lista
                for (int i = 0; i < selectedAmenities.length; i++) {
                    //seteo el valor del array en falso
                    selectedAmenities[i] = true; //selecciono todos los items
                    //agrego el indice a la lista
                    AmenitiesList.add(i);
                    //ordeno la lista
                    AmenitiesList.sort(Integer::compareTo);
                    //concateno el valor del array con la variable string
                    item.append(AmenitiesArray[i]);
                    item.append(", "); //separo los items con una coma


                }


                    //seteo el texto del textview
                    tvAmenities.setText(item.toString());



            });

            //muestro el dialog
            builder.show();
        });
    }
}