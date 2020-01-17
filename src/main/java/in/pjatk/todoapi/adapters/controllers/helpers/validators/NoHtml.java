package in.pjatk.todoapi.adapters.controllers.helpers.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = NoHtmlValidator.class)
@Documented
public @interface NoHtml {

    String message() default "nohtml";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
