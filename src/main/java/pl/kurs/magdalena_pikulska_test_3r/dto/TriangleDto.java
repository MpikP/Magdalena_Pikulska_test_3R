package pl.kurs.magdalena_pikulska_test_3r.dto;

import java.sql.Timestamp;

public class TriangleDto implements FigureDto {
    private Long id;
    private Timestamp createdTime;
    private Double lengthBase;
    private Double lengthA;
    private Double lengthB;
    private Double height;
    private Double area;
    private Double perimeter;

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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }
}
