package in.pjatk.todoapi.application.helpers;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorErrorsMapper {

    public static <T> String mapValidationErrors(Set<ConstraintViolation<T>> validationErrors) {
        return validationErrors.stream().map(ConstraintViolation::getMessage).collect(
            Collectors.joining(","));
    }
}
