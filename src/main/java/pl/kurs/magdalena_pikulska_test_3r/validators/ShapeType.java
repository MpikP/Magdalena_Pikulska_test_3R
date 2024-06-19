package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ShapeTypeValidator.class}
)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShapeType {
    String message() default "Field: ShapeType / message: Invalid figure type.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
