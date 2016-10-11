package data.repositories.Flight;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import data.entities.Flight;

/**
 * Created by murat on 19/11/2015. flightbook
 */
public class FlightRepository implements IFlightRepository {
    private RuntimeExceptionDao<Flight, String> repo;


    @Inject
    public FlightRepository(Context context, FlightDataProvider commonEntityProvider) {
        repo = commonEntityProvider.get();
    }

    @Override
    public List<Flight> QueryForAll() { return repo.queryForAll(); }

    @Override
    public void Save(Flight entity) { repo.createOrUpdate(entity); }

    @Override
    public void Delete(Flight entity) {repo.delete(entity); }

    @Override
    public Flight Get(int id) { return repo.queryForId( Integer.toString(id)); }
}
