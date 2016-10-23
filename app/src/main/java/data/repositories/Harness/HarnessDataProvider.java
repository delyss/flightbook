package data.repositories.Harness;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Harness;

/**
 * Created by murat on 12/08/2015. flightbook
 */
public class HarnessDataProvider implements Provider<RuntimeExceptionDao<Harness, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public HarnessDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Harness, String> get() {
        try {
            Dao<Harness, String> dao = DaoManager.createDao(connectionSource, Harness.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
