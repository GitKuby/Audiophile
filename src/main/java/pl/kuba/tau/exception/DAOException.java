package pl.kuba.tau.exception;

public class DAOException extends Exception {

    public enum ErrorCode {
        ENTITY_NOT_FOUND("Entity with provided id does not exists");

        private final String message;

        private ErrorCode(String message) {
            this.message = message;
        }
    }

    public DAOException(ErrorCode err) {
        super(err.message);
    }

}
