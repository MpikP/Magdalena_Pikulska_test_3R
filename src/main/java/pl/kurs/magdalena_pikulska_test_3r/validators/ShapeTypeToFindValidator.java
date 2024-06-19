package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;

import java.util.Arrays;
import java.util.List;

public class ShapeTypeToFindValidator implements ConstraintValidator<ShapeTypeToFind, String> {
    private List<String> shapeTypesList;
    @Value("${shapeType}")
    private String shapeTypesString;

    @Override
    public void initialize(ShapeTypeToFind constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;

        shapeTypesList = Arrays.asList(shapeTypesString.split(", "));
        return shapeTypesList.contains(s.toLowerCase());
    }
}
