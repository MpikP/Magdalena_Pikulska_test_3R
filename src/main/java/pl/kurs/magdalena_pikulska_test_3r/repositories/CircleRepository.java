package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Circle;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long>, FigureRepository {

    List<Circle> findAllByRadiusBetween(Double from, Double to);
    List<Circle> findAllByRadiusGreaterThanEqual(Double from);
    List<Circle> findAllByRadiusLessThanEqual(Double to);

    List<Figure> findAllByCreatedTimeBetween(LocalDate from, LocalDate to);
    List<Figure> findAllByCreatedTimeGreaterThanEqual(LocalDate from);
    List<Figure> findAllByCreatedTimeLessThanEqual(LocalDate to);

}
