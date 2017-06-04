package pl.com.bottega.microecom.user.controllers.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMustMatchValidator implements ConstraintValidator<PasswordsMustMatch, CreateAccountRequest> {
    @Override
    public void initialize(PasswordsMustMatch validCreateAccountRequest) {

    }

    @Override
    public boolean isValid(CreateAccountRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if(request == null)
            return true;
        if(request.getPassword() != null && !request.getPassword().equals(request.getRepeatedPassword()))
            return false;
        return true;
    }
}
