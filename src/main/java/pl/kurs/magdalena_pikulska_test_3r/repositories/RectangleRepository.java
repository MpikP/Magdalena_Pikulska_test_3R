package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long> {



}
