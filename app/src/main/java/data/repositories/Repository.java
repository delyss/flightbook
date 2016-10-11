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
    public List<T> QueryForAll() {
        return commonEntity.queryForAll();
    }

    @Override
    public void Save(T entity) {
        commonEntity.create(entity);
    }

    @Override
    public void Delete(T entity) {commonEntity.delete(entity); }

    @Override
    public T Get(int id) { return commonEntity.queryForId( Integer.toString(id));  }
}
