package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.kurs.magdalena_pikulska_test_3r.commands.FindShapesQuery;

import java.util.Locale;

public class ParametersToFindValidator implements ConstraintValidator<ParametersToFind, FindShapesQuery> {
    @Override
    public void initialize(ParametersToFind constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(FindShapesQuery findShapesQuery, ConstraintValidatorContext constraintValidatorContext) {

        String shapeType = findShapesQuery.getType().toLowerCase(Locale.ROOT);
        if (shapeType == null)
            return true;
        else if(findShapesQuery.getRadiusFrom() == null
                && findShapesQuery.getRadiusTo() == null
                && findShapesQuery.getLengthFrom() == null
                && findShapesQuery.getLengthTo() == null
                && findShapesQuery.getHeightFrom() == null
                && findShapesQuery.getHeightTo() == null
                && findShapesQuery.getLengthBaseFrom() == null
                && findShapesQuery.getLengthBaseTo() == null
                && findShapesQuery.getLengthAFrom() == null
                && findShapesQuery.getLengthATo() == null
                && findShapesQuery.getLengthBFrom() == null
                && findShapesQuery.getLengthBTo() == null)
            return true;

        switch (shapeType){
            case "circle":
                if(findShapesQuery.getLengthFrom() == null
                        && findShapesQuery.getLengthTo() == null
                        && findShapesQuery.getHeightFrom() == null
                        && findShapesQuery.getHeightTo() == null
                        && findShapesQuery.getLengthBaseFrom() == null
                        && findShapesQuery.getLengthBaseTo() == null
                        && findShapesQuery.getLengthAFrom() == null
                        && findShapesQuery.getLengthATo() == null
                        && findShapesQuery.getLengthBFrom() == null
                        && findShapesQuery.getLengthBTo() == null)
                    return true;
                break;
            case "square":
                if(findShapesQuery.getRadiusFrom() == null
                        && findShapesQuery.getRadiusTo() == null
                        && findShapesQuery.getHeightFrom() == null
                        && findShapesQuery.getHeightTo() == null
                        && findShapesQuery.getLengthBaseFrom() == null
                        && findShapesQuery.getLengthBaseTo() == null
                        && findShapesQuery.getLengthAFrom() == null
                        && findShapesQuery.getLengthATo() == null
                        && findShapesQuery.getLengthBFrom() == null
                        && findShapesQuery.getLengthBTo() == null)
                    return true;
                break;
            case "rectangle":
                if(findShapesQuery.getRadiusFrom() == null
                        && findShapesQuery.getRadiusTo() == null
                        && findShapesQuery.getLengthBaseFrom() == null
                        && findShapesQuery.getLengthBaseTo() == null
                        && findShapesQuery.getLengthAFrom() == null
                        && findShapesQuery.getLengthATo() == null
                        && findShapesQuery.getLengthBFrom() == null
                        && findShapesQuery.getLengthBTo() == null)
                    return true;
            case "triangle":
                if(findShapesQuery.getRadiusFrom() == null
                        && findShapesQuery.getRadiusTo() == null
                        && findShapesQuery.getLengthFrom() == null
                        && findShapesQuery.getLengthTo() == null)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }
}
