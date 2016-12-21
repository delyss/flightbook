package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Pilot;
import data.repositories.Pilot.PilotRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

import static com.example.murat.akuhavkflightbook.tabs.definition.items.ItemsHelper.showNotWorkingMessage;

public class DefinitionProfile extends RoboFragmentActivity {

    @Inject
    private PilotRepository pilotRepository;

    private @InjectView(R.id.txtProfileFirstName) TextView txtProfileFirstName;
    private @InjectView(R.id.txtProfileEmail) TextView txtProfileEmail;
    private @InjectView(R.id.btnEdit) FloatingActionButton btnEdit;
    private @InjectView (R.id.toolbar) Toolbar toolbar;
    private Pilot pilot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_profile);

        toolbar.setTitle("     Profile");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_pilot);
        toolbar.setBackgroundColor(Color.rgb(61,187,245));

        List<Pilot> pilotList = pilotRepository.QueryForAll();
        if(pilotList.size() > 0) {
            pilot = pilotList.get(0);
            setFormInputsFromPilot(pilot);
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotWorkingMessage(getApplicationContext());
//                showInputDialog();
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
        final EditText txtEmail = (EditText) promptView.findViewById(R.id.txt_edit_ProfileEmail);

        if(pilot != null){
            txtFirstName.setText(pilot.getName());
            txtLastName.setText(pilot.getLastName());
            txtEmail.setText(pilot.getEmail());
        }
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(pilot == null) {
                            pilot = new Pilot();
                        }
                        pilot.setName(txtFirstName.getText().toString());
                        pilot.setLastName(txtLastName.getText().toString());
                        pilot.setEmail(txtEmail.getText().toString());
                        pilotRepository.Save(pilot);
                        setFormInputsFromPilot(pilot);
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

    private void setFormInputsFromPilot(Pilot pilot) {
        txtProfileFirstName.setText("name: "+ pilot.getName() + " " + pilot.getLastName());
        txtProfileEmail.setText("email: "+ pilot.getEmail());
    }
}
