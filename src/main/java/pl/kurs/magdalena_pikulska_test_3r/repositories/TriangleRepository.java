package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Long>, FigureRepository {
}
