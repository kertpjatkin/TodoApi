package in.pjatk.todoapi.application.controllers.helpers.validators;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isEmpty(value)) {
            return true;
        }

        String sanitized = Jsoup.clean(value, Whitelist.basic());
        return sanitized.equals(value);
    }
}
