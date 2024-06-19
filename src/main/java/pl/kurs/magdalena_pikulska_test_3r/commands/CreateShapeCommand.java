package pl.kurs.magdalena_pikulska_test_3r.commands;

import jakarta.validation.constraints.Positive;
import pl.kurs.magdalena_pikulska_test_3r.validators.ParametersToCreate;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeTypeToFind;


@ShapeType
@ParametersToCreate
public class CreateShapeCommand {

    @JsonProperty("type")
    @NotBlank
    @NotEmpty
    private String type;

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

    public String getType() {
        return type;
    }


}
