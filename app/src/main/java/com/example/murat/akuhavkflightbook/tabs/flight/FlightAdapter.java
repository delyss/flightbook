package com.example.murat.akuhavkflightbook.tabs.flight;

/**
 * Created by muratkelekci on 23/10/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;

import java.util.List;

import data.entities.Flight;
import data.entities.Harness;
import data.entities.Wing;
import data.repositories.Flight.FlightRepository;
import data.repositories.Harness.HarnessRepository;
import data.repositories.Wing.WingRepository;

import static com.example.murat.akuhavkflightbook.R.layout.definition_harness_row;

public class FlightAdapter extends BaseAdapter {
    private FlightRepository flightRepository;
    private WingRepository wingRepository;
    private HarnessRepository harnessRepository;
    private LayoutInflater layoutInflater;
    private List<Flight> allData;

    public FlightAdapter(Activity activity, FlightRepository flightRepository,
                         WingRepository wingRepository,
                         HarnessRepository harnessRepository) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allData = flightRepository.QueryForAll();
        this.flightRepository = flightRepository;
        this.wingRepository = wingRepository;
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
                (TextView) rowView.findViewById(R.id.textViewDefinitionHarnessCounter);
        Flight def = allData.get(position);

        Wing wing = wingRepository.Get(def.getWingId());
        Harness harness = harnessRepository.Get(def.getHarnessId());

        textViewDefinitionHarnessName.setText(wing.getName() + " -" + harness.getName());
        textViewDefinitionHarnessCounter.setText(String.valueOf(position + 1) + ". ");
        return rowView;
    }
}
