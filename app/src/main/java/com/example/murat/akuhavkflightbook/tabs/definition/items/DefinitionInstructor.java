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
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionInstructorAdapter;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Instructor;
import data.repositories.Instructor.InstructorRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionInstructor extends RoboFragmentActivity {

    @Inject
    private InstructorRepository instructorRepository;
    private
    @InjectView(R.id.lwInstructor)
    ListView lwInstructor;
    private
    @InjectView(R.id.btnAddInstructor)
    FloatingActionButton btnAddInstructor;
    private
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private Instructor instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_instructor);

        toolbar.setTitle("     Instructor");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_harness);
        toolbar.setBackgroundColor(Color.rgb(68, 121, 211));

        LoadList();
        lwInstructor.setClickable(true);
        lwInstructor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                instructor = (Instructor) parent.getItemAtPosition(position);
                showInputDialog(instructor);
            }
        });
        lwInstructor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                instructor = (Instructor) arg0.getItemAtPosition(pos);
                ShowInstructorDeleteDialog(instructor);
                return true;
            }
        });
        btnAddInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructor = new Instructor();
                showInputDialog(instructor);
            }
        });
    }

    private void ShowInstructorDeleteDialog(final Instructor instructor) {
        new AlertDialog.Builder(this)
                .setTitle("Delete " + instructor.getName())
                .setMessage("Are you sure you want to delete this instructor?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        instructorRepository.Delete(instructor);
                        Toast.makeText(getApplicationContext(), instructor.getName() + " deleted..",
                                Toast.LENGTH_LONG).show();
                        LoadList();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    protected void showInputDialog(final Instructor instructor) {
        LayoutInflater layoutInflater = LayoutInflater.from(DefinitionInstructor.this);
        View promptView = layoutInflater.inflate(R.layout.edit_definition_instructor, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DefinitionInstructor.this);
        alertDialogBuilder.setView(promptView);

        final EditText txtInstructorName = (EditText) promptView.findViewById(R.id.txt_edit_InstructorName);
        final EditText txtInstructorSurname = (EditText) promptView.findViewById(R.id.txt_edit_InstructorSurname);

        if (instructor.getId() != null) {
            txtInstructorName.setText(instructor.getName());
        }

        alertDialogBuilder.setCancelable(false);
        if (instructor.getId() == null || (instructor.getId() != null)) {
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    instructor.setName(txtInstructorName.getText().toString());
                    instructor.setSurname(txtInstructorSurname.getText().toString());
                    instructor.setActive(true);
                    instructorRepository.Save(instructor);
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
        List instructors = instructorRepository.GetActiveInstructors();
        DefinitionInstructorAdapter adapter = new DefinitionInstructorAdapter(this, instructors, instructorRepository);
        lwInstructor.setAdapter(adapter);
    }
}
