package cz.rkr.cardiff.config;

import cz.rkr.cardiff.api.model.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

/**
 * This is universal ExceptionHandler:
 * - it uses PayloadReader to read clean payload (needs to be cached)
 * - it logs read payload
 * - it supports @ResponseStatus on Exception to set return status
 */
@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable throwable, HttpServletRequest request) {
        String path = request.getRequestURI();
        ResponseEntity<ExceptionResponse> response = buildExceptionResponseFromThrowable(throwable, path);
        if (response.getStatusCode().is4xxClientError()) {
            if (response.getStatusCode() != HttpStatus.UNAUTHORIZED) {
                ExceptionResponse innerResponse = response.getBody();
                log.warn("Expected exception caught. {} : {}", innerResponse.getError(), innerResponse.getMessage());
            }
        }

        return response;
    }

    private ResponseEntity<ExceptionResponse> buildExceptionResponseFromThrowable(Throwable throwable, String path) {
        HttpStatus status = getStatusFromThrowable(throwable);
        ExceptionResponse response = new ExceptionResponse()
                .timestamp(ZonedDateTime.now())
                .status(status.value() + "")
                .error(throwable.getClass().getSimpleName())
                .message(throwable.getMessage())
                .path(path);

        return ResponseEntity.status(status).body(response);
    }

    private HttpStatus getStatusFromThrowable(Throwable throwable) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(throwable.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus.code();
        }
        return HttpStatus.valueOf(500);
    }
}