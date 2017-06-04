package pl.com.bottega.microecom.user.controllers.registration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { PasswordMustMatchValidator.class })
@Documented
public @interface PasswordsMustMatch {

    String message() default "passwords mismatch";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
