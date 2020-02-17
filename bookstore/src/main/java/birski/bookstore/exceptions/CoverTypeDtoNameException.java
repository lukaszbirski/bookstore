package birski.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoverTypeDtoNameException extends RuntimeException{

    public CoverTypeDtoNameException(String message) {
        super(message);
    }
}
