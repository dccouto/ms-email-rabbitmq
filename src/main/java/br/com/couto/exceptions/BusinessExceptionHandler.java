package br.com.couto.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.couto.dtos.ExceptionResponseDto;

@RestControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler{

    /**
     * Captura EmailException e retorna objeto Dto com as informações
     * 
     * @return {@link HttpStatus} Bad Request
     */
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Object> badRequestException(Throwable exception, WebRequest request) {
        return sendResponseExceptionRequest(exception, request, HttpStatus.BAD_REQUEST);
    }

    
    /**
     * Captura todas Exception
     * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Throwable exception, WebRequest request) {
    	return sendResponseExceptionRequest(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Monta o objeto com as informações da Exception
     * 
     * @return {@link ResponseEntity}
     */
	private static ResponseEntity<Object> sendResponseExceptionRequest(Throwable exception, WebRequest request,
			HttpStatus status) {
		
		var exceptionResponseDto = new ExceptionResponseDto(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(status).body(exceptionResponseDto);
	}
}
