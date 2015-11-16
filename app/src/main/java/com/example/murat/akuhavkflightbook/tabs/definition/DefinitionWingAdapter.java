package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;
import java.util.List;
import data.entities.Wing;

import static com.example.murat.akuhavkflightbook.R.layout.definition_wing_row;

/**
 * Created by murat on 21/10/2015. flightbook
 */
public class DefinitionWingAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    List<Wing> allData;

    public DefinitionWingAdapter(Activity activity, List<Wing> wings) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allData = wings;
    }

    @Override
    public int getCount() {
        return allData.size();
    }

    @Override
    public Object getItem(int position) {
        return allData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = layoutInflater.inflate(definition_wing_row, null);
        Wing def = allData.get(position);
        TextView txtWingName = (TextView)rowView.findViewById(R.id.textViewDefinitionWingName);
        TextView txtWingCounter = (TextView)rowView.findViewById(R.id.textViewDefinitionWingCounter);
        txtWingName.setText(def.getName());
        txtWingCounter.setText(String.valueOf(position + 1) + ". ");

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgWingLeftIco);
        imageView.setImageResource(R.mipmap.ic_launcher_definition_wing);


        return rowView;
    }
}
