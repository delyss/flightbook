package data.repositories.Pilot;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import data.entities.Pilot;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class PilotRepository implements IPilotRepository {

    private RuntimeExceptionDao<Pilot, String> repo;


    @Inject
    public PilotRepository(Context context, PilotDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Pilot> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Pilot entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Pilot entity) {repo.delete(entity); }

    @Override
    public Pilot Get(int id) { return repo.queryForId( Integer.toString(id));  }
}
