package pl.kurs.magdalena_pikulska_test_3r.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.CircleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CircleService extends GenericManagementService<Circle, CircleRepository>{

    public CircleService(CircleRepository repository) {
        super(repository);
    }

    public Circle add(Circle c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }



}
