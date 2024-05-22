package pl.kurs.magdalena_pikulska_test_3r.commands;

import jakarta.validation.constraints.Positive;

public class Parameters {
    @Positive
    private Double radius;
    @Positive
    private Double length;
    @Positive
    private Double height;
    @Positive
    private Double lengthBase;
    @Positive
    private Double lengthA;
    @Positive
    private Double lengthB;

    public Double getRadius() {
        return radius;
    }

    public Double getLength() {
        return length;
    }

    public Double getHeight() {
        return height;
    }

    public Double getLengthBase() {
        return lengthBase;
    }

    public Double getLengthA() {
        return lengthA;
    }

    public Double getLengthB() {
        return lengthB;
    }
}
