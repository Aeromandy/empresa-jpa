package org.soulcodeacademy.empresa.controllers.errors;

import org.soulcodeacademy.empresa.services.errors.DadosInsuficientesError;
import org.soulcodeacademy.empresa.services.errors.RecursoNaoEncontradoError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoError.class)
    public ResponseEntity<CustomErrorResponse> recursoNaoEncontradoError(RecursoNaoEncontradoError erro, HttpServletRequest request) {
        CustomErrorResponse response = new CustomErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(erro.getMessage());
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}