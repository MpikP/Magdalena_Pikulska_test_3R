package pl.kurs.magdalena_pikulska_test_3r.services;


import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import pl.kurs.magdalena_pikulska_test_3r.repositories.SquareRepository;
import org.springframework.stereotype.Service;


@Service
public class SquareService extends GenericManagementService<Square, SquareRepository> implements FigureService<Square> {
    public SquareService(SquareRepository repository) {
        super(repository);
    }

}
