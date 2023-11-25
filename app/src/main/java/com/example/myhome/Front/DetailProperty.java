package com.example.myhome.Front;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.model.Address;
import com.example.myhome.Api.MyHome;
import com.example.myhome.model.Properties;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class DetailProperty extends AppCompatActivity implements PropertiesCallback {
    private CustomSpinnerAdapter adapter;
    private Spinner spnrAmenities;
    private Spinner spnrTipoPropiedad;
    private Spinner spnrEstado;
    private Spinner spnrOrientacion;
    private Spinner spnrPosicion;
    private Spinner spnrAntiguedad;
    private Properties propiedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_property);

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
            MenuHandler.handleMenuItemClick(this, item);
            return true;
        });

        spnrAmenities = findViewById(R.id.spnrAmenities);
        adapter = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.lista_amenities));
        adapter.setCheckBoxesEnabled(false); // Deshabilita checkbox "Todos"

        spnrTipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        spnrTipoPropiedad.setEnabled(false);

        spnrEstado = findViewById(R.id.spnrEstado);
        spnrEstado.setEnabled(false);

        spnrOrientacion = findViewById(R.id.spnrOrientacion);
        spnrOrientacion.setEnabled(false);

        spnrPosicion = findViewById(R.id.spnrPosicion);
        spnrPosicion.setEnabled(false);

        spnrAntiguedad = findViewById(R.id.spnrAntiguedad);
        spnrAntiguedad.setEnabled(false);

        obtenerPropiedad();


        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad y vuelve a la actividad anterior
                finish();
            }
        });
    }

    @Override
    public void onPropertiesSuccess(List<PropertySummary> properties) {
    }

    @Override
    public void onPropertiesSuccess(Properties propiedad) {
        int posicionTipoPropiedad = obtenerPosicion(getResources().getStringArray(R.array.lista_de_tipo_propiedad), propiedad.getPropertyType());
        int posicionEstado = obtenerPosicion(getResources().getStringArray(R.array.lista_de_estados), propiedad.getPropertyStatus());
        int posicionOrientacion = obtenerPosicion(getResources().getStringArray(R.array.lista_orientacion), propiedad.getPropertyOrientation());
        int posicionPosicion = obtenerPosicion(getResources().getStringArray(R.array.lista_posicion), propiedad.getPropertyPosition());
        int posicionAntiguedad = obtenerPosicion(getResources().getStringArray(R.array.lista_antiguedad), propiedad.getPropertyAge());

        String[] amenitiesApi = propiedad.getPropertyAmenities();
        String[] amenities = getResources().getStringArray(R.array.lista_amenities);
        boolean[] amenitiesChecks = new boolean[amenities.length];

        if (amenitiesApi.length == 0) {
            // Establecer todos los valores en false
            Arrays.fill(amenitiesChecks, false);
            adapter.deselectAll();
        } else if (amenities.length-1 == amenitiesApi.length) {
            // Establecer todos los valores en true
            Arrays.fill(amenitiesChecks, true);
            adapter.selectAll();
        } else {
            for (int i = 0; i < amenities.length; i++) {
                boolean coincide = false;

                for (int j = 0; j < amenitiesApi.length; j++) {
                    if (amenities[i].equals(amenitiesApi[j])) {
                        coincide = true;
                        break;
                    }
                }

                amenitiesChecks[i] = coincide;
            }

            adapter.setCheckedItems(amenitiesChecks);
        }

        spnrAmenities.setAdapter(adapter);
        spnrAmenities.setEnabled(false);

        spnrTipoPropiedad.setSelection(posicionTipoPropiedad);
        spnrEstado.setSelection(posicionEstado);
        spnrOrientacion.setSelection(posicionOrientacion);
        spnrPosicion.setSelection(posicionPosicion);
        spnrAntiguedad.setSelection(posicionAntiguedad);

        Switch tieneCochera = findViewById(R.id.tieneCochera);
        tieneCochera.setChecked(propiedad.getPropertyHasGarage());

        Switch tieneBalcon = findViewById(R.id.tieneBalcon);
        tieneBalcon.setChecked(propiedad.getPropertyHasBalcony());

        Switch tieneBaulera = findViewById(R.id.tieneBaulera);
        tieneBaulera.setChecked(propiedad.getPropertyHasStorage());

        Switch tieneTerraza = findViewById(R.id.tieneTerraza);
        tieneTerraza.setChecked(propiedad.getpropertyHasTerrace());

        Address address = propiedad.getPropertyAddress();

        EditText calle = findViewById(R.id.txtCalle);
        EditText numero = findViewById(R.id.txtNumero);
        EditText piso = findViewById(R.id.txtPiso);
        EditText departamento = findViewById(R.id.txtDpto);
        EditText barrio = findViewById(R.id.txtBarrio);
        EditText provincia = findViewById(R.id.txtProvincia);
        EditText pais = findViewById(R.id.txtPais);
        EditText localidad = findViewById(R.id.txtLocalidad);
        EditText cantidadAmbientes = findViewById(R.id.txtCantidadAmbientes);
        EditText cantidadBanios = findViewById(R.id.txtCantidadBanios);
        EditText cantidadCuartos = findViewById(R.id.txtCantidadCuartos);
        EditText cantidadCocheras = findViewById(R.id.txtCantidadCochera);
        EditText cubiertos = findViewById(R.id.txtCubiertos);
        EditText descubiertos = findViewById(R.id.txtDescubiertos);
        EditText semiCubiertos = findViewById(R.id.txtSemiCubiertos);
        EditText precioPropiedad = findViewById(R.id.txtPrecioPropiedad);
        EditText precioExpensas = findViewById(R.id.txtPrecioExpensas);
        EditText descripcion = findViewById(R.id.txtDescripcion1);

        calle.setText(address.getAddressName());
        numero.setText(String.valueOf(address.getAddressNumber()));
        piso.setText(String.valueOf(address.getAddressFloor()));
        departamento.setText(address.getAddressUnit());
        barrio.setText(address.getAddressNeighbourhood());
        provincia.setText(address.getAddressState());
        pais.setText(address.getAddressCountry());
        localidad.setText(address.getAddressCity());
        cantidadAmbientes.setText(String.valueOf(propiedad.getPropertyBedroomQuantity()));
        cantidadBanios.setText(String.valueOf(propiedad.getPropertyBathroomQuantity()));
        cantidadCuartos.setText(String.valueOf(propiedad.getPropertyRoomQuantity()));
        cantidadCocheras.setText(String.valueOf(propiedad.getPropertyGarageQuantity()));
        cubiertos.setText(String.valueOf(propiedad.getPropertyCoveredM2()));
        descubiertos.setText(String.valueOf(propiedad.getPropertyUncoveredM2()));
        semiCubiertos.setText(String.valueOf(propiedad.getPropertySemiCoveredM2()));
        precioPropiedad.setText(String.valueOf(propiedad.getPropertyPrice()));
        precioExpensas.setText(String.valueOf(propiedad.getPropertyExpenses()));
        descripcion.setText(propiedad.getPropertyDescription());
    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {

    }

    public void obtenerPropiedad() {
        PropertyDTO property = new PropertyDTO();

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, this);
    }

    private int obtenerPosicion(String[] opciones, String opcion) {
        opciones = Arrays.stream(opciones)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        return Arrays.asList(opciones).indexOf(opcion.toLowerCase());
    }

    public void btnClose(View view) {
        // Este método se llamará tanto desde el Button como desde el ImageButton
        finish();
    }
}
