package data;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import data.entities.Flight;
import data.entities.Harness;
import data.entities.Institution;
import data.entities.Instructor;
import data.entities.LastDates;
import data.entities.Wing;
import data.entities.Pilot;
import data.entities.Takeoff;


/**
 * Created by murat on 09/08/2015. flightbook
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "FlighBook.db";
    private static final int DATABASE_VERSION = 29;

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
            TableUtils.createTable(connectionSource, Takeoff.class);
            TableUtils.createTable(connectionSource, Flight.class);
            TableUtils.createTable(connectionSource, LastDates.class);

            //for demo users
//            AddDummyInstructors();
//            AddDummyProfile();
//            AddDummyTakeOff();
//            AddDummyHarness();
//            AddInstitutionWings();

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
            TableUtils.dropTable(connectionSource, Takeoff.class, true);
            TableUtils.dropTable(connectionSource, Flight.class, true);
            TableUtils.dropTable(connectionSource, LastDates.class, true);
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
        pilot.setLastName("kelekci");
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
            takeoff.setName("Takeoff_" + Integer.toString(i));
            takeoff.setAltitude(85);
            takeoff.setCharacteristic(new ArrayList<String>(){
                {
                    add("Education");
                    add("Thermic");
                }});
            takeoff.setDescription("iste ruzgari soledir, falanca egitim icin elverislidir." +
                    "su saatten sonra termik kopar kacirma vs..");
            takeoff.setLocation("Turkey, Antalya, Korkuteli, X village. Falan filan" +
                    "dagin tepesi kime sorsan soyler.");
            takeoff.setIsConstant(true);
            try {
                getDao(Takeoff.class).create(takeoff);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void AddDummyHarness() {
        Harness harness1 = new Harness();
        harness1.setName("Avasport Cruiser small");
        harness1.setIsConstant(true);
        ///
        Harness harness2 = new Harness();
        harness2.setName("Avasport Cruiser medium");
        harness2.setIsConstant(true);
        ///
        Harness harness3 = new Harness();
        harness3.setName("Avasport Cruiser large");
        harness3.setIsConstant(true);
        ///
        Harness harness4 = new Harness();
        harness4.setName("Woody Valley Velvet 2 small");
        harness4.setIsConstant(true);
        ///
        Harness harness5 = new Harness();
        harness5.setName("Woody Valley Velvet 2 medium");
        harness5.setIsConstant(true);
        ///
        Harness harness6 = new Harness();
        harness6.setName("Swing Connect 2 small");
        harness6.setIsConstant(true);
        ///
        Harness harness7 = new Harness();
        harness7.setName("Swing Connect 2 medium");
        harness7.setIsConstant(true);
        ///
        Harness harness8 = new Harness();
        harness8.setName("Woody Valley Velvet 2 large");
        harness8.setIsConstant(true);
        try {
            getDao(Harness.class).create(harness1);
            getDao(Harness.class).create(harness2);
            getDao(Harness.class).create(harness3);
            getDao(Harness.class).create(harness4);
            getDao(Harness.class).create(harness5);
            getDao(Harness.class).create(harness6);
            getDao(Harness.class).create(harness7);
            getDao(Harness.class).create(harness8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
INSERT INTO `wings`(`id`, `name`, `created_at`, `updated_at`, `Training`, `ClassName`,
`ClasssValue`, `WeightMin`, `WeightMax`, `IsActive`) VALUES
(UUID(),'Axis 4 Small',NOW(),NOW(),1,'DHV','1-2',55,85,1)
    */


    private void AddInstitutionWings() {
        Wing wing1 = new Wing();
        wing1.setClassName("DHV");
        wing1.setClassValue("1-2");
        wing1.setIsConstant(true);
        wing1.setWeightMin(55);
        wing1.setWeightMax(85);
        wing1.setTraining(false);
        wing1.setName("Axis 4 Small");
        ///
        Wing wing2 = new Wing();
        wing2.setClassName("DHV");
        wing2.setClassValue("1-2");
        wing2.setIsConstant(true);
        wing2.setWeightMin(55);
        wing2.setWeightMax(85);
        wing2.setTraining(false);
        wing2.setName("Axis 5 Kırmızı Small");
        ///
        Wing wing3 = new Wing();
        wing3.setClassName("DHV");
        wing3.setClassValue("1-2");
        wing3.setIsConstant(true);
        wing3.setWeightMin(55);
        wing3.setWeightMax(85);
        wing3.setTraining(false);
        wing3.setName("Axis 5 Mavi Small");
        ///
        Wing wing4 = new Wing();
        wing4.setClassName("DHV");
        wing4.setClassValue("1-2");
        wing4.setIsConstant(true);
        wing4.setWeightMin(55);
        wing4.setWeightMax(85);
        wing4.setTraining(false);
        wing4.setName("Mistral 5 Small");
        ///
        Wing wing5 = new Wing();
        wing5.setClassName("DHV");
        wing5.setClassValue("1-2");
        wing5.setIsConstant(true);
        wing5.setWeightMin(55);
        wing5.setWeightMax(85);
        wing5.setTraining(false);
        wing5.setName("Mistral 7 Small");
        ///
        Wing wing6 = new Wing();
        wing6.setClassName("DHV");
        wing6.setClassValue("1-2");
        wing6.setIsConstant(true);
        wing6.setWeightMin(55);
        wing6.setWeightMax(85);
        wing6.setTraining(false);
        wing6.setName("Axis 3 medium");
        ///
        Wing wing7 = new Wing();
        wing7.setClassName("DHV");
        wing7.setClassValue("1-2");
        wing7.setIsConstant(true);
        wing7.setWeightMin(55);
        wing7.setWeightMax(85);
        wing7.setTraining(false);
        wing7.setName("Arcus 4 medium");
        ///
        Wing wing8 = new Wing();
        wing8.setClassName("DHV");
        wing8.setClassValue("1-2");
        wing8.setIsConstant(true);
        wing8.setWeightMin(55);
        wing8.setWeightMax(85);
        wing8.setTraining(false);
        wing8.setName("Arcus 6 medium");
        ///
        Wing wing9 = new Wing();
        wing9.setClassName("DHV");
        wing9.setClassValue("1-2");
        wing9.setIsConstant(true);
        wing9.setWeightMin(55);
        wing9.setWeightMax(85);
        wing9.setTraining(false);
        wing9.setName("Arcus 7 medium");
        ///
        Wing wing10 = new Wing();
        wing10.setClassName("DHV");
        wing10.setClassValue("1-2");
        wing10.setIsConstant(true);
        wing10.setWeightMin(55);
        wing10.setWeightMax(85);
        wing10.setTraining(false);
        wing10.setName("Mistral 5 medium");
        ///
        Wing wing11 = new Wing();
        wing11.setClassName("DHV");
        wing11.setClassValue("1-2");
        wing11.setIsConstant(true);
        wing11.setWeightMin(55);
        wing11.setWeightMax(85);
        wing11.setTraining(false);
        wing11.setName("Mistral 6 medium");
        ///
        Wing wing12 = new Wing();
        wing12.setClassName("DHV");
        wing12.setClassValue("1-2");
        wing12.setIsConstant(true);
        wing12.setWeightMin(55);
        wing12.setWeightMax(85);
        wing12.setTraining(false);
        wing12.setName("Axis 3 large");
        ///
        Wing wing13 = new Wing();
        wing13.setClassName("DHV");
        wing13.setClassValue("1-2");
        wing13.setIsConstant(true);
        wing13.setWeightMin(55);
        wing13.setWeightMax(85);
        wing13.setTraining(false);
        wing13.setName("Arcus 7 large");
        ///
        Wing wing14 = new Wing();
        wing14.setClassName("DHV");
        wing14.setClassValue("1-2");
        wing14.setIsConstant(true);
        wing14.setWeightMin(55);
        wing14.setWeightMax(85);
        wing14.setTraining(false);
        wing14.setName("Mistral 5 large");
        ///
        Wing wing15 = new Wing();
        wing15.setClassName("DHV");
        wing15.setClassValue("1-2");
        wing15.setIsConstant(true);
        wing15.setWeightMin(55);
        wing15.setWeightMax(85);
        wing15.setTraining(false);
        wing15.setName("Mistral 6 large");
        try {
            getDao(Wing.class).create(wing1);
            getDao(Wing.class).create(wing2);
            getDao(Wing.class).create(wing3);
            getDao(Wing.class).create(wing4);
            getDao(Wing.class).create(wing5);
            getDao(Wing.class).create(wing6);
            getDao(Wing.class).create(wing7);
            getDao(Wing.class).create(wing8);
            getDao(Wing.class).create(wing9);
            getDao(Wing.class).create(wing10);
            getDao(Wing.class).create(wing11);
            getDao(Wing.class).create(wing12);
            getDao(Wing.class).create(wing13);
            getDao(Wing.class).create(wing14);
            getDao(Wing.class).create(wing15);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
