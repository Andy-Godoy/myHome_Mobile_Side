package com.example.myhome.Front;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import com.example.myhome.R;

public class MenuHandler {
    public static void handleMenuItemClick(Context context, MenuItem item, Class<?> currentActivityClass) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_home && !currentActivityClass.equals(ListAgencieProperties.class))  {
            if (!item.isChecked()) {
                Intent intent = new Intent(context, ListAgencieProperties.class);
                context.startActivity(intent);
            }
            // Lógica para abrir la ventana correspondiente a "Mis propiedades" desde acá
        } else if (itemId == R.id.action_add && !currentActivityClass.equals(NewProperties.class)) {
            Intent intent=new Intent(context, NewProperties.class);
            context.startActivity(intent);
            // Lógica para abrir la ventana correspondiente a "Agregar propiedad" desde acá
        } else if (itemId == R.id.action_profile  && !currentActivityClass.equals(AgenciesProfile.class)) {
            Intent intent=new Intent(context, AgenciesProfile.class);
            context.startActivity(intent);
            //Lógica para abrir la ventana correspondiente a "Perfil" desde acá
        }
    }
}
