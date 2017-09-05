package pl.training.bank.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnsignedValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsigned {

    boolean enabled() default true;

    String message() default "Value must be greater then 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
