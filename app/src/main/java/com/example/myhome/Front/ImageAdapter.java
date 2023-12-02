package com.example.myhome.Front;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter implements ListAdapter {
    private final Context context;
    private final ArrayList<Uri> imageUris;

    public ImageAdapter(Context context, ArrayList<Uri> imageUris) {
        this.context = context;
        this.imageUris = imageUris;
    }

    @Override
    public int getCount() {
        return imageUris.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100)); // Tamaño de la vista
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8); // Margen entre elementos
        } else {
            imageView = (ImageView) convertView;
        }

        Uri imageUri = imageUris.get(position);
        Glide.with(context).load(imageUri).into(imageView);

        return imageView;
    }
}