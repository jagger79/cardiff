package cz.rkr.cardiff.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubscriptionWrongDatesFound extends RuntimeException {
    public SubscriptionWrongDatesFound(String message) {
        super(message);
    }
}