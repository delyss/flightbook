package data;

import com.google.inject.AbstractModule;

import data.repositories.Institution.IInstitutionRepository;
import data.repositories.Institution.InstitutionRepository;

/**
 * Created by murat on 09/08/2015. flightbook
 */
public class IocModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(new TypeLiteral<IRepository<Institution>>(){}).to(new TypeLiteral<Repository<Institution>>(){});

//        bind(IPilotRepository.class).to(PilotRepository.class);
    //    bind(IInstitutionRepository.class).to(InstitutionRepository.class);

    }
}
