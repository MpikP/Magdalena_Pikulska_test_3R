package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.RectangleRepository;
import org.springframework.stereotype.Service;


@Service
public class RectangleService extends GenericManagementService<Rectangle, RectangleRepository> implements FigureService<Rectangle> {
    public RectangleService(RectangleRepository repository) {
        super(repository);
    }

}
