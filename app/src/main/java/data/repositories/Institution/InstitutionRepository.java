package data.repositories.Institution;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import data.entities.Institution;


/**
 * Created by murat on 09/08/2015. flightbook
 */
public class InstitutionRepository implements IInstitutionRepository {
    private RuntimeExceptionDao<Institution, String> repo;

    @Inject
    public InstitutionRepository(Context context, InstitutionDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Institution> QueryForAll() {
        return repo.queryForAll();
    }

    @Override
    public void Save(Institution entity) {
        repo.createOrUpdate(entity);
    }

    @Override
    public void Delete(Institution entity) {repo.delete(entity); }

    @Override
    public Institution Get(int id) { return repo.queryForId( Integer.toString(id)); }
}
