package br.com.lecom.resources.exceptions;

public class TarefaNaoEncontradoException extends RuntimeException {

    public TarefaNaoEncontradoException(String message) {
        super(message);
    }
}
