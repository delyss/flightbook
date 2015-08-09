package data.repositories;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class Repository<T> implements IRepository<T> {
    private RuntimeExceptionDao<T, String> commonEntity;

    @Inject
    public Repository(Context context, CommonDataProvider<T> commonEntityProvider) {
        commonEntity = commonEntityProvider.get();
    }


    @Override
    public List<T> queryForAll() {
        return commonEntity.queryForAll();
    }

    @Override
    public void save(T entity) {
        commonEntity.create(entity);
    }
}
