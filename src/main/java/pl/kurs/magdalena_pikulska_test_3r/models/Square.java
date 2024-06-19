package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "squares")
public class Square extends Shape {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double lengthSquare;


    public Square() {
    }

    public Square(Double length) {
        this.lengthSquare = length;
    }

    public Double getLengthSquare() {
        return lengthSquare;
    }

    public void setLengthSquare(Double lengthSquare) {
        this.lengthSquare = lengthSquare;
    }

    @Override
    public Double getArea() {
        return getLengthSquare() * getLengthSquare();
    }

    @Override
    public Double getPerimeter() {
        return 4.0 * getLengthSquare();
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
        Square square = (Square) o;
        return Objects.equals(lengthSquare, square.lengthSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lengthSquare);
    }
}
