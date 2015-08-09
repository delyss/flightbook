package com.example.murat.akuhavkflightbook;


import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.inject.Inject;

import data.entities.Institution;
import data.repositories.Repository;



//public class MainActivity extends AppCompatActivity {
public class MainActivity extends RoboActivity {
    @Inject
    private Repository<Institution> institutionRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
//            transaction.replace(R.id.sample_content_fragment, fragment);
//            transaction.commit();
//        }

        data.entities.Institution institution = new data.entities.Institution();
        institution.setName("ilk test");
        institutionRepo.save(institution);

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
