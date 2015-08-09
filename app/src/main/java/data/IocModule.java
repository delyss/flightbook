package data;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import data.entities.Institution;
import data.repositories.IInstitutionRepository;
import data.repositories.IPilotRepository;
import data.repositories.IRepository;
import data.repositories.InstitutionRepository;
import data.repositories.PilotRepository;
import data.repositories.Repository;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class IocModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<IRepository<Institution>>(){}).to(new TypeLiteral<Repository<Institution>>(){});
        bind(IPilotRepository.class).to(PilotRepository.class);
        bind(IInstitutionRepository.class).to(InstitutionRepository.class);
    }
}
