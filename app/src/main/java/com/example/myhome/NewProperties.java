package com.example.myhome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class NewProperties extends AppCompatActivity implements PropertiesCallback {

    private GridView gridView;
    private ImageAdapter imageAdapter;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private static final int PICK_IMAGES_REQUEST = 1;
    private Spinner spinner;
    private CustomSpinnerAdapter adapter;
    private CheckBox checkBox;
    private Properties properties = new Properties();
    private Address address = new Address();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_properties);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Obtén el ID del ítem de menú correspondiente a esta actividad
        int menuItemId = R.id.action_add; // Reemplaza con el ID correcto para esta actividad

        // Marcar el ítem del menú como seleccionado
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configurar el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandler.handleMenuItemClick(this, item);
            return true;
        });

        spinner = findViewById(R.id.spnrAmenities);
        adapter = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.lista_amenities));
        spinner.setAdapter(adapter);

        gridView = findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(this, imageUris);
        gridView.setAdapter(imageAdapter);

        Button btnAddImage = findViewById(R.id.btnAddImage);
        Button btnGuardarPropiedad = findViewById(R.id.btnGuardarPropiedad);

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería para seleccionar imágenes
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccionar imágenes"), PICK_IMAGES_REQUEST);
            }
        });

        btnGuardarPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.spnrAmenities);

                setAddress();
                setProperty();
                guardarPropiead();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                imageUris.add(imageUri);
            }
            imageAdapter.notifyDataSetChanged();
        }
    }

    private void setAddress() {
        address.setAddressName(((TextView) findViewById(R.id.txtCalle)).getText().toString());
        address.setAddressNumber(Integer.parseInt(((TextView) findViewById(R.id.txtNumero)).getText().toString()));
        address.setAddressFloor(Integer.parseInt(((TextView) findViewById(R.id.txtPiso)).getText().toString()));
        address.setAddressUnit(((TextView) findViewById(R.id.txtDpto)).getText().toString());
        address.setAddressNeighbourhood(((TextView) findViewById(R.id.txtBarrio)).getText().toString());
        address.setAddressCity(((TextView) findViewById(R.id.txtLocalidad)).getText().toString());
        address.setAddressState(((TextView) findViewById(R.id.txtProvincia)).getText().toString());
        address.setAddressCountry(((TextView) findViewById(R.id.txtPais)).getText().toString());
        address.setAddressLatitude(0);
        address.setAddressLongitude(0);
    }

    private void setProperty() {
        String[] amenities = new String[adapter.getAmenities().size()];
        int x = 0;

        for (String i : adapter.getAmenities()) {
            amenities[x] = i;
            x++;
        }

        properties.setPropertyAddress(address);
        Object temp = ((MyHome) this.getApplication()).getUsuario();
        properties.setAgencyId(((MyHome) this.getApplication()).getUsuario().getAgencyId());
        properties.setPropertyType(((Spinner) findViewById(R.id.spnrTipoPropiedad)).getSelectedItem().toString());
        properties.setPropertyStatus(((Spinner) findViewById(R.id.spnrEstado)).getSelectedItem().toString());
        properties.setPropertyPrice(Integer.parseInt(((TextView) findViewById(R.id.txtPrecioPropiedad)).getText().toString()));
        properties.setPropertyExpenses(Integer.parseInt(((TextView) findViewById(R.id.txtPrecioExpensas)).getText().toString()));
        properties.setPropertyRoomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadAmbientes)).getText().toString()));
        properties.setPropertyBedroomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadCuartos)).getText().toString()));
        properties.setPropertyBathroomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadBanios)).getText().toString()));
        properties.setPropertyGarageQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadCochera)).getText().toString()));
        properties.setPropertyHasBalcony(((Switch) findViewById(R.id.tieneBalcon)).isChecked());
        properties.setPropertyHasGarage(((Switch) findViewById(R.id.tieneCochera)).isChecked());
        properties.setPropertyHasStorage(((Switch) findViewById(R.id.tieneBaulera)).isChecked());
        properties.setpropertyTerrace(((Switch) findViewById(R.id.tieneTerraza)).isChecked());
        properties.setPropertyPosition(((Spinner) findViewById(R.id.spnrPosicion)).getSelectedItem().toString());
        properties.setPropertyOrientation(((Spinner) findViewById(R.id.spnrOrientacion)).getSelectedItem().toString());
        properties.setPropertyAge(((Spinner) findViewById(R.id.spnrAntiguedad)).getSelectedItem().toString());
        properties.setPropertyAmenities(amenities);
        properties.setPropertyDescription(((TextView) findViewById(R.id.txtDescripcion1)).getText().toString());
        properties.setPropertyCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtCubiertos)).getText().toString()));
        properties.setPropertySemiCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtSemiCubiertos)).getText().toString()));
        properties.setPropertyUncoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtCubiertos)).getText().toString()));
    }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {}

    @Override
    public void onPropertiesSuccess(Properties propiedad) {
        if (propiedad != null) {
            Intent miIntent = new Intent(NewProperties.this, ListAgencieProperties.class);
            startActivity(miIntent);
        }
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void guardarPropiead(){
        PropertyApi propertyApi = new PropertyApi();
        propertyApi.setPropiedades(properties, this);
    }
}