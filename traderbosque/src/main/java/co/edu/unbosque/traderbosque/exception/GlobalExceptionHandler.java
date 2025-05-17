package co.edu.unbosque.traderbosque.exception;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


public class GlobalExceptionHandler {

/**
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmail(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AlpacaSyncException.class)
    public ResponseEntity<String> handleAlpaca(AlpacaSyncException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }
**/

}