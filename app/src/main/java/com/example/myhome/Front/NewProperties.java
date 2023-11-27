package com.example.myhome.Front;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.AzureService.AzureBlobStorageManager;
import com.example.myhome.model.Address;
import com.example.myhome.Front.ImageAdapter;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Interfaces.PropertiesCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Address;
import com.example.myhome.model.Properties;
import com.example.myhome.model.PropertySummary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private TextView tvCourses;
    private MaterialCardView selectedCard;
    private boolean[] selectedCourses;
    private ArrayList<Integer> coursesList = new ArrayList<>();
    private String[] courses;
    private String[] amenities = new String[]{};

    private AzureBlobStorageManager azureBlobStorageManager;


    private void handleImageSelection(List<Uri> selectedImages) {
        // Aquí puedes realizar cualquier acción necesaria con las imágenes seleccionadas
        // Por ejemplo, agregarlas a la lista imageUris
        imageUris.addAll(selectedImages);
        imageAdapter.notifyDataSetChanged(); // Asegúrate de notificar al adaptador sobre los cambios
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_properties);

        azureBlobStorageManager = new AzureBlobStorageManager(this);

        tvCourses = findViewById(R.id.spnrAmenities);
        selectedCard = findViewById(R.id.selectCard);
        courses = getResources().getStringArray(R.array.lista_amenities);
        selectedCourses = new boolean[courses.length];

        selectedCard.setOnClickListener(v -> {
            showCoursesDialog();
        });

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

       // Obtenemos el ID del ítem de menú correspondiente a esta actividad
        int menuItemId = R.id.action_add; // Reemplazamos con el ID correcto para esta actividad

        // Marcamos el ítem del menú como seleccionado
        bottomNavigationView.setSelectedItemId(menuItemId);

        // Configuramos el listener para los elementos del menú
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            MenuHandler.handleMenuItemClick(this, item);
            return true;
        });

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
                azureBlobStorageManager.openGallery(intent);
                startActivityForResult(intent, PICK_IMAGES_REQUEST);
            }
        });

        btnGuardarPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacion()) {
                    setAddress();
                    setProperty();
                    if (azureBlobStorageManager.uploadImages(imageUris))
                    {


                        //properties.setPropertyImages((String[]) azureBlobStorageManager.getUploadedImageUrls().toArray());
                        Object[] objectArray = azureBlobStorageManager.getUploadedImageUrls().toArray();
                        String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);

                        properties.setPropertyImages(stringArray);
                        guardarPropiedad();
                    }

                }
           }
        });
    }

    private void showCoursesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewProperties.this);
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
//                .setNeutralButton("Todos", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                for (int i = 0; i < selectedCourses.length; i++) {
//                    selectedCourses[i] = true;
//                    coursesList.add(i);
//                }
//                // Actualizar la vista con la lista seleccionada
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < coursesList.size(); i++) {
//                    stringBuilder.append(courses[coursesList.get(i)]);
//                    if (i != coursesList.size() - 1) {
//                        stringBuilder.append(", ");
//                    }
//                }
//                tvCourses.setText(stringBuilder.toString());
//            }
//        })

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                List<Uri> selectedImages = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    selectedImages.add(imageUri);
                }
                handleImageSelection(selectedImages);
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                handleImageSelection(Collections.singletonList(imageUri));
            }
        }
    }

    private void setAddress() {
        address.setAddressName(((TextView) findViewById(R.id.txtCalle)).getText().toString());
        if (((TextView) findViewById(R.id.txtNumero)).getText().toString() != null && (((TextView) findViewById(R.id.txtNumero)).getText().toString() != "")) {
            address.setAddressNumber(Integer.parseInt(((TextView) findViewById(R.id.txtNumero)).getText().toString()));
        }
        if (((EditText) findViewById(R.id.txtPiso)).getText().toString() != null && (((EditText) findViewById(R.id.txtPiso)).getText().toString() != "")){
            address.setAddressFloor(Integer.parseInt(((EditText) findViewById(R.id.txtPiso)).getText().toString()));
        }
        address.setAddressUnit(((TextView) findViewById(R.id.txtDpto)).getText().toString());
        address.setAddressNeighbourhood(((TextView) findViewById(R.id.txtBarrio)).getText().toString());
        address.setAddressCity(((TextView) findViewById(R.id.txtLocalidad)).getText().toString());
        address.setAddressState(((TextView) findViewById(R.id.txtProvincia)).getText().toString());
        address.setAddressCountry(((TextView) findViewById(R.id.txtPais)).getText().toString());
        address.setAddressLatitude(0);
        address.setAddressLongitude(0);
    }

    private void setProperty() {
        properties.setPropertyAddress(address);
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
        properties.setpropertyHasTerrace(((Switch) findViewById(R.id.tieneTerraza)).isChecked());
        properties.setPropertyPosition(((Spinner) findViewById(R.id.spnrPosicion)).getSelectedItem().toString());
        properties.setPropertyOrientation(((Spinner) findViewById(R.id.spnrOrientacion)).getSelectedItem().toString());
        properties.setPropertyAge(((Spinner) findViewById(R.id.spnrAntiguedad)).getSelectedItem().toString());
        properties.setPropertyAmenities(amenities);
        properties.setPropertyDescription(((TextView) findViewById(R.id.txtDescripcion1)).getText().toString());
        properties.setPropertyCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtCubiertos)).getText().toString()));
        properties.setPropertySemiCoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtSemiCubiertos)).getText().toString()));
        properties.setPropertyUncoveredM2(Integer.parseInt(((TextView) findViewById(R.id.txtDescubiertos)).getText().toString()));

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

    @Override
    public void onPropertiesSuccess(Long propertyId) {

    }

    public void guardarPropiedad() {
        PropertyApi propertyApi = new PropertyApi();
        propertyApi.setPropiedades(properties, this);
    }

    public boolean validacion() {
        boolean esValido = true;

        //Address
        TextView addressName = findViewById(R.id.txtCalle);
        TextView addressNumber = findViewById(R.id.txtNumero);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);
        TextView addressCountry = findViewById(R.id.txtPais);
        TextView addresFloor = findViewById(R.id.txtPiso);

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
        }else if (addresFloor.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un piso mayor o igual a cero", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }
}