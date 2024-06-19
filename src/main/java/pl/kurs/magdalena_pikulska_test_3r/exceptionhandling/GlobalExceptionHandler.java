package pl.kurs.magdalena_pikulska_test_3r.exceptionhandling;

import pl.kurs.magdalena_pikulska_test_3r.exceptions.ResourceNotFoundException;
import pl.kurs.magdalena_pikulska_test_3r.exceptions.WrongEntityStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseBodyDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<String> errorsMessages = e.getFieldErrors().stream()
                .map(fe -> "Field: " + fe.getField() + " / rejected value: '" + fe.getRejectedValue() + "' / message: " + fe.getDefaultMessage())
                .collect(Collectors.toList());

        if (errorsMessages.isEmpty()) {
            errorsMessages = e.getBindingResult().getAllErrors().stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.toList());
        }

        ExceptionResponseBodyDto exceptionResponseBodyDto = new ExceptionResponseBodyDto(
                errorsMessages,
                "BAD_REQUEST",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseBodyDto);
    }


    @ExceptionHandler(WrongEntityStateException.class)
    public ResponseEntity<ExceptionResponseBodyDto> handleWrongEntityStateException(WrongEntityStateException e) {
        ExceptionResponseBodyDto exceptionResponseBodyDto = new ExceptionResponseBodyDto(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseBodyDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseBodyDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionResponseBodyDto exceptionResponseBodyDto = new ExceptionResponseBodyDto(
                List.of(e.getMessage()),
                "NOT_FOUND",
                Timestamp.from(Instant.now())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseBodyDto);
    }
}

