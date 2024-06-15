package pl.kurs.magdalena_pikulska_test_3r.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name ="triangles")
public class Triangle extends Shape implements Figure{
    private static final long serialVersionUID = 1L;


    @Column(nullable = false)
    private Double lengthBase;
    private Double lengthA;
    private Double lengthB;
    @Column(nullable = false)
    private Double height;

    public Triangle() {
    }

    public Triangle(Double lengthBase, Double lengthA, Double lengthB, Double height) {
        this.lengthBase = lengthBase;
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.height = height;
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
        Triangle triangle = (Triangle) o;
        return Objects.equals(lengthBase, triangle.lengthBase) && Objects.equals(lengthA, triangle.lengthA) && Objects.equals(lengthB, triangle.lengthB) && Objects.equals(height, triangle.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lengthBase, lengthA, lengthB, height);
    }


}
