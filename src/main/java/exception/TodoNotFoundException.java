package exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException() {
        super("Todo not found");
    }
}
