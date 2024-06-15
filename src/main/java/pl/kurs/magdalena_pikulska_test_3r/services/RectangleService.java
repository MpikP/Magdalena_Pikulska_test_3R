package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.RectangleRepository;
import org.springframework.stereotype.Service;


@Service
public class RectangleService extends GenericManagementService<Rectangle, RectangleRepository> implements FigureService<Rectangle>{
    public RectangleService(RectangleRepository repository) {
        super(repository);
    }




    public Rectangle add(Rectangle c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }

    @Override
    public Double calculateArea(Rectangle shape) {
        return shape.getLength() * shape.getHeight();
    }

    @Override
    public Double calculatePerimeter(Rectangle shape) {
        return shape.getLength() * shape.getHeight() * 2.0;
    }
}
