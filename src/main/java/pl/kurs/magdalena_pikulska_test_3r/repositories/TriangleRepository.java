package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Long>, FigureRepository {
    List<Triangle> findAllByLengthBaseBetween(Double from, Double to);
    List<Triangle> findAllByLengthBaseGreaterThanEqual(Double from);
    List<Triangle> findAllByLengthBaseLessThanEqual(Double to);

    List<Triangle> findAllByLengthABetween(Double from, Double to);
    List<Triangle> findAllByLengthAGreaterThanEqual(Double from);
    List<Triangle> findAllByLengthALessThanEqual(Double to);

    List<Triangle> findAllByLengthBBetween(Double from, Double to);
    List<Triangle> findAllByLengthBGreaterThanEqual(Double from);
    List<Triangle> findAllByLengthBLessThanEqual(Double to);

    List<Triangle> findAllByHeightBetween(Double from, Double to);
    List<Triangle> findAllByHeightGreaterThanEqual(Double from);
    List<Triangle> findAllByHeightLessThanEqual(Double to);

    List<Figure> findAllByCreatedTimeBetween(LocalDate from, LocalDate to);
    List<Figure> findAllByCreatedTimeGreaterThanEqual(LocalDate from);
    List<Figure> findAllByCreatedTimeLessThanEqual(LocalDate to);


}
