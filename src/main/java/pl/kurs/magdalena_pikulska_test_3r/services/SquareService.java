package pl.kurs.magdalena_pikulska_test_3r.services;


import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.repositories.SquareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquareService extends GenericManagementService<Square, SquareRepository>{
    public SquareService(SquareRepository repository) {
        super(repository);
    }

    public List<Square> getAllByLengthBetween(Double from, Double to){
        return repository.findAllByLengthBetween(from, to);
    }
    public List<Square> getAllByLengthGreaterThanEqual(Double from){
        return repository.findAllByLengthGreaterThanEqual(from);
    }
    public List<Square> getAllByLengthLessThanEqual(Double to){
        return repository.findAllByLengthLessThanEqual(to);
    }
}
