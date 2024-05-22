package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SquareRepository extends JpaRepository<Square, Long>, FigureRepository {
    List<Square> findAllByLengthBetween(Double from, Double to);
    List<Square> findAllByLengthGreaterThanEqual(Double from);
    List<Square> findAllByLengthLessThanEqual(Double to);

    List<Figure> findAllByCreatedTimeBetween(LocalDate from, LocalDate to);
    List<Figure> findAllByCreatedTimeGreaterThanEqual(LocalDate from);
    List<Figure> findAllByCreatedTimeLessThanEqual(LocalDate to);
}
