package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;
import java.util.List;

import data.entities.Harness;
import data.repositories.Harness.HarnessRepository;

import static com.example.murat.akuhavkflightbook.R.layout.definition_harness_row;

/**
 * Created by murat on 19/10/2015. flightbook
 */
public class DefinitionHarnessAdapter extends BaseAdapter {
    private HarnessRepository harnessRepository;
    private LayoutInflater layoutInflater;
    List<Harness> allData;

    public DefinitionHarnessAdapter(Activity activity, List<Harness> harnesss, HarnessRepository harnessRepository) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allData = harnesss;
        this.harnessRepository = harnessRepository;
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

        rowView = layoutInflater.inflate(definition_harness_row, null);
        TextView textViewDefinitionHarnessName =
                (TextView) rowView.findViewById(R.id.textViewDefinitionHarnessName);
        TextView textViewDefinitionHarnessCounter =
                (TextView)rowView.findViewById(R.id.textViewDefinitionHarnessCounter);
        Harness def = allData.get(position);

        textViewDefinitionHarnessName.setText(def.getName());
        textViewDefinitionHarnessCounter.setText(String.valueOf(position + 1) + ". ");
        return rowView;
    }
}
