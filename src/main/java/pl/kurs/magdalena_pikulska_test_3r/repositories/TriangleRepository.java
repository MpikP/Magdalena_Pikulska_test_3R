package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;

public interface TriangleRepository extends JpaRepository<Triangle, Long> {
}
