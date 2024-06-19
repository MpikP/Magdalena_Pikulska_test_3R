package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.CircleRepository;
import org.springframework.stereotype.Service;


@Service
public class CircleService extends GenericManagementService<Circle, CircleRepository> implements FigureService<Circle> {

    public CircleService(CircleRepository repository) {
        super(repository);
    }

}
