package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.TriangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriangleService extends GenericManagementService<Triangle, TriangleRepository> {
    public TriangleService(TriangleRepository repository) {
        super(repository);
    }



    public Triangle add(Triangle c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }


}