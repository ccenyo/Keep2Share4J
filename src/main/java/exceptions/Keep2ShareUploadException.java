package exceptions;

public class Keep2ShareUploadException extends Keep2ShareException {
    public Keep2ShareUploadException(String errorMessage) {
        super(errorMessage);
    }

    public Keep2ShareUploadException(String errorMessage, Object... args) {
        super(errorMessage, args);
    }

    public Keep2ShareUploadException(String errorMessage, Throwable errCause) {
        super(errorMessage, errCause);
    }

    public Keep2ShareUploadException(Exception e) {
        super(e);
    }
}
