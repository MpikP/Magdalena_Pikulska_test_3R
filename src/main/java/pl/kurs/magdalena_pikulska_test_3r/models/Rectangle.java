package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rectangles")
public class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double lengthRectangle;
    @Column(nullable = false)
    private Double heightRectangle;

    public Rectangle() {
    }

    public Rectangle(Double length, Double height) {
        super();
        this.lengthRectangle = length;
        this.heightRectangle = height;
    }

    public Double getLengthRectangle() {
        return lengthRectangle;
    }

    public void setLengthRectangle(Double lengthRectangle) {
        this.lengthRectangle = lengthRectangle;
    }

    public Double getHeightRectangle() {
        return heightRectangle;
    }

    public void setHeightRectangle(Double heightRectangle) {
        this.heightRectangle = heightRectangle;
    }

    @Override
    public Double getArea() {
        return getLengthRectangle() * getHeightRectangle();
    }

    @Override
    public Double getPerimeter() {
        return getLengthRectangle() * getHeightRectangle() * 2.0;
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
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(lengthRectangle, rectangle.lengthRectangle) && Objects.equals(heightRectangle, rectangle.heightRectangle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lengthRectangle, heightRectangle);
    }
}
