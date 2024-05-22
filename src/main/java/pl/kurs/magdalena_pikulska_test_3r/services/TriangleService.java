package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.TriangleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriangleService extends GenericManagementService<Triangle, TriangleRepository> {
    public TriangleService(TriangleRepository repository) {
        super(repository);
    }

    public List<Triangle> getAllByLengthBaseBetween(Double from, Double to) {
        return repository.findAllByLengthBaseBetween(from, to);
    }

    public List<Triangle> getAllByLengthBaseGreaterThanEqual(Double from) {
        return repository.findAllByLengthBaseGreaterThanEqual(from);
    }

    public List<Triangle> getAllByLengthBaseLessThanEqual(Double to) {
        return repository.findAllByLengthBaseLessThanEqual(to);
    }

    public List<Triangle> getAllByLengthABetween(Double from, Double to) {
        return repository.findAllByLengthABetween(from, to);
    }

    public List<Triangle> getAllByLengthAGreaterThanEqual(Double from) {
        return repository.findAllByLengthAGreaterThanEqual(from);
    }

    public List<Triangle> getAllByLengthALessThanEqual(Double to) {
        return repository.findAllByLengthALessThanEqual(to);
    }

    public List<Triangle> getAllByLengthBBetween(Double from, Double to) {
        return repository.findAllByLengthBBetween(from, to);
    }

    public List<Triangle> getAllByLengthBGreaterThanEqual(Double from) {
        return repository.findAllByLengthBGreaterThanEqual(from);
    }

    public List<Triangle> getAllByLengthBLessThanEqual(Double to) {
        return repository.findAllByLengthBLessThanEqual(to);
    }

    public List<Triangle> getAllByLHeightBetween(Double from, Double to) {
        return repository.findAllByHeightBetween(from, to);
    }

    public List<Triangle> getAllByHeightGreaterThanEqual(Double from) {
        return repository.findAllByHeightGreaterThanEqual(from);
    }

    public List<Triangle> getAllByHeightLessThanEqual(Double to) {
        return repository.findAllByHeightLessThanEqual(to);
    }



}