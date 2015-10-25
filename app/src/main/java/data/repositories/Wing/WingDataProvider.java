package data.repositories.Wing;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Wing;

/**
 * Created by murat on 12/08/2015. flightbook
 */
public class WingDataProvider implements Provider<RuntimeExceptionDao<Wing, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public WingDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Wing, String> get() {
        try {
            Dao<Wing, String> dao = DaoManager.createDao(connectionSource, Wing.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
