package data.repositories.Harness;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import data.entities.Harness;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class HarnessRepository implements IHarnessRepository {
    private RuntimeExceptionDao<Harness, String> repo;


    @Inject
    public HarnessRepository(Context context, HarnessDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Harness> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Harness entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Harness entity) {repo.delete(entity); }

    @Override
    public Harness Get(int id) { return repo.queryForId( Integer.toString(id)); }
}
