package com.example.myhome.Front;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.myhome.R;

public class MenuHandlerUsuario {
    public static void handleMenuItemClick(Context context, MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_home) {
            if (!item.isChecked()) {
//                AGREGAR LA CLASE DE LISTA DE PROPIEDADES DE USUARIO
                Intent intent = new Intent(context, ListUserProperties.class);
                context.startActivity(intent);
            }
        } else if (itemId == R.id.action_favorite) {
//            Intent intent = new Intent(context, NewProperties.class);
//            context.startActivity(intent);
            // Lógica para abrir la ventana correspondiente a "Agregar propiedad" desde acá
        } else if (itemId == R.id.action_profile) {
            //                AGREGAR LA CLASE DE PERFIL DE USUARIO
        }
//            Intent intent = new Intent(context, );
//            context.startActivity(intent);
            //Lógica para abrir la ventana correspondiente a "Perfil" desde acá
        }
    }
