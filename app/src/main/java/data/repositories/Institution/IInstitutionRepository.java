package data.repositories.Institution;

import java.util.List;

import data.entities.Institution;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public interface IInstitutionRepository {
    List<Institution> queryForAll();
    void save(Institution entity);
}
