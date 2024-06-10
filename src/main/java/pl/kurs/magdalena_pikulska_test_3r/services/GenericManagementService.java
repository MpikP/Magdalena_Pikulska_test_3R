package pl.kurs.magdalena_pikulska_test_3r.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Identificationable;
import pl.kurs.magdalena_pikulska_test_3r.repositories.FigureRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional

public abstract class GenericManagementService<T extends Identificationable & Figure, R extends JpaRepository<T, Long> & FigureRepository & PagingAndSortingRepository<T, Long>>  {

    protected R repository;

    public GenericManagementService(R repository) {
        this.repository = repository;
    }





}
