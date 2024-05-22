package pl.kurs.magdalena_pikulska_test_3r.validators;

import pl.kurs.magdalena_pikulska_test_3r.commands.CreateShapeCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ParametersValidator implements ConstraintValidator<Parameters, CreateShapeCommand> {
    @Override
    public void initialize(Parameters constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateShapeCommand command, ConstraintValidatorContext constraintValidatorContext) {
        if (command == null)
            return false;

        if(command.getParameters() == null)
            return false;

        String shapeType = command.getType();
        pl.kurs.magdalena_pikulska_test_3r.commands.Parameters parameters = command.getParameters();

        switch (shapeType){
            case "circle":
                if(parameters.getRadius() == null )
                    return false;
                break;
            case "square":
                if(parameters.getLength() == null)
                    return false;
                break;
            case "rectangle":
                if(parameters.getLength() == null || parameters.getHeight() == null)
                    return false;
                break;
            case "triangle":
                if(parameters.getLengthA() == null || parameters.getLengthB() == null || parameters.getLengthBase() == null || parameters.getHeight() == null)
                    return false;
                break;
        }

        return true;
    }
}
