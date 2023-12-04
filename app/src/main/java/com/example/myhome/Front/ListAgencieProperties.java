package com.example.myhome.Front;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Ignore.ImageSliderAdapter;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ListAgencieProperties extends AppCompatActivity implements PropertiesCallback {

    private Button btnPropiedades;
    private Button btnNuevaPropiedad;
    private Button btnPerfil;
    private LinearLayout cardConteiner;
    private List<PropertySummary> properties;
    private Long agencyId;
   private  EditText searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_main);

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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int rootViewHeight = decorView.getHeight();
                int keypadHeight = rootViewHeight - decorView.findViewById(android.R.id.content).getHeight();
                if (keypadHeight > rootViewHeight * 0.15) {
                    // Teclado visible, ocultar BottomNavigationView
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    // Teclado oculto, mostrar BottomNavigationView
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });


        searchEditText = findViewById(R.id.editTextSearch);
        cardConteiner = findViewById(R.id.cardContainer);
        cargarPropiedades();
        setupSearchEditText();
    }

    // Override //
    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
        actualizarVista(properties);
    }

    @Override
    public void onPropertiesSuccess(Properties propiedad) {}

    public void onResume() {
        super.onResume();
        cardConteiner.removeAllViews();
        cargarPropiedades();
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {
        Toast.makeText(this, "La propiedad ha sido eliminada", Toast.LENGTH_SHORT).show();
        cardConteiner.removeView(cardConteiner.findViewById(Integer.valueOf(propertyId.toString())));

        searchEditText.setText("");
        filterProperties("");
        cargarPropiedades();
    }

    //  Métodos //
    private void cargarPropiedades() {
        FiltersDTO filters = new FiltersDTO();
        this.properties = null;

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            agencyId = ((MyHome) this.getApplication()).getUsuario().getAgencyId();
            filters.setAgencyId(agencyId);
        }

        PropertyApi propertyApi = new PropertyApi();
        properties = propertyApi.verPropiedades(filters, this);
    }

    public void verPropiedades(View view) {

        Intent miIntent = new Intent(ListAgencieProperties.this, ListAgencieProperties.class);
        startActivity(miIntent);
    }

    public void nuevaPropiedad(View view) {

        Intent miIntent = new Intent(ListAgencieProperties.this, NewProperties.class);
        startActivity(miIntent);
    }

    public void verPerfil(View view) {

        Intent miIntent = new Intent(ListAgencieProperties.this, AgenciesProfile.class);
        startActivity(miIntent);
    }

    private void setupSearchEditText() {

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No necesario para tu caso
                Log.d("TAG", "beforeTextChanged: " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterProperties(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TAG", "afterTextChanged: " + editable.toString());
                // No necesario para tu caso
            }
        });
    }

    private void filterProperties(String searchText) {
        List<PropertySummary> filteredProperties = new ArrayList<>();
        if(properties!=null){
            filteredProperties = properties.stream()
                    .filter(property -> propertyContainsText(property, searchText))
                    .collect(Collectors.toList());
           }
        actualizarVista(filteredProperties);
    }

    private boolean propertyContainsText(PropertySummary property, String searchText) {
        // Verifica si la descripción de la propiedad contiene el texto de búsqueda
        String propertyDescription = property.getPropertyAddress().toLowerCase();
        return propertyDescription.contains(searchText.toLowerCase());
    }

    private void actualizarVista(List<PropertySummary> properties) {
        cardConteiner.removeAllViews();

        if (properties != null) {
            if(this.properties == null){
                this.properties = properties;
            }

            for (PropertySummary p : properties) {

                View propertyCard = LayoutInflater.from(this).inflate(R.layout.card_property, cardConteiner, false);
                String[] propertyImages = p.getPropertyImages();

                if (propertyImages != null) {


                    if (propertyImages.length > 0 && !(propertyImages[0].isEmpty())) {

                        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, Arrays.asList(propertyImages));
                        ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);
                    }

                    else{
                        propertyImages = new String[1];
                        propertyImages[0] = "https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg";
                    }

                }
                else{
                    propertyImages = new String[1];
                    propertyImages[0] = "https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg";
                }

                ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, Arrays.asList(propertyImages));
                ((LoopingViewPager) propertyCard.findViewById(R.id.imageSliderSlider)).setAdapter(imageSliderAdapter);


                ((TextView) propertyCard.findViewById(R.id.propertyPrice)).setText("USD ".concat(p.getPropertyPrice().toString()));
                ((TextView) propertyCard.findViewById(R.id.propertyAddress)).setText(p.getPropertyAddress());
                ((TextView) propertyCard.findViewById(R.id.propertyLocation)).setText(p.getPropertyNeighbourhood().concat(", ").concat(p.getPropertyCity()));
                ((TextView) propertyCard.findViewById(R.id.propertyDimensions)).setText(p.getPropertyDimension().toString().concat(" M2"));
                ((TextView) propertyCard.findViewById(R.id.propertyRooms)).setText(p.getPropertyBedroomQuantity().toString().concat(" Habitaciones"));
                ((TextView) propertyCard.findViewById(R.id.propertyDescription)).setText(p.getPropertyDescription());

                propertyCard.setId(Integer.valueOf(p.getPropertyId().toString()));

                propertyCard.findViewById(R.id.eliminarPropiedad).setOnClickListener(new View.OnClickListener() {

                    // Establecer clic en eliminar propiedad
                    public void onClick(View v) {
                        // Mostramos un mensaje de advertencia al usuario
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListAgencieProperties.this);
                        builder.setTitle("Eliminar propiedad");
                        builder.setMessage("¿Está seguro de que desea eliminar su propiedad?");
                        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Obtengo el ID de la propiedad
                                Long propertyId = p.getPropertyId();

                                //Llamo a retrofit para eliminar la propiedad
                                PropertyApi propertyApi = new PropertyApi();
                                propertyApi.eliminarPropiedad(propertyId, agencyId, ListAgencieProperties.this);

                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // No hace nada
                            }
                        });
                        builder.show();
                    }
                });

                propertyCard.findViewById(R.id.editarPropiedad).setOnClickListener(new View.OnClickListener() {

                    // Establecer clic en editar propiedad
                    public void onClick(View v) {
                        // Mostramos un mensaje de advertencia al usuario
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListAgencieProperties.this);
                        builder.setTitle("Editar propiedad");
                        builder.setMessage("¿Está seguro de que desea editar su propiedad?");
                        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Obtengo el ID de la propiedad
                                String propertyId = p.getPropertyId().toString();

                                //Iniciar la actividad para editar la propiedad pasando el id de propiedad
                                Intent intent = new Intent(ListAgencieProperties.this, EditProperty.class);
                                intent.putExtra("propertyId", propertyId);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // No hace nada
                            }
                        });
                        builder.show();
                    }
                });

                // Establecer clic en la vista
                propertyCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Obtengo el ID de la propiedad
                        String propertyId = p.getPropertyId().toString();

                        // Iniciar la actividad DetailProperty y paso el ID como extra
                        Intent intent = new Intent(ListAgencieProperties.this, DetailProperty.class);
                        intent.putExtra("propertyId", propertyId);
                        startActivity(intent);
                    }
                });

                cardConteiner.addView(propertyCard);

            }

        }


    }

}