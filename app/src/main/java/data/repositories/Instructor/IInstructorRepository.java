package data.repositories.Instructor;

import java.util.List;

import data.entities.Instructor;
import data.repositories.IRepository;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public interface IInstructorRepository extends IRepository<Instructor> {
    List GetActiveInstructors();
}
