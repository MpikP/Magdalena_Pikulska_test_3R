package pl.kurs.magdalena_pikulska_test_3r.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Identificationable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional

public abstract class GenericManagementService<T extends Identificationable, R extends JpaRepository<T, Long> & PagingAndSortingRepository<T, Long>> {

    protected R repository;

    public GenericManagementService(R repository) {
        this.repository = repository;
    }


    public T add(T shape) {

        if (shape.getId() != null) {
            throw new WrongEntityStateException("ID is not null!");
        }
        return repository.save(shape);

    }
}
