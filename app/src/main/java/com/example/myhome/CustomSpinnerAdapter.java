package com.example.myhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private boolean allChecked = false;
    private boolean[] checkedItems;
    private Set<String> amenities = new HashSet<>();

    public CustomSpinnerAdapter(Context context, String[] items) {
        super(context, 0, items);
        inflater = LayoutInflater.from(context);
        checkedItems = new boolean[items.length];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, R.layout.custom_spinner_item);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, R.layout.custom_spinner_item);
    }

    private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(resource, parent, false);
        }

        CheckBox checkBox = view.findViewById(R.id.checkBox);
        TextView textView = view.findViewById(R.id.spinnerItemText);

        String item = getItem(position);
        if (item != null) {
            textView.setText(item);

//            if(checkBox.isChecked()){
//                amenities.add(item);
//            }
//
//            else {
//                amenities.remove(item);
//            }

            if (position == 0) {
                checkBox.setChecked(allChecked);
                checkBox.setEnabled(true); // Permitir seleccionar/deseleccionar "Todos"
                checkBox.setOnCheckedChangeListener(null); // Limpiar el oyente anterior
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        allChecked = isChecked;
                        updateCheckedItems(isChecked);
                        notifyDataSetChanged();
                        updateAmenitiesList(); // Actualizar el conjunto amenities
                    }
                });

            } else {
                checkBox.setChecked(checkedItems[position]);
                checkBox.setOnCheckedChangeListener(null); // Limpiar el oyente anterior
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        checkedItems[position] = isChecked;
                        updateAmenitiesList(); // Actualizar el conjunto amenities
                    }
                });
            }


        }

        return view;
    }

    private void updateCheckedItems(boolean isChecked) {
        for (int i = 1; i < checkedItems.length; i++) {
            checkedItems[i] = isChecked;
        }
    }

    private void updateAmenitiesList() {
        amenities.clear(); // Limpiar el conjunto de amenidades
        if (allChecked) {
            // Si "Todos" está marcado, agrega todas las amenidades
            for (int i = 1; i < checkedItems.length; i++) {
                amenities.add(getItem(i));
            }
        } else {
            // Agrega las amenidades seleccionadas individualmente
            for (int i = 1; i < checkedItems.length; i++) {
                if (checkedItems[i]) {
                    amenities.add(getItem(i));
                }
            }
        }
    }

    public void selectAll() {
        allChecked = true;
        updateCheckedItems(true);
        notifyDataSetChanged();
    }

    public void deselectAll() {
        allChecked = false;
        updateCheckedItems(false);
        notifyDataSetChanged();
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public int getSelectedCount() {
        int count = 0;
        for (int i = 1; i < checkedItems.length; i++) {
            if (checkedItems[i]) {
                count++;
            }
        }
        return count;
    }

    public String getSelectedText() {
        if (allChecked) {
            return "Todos";
        } else {
            int selectedCount = getSelectedCount();
            return "Seleccionados: " + selectedCount;
        }
    }

    public void setCheckBoxesEnabled(boolean enabled) {
        for (int i = 1; i < getCount(); i++) {
            View view = getView(i, null, null);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setEnabled(enabled);
        }
    }

    public Set<String> getAmenities(){
        return amenities;
    }

    public void setAmenities(Set<String> amenities){
        this.amenities = amenities;
    }
}
