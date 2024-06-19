package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "triangles")
public class Triangle extends Shape {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false)
    private Double lengthBase;
    private Double lengthA;
    private Double lengthB;
    @Column(nullable = false)
    private Double heightTriangle;

    public Triangle() {
    }

    public Triangle(Double lengthBase, Double lengthA, Double lengthB, Double height) {
        this.lengthBase = lengthBase;
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.heightTriangle = height;
    }

    public Double getLengthBase() {
        return lengthBase;
    }

    public void setLengthBase(Double lengthBase) {
        this.lengthBase = lengthBase;
    }

    public Double getLengthA() {
        return lengthA;
    }

    public void setLengthA(Double lengthA) {
        this.lengthA = lengthA;
    }

    public Double getLengthB() {
        return lengthB;
    }

    public void setLengthB(Double lengthB) {
        this.lengthB = lengthB;
    }

    public Double getHeightTriangle() {
        return heightTriangle;
    }

    public void setHeightTriangle(Double heightTriangle) {
        this.heightTriangle = heightTriangle;
    }


    @Override
    public Double getArea() {
        return (getLengthBase() * getHeightTriangle()) / 3;
    }

    @Override
    public Double getPerimeter() {
        return getLengthBase() + getLengthB() + getLengthA();
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
        Triangle triangle = (Triangle) o;
        return Objects.equals(lengthBase, triangle.lengthBase) && Objects.equals(lengthA, triangle.lengthA) && Objects.equals(lengthB, triangle.lengthB) && Objects.equals(heightTriangle, triangle.heightTriangle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lengthBase, lengthA, lengthB, heightTriangle);
    }


}
