package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.CircleRepository;
import org.springframework.stereotype.Service;


@Service
public class CircleService extends GenericManagementService<Circle, CircleRepository> implements FigureService<Circle> {

    public CircleService(CircleRepository repository) {
        super(repository);
    }

    public Circle add(Circle c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }

    @Override
    public Double calculateArea(Circle shape) {
        return shape.getRadius() * shape.getRadius() * FigureService.PI_NUMBER;
    }

    @Override
    public Double calculatePerimeter(Circle shape) {
        return 2.0 * shape.getRadius() * FigureService.PI_NUMBER;
    }
}
