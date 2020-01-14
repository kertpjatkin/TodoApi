package in.pjatk.todoapi.application.controllers.helpers;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ResponseUtil {

    public static <T> ResponseEntity<T> buildBadResponse(T body) {
        return ResponseEntity.badRequest().body(body);
    }

    public static <T> ResponseEntity<T> buildUnauthorized() {
        return ResponseEntity.status(UNAUTHORIZED).build();
    }

    public static <T> ResponseEntity<T> buildBadResponse() {
        return ResponseEntity.badRequest().build();
    }

    public static <T> ResponseEntity<T> buildOkResponse(T body) {
        return ResponseEntity.ok().body(body);
    }

    public static <T> ResponseEntity<T> buildOkResponse() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> buildServerError() {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

    public static Map<String, String> mapValidationErrors(List<ObjectError> errors) {
        return errors.stream()
            .map(error -> {
                var field = ((FieldError) error).getField();
                return new SimpleEntry<>(field, error.getDefaultMessage());
            })
            .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }
}
