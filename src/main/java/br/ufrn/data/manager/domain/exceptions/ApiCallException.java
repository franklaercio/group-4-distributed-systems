package br.ufrn.data.manager.domain.exceptions;

public class ApiCallException extends Exception {

    public ApiCallException(String message) {
        super(message);
    }

    public ApiCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
