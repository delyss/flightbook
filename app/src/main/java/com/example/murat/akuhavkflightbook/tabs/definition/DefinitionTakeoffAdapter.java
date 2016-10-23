package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;
import static com.example.murat.akuhavkflightbook.R.layout.definition_takeoff_row;

import java.util.List;

import data.entities.Takeoff;

/**
 * Created by murat on 09/11/2015. flightbook
 */
public class DefinitionTakeoffAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    List<Takeoff> allData;

    public DefinitionTakeoffAdapter(Activity activity, List<Takeoff> takeoffs) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allData = takeoffs;
    }

    @Override
    public int getCount() { return allData.size(); }

    @Override
    public Object getItem(int position) { return allData.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = layoutInflater.inflate(definition_takeoff_row, null);
        Takeoff def = allData.get(position);
        TextView txtTakeoffName = (TextView)rowView.findViewById(R.id.textViewDefinitionTakeoffName);
        TextView txtTakeoffCounter = (TextView)rowView.findViewById(R.id.textViewDefinitionTakeoffCounter);
        txtTakeoffName.setText(def.getName());
        txtTakeoffCounter.setText(String.valueOf(position + 1) + ". ");
        return rowView;
    }
}