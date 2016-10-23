/**
 * Created by muratkelekci on 23/10/2016.
 */
package com.example.murat.akuhavkflightbook;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import data.entities.BaseEntity;

public class BaseEntitySpinAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private List<T> values;

    public BaseEntitySpinAdapter(Context context, int textViewResourceId,
                                 List<T> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;

    }

    public int getCount() {
        return values.size();
    }

    public T getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setText(((BaseEntity) values.get(position)).getName());

        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setText(((BaseEntity) values.get(position)).getName());

        return label;
    }
}
