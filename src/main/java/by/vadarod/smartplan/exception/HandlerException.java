package by.vadarod.smartplan.exception;

import by.vadarod.smartplan.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = DuplicateEntityException.class)
    public ResponseEntity<ErrorResponse> duplicate(DuplicateEntityException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notExist(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("Ошибка валидации");

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        for (ObjectError error:errors) {
            errorResponse.getMessages().add(error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = CustomRefreshTokenException.class)
    public ResponseEntity<ErrorResponse> tokenError(CustomRefreshTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = DisabledException.class)
    public ResponseEntity<ErrorResponse> disabled(DisabledException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> generalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage("Произошла непредвиденная ошибка. Пожалуйста, попробуйте позже.");
        errorResponse.getMessages().add(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
