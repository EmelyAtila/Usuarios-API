package br.com.emelyatila.backend.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroRecordResponse> handleNotFoundException(NotFoundException ex) {
        ErroRecordResponse erro = new ErroRecordResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroRecordResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErroRecordResponse erro = new ErroRecordResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRecordResponse> handleException(Exception ex) {
        ErroRecordResponse erro = new ErroRecordResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
    // perguntar??
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRecordResponse> handleValidationException(MethodArgumentNotValidException ex){

        Map<String, String> detalhes = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            detalhes.put(error.getField(),error.getDefaultMessage());
        }

        ErroRecordResponse erro = new ErroRecordResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                detalhes
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
