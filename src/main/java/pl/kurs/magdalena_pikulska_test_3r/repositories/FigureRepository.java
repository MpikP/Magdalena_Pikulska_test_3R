package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FigureRepository {
    List<Figure> findAllByCreatedTimeBetween(LocalDate from, LocalDate to);
    List<Figure> findAllByCreatedTimeGreaterThanEqual(LocalDate from);
    List<Figure> findAllByCreatedTimeLessThanEqual(LocalDate to);




}
