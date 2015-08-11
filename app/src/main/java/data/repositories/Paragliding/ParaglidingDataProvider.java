package data.repositories.Paragliding;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import data.DbHelper;
import data.entities.Paragliding;

/**
 * Created by murat on 12/08/2015. flightbook
 */
public class ParaglidingDataProvider implements Provider<RuntimeExceptionDao<Paragliding, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public ParaglidingDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Paragliding, String> get() {
        try {
            Dao<Paragliding, String> dao = DaoManager.createDao(connectionSource, Paragliding.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
