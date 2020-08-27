package br.com.lecom.resources.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
