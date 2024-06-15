package pl.kurs.magdalena_pikulska_test_3r.factory;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;
import pl.kurs.magdalena_pikulska_test_3r.models.Figure;

import java.util.List;

public interface FigureQueryFactory {
    @Transactional
    List<Figure> getFromQuery(String type, FindShapesQuery findShapesQuery, Pageable pageable);
}
