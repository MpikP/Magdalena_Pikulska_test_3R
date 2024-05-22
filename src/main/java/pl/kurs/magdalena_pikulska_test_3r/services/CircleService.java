package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.CircleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleService extends GenericManagementService<Circle, CircleRepository>{

    public CircleService(CircleRepository repository) {
        super(repository);
    }

    public List<Circle> getAllByRadiusBetween(Double from, Double to){
        return repository.findAllByRadiusBetween(from, to);
    }
    public List<Circle> getAllByRadiusGreaterThanEqual(Double from){
        return repository.findAllByRadiusGreaterThanEqual(from);
    }
    public List<Circle> getAllByRadiusLessThanEqual(Double to){
        return repository.findAllByRadiusLessThanEqual(to);
    }

}
