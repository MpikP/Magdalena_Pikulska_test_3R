package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long>, FigureRepository {
    List<Rectangle> findAllByLengthBetween(Double from, Double to);
    List<Rectangle> findAllByLengthGreaterThanEqual(Double from);
    List<Rectangle> findAllByLengthLessThanEqual(Double to);

    List<Rectangle> findAllByHeightBetween(Double from, Double to);
    List<Rectangle> findAllByHeightGreaterThanEqual(Double from);
    List<Rectangle> findAllByHeightLessThanEqual(Double to);

    @Query("SELECT r FROM Rectangle r WHERE r.length BETWEEN :lengthFrom AND :lengthTo AND r.height BETWEEN :heightFrom AND :heightTo")
    List<Rectangle> findAllByLengthBetweenAndHeightBetween(Double lengthFrom, Double lengthTo, Double heightFrom, Double heightTo);


    List<Figure> findAllByCreatedTimeBetween(LocalDate from, LocalDate to);
    List<Figure> findAllByCreatedTimeGreaterThanEqual(LocalDate from);
    List<Figure> findAllByCreatedTimeLessThanEqual(LocalDate to);
}
