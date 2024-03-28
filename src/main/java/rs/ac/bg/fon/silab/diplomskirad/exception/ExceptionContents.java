package rs.ac.bg.fon.silab.diplomskirad.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ExceptionContents(String message, Throwable throwable,
                                HttpStatus httpStatus, ZonedDateTime timestamp) { }
