package pl.kurs.magdalena_pikulska_test_3r.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ParametersValidator.class}
)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameters {
    String message() default "Field: Parameters / message: Invalid parameters.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}