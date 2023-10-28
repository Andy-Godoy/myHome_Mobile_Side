package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View;
public class ListAgencieProperties extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_agencie_properties);

    }

        public void nuevapropiedad(View view) {
            Intent miIntent=new Intent(ListAgencieProperties.this, AddProperties.class);
            startActivity(miIntent);
        }


    }