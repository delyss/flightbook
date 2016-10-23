package data.repositories.Flight;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Flight;

/**
 * Created by murat on 19/11/2015. flightbook
 */
public class FlightDataProvider implements Provider<RuntimeExceptionDao<Flight, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public FlightDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Flight, String> get() {
        try {
            Dao<Flight, String> dao = DaoManager.createDao(connectionSource, Flight.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
