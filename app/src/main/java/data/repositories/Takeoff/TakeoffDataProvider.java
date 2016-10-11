package data.repositories.Takeoff;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Takeoff;

/**
 * Created by murat on 12/08/2015. flightbook
 */
public class TakeoffDataProvider implements Provider<RuntimeExceptionDao<Takeoff, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public TakeoffDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Takeoff, String> get() {
        try {
            Dao<Takeoff, String> dao = DaoManager.createDao(connectionSource, Takeoff.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
