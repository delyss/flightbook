package com.example.murat.akuhavkflightbook;



import data.entities.Pilot;
import data.repositories.Pilot.PilotRepository;
import roboguice.activity.RoboFragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Institution;


//public class MainActivity extends AppCompatActivity {
public class MainActivity extends RoboFragmentActivity {
    @Inject
    private PilotRepository pilotRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        data.entities.Pilot pilot = new data.entities.Pilot();
        pilot.setName("ikinci test");
        pilotRepository.Save(pilot);

        List<Pilot> ss = pilotRepository.QueryForAll();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
