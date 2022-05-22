package exceptions;

public class Keep2ShareAuthenticationException extends Keep2ShareException {
    public Keep2ShareAuthenticationException(String errorMessage) {
        super(errorMessage);
    }

    public Keep2ShareAuthenticationException(String errorMessage, Object... args) {
        super(errorMessage, args);
    }

    public Keep2ShareAuthenticationException(String errorMessage, Throwable errCause) {
        super(errorMessage, errCause);
    }

    public Keep2ShareAuthenticationException(Exception e) {
        super(e);
    }
}
