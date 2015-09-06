package data.repositories.Takeoff;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import data.entities.Takeoff;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class TakeoffRepository implements ITakeoffRepository {

    private RuntimeExceptionDao<Takeoff, String> repo;


    @Inject
    public TakeoffRepository(Context context, TakeoffDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Takeoff> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Takeoff entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Takeoff entity) {repo.delete(entity); }

    @Override
    public Takeoff Get(int id) { return repo.queryForId( Integer.toString(id)); }
}
