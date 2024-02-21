package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(value = RecordNotFoundException.class)

    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)

    public ResponseEntity<Object> exception(DataIntegrityViolationException exception) {
        return new ResponseEntity<>("Vul correcte waardes in.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public ResponseEntity<Object> exception(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>("Vul correcte waardes in.", HttpStatus.BAD_REQUEST);
    }

}
