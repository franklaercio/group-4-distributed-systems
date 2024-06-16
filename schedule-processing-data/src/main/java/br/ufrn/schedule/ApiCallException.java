package br.ufrn.schedule;

public class ApiCallException extends Exception {

    public ApiCallException(String message) {
        super(message);
    }

    public ApiCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
