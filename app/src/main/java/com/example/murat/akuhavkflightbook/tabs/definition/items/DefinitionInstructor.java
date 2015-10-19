package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionInstructorAdapter;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionInstructorItem;
import com.google.inject.Inject;
import java.util.List;
import data.entities.Instructor;
import data.repositories.Instructor.InstructorRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionInstructor extends RoboFragmentActivity {

    @Inject private InstructorRepository instructorRepository;
    private @InjectView(R.id.lwInstructor) ListView lwInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_instructor);

        List<Instructor> instructors = instructorRepository.QueryForAll();
        DefinitionInstructorAdapter adapter = new DefinitionInstructorAdapter(this, instructors, instructorRepository);

        lwInstructor.setAdapter(adapter);
        lwInstructor.setClickable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_definition_instructor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
