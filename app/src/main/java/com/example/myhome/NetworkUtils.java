package com.example.myhome;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkUtils {

    // Método para verificar la conexión a Internet
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    // Método para mostrar un mensaje de error si no hay conexión a Internet
    public static void showNoInternetMessage(Context context) {
        Toast.makeText(context, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
    }
}
