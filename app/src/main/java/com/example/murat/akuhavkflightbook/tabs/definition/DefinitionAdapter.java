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

/**
 * Created by murat on 14/08/2015. flightbook
 */
public class DefinitionAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Definition> definitionLists;

    public DefinitionAdapter(Activity activity, List<Definition> definitionLists) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.definitionLists = definitionLists;
    }

    @Override
    public int getCount() {
        return definitionLists.size();
    }

    @Override
    public Definition getItem(int position) {
        return definitionLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = layoutInflater.inflate(R.layout.definition_rows, null);
        TextView textView = (TextView) rowView.findViewById(R.id.txtName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgLeftIco);



        Definition def = definitionLists.get(position);

        textView.setText(def.getName());
        imageView.setImageResource(R.drawable.ic_launcher);
        return rowView;
    }
}
