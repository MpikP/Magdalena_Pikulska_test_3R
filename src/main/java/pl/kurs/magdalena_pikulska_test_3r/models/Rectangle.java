package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rectangles")
public class Rectangle extends Shape implements Figure{
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double length;
    @Column(nullable = false)
    private Double height;

    public Rectangle() {
    }

    public Rectangle(Double length, Double height) {
        super();
        this.length = length;
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(length, rectangle.length) && Objects.equals(height, rectangle.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length, height);
    }

    @Override
    public Double calculateArea() {
        return length * height;
    }

    @Override
    public Double calculatePerimeter() {
        return 2 * (length + height);
    }
}
