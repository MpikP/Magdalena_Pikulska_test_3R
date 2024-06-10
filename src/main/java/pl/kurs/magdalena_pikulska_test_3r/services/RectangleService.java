package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.RectangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectangleService extends GenericManagementService<Rectangle, RectangleRepository>{
    public RectangleService(RectangleRepository repository) {
        super(repository);
    }




    public Rectangle add(Rectangle c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }

}
