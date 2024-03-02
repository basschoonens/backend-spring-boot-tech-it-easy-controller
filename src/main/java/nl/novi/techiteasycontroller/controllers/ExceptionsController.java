package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

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
    public ResponseEntity<?> exception(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult(); // De BindingResult van een exception, bevat alle errors
        return ResponseEntity.badRequest().body(processBindingResult(result));
    }

    private String processBindingResult(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();// Een FieldError is de error, daaruit gaan we de errorMessage halen om aan de gebruiker te tonen
        StringBuilder stringBuilder = new StringBuilder(); // Met een StringBuilder kun je makkelijk een String bouwen.
        for (FieldError fieldError: fieldErrors) {
            stringBuilder.append(fieldError.getField()); // Voeg de naam van het overtredende veld toe aan de String (zoals "name")
            stringBuilder.append(" : "); // Voeg een : toe aan de String
            stringBuilder.append(fieldError.getDefaultMessage()); // Voeg de message van de error toe aan de String (zoals "cannot be null")
            stringBuilder.append("\n"); // Voeg een enter toe, zodat elke error op een nieuwe regel staat.
        }
        return stringBuilder.toString();
    }

}
