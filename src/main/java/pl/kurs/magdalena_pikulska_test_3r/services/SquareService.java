package pl.kurs.magdalena_pikulska_test_3r.services;


import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.repositories.SquareRepository;
import org.springframework.stereotype.Service;


@Service
public class SquareService extends GenericManagementService<Square, SquareRepository>{
    public SquareService(SquareRepository repository) {
        super(repository);
    }

    public Square add(Square c){

        if (c.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(c);

    }
}
