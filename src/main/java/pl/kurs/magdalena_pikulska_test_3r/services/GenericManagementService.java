package pl.kurs.magdalena_pikulska_test_3r.services;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Identificationable;
import pl.kurs.magdalena_pikulska_test_3r.repositories.FigureRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional

public abstract class GenericManagementService<T extends Identificationable & Figure, R extends JpaRepository<T, Long> & FigureRepository> {

    protected R repository;

    public GenericManagementService(R repository) {
        this.repository = repository;
    }

    public T add(T entity) {

        if (entity.getId() != null) {
            throw new WrongEntityStateException("ID encji nie jest nullem!");
        }
        return repository.save(entity);

    }


    public List<T> getAll() {
        return repository.findAll();
    }



    public List<T> getAllByAreaGreaterThanEqual(Double from) {
        return repository.findAll().stream()
                .filter(x -> x.calculateArea() >= from)
                .collect(Collectors.toList());
    }



    public List<T> getAllByAreaLessThanEqual(Double to) {
        return repository.findAll().stream()
                .filter(x -> x.calculateArea() <= to)
                .collect(Collectors.toList());
    }



    public List<T> getAllByAreaBetween(Double from, Double to) {
        return getAllByAreaGreaterThanEqual(from)
                .stream()
                .filter(getAllByAreaLessThanEqual(to)::contains)
                .collect(Collectors.toList());
    }



    public List<T> getAllByPerimeterGreaterThanEqual(Double from) {
        return repository.findAll().stream()
                .filter(x -> x.calculatePerimeter() >= from)
                .collect(Collectors.toList());
    }



    public List<T> getAllByPerimeterLessThanEqual(Double to) {
        return repository.findAll().stream()
                .filter(x -> x.calculatePerimeter() <= to)
                .collect(Collectors.toList());
    }



    public List<T> getAllByPerimeterBetween(Double from, Double to) {
        return getAllByPerimeterGreaterThanEqual(from)
                .stream()
                .filter(getAllByPerimeterLessThanEqual(to)::contains)
                .collect(Collectors.toList());
    }



    public List<Figure> getAllByCreatedTimeBetween(LocalDate from, LocalDate to){
        return repository.findAllByCreatedTimeBetween(from, to);
    }

    public List<Figure> getAllByCreatedTimeGreaterThanEqual(LocalDate from){
        return repository.findAllByCreatedTimeGreaterThanEqual(from);
    }

    public List<Figure> getAllByCreatedTimeLessThanEqual(LocalDate to){
        return repository.findAllByCreatedTimeLessThanEqual(to);
    }


}
