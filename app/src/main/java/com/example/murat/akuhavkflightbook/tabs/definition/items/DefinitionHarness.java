package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.DefinitionHarnessAdapter;
import com.google.inject.Inject;
import java.util.List;
import data.entities.Harness;
import data.repositories.Harness.HarnessRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class DefinitionHarness extends RoboFragmentActivity {

    @Inject private HarnessRepository harnessRepository;
    private @InjectView(R.id.lwHarness) ListView lwHarness;
    private @InjectView(R.id.btnAddHarness) FloatingActionButton btnAddHarness;
    private @InjectView (R.id.toolbar) Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition_harness);

        toolbar.setTitle("     Harness");
        toolbar.setLogo(R.mipmap.ic_launcher_definition_harness);
        toolbar.setBackgroundColor(Color.rgb(68,121,211));

        List<Harness> harnesss = harnessRepository.QueryForAll();
        DefinitionHarnessAdapter adapter = new DefinitionHarnessAdapter(this, harnesss, harnessRepository);

        lwHarness.setAdapter(adapter);
        lwHarness.setClickable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_definition_harness, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
