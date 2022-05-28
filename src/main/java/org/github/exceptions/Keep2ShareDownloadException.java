package org.github.exceptions;

public class Keep2ShareDownloadException extends Keep2ShareException {
    public Keep2ShareDownloadException(String errorMessage) {
        super(errorMessage);
    }

    public Keep2ShareDownloadException(String errorMessage, Object... args) {
        super(errorMessage, args);
    }

    public Keep2ShareDownloadException(String errorMessage, Throwable errCause) {
        super(errorMessage, errCause);
    }

    public Keep2ShareDownloadException(Exception e) {
        super(e);
    }
}
