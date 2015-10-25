package data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import data.entities.Harness;
import data.entities.Institution;
import data.entities.Instructor;
import data.entities.Wing;
import data.entities.Pilot;
import data.entities.Takeoff;


/**
 * Created by murat on 09/08/2015. flightbook
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "FlighBook.db";
    private static final int DATABASE_VERSION = 6;

    @Inject
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase,
                         ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Pilot.class);
            TableUtils.createTable(connectionSource, Harness.class);
            TableUtils.createTable(connectionSource, Institution.class);
            TableUtils.createTable(connectionSource, Instructor.class);
            TableUtils.createTable(connectionSource, Wing.class);

            //for demo users
            AddDummyInstructors();
            AddDummyProfile();
            AddDummyTakeOff();
            AddDummyHarness();
            AddDummyWing();

        } catch (SQLException e) {
            Log.e(DbHelper.class.getName(),
                    "Cannot create database:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          ConnectionSource connectionSource, int arg2, int arg3) {
        try {
            TableUtils.dropTable(connectionSource, Pilot.class, true);
            TableUtils.dropTable(connectionSource, Harness.class, true);
            TableUtils.dropTable(connectionSource, Institution.class, true);
            TableUtils.dropTable(connectionSource, Instructor.class, true);
            TableUtils.dropTable(connectionSource, Wing.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DbHelper.class.getName(),
                    "Cannot drop database:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    private void AddDummyInstructors() {
        for (int i = 1; i < 4; i++) {
            Instructor instructor = new Instructor();
            instructor.setActive(true);
            instructor.setInstitutionId(1);
            instructor.setSurname("Instructor");
            instructor.setName(Integer.toString(i));
            try {
                getDao(Instructor.class).create(instructor);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void AddDummyProfile() {
        Pilot pilot = new Pilot();
        pilot.setName("murat");
        pilot.setSurname("kelekci");
        pilot.setEmail("mrtklkc@gmail.com");
        try {
            getDao(Pilot.class).create(pilot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void AddDummyTakeOff() {
        for (int i = 1; i < 4; i++) {
            Takeoff takeoff = new Takeoff();
            takeoff.setName(Integer.toString(i)+". Takeoff");
            try {
                getDao(Takeoff.class).create(takeoff);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void AddDummyHarness() {
        for (int i = 1; i < 4; i++) {
            Harness harness = new Harness();
            harness.setName(Integer.toString(i)+". Harness");
            try {
                getDao(Harness.class).create(harness);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void AddDummyWing() {
        for (int i = 1; i < 4; i++) {
            Wing wing = new Wing();
            wing.setName(Integer.toString(i)+". Wing");
            try {
                getDao(Wing.class).create(wing);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
