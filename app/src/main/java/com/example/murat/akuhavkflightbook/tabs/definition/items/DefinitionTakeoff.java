package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionTakeoffAdapter;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import data.entities.Takeoff;
import data.repositories.Takeoff.TakeoffRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionTakeoff extends RoboFragmentActivity {

    @Inject private TakeoffRepository takeoffRepository;
    private @InjectView(R.id.lwTakeoffs) ListView lwTakeoffs;
    private @InjectView(R.id.btnAddTakeoff) FloatingActionButton btnAddTakeoff;
    private @InjectView (R.id.toolbar) Toolbar toolbar;
    private Takeoff takeoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_takeoff);

        toolbar.setTitle("     Takeoff");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_takeoff);
        toolbar.setBackgroundColor(Color.rgb(68,138,255));

        LoadList();
        lwTakeoffs.setClickable(true);
        lwTakeoffs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                takeoff = (Takeoff) parent.getItemAtPosition(position);
                showInputDialog(takeoff);
            }
        });
        lwTakeoffs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                takeoff = (Takeoff) arg0.getItemAtPosition(pos);
                if (takeoff.getIsConstant()) {
                    Toast.makeText(getApplicationContext(),
                            takeoff.getName() + " can not be deleted..", Toast.LENGTH_SHORT).show();
                    return true;
                }
                ShowTakeoffDeleteDialog(takeoff);
                return true;
            }
        });
        btnAddTakeoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeoff = new Takeoff();
                showInputDialog(takeoff);
            }
        });
    }

    protected void showInputDialog(final Takeoff takeoff) {
        LayoutInflater layoutInflater = LayoutInflater.from(DefinitionTakeoff.this);
        View promptView = layoutInflater.inflate(R.layout.edit_definition_takeoff, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DefinitionTakeoff.this);
        alertDialogBuilder.setView(promptView);

        final EditText txtEditTakeoffName =
                (EditText)promptView.findViewById(R.id.txtEditTakeoffName);
        final EditText txtEditTakeoffAltitude =
                (EditText)promptView.findViewById(R.id.txtEditTakeoffAltitude);
        final CheckBox chbEditTakeoffEducation =
                (CheckBox)promptView.findViewById(R.id.chbEditTakeoffEducation);
        final CheckBox chbEditTakeoffThermic =
                (CheckBox)promptView.findViewById(R.id.chbEditTakeoffThermic);
        final CheckBox chbEditTakeoffSoaring =
                (CheckBox)promptView.findViewById(R.id.chbEditTakeoffSoaring);
        final CheckBox chbEditTakeoffAboveSea =
                (CheckBox)promptView.findViewById(R.id.chbEditTakeoffAboveSea);
        final EditText txtEditTakeoffLocation =
                (EditText)promptView.findViewById(R.id.txtEditTakeoffLocation);
        final EditText txtEditTakeoffDescription =
                (EditText)promptView.findViewById(R.id.txtEditTakeoffDescription);

        if(takeoff.getId() != null) {
            txtEditTakeoffName.setText(takeoff.getName());
            txtEditTakeoffAltitude.setText((takeoff.getAltitude()).toString());
            List<String> characteristics = Arrays.asList(takeoff.getCharacteristic());
            chbEditTakeoffEducation.setChecked(characteristics.contains("Education"));
            chbEditTakeoffThermic.setChecked(characteristics.contains("Thermic"));
            chbEditTakeoffSoaring.setChecked(characteristics.contains("Soaring"));
            chbEditTakeoffAboveSea.setChecked(characteristics.contains("AboveSea"));
            txtEditTakeoffLocation.setText(takeoff.getLocation());
            txtEditTakeoffDescription.setText(takeoff.getDescription());
        }


        alertDialogBuilder.setCancelable(false);
        if(takeoff.getId() == null || (takeoff.getId() != null && !takeoff.getIsConstant()))
        {
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    takeoff.setName(txtEditTakeoffName.getText().toString());
                    takeoff.setAltitude(Integer.parseInt(txtEditTakeoffAltitude.getText().toString()));
                    List<String> selectedCharacteristicArr = new ArrayList<>();
                    if(chbEditTakeoffAboveSea.isChecked()) selectedCharacteristicArr.add("AboveSea");
                    if(chbEditTakeoffEducation.isChecked()) selectedCharacteristicArr.add("Education");
                    if(chbEditTakeoffSoaring.isChecked()) selectedCharacteristicArr.add("Soaring");
                    if(chbEditTakeoffThermic.isChecked()) selectedCharacteristicArr.add("Thermic");

                    takeoff.setCharacteristic(selectedCharacteristicArr);
                    takeoff.setDescription(txtEditTakeoffDescription.getText().toString());
                    takeoff.setLocation(txtEditTakeoffLocation.getText().toString());
                    takeoff.setIsConstant(false);
                    takeoffRepository.Save(takeoff);
                    LoadList();
                }
            });
        }
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.create().show();
    }

    private void ShowTakeoffDeleteDialog(final Takeoff takeoff) {
        new AlertDialog.Builder(this)
                .setTitle("Delete "+takeoff.getName())
                .setMessage("Are you sure you want to delete this takeoff?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        takeoffRepository.Delete(takeoff);
                        Toast.makeText(getApplicationContext(), takeoff.getName() + " deleted..",
                                Toast.LENGTH_LONG).show();
                        LoadList();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void LoadList(){
        final List<Takeoff> takeoffs = takeoffRepository.QueryForAll();
        DefinitionTakeoffAdapter adapter = new DefinitionTakeoffAdapter(this, takeoffs);
        lwTakeoffs.setAdapter(adapter);
    }
}
