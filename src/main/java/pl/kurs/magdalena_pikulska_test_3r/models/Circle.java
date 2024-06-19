package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;
import pl.kurs.magdalena_pikulska_test_3r.services.FigureService;

import java.util.Objects;

@Entity
@Table(name = "circles")
public class Circle extends Shape {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false)
    private Double radius;


    public Circle() {
    }

    public Circle(Double radius) {
        super();
        this.radius = radius;
    }


    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }


    @Override
    public Double getArea() {
        return getRadius() * getRadius() * Math.PI;
    }

    @Override
    public Double getPerimeter() {
        return 2.0 * getRadius() * Math.PI;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Objects.equals(radius, circle.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }


}


