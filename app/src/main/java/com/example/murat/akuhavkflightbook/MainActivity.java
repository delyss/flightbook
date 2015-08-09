package com.example.murat.akuhavkflightbook;



import data.repositories.Institution.InstitutionRepository;
import roboguice.activity.RoboFragmentActivity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.inject.Inject;

import java.util.List;

import data.entities.Institution;
import data.repositories.Repository;



//public class MainActivity extends AppCompatActivity {
public class MainActivity extends RoboFragmentActivity {
    @Inject
    private InstitutionRepository institutionRepo;

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

        data.entities.Institution institution = new data.entities.Institution();
        institution.setName("ikinci test");
        institutionRepo.save(institution);

        List<Institution> ss = institutionRepo.queryForAll();

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
