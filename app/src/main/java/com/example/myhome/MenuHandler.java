package com.example.myhome;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

public class MenuHandler {
    public static void handleMenuItemClick(Context context, MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_home) {
            if (!item.isChecked()) {
                Intent intent = new Intent(context, ListAgencieProperties.class);
                context.startActivity(intent);
                Log.d("MenuHandler", "Home");
            }
            // Lógica para abrir la ventana correspondiente a "Mis propiedades" desde acá
        } else if (itemId == R.id.action_add) {
            Intent intent=new Intent(context, NewProperties.class);
            context.startActivity(intent);
            // Lógica para abrir la ventana correspondiente a "Agregar propiedad" desde acá
        } else if (itemId == R.id.action_profile) {
            //Intent intent=new Intent(context, AgenciesProfile.class);
            //context.startActivity(intent);
            //Lógica para abrir la ventana correspondiente a "Perfil" desde acá
        }
    }
}
