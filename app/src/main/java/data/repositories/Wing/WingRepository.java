package data.repositories.Wing;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import data.entities.Wing;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class WingRepository implements IWingRepository {

    private RuntimeExceptionDao<Wing, String> repo;


    @Inject
    public WingRepository(Context context, WingDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Wing> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Wing entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Wing entity) {repo.delete(entity); }

    @Override
    public Wing Get(int id) { return repo.queryForId( Integer.toString(id)); }
}
