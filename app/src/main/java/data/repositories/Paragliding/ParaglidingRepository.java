package data.repositories.Paragliding;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import data.entities.Paragliding;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class ParaglidingRepository implements IParaglidingRepository {

    private RuntimeExceptionDao<Paragliding, String> repo;


    @Inject
    public ParaglidingRepository(Context context, ParaglidingDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Paragliding> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Paragliding entity) { repo.createOrUpdate(entity); }
}
