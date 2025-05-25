package by.it.dsmobile.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(final String message) {
        super(message);
    }
}
