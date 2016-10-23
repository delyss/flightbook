package com.example.murat.akuhavkflightbook.tabs.flight;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import data.repositories.Harness.HarnessRepository;
import roboguice.activity.RoboFragmentActivity;

public class Flights extends RoboFragmentActivity {

    @Inject
    private HarnessRepository harnessRepository;

    public Flights(View view, final Activity activity) {
        FloatingActionButton btnAddFlight = (FloatingActionButton) view.findViewById(R.id.btnAddFlight);
        btnAddFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditFlight.class);
                intent.putExtra("test", "mesaj");
                activity.startActivity(intent);
            }
        });
    }
}