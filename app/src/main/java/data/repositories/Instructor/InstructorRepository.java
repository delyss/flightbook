package data.repositories.Instructor;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import data.entities.Instructor;

import static java.util.Collections.*;


/**
 * Created by murat on 09/08/2015. flightbook
 */
public class InstructorRepository implements IInstructorRepository {

    private RuntimeExceptionDao<Instructor, String> repo;


    @Inject
    public InstructorRepository(Context context, InstructorDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Instructor> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Instructor entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Instructor entity) {repo.delete(entity); }

    @Override
    public Instructor Get(int id) { return repo.queryForId( Integer.toString(id)); }

    @Override
    public List GetActiveInstructors() {
        QueryBuilder<Instructor, String> qb = repo.queryBuilder();
        Where where = qb.where();
        try {
            where.eq("Active", true);
            return where.query();
        } catch (SQLException e) {
            e.printStackTrace();
            return EMPTY_LIST;
        }
    }
}
