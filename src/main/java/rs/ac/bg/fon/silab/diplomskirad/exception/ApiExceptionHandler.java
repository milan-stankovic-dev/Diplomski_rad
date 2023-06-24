package rs.ac.bg.fon.silab.diplomskirad.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MyRequestException.class})
    public ResponseEntity<Object> handleApiException(MyRequestException e){
        final var badRequest = HttpStatus.BAD_REQUEST;

        var exception = new ExceptionContents(
          e.getMessage(),
          e, badRequest,
            ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }
}
