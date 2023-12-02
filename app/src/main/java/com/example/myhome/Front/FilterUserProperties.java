package com.example.myhome.Front;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.R;
import com.example.myhome.model.Properties;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Front.ListUserProperties;
import com.google.android.material.card.MaterialCardView;
import org.florescu.android.rangeseekbar.RangeSeekBar;


import android.view.View;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class FilterUserProperties extends AppCompatActivity {
    private TextView tvCourses;
    private MaterialCardView selectedCard;
    private boolean[] selectedCourses;
    private final ArrayList<Integer> coursesList = new ArrayList<>();
    private String[] courses;
    private String[] amenities = new String[]{};


    // private Properties properties = new Properties();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_user_properties);



        RangeSeekBar rangeSeekBar = findViewById(R.id.rangeSeekbar);

        Spinner estado = findViewById(R.id.spnrEstado);

        if(estado.toString().equals("Alquiler")){
            rangeSeekBar.setRangeValues(0, 100000);
        }else {
            rangeSeekBar.setRangeValues(0, 500000);
        }



        //amenities
        tvCourses = findViewById(R.id.spnrAmenitiesFilter);
        selectedCard = findViewById(R.id.selectCard);
        courses = getResources().getStringArray(R.array.lista_amenities);
        selectedCourses = new boolean[courses.length];

        selectedCard.setOnClickListener(v -> {
            showCoursesDialog();
        });

        //boton para volver atras
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                // Create an Intent to open the ListUserProperties activity
                Intent intent = new Intent(context, ListUserProperties.class);

                // Start the ListUserProperties activity
                context.startActivity(intent);
            }
        });
        //boton aplicar filtros
        Button btnAplicarFiltros = findViewById(R.id.btnAplicarFiltros);
        btnAplicarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //trigger de aplicar filtors

                if(validacionFiltro()) {
                    FiltersDTO filters=aplicarFiltros();

                    Intent intent=new Intent();
                    intent.putExtra("filters", (Serializable) filters);
                    setResult(RESULT_OK,intent);
                    finish();

                }
            }
        });


        //boton limpiar filtros
        Button btnLimpiarFiltros=findViewById(R.id.btnCleanFilters);

        btnLimpiarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               limpiarfiltro();

            }
        });

    }


    public boolean validacionFiltro() {
        boolean esValido = true;
        //precio Max y Min


        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientesEdit);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartosEdit);

        RangeSeekBar rangeSeekBar = findViewById(R.id.rangeSeekbar);



        int precioMaxvalor = rangeSeekBar.getSelectedMaxValue().intValue();


        int precioMinvalor =rangeSeekBar.getSelectedMinValue().intValue();

        String valorCantidadBanios=cantidadBanios.getText().toString();
        int cantidadBaniosValor =(valorCantidadBanios.isEmpty())?0:Integer.valueOf(valorCantidadBanios);

        String valorcantidadAmbientes=cantidadAmbientes.getText().toString();
        int cantidadAmbientesValor =(valorcantidadAmbientes.isEmpty())?0:Integer.valueOf(valorcantidadAmbientes);

        String valorCantidadCuartos=cantidadCuartos.getText().toString();
        int cantidadCuartosValor =(valorCantidadCuartos.isEmpty())?0:Integer.valueOf(valorCantidadCuartos);




//validad cantidad banios ambientes y cuartos



       if (precioMaxvalor<precioMinvalor){
            Toast.makeText(this, "El precio maximo debe ser mayor que el menor", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (precioMaxvalor < 0 || precioMinvalor < 0){
            Toast.makeText(this, "Los precios deben ser mayor a 0", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }

    public FiltersDTO aplicarFiltros(){
        RangeSeekBar rangeSeekBar = findViewById(R.id.rangeSeekbar);

        Spinner tipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        Spinner estado = findViewById(R.id.spnrEstado);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);


        //Spinner amenities=findViewById(R.id.spnrAmenitiesFilter);
        Spinner antiguedad=findViewById(R.id.spnrAntiguedad);
        TextView addressCountry = findViewById(R.id.txtPaisEdit);
        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientesEdit);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartosEdit);

        int precioMaxvalor = rangeSeekBar.getSelectedMaxValue().intValue();


        int precioMinvalor =rangeSeekBar.getSelectedMinValue().intValue();






        String valorCantidadBanios=cantidadBanios.getText().toString();
        int cantidadBaniosValor =(valorCantidadBanios.isEmpty())?0:Integer.valueOf(valorCantidadBanios);

        String valorcantidadAmbientes=cantidadAmbientes.getText().toString();
        int cantidadAmbientesValor =(valorcantidadAmbientes.isEmpty())?0:Integer.valueOf(valorcantidadAmbientes);

        String valorCantidadCuartos=cantidadCuartos.getText().toString();
        int cantidadCuartosValor =(valorCantidadCuartos.isEmpty())?0:Integer.valueOf(valorCantidadCuartos);


        //le paso la data a FiltersDTO
        FiltersDTO filtersDTO = new FiltersDTO();

        filtersDTO.setPropertyType(tipoPropiedad.toString());
        filtersDTO.setPropertyStatus(estado.toString()); //poner .getSelected revisar si manda bien
        filtersDTO.setLocalidad(addressCity.toString());
        filtersDTO.setProvincia(addressState.toString());
        filtersDTO.setPropertyAmenities(amenities);
        filtersDTO.setPropertyAge(antiguedad.toString());
        filtersDTO.setPais(addressCountry.toString());
        filtersDTO.setCantidadBanios(cantidadBaniosValor);
        filtersDTO.setCantidadAmbientes(cantidadAmbientesValor);
        filtersDTO.setCantidadCuatros(cantidadCuartosValor);
        filtersDTO.setPrecioMax(precioMaxvalor);
        filtersDTO.setPrecioMin(precioMinvalor);

        return filtersDTO;
    }

    public void limpiarfiltro(){
        RangeSeekBar rangeSeekBar = findViewById(R.id.rangeSeekbar);

        rangeSeekBar.resetSelectedValues();
        Spinner tipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        Spinner estado = findViewById(R.id.spnrEstado);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);
        //Spinner amenities=findViewById(R.id.spnrAmenitiesFilter);
        Spinner antiguedad=findViewById(R.id.spnrAntiguedad);
        TextView addressCountry = findViewById(R.id.txtPaisEdit);
        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientesEdit);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartosEdit);


        //setea valores por defecto
        tipoPropiedad.setSelection(0);
        estado.setSelection(0);
        addressCity.setText("");
        addressState.setText("");
        //amenities.setSelection(0);
        antiguedad.setSelection(0);
        addressCountry.setText("");
        cantidadBanios.setText("");
        cantidadAmbientes.setText("");
        cantidadCuartos.setText("");


        for (int i = 0; i < selectedCourses.length; i++) {
            selectedCourses[i] = false;
            coursesList.clear();
            tvCourses.setText("");
        }

    }

    private void showCoursesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FilterUserProperties.this);
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


}
