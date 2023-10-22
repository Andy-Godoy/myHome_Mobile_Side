package com.example.myhome;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);

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
