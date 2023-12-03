package com.example.myhome.Front;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

import java.util.ArrayList;

public class ListViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        ListView listView = findViewById(R.id.multiple_list_view);
        ArrayList<String> arrayList = getList();
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<>(ListViewTest.this, android.R.layout.simple_list_item_multiple_choice,
                arrayList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String items = (String) adapterView.getItemAtPosition(i);
            Toast.makeText(ListViewTest.this, "select by list of item" +items,
                    Toast.LENGTH_SHORT).show();
        });
    }

    private ArrayList<String> getList() {

        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0; i<=10; i++){
            arrayList.add("Item" +i);
        }
        return arrayList;

    }
}
