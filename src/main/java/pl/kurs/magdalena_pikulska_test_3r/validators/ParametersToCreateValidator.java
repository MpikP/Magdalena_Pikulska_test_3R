package pl.kurs.magdalena_pikulska_test_3r.validators;

import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ParametersToCreateValidator implements ConstraintValidator<ParametersToCreate, CreateShapeCommand> {
    @Override
    public void initialize(ParametersToCreate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateShapeCommand command, ConstraintValidatorContext constraintValidatorContext) {
        if (command == null)
            return false;

        String shapeType = command.getType();

        switch (shapeType){
            case "circle":
                if(command.getRadius() == null )
                    return false;
                break;
            case "square":
                if(command.getLength() == null)
                    return false;
                break;
            case "rectangle":
                if(command.getLength() == null || command.getHeight() == null)
                    return false;
                break;
            case "triangle":
                if(command.getLengthA() == null || command.getLengthB() == null || command.getLengthBase() == null || command.getHeight() == null)
                    return false;
                break;
        }

        return true;
    }
}
