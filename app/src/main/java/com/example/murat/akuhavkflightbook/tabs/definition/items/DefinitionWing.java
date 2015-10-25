package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionWingAdapter;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import data.entities.Wing;
import data.repositories.Wing.WingRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionWing extends RoboFragmentActivity {

    @Inject private WingRepository wingRepository;
    private @InjectView(R.id.lwWings) ListView lwWings;
    private @InjectView(R.id.btnAddWing)Button btnAddWing;
    private String[] arrayWeightRange =new String[]{
            "55", "60", "65", "70", "75", "85", "95", "105", "115", "120", "125", "130"
    };
    private Wing wing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_wing);

        LoadList();
        lwWings.setClickable(true);
        lwWings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wing = (Wing) parent.getItemAtPosition(position);
                showInputDialog(wing);
            }
        });
        lwWings.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                ShowWingDeleteDialog(wing);
                return true;
            }
        });
        btnAddWing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wing = new Wing();
                showInputDialog(wing);
            }
        });
    }

    private void ShowWingDeleteDialog(final Wing wing) {
        new AlertDialog.Builder(this)
                .setTitle("Delete "+wing.getName())
                .setMessage("Are you sure you want to delete this wing?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        wingRepository.Delete(wing);
                        Toast.makeText(getApplicationContext(), wing.getName() + " deleted..",
                                Toast.LENGTH_LONG).show();
                        LoadList();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }


    protected void showInputDialog(final Wing wing) {
        LayoutInflater layoutInflater = LayoutInflater.from(DefinitionWing.this);
        View promptView = layoutInflater.inflate(R.layout.edit_definition_wing, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DefinitionWing.this);
        alertDialogBuilder.setView(promptView);

        final EditText txtWingName = (EditText) promptView.findViewById(R.id.txt_edit_WingName);
        final RadioButton radioTraining = (RadioButton) promptView.findViewById(R.id.radioTraining);
        final Spinner spnWeightMin = (Spinner) promptView.findViewById(R.id.spnWeightFirst);
        final Spinner spnWeightMax = (Spinner) promptView.findViewById(R.id.spnWeightLast);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayWeightRange);
        spnWeightMin.setAdapter(adapter);
        spnWeightMax.setAdapter(adapter);

        if(wing.getId() != null) {
            txtWingName.setText(wing.getName());
            radioTraining.setChecked(wing.getTraining());
            List<String> listWeightRange = Arrays.asList(arrayWeightRange);
            spnWeightMin.setSelection(listWeightRange.indexOf(wing.getWeightMin().toString()));
            spnWeightMax.setSelection(listWeightRange.indexOf(wing.getWeightMax().toString()));
        }
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        wing.setName(txtWingName.getText().toString());
                        wing.setTraining(radioTraining.isChecked());
                        wing.setWeightMin(Integer.parseInt(spnWeightMin.getSelectedItem().toString()));
                        wing.setWeightMax(Integer.parseInt(spnWeightMax.getSelectedItem().toString()));
                        wingRepository.Save(wing);
                        LoadList();
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

    private void LoadList(){
        final List<Wing> wings = wingRepository.QueryForAll();
        DefinitionWingAdapter adapter = new DefinitionWingAdapter(this, wings);

        lwWings.setAdapter(adapter);
    }
}
