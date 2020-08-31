package br.com.lecom.resources.exceptions;

public class DocumentoNaoEncontradoException extends RuntimeException {

    public DocumentoNaoEncontradoException(String message) {
        super(message);
    }
}
