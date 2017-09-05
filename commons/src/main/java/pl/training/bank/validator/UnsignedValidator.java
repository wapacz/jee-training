package pl.training.bank.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnsignedValidator implements ConstraintValidator<Unsigned, Long> {

    private boolean enabled;

    @Override
    public void initialize(Unsigned unsigned) {
        enabled = unsigned.enabled();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return value > 0 || !enabled;
    }

}
