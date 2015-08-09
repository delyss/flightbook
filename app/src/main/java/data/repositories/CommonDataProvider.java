package data.repositories;

import android.database.SQLException;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

import data.DbHelper;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class CommonDataProvider<T> implements Provider<RuntimeExceptionDao<T, String>> {
    private ConnectionSource connectionSource;
    final Class<T> typeParameterClass;

    @Inject
    public CommonDataProvider(DbHelper dbHelper, Class<T> typeParameterClass) {
        connectionSource = dbHelper.getConnectionSource();
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public RuntimeExceptionDao<T, String> get() {
        try {
            Dao<T, String> dao = DaoManager.createDao(
                    connectionSource, typeParameterClass);
            return new RuntimeExceptionDao<>(dao);
        } catch (SQLException e) {
            Log.e("SQL", e.toString());
        } catch (Exception ex) {
            Log.e("EX", ex.toString());
        }
        return null;
    }
}
