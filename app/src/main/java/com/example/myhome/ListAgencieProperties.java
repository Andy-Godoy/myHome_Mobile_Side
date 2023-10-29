package com.example.myhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.asksira.loopingviewpager.LoopingViewPager;


    public class ListAgencieProperties extends AppCompatActivity {
        private LoopingViewPager imageSliderSlider;
        private ImageView imageArrowright;
        private TextView txtUdCounter, txtCountry, txtPalermoSohoP, txtLanguage, txtDormitorioCounter, txtDepartamentoen;
        private ImageView imageImageFifteen;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_agencie_properties);

            // Inicializa elementos de la vista
            imageSliderSlider = findViewById(R.id.imageSliderSlider);
            imageArrowright = findViewById(R.id.imageArrowright);
            txtUdCounter = findViewById(R.id.txtUdCounter);
            txtCountry = findViewById(R.id.txtCountry);
            txtPalermoSohoP = findViewById(R.id.txtPalermoSohoP);
            txtLanguage = findViewById(R.id.txtLanguage);
            txtDormitorioCounter = findViewById(R.id.txtDormitorioCounter);
            txtDepartamentoen = findViewById(R.id.txtDepartamentoen);
            imageImageFifteen = findViewById(R.id.imageImageFifteen);

            // Manejo de clic en imageArrowright
            imageArrowright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción a realizar cuando se hace clic en imageArrowright
                    // Por ejemplo, puedes avanzar a la siguiente imagen en el slider.
                    // Si estás utilizando un ViewPager para mostrar imágenes, puedes manejar la navegación aquí.
                }
            });

            // Ejemplo de manejo de clic en txtUdCounter
            txtUdCounter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción a realizar cuando se hace clic en txtUdCounter
                    // Puedes definir una acción específica, como mostrar más detalles o navegar a otra actividad.
                }
            });

            // Otros elementos de la vista se pueden manejar de manera similar.
        }


        public void nuevapropiedad(View view) {
            Intent miIntent=new Intent(ListAgencieProperties.this, AddProperties.class);
            startActivity(miIntent);
        }


    }