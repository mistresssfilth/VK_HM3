package exception;

public class EmptyCellException extends Exception {
    public EmptyCellException(String message) {
        System.out.println(message);
    }
}
