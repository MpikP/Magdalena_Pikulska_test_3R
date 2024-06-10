package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long>, FigureRepository {



}
