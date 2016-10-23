package com.example.murat.akuhavkflightbook.tabs.flight;

import android.os.Bundle;
import android.widget.ListView;

import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import data.entities.Harness;
import data.repositories.Flight.FlightRepository;
import data.repositories.Harness.HarnessRepository;
import data.repositories.Wing.WingRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class FlightList extends RoboFragmentActivity {

    @Inject
    FlightRepository flightRepository;
    @Inject
    WingRepository wingRepository;
    @Inject
    HarnessRepository harnessRepository;

    @InjectView(R.id.lwFlight)
    ListView lwFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        LoadList();
    }

    private void LoadList() {
        FlightAdapter adapter = new FlightAdapter(this, flightRepository,
                wingRepository, harnessRepository);
        lwFlight.setAdapter(adapter);
    }
}
