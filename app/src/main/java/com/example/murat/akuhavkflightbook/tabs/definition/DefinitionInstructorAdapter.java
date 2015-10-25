package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import com.example.murat.akuhavkflightbook.R;
import java.util.ArrayList;
import java.util.List;

import data.entities.Instructor;
import data.repositories.Instructor.InstructorRepository;

import static com.example.murat.akuhavkflightbook.R.layout.definition_instructor_row;

/**
 * Created by murat on 19/10/2015. flightbook
 */
public class DefinitionInstructorAdapter extends BaseAdapter {
    private InstructorRepository instructorRepository;
    private LayoutInflater layoutInflater;
    List<Instructor> allData;

    public DefinitionInstructorAdapter(Activity activity, List<Instructor> instructors, InstructorRepository instructorRepository) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allData = instructors;
        this.instructorRepository = instructorRepository;
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

        rowView = layoutInflater.inflate(definition_instructor_row, null);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.chxInstructorDefinitionRow);
        Instructor def = allData.get(position);

        checkBox.setText(def.getName());
        checkBox.setChecked(def.getActive());
        checkBox.setId(def.getId());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                Instructor ins = findById(cb.getId());
                if (ins != null) {
                    ins.setActive(cb.isChecked());
                    instructorRepository.Save(ins);
                }
            }
        });

        return rowView;
    }
    
    private Instructor findById(int id){
        for (Instructor ins: allData ) {
            if(ins.getId() == id) {
                return ins;
            }
        }
        return null;
    }
}
