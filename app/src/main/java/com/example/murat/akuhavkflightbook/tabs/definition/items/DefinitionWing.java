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
import android.widget.ArrayAdapter;
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

import static com.example.murat.akuhavkflightbook.tabs.definition.items.ItemsHelper.showNotWorkingMessage;

public class DefinitionWing extends RoboFragmentActivity {

    @Inject
    private WingRepository wingRepository;
    private
    @InjectView(R.id.lwWings)
    ListView lwWings;
    private
    @InjectView(R.id.btnAddWing)
    FloatingActionButton btnAddWing;
    private
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private final List<String> listWeightRange = Arrays.asList(
            "55", "60", "65", "70", "75", "85", "95", "105", "115", "120", "125", "130"
    );
    private final List<String> listClassName = Arrays.asList("DHV", "EN");
    private final List<String> listClassDhvValue = Arrays.asList("1", "1-2", "2", "2-3", "3");
    private final List<String> listClassEnValue = Arrays.asList("A", "B", "C", "D");
    private Wing wing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_wing);

        toolbar.setTitle("     Wings");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_wing);
        toolbar.setBackgroundColor(Color.rgb(2, 136, 209));

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
                wing = (Wing) arg0.getItemAtPosition(pos);
                if (wing.getIsConstant()) {
                    Toast.makeText(getApplicationContext(),
                            wing.getName() + " can not be deleted..", Toast.LENGTH_SHORT).show();
                    return true;
                }
                ShowWingDeleteDialog(wing);
                return true;
            }
        });
        btnAddWing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotWorkingMessage(getApplicationContext());
//                wing = new Wing();
//                showInputDialog(wing);
            }
        });
    }

    private void ShowWingDeleteDialog(final Wing wing) {
        new AlertDialog.Builder(this)
                .setTitle("Delete " + wing.getName())
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
        final Spinner spnClassName = (Spinner) promptView.findViewById(R.id.spnClassName);
        final Spinner spnClassValue = (Spinner) promptView.findViewById(R.id.spnClassValue);

        ArrayAdapter<String> adapterWeight = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listWeightRange);
        ArrayAdapter<String> adapterClassName = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listClassName);
        final ArrayAdapter<String> adapterClassEnValue = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listClassEnValue);
        final ArrayAdapter<String> adapterClassDhvValue = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listClassDhvValue);

        spnWeightMin.setAdapter(adapterWeight);
        spnWeightMax.setAdapter(adapterWeight);
        spnClassName.setAdapter(adapterClassName);

        spnClassName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String enClassification = "EN";
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (selectedItem.equals(enClassification)) {
                    spnClassValue.setAdapter(adapterClassEnValue);
                } else {
                    spnClassValue.setAdapter(adapterClassDhvValue);
                }
                if (wing.getId() != null) {
                    if (wing.getClassName().equals(enClassification)) {
                        spnClassValue.setSelection(listClassEnValue.indexOf(wing.getClassValue()));
                    } else {
                        spnClassValue.setSelection(listClassDhvValue.indexOf(wing.getClassValue()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (wing.getId() != null) {
            txtWingName.setText(wing.getName());
            radioTraining.setChecked(wing.getTraining());
            spnWeightMin.setSelection(listWeightRange.indexOf(wing.getWeightMin().toString()));
            spnWeightMax.setSelection(listWeightRange.indexOf(wing.getWeightMax().toString()));
            spnClassName.setSelection(listClassName.indexOf(wing.getClassName()));
        }

        alertDialogBuilder.setCancelable(false);
        if (wing.getId() == null || (wing.getId() != null && !wing.getIsConstant())) {
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    wing.setName(txtWingName.getText().toString());
                    wing.setTraining(radioTraining.isChecked());
                    wing.setWeightMin(Integer.parseInt(spnWeightMin.getSelectedItem().toString()));
                    wing.setWeightMax(Integer.parseInt(spnWeightMax.getSelectedItem().toString()));
                    wing.setClassName(spnClassName.getSelectedItem().toString());
                    wing.setClassValue(spnClassValue.getSelectedItem().toString());
                    wing.setIsConstant(false);
                    wingRepository.Save(wing);
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

    private void LoadList() {
        final List<Wing> wings = wingRepository.QueryForAll();
        DefinitionWingAdapter adapter = new DefinitionWingAdapter(this, wings);
        lwWings.setAdapter(adapter);
    }
}
