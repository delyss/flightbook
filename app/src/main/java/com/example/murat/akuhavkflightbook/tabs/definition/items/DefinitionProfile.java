package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Pilot;
import data.repositories.Pilot.PilotRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionProfile extends RoboFragmentActivity {

    @Inject
    private PilotRepository pilotRepository;


    private @InjectView(R.id.btnEdit) Button btnEdit;
    private @InjectView(R.id.txtProfileFirstName) TextView txtProfileFirstName;
    private @InjectView(R.id.txtProfileLastName) TextView txtProfileLastName;

    private Pilot pilot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_profile);

        List<Pilot> pilotList = pilotRepository.QueryForAll();
        if(pilotList.size() > 0) {
            pilot = pilotList.get(0);
            txtProfileFirstName.setText(pilot.getName());
            txtProfileLastName.setText(pilot.getSurname());
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }

    protected void showInputDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(DefinitionProfile.this);
        View promptView = layoutInflater.inflate(R.layout.edit_definition_profile, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DefinitionProfile.this);
        alertDialogBuilder.setView(promptView);

        final EditText txtFirstName = (EditText) promptView.findViewById(R.id.txt_edit_ProfileFirstName);
        final EditText txtLastName = (EditText) promptView.findViewById(R.id.txt_edit_ProfileLastName);

        if(pilot != null){
            txtFirstName.setText(pilot.getName());
            txtLastName.setText(pilot.getSurname());
        }
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String firstName = txtFirstName.getText().toString();
                        String lastName = txtLastName.getText().toString();

                        txtProfileFirstName.setText(firstName);
                        txtProfileLastName.setText(lastName);

                        if(pilot == null) {
                            pilot = new Pilot();
                        }
                        pilot.setName(firstName);
                        pilot.setSurname(lastName);
                        pilotRepository.Save(pilot);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_definition_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
