package com.example.myhome.Front;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myhome.R;
import com.example.myhome.model.FiltersDTO;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import java.io.Serializable;


public class FilterUserProperties extends AppCompatActivity {

   // private Properties properties = new Properties();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_user_properties);
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
        TextView precioMax = findViewById(R.id.txtprecioMaxEdit);
        TextView precioMin = findViewById(R.id.txtprecioMinEdit);

        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientesEdit);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartosEdit);


        String valorTextoMax = precioMax.getText().toString();
        int precioMaxvalor = (valorTextoMax.isEmpty())?0:Integer.valueOf(valorTextoMax);

        String valorTextoMin = precioMin.getText().toString();
        int precioMinvalor = (valorTextoMin.isEmpty())?0:Integer.valueOf(valorTextoMin);

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


        Spinner tipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        Spinner estado = findViewById(R.id.spnrEstado);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);
        Spinner amenities=findViewById(R.id.spnrAmenitiesFilter);
        Spinner antiguedad=findViewById(R.id.spnrAntiguedad);
        TextView addressCountry = findViewById(R.id.txtPaisEdit);
        TextView cantidadBanios = findViewById(R.id.txtCantidadBanios);
        TextView cantidadAmbientes = findViewById(R.id.txtCantidadAmbientesEdit);
        TextView cantidadCuartos = findViewById(R.id.txtCantidadCuartosEdit);

        TextView precioMax = findViewById(R.id.txtprecioMaxEdit);
        TextView precioMin = findViewById(R.id.txtprecioMinEdit);




        String valorTextoMax = precioMax.getText().toString();
        int precioMaxvalor = (valorTextoMax.isEmpty())?0:Integer.valueOf(valorTextoMax);

        String valorTextoMin = precioMin.getText().toString();
        int precioMinvalor = (valorTextoMin.isEmpty())?0:Integer.valueOf(valorTextoMin);

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
        //filtersDTO.setPropertyAmenities(amenities.s); //String [] ?
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
        Spinner tipoPropiedad = findViewById(R.id.spnrTipoPropiedad);
        Spinner estado = findViewById(R.id.spnrEstado);
        TextView addressCity = findViewById(R.id.txtLocalidad);
        TextView addressState = findViewById(R.id.txtProvincia);
        Spinner amenities=findViewById(R.id.spnrAmenitiesFilter);
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
        amenities.setSelection(0);
        antiguedad.setSelection(0);
        addressCountry.setText("");
        cantidadBanios.setText("");
        cantidadAmbientes.setText("");
        cantidadCuartos.setText("");

    }



}
