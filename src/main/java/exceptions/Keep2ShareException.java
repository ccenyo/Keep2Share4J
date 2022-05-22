package exceptions;


import views.ErrorResponse;

public class Keep2ShareException extends RuntimeException {

    public Keep2ShareException(String errorMessage) {
        super(errorMessage);
    }

    public Keep2ShareException(String errorMessage, Object... args) {
        super(String.format(errorMessage, args));
    }

    public Keep2ShareException(String errorMessage, Throwable errCause) {
        super(errorMessage, errCause);
    }

    public Keep2ShareException(Exception e) {
        super(e);
    }

    public Keep2ShareException(ErrorResponse unMashErrorJson) {
        super("Error "+unMashErrorJson.getCode() +" : "+unMashErrorJson.getMessage());
    }
}
