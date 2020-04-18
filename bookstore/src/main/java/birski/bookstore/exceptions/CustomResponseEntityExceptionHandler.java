package birski.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleNameException(NameException exception, WebRequest webRequest){
        NameExceptionResponse nameExceptionResponse = new NameExceptionResponse(exception.getMessage());
        return new ResponseEntity(nameExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception, WebRequest webRequest){
        UsernameAlreadyExistsResponse usernameAlreadyExistsResponse = new UsernameAlreadyExistsResponse(exception.getMessage());
        return new ResponseEntity(usernameAlreadyExistsResponse, HttpStatus.BAD_REQUEST);
    }

}
