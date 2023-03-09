package cz.rkr.cardiff.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubscriptionFound extends RuntimeException {
    public SubscriptionFound(String message) {
        super(message);
    }
}