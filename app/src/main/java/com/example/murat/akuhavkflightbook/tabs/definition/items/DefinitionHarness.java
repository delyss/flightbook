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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionHarnessAdapter;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Harness;
import data.repositories.Harness.HarnessRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

import static com.example.murat.akuhavkflightbook.tabs.definition.items.ItemsHelper.showNotWorkingMessage;

public class DefinitionHarness extends RoboFragmentActivity {

    @Inject
    private HarnessRepository harnessRepository;
    private
    @InjectView(R.id.lwHarness)
    ListView lwHarness;
    private
    @InjectView(R.id.btnAddHarness)
    FloatingActionButton btnAddHarness;
    private
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private Harness harness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_harness);

        toolbar.setTitle("     Harness");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_harness);
        toolbar.setBackgroundColor(Color.rgb(68, 121, 211));

        LoadList();
        lwHarness.setClickable(true);
        lwHarness.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                harness = (Harness) parent.getItemAtPosition(position);
                showInputDialog(harness);
            }
        });
        lwHarness.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                harness = (Harness) arg0.getItemAtPosition(pos);
                if (harness.getIsConstant()) {
                    Toast.makeText(getApplicationContext(),
                            harness.getName() + " can not be deleted..", Toast.LENGTH_SHORT).show();
                    return true;
                }
                ShowHarnessDeleteDialog(harness);
                return true;
            }
        });
        btnAddHarness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotWorkingMessage(getApplicationContext());
//                harness = new Harness();
//                showInputDialog(harness);
            }
        });
    }

    private void ShowHarnessDeleteDialog(final Harness harness) {
        new AlertDialog.Builder(this)
                .setTitle("Delete " + harness.getName())
                .setMessage("Are you sure you want to delete this harness?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        harnessRepository.Delete(harness);
                        Toast.makeText(getApplicationContext(), harness.getName() + " deleted..",
                                Toast.LENGTH_LONG).show();
                        LoadList();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    protected void showInputDialog(final Harness harness) {
        LayoutInflater layoutInflater = LayoutInflater.from(DefinitionHarness.this);
        View promptView = layoutInflater.inflate(R.layout.edit_definition_harness, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DefinitionHarness.this);
        alertDialogBuilder.setView(promptView);

        final EditText txtHarnessName = (EditText) promptView.findViewById(R.id.txt_edit_HarnessName);

        if (harness.getId() != null) {
            txtHarnessName.setText(harness.getName());
        }

        alertDialogBuilder.setCancelable(false);
        if (harness.getId() == null || (harness.getId() != null && !harness.getIsConstant())) {
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    harness.setName(txtHarnessName.getText().toString());
                    harness.setIsConstant(false);
                    harnessRepository.Save(harness);
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
        List<Harness> harnesss = harnessRepository.QueryForAll();
        DefinitionHarnessAdapter adapter = new DefinitionHarnessAdapter(this, harnesss, harnessRepository);
        lwHarness.setAdapter(adapter);
    }
}
