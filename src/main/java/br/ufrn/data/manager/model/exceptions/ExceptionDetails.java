package br.ufrn.data.manager.model.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDetails {

    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

    private ExceptionDetails() {
    }

    public static class Builder {
        private LocalDateTime timestamp;
        private String error;
        private String message;
        private String path;

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ExceptionDetails build() {
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.timestamp = this.timestamp;
            exceptionDetails.error = this.error;
            exceptionDetails.message = this.message;
            exceptionDetails.path = this.path;
            return exceptionDetails;
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
