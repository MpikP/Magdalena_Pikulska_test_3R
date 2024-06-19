package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import pl.kurs.magdalena_pikulska_test_3r.repositories.TriangleRepository;
import org.springframework.stereotype.Service;

@Service
public class TriangleService extends GenericManagementService<Triangle, TriangleRepository> implements FigureService<Triangle> {
    public TriangleService(TriangleRepository repository) {
        super(repository);
    }

}