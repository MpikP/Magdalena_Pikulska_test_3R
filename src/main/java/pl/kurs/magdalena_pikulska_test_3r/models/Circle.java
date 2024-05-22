package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "circles")
public class Circle extends Shape implements Figure{
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

    @Override
    public Double calculateArea() {
        return PI_NUMBER * radius * radius;
    }

    @Override
    public Double calculatePerimeter() {
        return  2 * PI_NUMBER * radius;
    }

}


