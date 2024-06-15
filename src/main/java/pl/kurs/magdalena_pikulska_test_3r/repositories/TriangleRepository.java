package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Long> {
}
