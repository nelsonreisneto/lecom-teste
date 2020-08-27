package br.com.lecom.resources.handlers;

import br.com.lecom.domains.dtos.RespostaExceptionDto;
import br.com.lecom.resources.exceptions.ClienteNaoEncontradoException;
import br.com.lecom.resources.exceptions.TarefaNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TarefaNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RespostaExceptionDto> tarefaNaoEncontrado(
            TarefaNaoEncontradoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(RespostaExceptionDto
                .builder()
                .mensagem(e.getMessage())
                .data(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy mm:ss")))
                .caminho(request.getContextPath())
                .build());
    }

    @ResponseBody
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RespostaExceptionDto> clienteNaoEncontrado(
            TarefaNaoEncontradoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(RespostaExceptionDto
                .builder()
                .mensagem(e.getMessage())
                .data(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy mm:ss")))
                .caminho(request.getContextPath())
                .build());
    }
}
