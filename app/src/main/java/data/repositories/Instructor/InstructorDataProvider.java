package data.repositories.Instructor;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Instructor;


/**
 * Created by murat on 12/08/2015. flightbook
 */
public class InstructorDataProvider implements Provider<RuntimeExceptionDao<Instructor, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public InstructorDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Instructor, String> get() {
        try {
            Dao<Instructor, String> dao = DaoManager.createDao(connectionSource, Instructor.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
