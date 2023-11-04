package com.example.myhome;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import java.util.ArrayList;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.widget.GridView;


public class NewProperties extends AppCompatActivity {

    private GridView gridView;
    private ImageAdapter imageAdapter;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private static final int PICK_IMAGES_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_properties);

        ViewStub viewStub = findViewById(R.id.multi_select_stub);
        View inflatedView = viewStub.inflate();

        gridView = findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(this, imageUris);
        gridView.setAdapter(imageAdapter);

        Button btnAddImage = findViewById(R.id.btnAddImage);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería para seleccionar imágenes
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccionar imágenes"), PICK_IMAGES_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                imageUris.add(imageUri);
            }
            imageAdapter.notifyDataSetChanged();
        }
    }
}