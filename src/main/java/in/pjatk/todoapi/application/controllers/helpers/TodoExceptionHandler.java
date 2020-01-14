package in.pjatk.todoapi.application.controllers.helpers;

import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildServerError;

import io.medaid.errorhandler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class TodoExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorHandler errorHandler;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleEntityNotFound(
        Exception e) {
        errorHandler.logError(e);
        return buildServerError();
    }
}

