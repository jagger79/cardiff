package cz.rkr.cardiff.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuotationNotFound extends RuntimeException {
    public QuotationNotFound(String message) {
        super(message);
    }
}