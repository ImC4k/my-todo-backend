package exception;

public class LabelNotFoundException extends RuntimeException {
    public LabelNotFoundException() {
        super("Label not found");
    }
}
