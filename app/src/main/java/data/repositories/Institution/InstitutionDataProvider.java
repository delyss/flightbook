package data.repositories.Institution;

import android.database.SQLException;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import data.DbHelper;
import data.entities.Institution;

/**
 * Created by murat on 10/08/2015. flightbook
 */
public class InstitutionDataProvider implements Provider<RuntimeExceptionDao<Institution, String>> {
    private ConnectionSource connectionSource;

    @Inject
    public InstitutionDataProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public RuntimeExceptionDao<Institution, String> get() {
        try {
            Dao<Institution, String> dao = DaoManager.createDao(connectionSource, Institution.class);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
