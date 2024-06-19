package pl.kurs.magdalena_pikulska_test_3r.validators;


import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

public class ShapeTypeValidator implements ConstraintValidator<ShapeType, CreateShapeCommand> {
    private List<String> shapeTypesList;
    @Value("${shapeType}")
    private String shapeTypesString;

    @Override
    public void initialize(ShapeType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateShapeCommand command, ConstraintValidatorContext constraintValidatorContext) {

        if (command == null)
            return false;

        shapeTypesList = Arrays.asList(shapeTypesString.split(", "));
        return shapeTypesList.contains(command.getType().toLowerCase());


    }
}
