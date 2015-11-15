package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.chxHarnessDefinitionRow);
        Harness def = allData.get(position);

        checkBox.setText(def.getName());
        //checkBox.setChecked(def.getActive());
        checkBox.setId(def.getId());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                Harness ins = findById(cb.getId());
                if (ins != null) {
                    //ins.setActive(cb.isChecked());
                    harnessRepository.Save(ins);
                }
            }
        });

        return rowView;
    }
    
    private Harness findById(int id){
        for (Harness ins: allData ) {
            if(ins.getId() == id) {
                return ins;
            }
        }
        return null;
    }
}
