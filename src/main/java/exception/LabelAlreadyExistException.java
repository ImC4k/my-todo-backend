package exception;

public class LabelAlreadyExistException extends RuntimeException {
    public LabelAlreadyExistException() {
        super("Label already exist");
    }
}
