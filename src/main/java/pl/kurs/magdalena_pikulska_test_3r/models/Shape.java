package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shape implements Serializable, Identificationable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp createdTime = Timestamp.from(Instant.now());

    public Shape() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(id, shape.id) && Objects.equals(createdTime, shape.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdTime);
    }

    public abstract Double getArea();

    public abstract Double getPerimeter();

    public abstract String getType();
}
