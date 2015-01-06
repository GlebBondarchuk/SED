package com.bsu.sed.model.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * @author gbondarchuk
 */

@Documented
@Constraint(
        validatedBy = {}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@org.hibernate.validator.constraints.Email(regexp = ConstraintConstants.EMAIL_TEMPLATE)
public @interface Email {
    String message() default "Email doesn't match to template.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
