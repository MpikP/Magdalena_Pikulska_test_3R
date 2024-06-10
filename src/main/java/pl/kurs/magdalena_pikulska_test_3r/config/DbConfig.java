package pl.kurs.magdalena_pikulska_test_3r.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    @Transactional
    public void createFunctionsInDb() {
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculateareacircle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculateAreaCircle\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculatearearectangle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculateAreaRectangle\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculateareasquare FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculateAreaSquare\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculateareatriangle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculateAreaTriangle\";")
                .executeUpdate();


        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculatePerimetercircle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculatePerimeterCircle\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculatePerimeterrectangle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculatePerimeterRectangle\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculatePerimetersquare FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculatePerimeterSquare\";")
                .executeUpdate();
        entityManager.createNativeQuery("CREATE ALIAS IF NOT EXISTS calculatePerimetertriangle FOR \"pl.kurs.magdalena_pikulska_test_3r.config.FunctionsDb.calculatePerimeterTriangle\";")
                .executeUpdate();
    }
}
