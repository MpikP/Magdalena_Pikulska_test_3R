package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "squares")
public class Square extends Shape implements Figure{
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double length;


    public Square() {
    }

    public Square(Double length) {
        this.length = length;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Square square = (Square) o;
        return Objects.equals(length, square.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length);
    }

    @Override
    public Double calculateArea() {
        return length * length;
    }

    @Override
    public Double calculatePerimeter() {
        return 4 * length;
    }
}
