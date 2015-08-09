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
import data.entities.Paragliding;
import data.entities.Pilot;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "FlighBook.db";
    private static final int DATABASE_VERSION = 2;

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
            TableUtils.createTable(connectionSource, Paragliding.class);
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
            TableUtils.dropTable(connectionSource, Paragliding.class, true);
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

}
