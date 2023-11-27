package com.example.myhome;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myhome.model.Properties;
import com.example.myhome.model.FiltersDTO;
import com.example.myhome.Api.PropertyApi;
import com.example.myhome.Front.ListUserProperties;
import android.view.View;
public class FilterUserProperties extends AppCompatActivity {

   // private Properties properties = new Properties();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_user_properties);

    }

    ImageButton btnBack = findViewById(R.id.btnBack);
//arreglar boton para volver atras

    /*
btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();

            // Crear un Intent para abrir la actividad ListUserProperties
            Intent intent = new Intent(context, ListUserProperties.class);

            // Iniciar la actividad ListUserProperties
            context.startActivity(intent);
        }
    });
*/


    Button btnAplicarFiltros = findViewById(R.id.btnAplicarFiltos);
/*
    btnAplicarFiltros.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {
            if (validacionFiltro()) {
                //buscarPropiedad();
            }
        }
    });
*/
    public boolean validacionFiltro() {
        boolean esValido = true;

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

        //precio Max y Min
        TextView precioMax = findViewById(R.id.txtprecioMaxEdit);
        TextView precioMin = findViewById(R.id.txtprecioMinEdit);

        String valorTextoMax = precioMax.getText().toString();
        int precioMaxvalor = Integer.parseInt(valorTextoMax);

        String valorTextoMin = precioMin.getText().toString();
        int precioMinvalor = Integer.parseInt(valorTextoMin);



        if (tipoPropiedad.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Debe seleccionar un tipo de propiedad", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (estado.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Debe seleccionar un estado", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (addressCity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una ciudad", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (addressState.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una provincia", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (amenities.getSelectedItem().toString().isEmpty()){   //ver
            Toast.makeText(this, "Debe seleccionar amenities", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (antiguedad.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "Debe seleccionar una antiguedad", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (addressCountry.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un pais", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (cantidadBanios.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de baños", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadAmbientes.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de ambientes", Toast.LENGTH_SHORT).show();
            esValido = false;
        } else if (cantidadCuartos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar una cantidad de cuartos", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (precioMax.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar un precio Maximo", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (precioMin.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar un precio Minimo", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (precioMaxvalor<precioMinvalor){
            Toast.makeText(this, "El precio maximo debe ser mayor que el menor", Toast.LENGTH_SHORT).show();
            esValido = false;
        }else if (precioMaxvalor < 0 || precioMinvalor < 0){
            Toast.makeText(this, "Los precios deben ser mayor a 0", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return true;
    }

    public void aplicarFiltros(){


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

        //precio Max y Min
        TextView precioMax = findViewById(R.id.txtprecioMaxEdit);
        TextView precioMin = findViewById(R.id.txtprecioMinEdit);

        String valorTextoMax = precioMax.getText().toString();
        int precioMaxvalor = Integer.parseInt(valorTextoMax);

        String valorTextoMin = precioMin.getText().toString();
        int precioMinvalor = Integer.parseInt(valorTextoMin);


        //le paso la data a FiltersDTO
        FiltersDTO filtersDTO = new FiltersDTO();

        filtersDTO.setPropertyType(tipoPropiedad.toString());
        filtersDTO.setPropertyStatus(estado.toString());
        filtersDTO.setLocalidad(addressCity.toString());
        filtersDTO.setProvincia(addressState.toString());
        //filtersDTO.setPropertyAmenities(amenities.toString()); //String [] ?
        filtersDTO.setPropertyAge(antiguedad.toString());
        filtersDTO.setPais(addressCountry.toString());
        filtersDTO.setCantidadBanios(Integer.parseInt(cantidadBanios.toString()));
        filtersDTO.setCantidadAmbientes(Integer.parseInt(cantidadAmbientes.toString()));
        filtersDTO.setCantidadCuatros(Integer.parseInt(cantidadCuartos.toString()));
        filtersDTO.setPrecioMax(precioMaxvalor);
        filtersDTO.setPrecioMin(precioMinvalor);

        /*
        PropertyApi p =New PropertyApi();
        p.verPropiedades(filtersDTO, callback);

        */





    }
}
