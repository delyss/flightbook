package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;

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
    private List allData;

    public DefinitionInstructorAdapter(Activity activity, List instructors, InstructorRepository instructorRepository) {
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
        TextView textViewDefinitionInstructorName =
                (TextView) rowView.findViewById(R.id.textViewDefinitionInstructorName);
        TextView textViewDefinitionInstructorCounter =
                (TextView)rowView.findViewById(R.id.textViewDefinitionInstructorCounter);
        Instructor ins = (Instructor) allData.get(position);

        textViewDefinitionInstructorName.setText(ins.toString());
        textViewDefinitionInstructorCounter.setText(String.valueOf(position + 1) + ". ");
        return rowView;
    }
}
