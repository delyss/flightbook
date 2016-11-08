package data.repositories.Pilot;

import data.entities.Pilot;
import data.repositories.IRepository;

/**
 * Created by murat on 09/08/2015. flightbook
 */
interface IPilotRepository extends IRepository<Pilot> {
    Pilot getPilot();

    boolean isRegistered();

    void saveRegister();
}
