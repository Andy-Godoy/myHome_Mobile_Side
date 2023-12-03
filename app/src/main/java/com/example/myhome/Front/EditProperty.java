package com.example.myhome.Front;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Address;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertyDTO;
import com.example.myhome.model.PropertySummary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditProperty extends AppCompatActivity implements PropertiesCallback {
    private CustomSpinnerAdapter adapter;
    private Spinner spnrTipoPropiedad;
    private Spinner spnrEstado;
    private Spinner spnrOrientacion;
    private Spinner spnrPosicion;
    private Spinner spnrAntiguedad;
    private Properties propiedad;
    private boolean isLoadView = true;
    private final Address address = new Address();
    private TextView tvCourses;
    private MaterialCardView selectedCard;
    private boolean[] selectedCourses;
    private final ArrayList<Integer> coursesList = new ArrayList<>();
    private String[] courses;
    private String[] amenities = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configuramos el listener para los elementos del menú
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

        tvCourses = findViewById(R.id.spnrAmenities);
        selectedCard = findViewById(R.id.selectCard);
        courses = getResources().getStringArray(R.array.lista_amenities);
        selectedCourses = new boolean[courses.length];

        selectedCard.setOnClickListener(v -> {
            showCoursesDialog();
        });

        adapter = new CustomSpinnerAdapter(this, getResources().getStringArray(R.array.lista_amenities));
        adapter.setCheckBoxesEnabled(false); // Deshabilita checkbox "Todos"

        spnrTipoPropiedad = findViewById(R.id.spnrTipoPropiedad);

        spnrEstado = findViewById(R.id.spnrEstado);

        spnrOrientacion = findViewById(R.id.spnrOrientacion);

        spnrPosicion = findViewById(R.id.spnrPosicion);

        spnrAntiguedad = findViewById(R.id.spnrAntiguedad);

        obtenerPropiedad();


        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Editar la propiedad
                if (validacion()) {
                    setAddress();
                    setProperty();
                    saveChanges();
                }
            }
        });
    }

    private void showCoursesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProperty.this);
        builder.setTitle("Selección amenities");
        builder.setCancelable(false);

        boolean[] originalSelectedCourses = Arrays.copyOf(selectedCourses, selectedCourses.length);
        ArrayList<Integer> originalCoursesList = new ArrayList<>(coursesList);
        builder.setMultiChoiceItems(courses, selectedCourses, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (which >= 0 && which < courses.length) {
                    // Si se hace clic en otro elemento que no sea "Todos"
                    if (isChecked) {
                        // Agregar el elemento a la lista
                        if (!coursesList.contains(which)) {
                            coursesList.add(which);
                        }
                    } else {
                        // Eliminar el elemento de la lista
                        coursesList.remove(Integer.valueOf(which));
                    }
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder = new StringBuilder();

                if (coursesList.size() > 0) {
                    amenities = new String[coursesList.size()];
                } else {
                    amenities = new String[]{};
                }

                for (int i = 0; i < coursesList.size(); i++) {
                    amenities[i] = courses[coursesList.get(i)];
                }

                for (int i = 0; i < coursesList.size(); i++) {
                    if (i < 3) {
                        stringBuilder.append(courses[coursesList.get(i)]);
                        if (i != coursesList.size() - 1) {
                            stringBuilder.append(", ");
                        }
                    } else {
                        stringBuilder.append("...");
                        break;
                    }
                }

                tvCourses.setText(stringBuilder.toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Restaurar al estado original al hacer clic en Cancelar
                System.arraycopy(originalSelectedCourses, 0, selectedCourses, 0, selectedCourses.length);
                coursesList.clear();
                coursesList.addAll(originalCoursesList);

                // Actualizar el TextView con el estado original
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < coursesList.size(); i++) {
                    stringBuilder.append(courses[coursesList.get(i)]);
                    if (i != coursesList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                tvCourses.setText(stringBuilder.toString());

                dialog.dismiss();
            }
        }).setNeutralButton("Limpiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < selectedCourses.length; i++) {
                    selectedCourses[i] = false;
                    coursesList.clear();
                    tvCourses.setText("");
                }
            }
        });

        builder.show();
    }

    private void setProperty() {

        propiedad.setPropertyAddress(address);
        propiedad.setPropertyType(((Spinner) findViewById(R.id.spnrTipoPropiedad)).getSelectedItem().toString());
        propiedad.setPropertyStatus(((Spinner) findViewById(R.id.spnrEstado)).getSelectedItem().toString());
        propiedad.setAgencyId(((MyHome) this.getApplication()).getUsuario().getAgencyId());
        propiedad.setPropertyPrice(Integer.parseInt(((TextView) findViewById(R.id.txtPrecioPropiedad)).getText().toString()));
        propiedad.setPropertyExpenses(Integer.parseInt(((TextView) findViewById(R.id.txtPrecioExpensas)).getText().toString()));
        propiedad.setPropertyRoomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadAmbientes)).getText().toString()));
        propiedad.setPropertyBedroomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadCuartos)).getText().toString()));
        propiedad.setPropertyBathroomQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadBanios)).getText().toString()));
        propiedad.setPropertyGarageQuantity(Integer.parseInt(((TextView) findViewById(R.id.txtCantidadCochera)).getText().toString()));
        propiedad.setPropertyHasBalcony(((Switch) findViewById(R.id.tieneBalcon)).isChecked());
        propiedad.setPropertyHasGarage(((Switch) findViewById(R.id.tieneCochera)).isChecked());
        propiedad.setPropertyHasStorage(((Switch) findViewById(R.id.tieneBaulera)).isChecked());
        propiedad.setPropertyHasTerrace(((Switch) findViewById(R.id.tieneTerraza)).isChecked());
        propiedad.setPropertyPosition(((Spinner) findViewById(R.id.spnrPosicion)).getSelectedItem().toString());
        propiedad.setPropertyOrientation(((Spinner) findViewById(R.id.spnrOrientacion)).getSelectedItem().toString());
        propiedad.setPropertyAge(((Spinner) findViewById(R.id.spnrAntiguedad)).getSelectedItem().toString());
        propiedad.setPropertyAmenities(amenities);
        propiedad.setPropertyDescription(((TextView) findViewById(R.id.txtDescripcion1)).getText().toString());
        propiedad.setPropertyCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtCubiertos)).getText().toString()));
        propiedad.setPropertySemiCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtSemiCubiertos)).getText().toString()));
        propiedad.setPropertyUncoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtDescubiertos)).getText().toString()));
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
        this.propiedad = propiedad;

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
        tieneTerraza.setChecked(propiedad.getPropertyHasTerrace());

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

        amenities = propiedad.getPropertyAmenities();

        if (!(amenities.length == 1 && (amenities[0].isEmpty() || amenities[0] == null))) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < amenities.length; i++) {
                coursesList.add(Arrays.asList(courses).indexOf(amenities[i]));
            }

            coursesList.forEach(position -> {
                selectedCourses[position] = true;
            });

            for (int i = 0; i < amenities.length; i++) {
                if (i < 3) {
                    stringBuilder.append(courses[coursesList.get(i)]);
                    if (i != amenities.length - 1) {
                        stringBuilder.append(", ");
                    }
                } else {
                    stringBuilder.append("...");
                    break;
                }
            }

            tvCourses.setText(stringBuilder.toString());
        }

        if (!isLoadView) {

            Toast.makeText(EditProperty.this, "Los cambios han sido guardados exitosamente.", Toast.LENGTH_SHORT).show();

            // Vuelve a la pantalla anterior
            finish();

        }
        isLoadView = false;

    }

    @Override
    public void onPropertiesFailure(String errorMessage) {
    }

    @Override
    public void onPropertiesSuccess(Long propertyId) {

    }

    public void obtenerPropiedad() {
        PropertyDTO property = new PropertyDTO();

        Long userId = 0l;

        if (((MyHome) this.getApplication()).getUsuario() != null) {
            userId = ((MyHome) this.getApplication()).getUsuario().getUserId();
            property.setPropertyId(Long.parseLong(getIntent().getStringExtra("propertyId")));
        }

        PropertyApi propertyApi = new PropertyApi();
        propiedad = propertyApi.obtenerPropiedad(property, userId, this);
    }

    private int obtenerPosicion(String[] opciones, String opcion) {
        opciones = Arrays.stream(opciones)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        return Arrays.asList(opciones).indexOf(opcion.toLowerCase());
    }

    public void saveChanges() {

        Long agencyId = ((MyHome) this.getApplication()).getUsuario().getAgencyId();
        // Este método se llamará tanto desde el Button como desde el ImageButton
        PropertyApi propertyApi = new PropertyApi();
        propertyApi.editarPropiedad(propiedad, agencyId ,this);
    }


    // Validaciones sobre la completitud de el formulario
    public boolean validacion() {
        boolean esValido = true;

        //Address
        TextView addressName = findViewById(R.id.txtCalle);
        TextView addressNumber = findViewById(R.id.txtNumero);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);
        TextView addressCountry = findViewById(R.id.txtPais);

        //Property
        Spinner tipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        Spinner estado = findViewById(R.id.spnrEstado);
        TextView precioPropiedad = findViewById(R.id.txtPrecioPropiedad);
        TextView precioExpensas = findViewById(R.id.txtPrecioExpensas);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientes);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartos);
        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadCochera = findViewById(R.id.txtCantidadCochera);
        TextView cubiertos = findViewById(R.id.txtCubiertos);
        TextView semiCubiertos = findViewById(R.id.txtSemiCubiertos);
        TextView descubiertos = findViewById(R.id.txtDescubiertos);

        if (addressName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un nombre de calle", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (addressNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un numero de calle", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (addressCity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una ciudad", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (addressState.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una provincia", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (addressCountry.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un pais", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (tipoPropiedad.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Debe seleccionar un tipo de propiedad", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (estado.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Debe seleccionar un estado", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (precioPropiedad.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un precio de propiedad", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (precioExpensas.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un precio de expensas", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadAmbientes.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de ambientes", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadCochera.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de cocheras", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadBanios.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de baños", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadCuartos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de cuartos", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cubiertos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de metros cubiertos", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (semiCubiertos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de metros semi cubiertos", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (descubiertos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de metros descubiertos", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (Integer.parseInt(cubiertos.getText().toString()) <=0) {
            Toast.makeText(this, "Debe ingresar una cantidad de metros cubiertos mayor a 0", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }
    public void btnClose(View view) {
        // Este método se llamará tanto desde el Button como desde el ImageButton
        finish();
    }

}
