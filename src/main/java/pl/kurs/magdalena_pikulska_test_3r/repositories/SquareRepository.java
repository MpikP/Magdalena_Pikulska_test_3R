package pl.kurs.magdalena_pikulska_test_3r.repositories;

import org.springframework.stereotype.Repository;
import pl.kurs.magdalena_pikulska_test_3r.models.Square;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SquareRepository extends JpaRepository<Square, Long> {

}
