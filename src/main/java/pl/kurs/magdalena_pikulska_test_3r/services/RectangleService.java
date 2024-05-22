package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.RectangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectangleService extends GenericManagementService<Rectangle, RectangleRepository>{
    public RectangleService(RectangleRepository repository) {
        super(repository);
    }

    public List<Rectangle> getAllByLengthBetween(Double from, Double to){
        return repository.findAllByLengthBetween(from, to);
    }
    public List<Rectangle> getAllByLengthGreaterThanEqual(Double from){
        return repository.findAllByLengthGreaterThanEqual(from);
    }
    public List<Rectangle> getAllByLengthLessThanEqual(Double to){
        return repository.findAllByLengthLessThanEqual(to);
    }

    public List<Rectangle> getAllByHeightBetween(Double from, Double to){
        return repository.findAllByHeightBetween(from, to);
    }
    public List<Rectangle> getAllByHeightGreaterThanEqual(Double from){
        return repository.findAllByHeightGreaterThanEqual(from);
    }
    public List<Rectangle> getAllByHeightLessThanEqual(Double to){
        return repository.findAllByHeightLessThanEqual(to);
    }

    public List<Rectangle> getAllByLengthBetweenAndHeightBetween(Double lengthFrom, Double lengthTo, Double heightFrom, Double heightTo){
        return repository.findAllByLengthBetweenAndHeightBetween(lengthFrom, lengthTo, heightFrom, heightTo);
    }
}
