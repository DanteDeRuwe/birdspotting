package com.dantederuwe.birdspotting.validation;
import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;

public class PastOrPresentYearValidator implements ConstraintValidator<PastOrPresentYear, Integer>{

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        if (year==null) return true; //another constraint will deal with this
        return year<=Year.now().getValue();
    }
}
