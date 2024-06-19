package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ParametersToCreateValidator.class}
)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParametersToCreate {
    String message() default "Field: Parameters to create shape / message: Invalid parameters.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
