package pl.kurs.magdalena_pikulska_test_3r.services;

import org.springframework.stereotype.Service;
import pl.kurs.magdalena_pikulska_test_3r.factory.ShapeServiceFactory;
import pl.kurs.magdalena_pikulska_test_3r.models.Shape;

@Service
public class DynamicManagementService {

    private ShapeServiceFactory factory;

    public DynamicManagementService(ShapeServiceFactory factory) {
        this.factory = factory;
    }


    public <T> T addFigure(Shape shape) {
        FigureService<T> service = factory.getService(shape.getClass());
        return service.add((T) shape);
    }

}

