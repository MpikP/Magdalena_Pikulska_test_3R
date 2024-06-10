package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ShapeTypeToFindValidator.class}
)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShapeTypeToFind {
    String message() default "Field: ShapeType / message: Invalid figure type.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
