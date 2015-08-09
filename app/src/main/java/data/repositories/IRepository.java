package data.repositories;

import java.util.List;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public interface IRepository<T> {
    List<T> queryForAll();
    void save(T entity);
}